package components;

import connectors.ComponentEPConnector;
import connectors.ControllerBatteryConnector;
import connectors.ControllerEPConnector;
import connectors.ControllerFridgeConnector;
import connectors.ControllerHeaterConnector;
import connectors.ControllerOndulatorConnector;
import connectors.OndulatorBatteryConnector;
import connectors.SPOndulatorConnector;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import fr.sorbonne_u.components.pre.dcc.connectors.DynamicComponentCreationConnector;
import fr.sorbonne_u.components.pre.dcc.interfaces.DynamicComponentCreationI;
import fr.sorbonne_u.components.pre.dcc.ports.DynamicComponentCreationOutboundPort;
import fr.sorbonne_u.components.reflection.connectors.ReflectionConnector;
import fr.sorbonne_u.components.reflection.interfaces.ReflectionI;
import fr.sorbonne_u.components.reflection.ports.ReflectionOutboundPort;

public class HouseDeploy extends		AbstractComponent
{
	protected static final String	CONTROLLER_URI = "controller-uri" ;
	protected static final String	FRIDGE_URI = "fridge-uri" ;
	protected static final String	HEATER_URI = "heater-uri" ;
	protected static final String	EP_URI = "ep-uri" ;
	protected static final String	CONTROLLER_OBP_URI = "oport" ;
	protected static final String	CONTROLLER_OBP2_URI = "oport2" ;
	protected static final String	CONTROLLER_OBP3_URI = "oport3" ;
	protected static final String	CONTROLLER_OBP4_URI = "oport4" ;
	protected static final String	CONTROLLER_OBP5_URI = "oport5" ;
	protected static final String	FRIDGE_IBP_URI = "iport" ;
	protected static final String	HEATER_IBP_URI = "heater-iport" ;
	
	protected static final String	EP_IBP_URI = "ep-iport" ;
	protected static final String	FRIDGE_EP_URI = "fridge-ep-uri" ;
	protected static final String	HEATER_EP_URI = "heater-ep-uri" ;
	
	protected static final String	SP_URI = "sp-uri" ;
	protected static final String	SP_OBP_URI = "sp-oport" ;
	
	protected static final String	ONDULATOR_URI = "ondulator-uri" ;
	protected static final String	ONDULATOR_IBP_URI = "ondulator-oport" ;
	protected static final String	ONDULATOR_OBP_URI = "ondulator-iport" ;
	
	protected static final String	BATTERY_URI = "battery-uri" ;
	protected static final String	BATTERY_IBP_URI = "battery-iport" ;
	
	protected String[] jvm_uris;
	protected DynamicComponentCreationOutboundPort[] ports_to_components_JVM;
	protected String[] reflection_ibp_uris;
	
	protected ReflectionOutboundPort rop;
	
	public HouseDeploy (String uri, String[] jvm_uris)//uris for : [Controller, Fridge, Heater, SP, Ondulator, Battery, ElecPanel]
	{
		super(uri,1,1);
		this.addRequiredInterface(DynamicComponentCreationI.class);
		this.jvm_uris = jvm_uris;
		ports_to_components_JVM = new DynamicComponentCreationOutboundPort[7];
		reflection_ibp_uris = new String[7];
	}
	
	public void dynamicDeploy() throws Exception
	{
		reflection_ibp_uris[0] = ports_to_components_JVM[0].createComponent(
				Controller.class.getCanonicalName(),
				new Object[]{CONTROLLER_URI,
						CONTROLLER_OBP_URI, 
						CONTROLLER_OBP2_URI, 
						CONTROLLER_OBP3_URI,
						CONTROLLER_OBP4_URI,
						CONTROLLER_OBP5_URI}) ;
		
		reflection_ibp_uris[1] = ports_to_components_JVM[1].createComponent(
				Fridge.class.getCanonicalName(),
				new Object[]{FRIDGE_URI,
						FRIDGE_IBP_URI,
						FRIDGE_EP_URI}) ;
		
		reflection_ibp_uris[2] = ports_to_components_JVM[2].createComponent(
				Heater.class.getCanonicalName(),
				new Object[]{HEATER_URI,
						HEATER_IBP_URI,
						HEATER_EP_URI}) ;
		
		reflection_ibp_uris[3] = ports_to_components_JVM[3].createComponent(
				SolarPanel.class.getCanonicalName(),
				new Object[]{SP_URI,
						SP_OBP_URI}) ;
		
		reflection_ibp_uris[4] = ports_to_components_JVM[4].createComponent(
				Ondulator.class.getCanonicalName(),
				new Object[]{ONDULATOR_URI,
						ONDULATOR_OBP_URI,
						ONDULATOR_IBP_URI}) ;
		
		reflection_ibp_uris[5] = ports_to_components_JVM[5].createComponent(
						Battery.class.getCanonicalName(),
						new Object[]{BATTERY_URI,
								BATTERY_IBP_URI}) ;
		
		reflection_ibp_uris[6] = ports_to_components_JVM[6].createComponent(
				ElecPanel.class.getCanonicalName(),
				new Object[]{EP_URI,
						EP_IBP_URI}) ;
		
		this.addRequiredInterface(ReflectionI.class) ;
		this.rop = new ReflectionOutboundPort(this) ;
		this.addPort(rop) ;
		this.rop.localPublishPort() ;
		
		rop.doConnection(reflection_ibp_uris[0], ReflectionConnector.class.getCanonicalName());//Controller
		
		rop.toggleTracing();
		
		rop.doPortConnection(CONTROLLER_OBP_URI, FRIDGE_IBP_URI, ControllerFridgeConnector.class.getCanonicalName());
		
		rop.doPortConnection(CONTROLLER_OBP4_URI, HEATER_IBP_URI, ControllerHeaterConnector.class.getCanonicalName());
		
		rop.doPortConnection(CONTROLLER_OBP5_URI, EP_IBP_URI, ControllerEPConnector.class.getCanonicalName());
		
		rop.doPortConnection(CONTROLLER_OBP2_URI, BATTERY_IBP_URI, ControllerBatteryConnector.class.getCanonicalName());
		
		rop.doPortConnection(CONTROLLER_OBP3_URI, ONDULATOR_IBP_URI, ControllerOndulatorConnector.class.getCanonicalName());
		
		this.doPortDisconnection(rop.getPortURI()) ;
		
		rop.doConnection(reflection_ibp_uris[3], ReflectionConnector.class.getCanonicalName());//SolarPanel
		
		rop.toggleTracing();
		
		rop.doPortConnection(SP_OBP_URI, ONDULATOR_IBP_URI, SPOndulatorConnector.class.getCanonicalName());
		
		this.doPortDisconnection(rop.getPortURI()) ;
		
		rop.doConnection(reflection_ibp_uris[4], ReflectionConnector.class.getCanonicalName());//Ondulator
		
		rop.toggleTracing();
		
		rop.doPortConnection(ONDULATOR_OBP_URI, BATTERY_IBP_URI, OndulatorBatteryConnector.class.getCanonicalName());
		
		this.doPortDisconnection(rop.getPortURI()) ;
		
		rop.doConnection(reflection_ibp_uris[2], ReflectionConnector.class.getCanonicalName());//Heater
		
		rop.toggleTracing();
		
		rop.doPortConnection(HEATER_EP_URI, EP_IBP_URI, ComponentEPConnector.class.getCanonicalName());

		this.doPortDisconnection(rop.getPortURI()) ;
		
		rop.doConnection(reflection_ibp_uris[1], ReflectionConnector.class.getCanonicalName());//Fridge
		
		rop.toggleTracing();
		
		rop.doPortConnection(FRIDGE_EP_URI, EP_IBP_URI, ComponentEPConnector.class.getCanonicalName());

		this.doPortDisconnection(rop.getPortURI()) ;
		
		rop.doConnection(reflection_ibp_uris[5], ReflectionConnector.class.getCanonicalName());//Fridge
		
		rop.toggleTracing();

		this.doPortDisconnection(rop.getPortURI()) ;
		
		rop.doConnection(reflection_ibp_uris[6], ReflectionConnector.class.getCanonicalName());//Fridge
		
		rop.toggleTracing();

		this.doPortDisconnection(rop.getPortURI()) ;
	}
	
	@Override
	public void			start() throws ComponentStartException
	{
		try {
			//jvm_uris size has to be = 7
			for(int i = 0; i < jvm_uris.length; i++)
			{
				DynamicComponentCreationOutboundPort tmpCObp = new DynamicComponentCreationOutboundPort(this);
				this.addPort(tmpCObp);
				tmpCObp.localPublishPort();
				tmpCObp.doConnection(jvm_uris[i] + AbstractCVM.DCC_INBOUNDPORT_URI_SUFFIX, DynamicComponentCreationConnector.class.getCanonicalName());
				ports_to_components_JVM[i] = tmpCObp;
			}
			
		} catch (Exception e) {
			throw new ComponentStartException(e) ;
		}
		
		super.start() ; 
		
	}
}
