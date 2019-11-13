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
	protected String[] reflection_ibp_uris;
	
	protected DynamicComponentCreationOutboundPort tmpCObp;
	
	protected ReflectionOutboundPort rop;
	
	public HouseDeploy (String uri, String[] jvm_uris)//uris for : [Controller, Fridge, Heater, SP, Ondulator, Battery, ElecPanel]
	{
		super(uri,1,1);
		this.addRequiredInterface(ReflectionI.class) ;
		this.addRequiredInterface(DynamicComponentCreationI.class);
		this.jvm_uris = jvm_uris;
		reflection_ibp_uris = new String[7];
		
		this.tracer.setTitle("Deployer") ;
		this.tracer.setRelativePosition(1, 2) ;
	}
	
	public void dynamicDeploy() throws Exception
	{
		this.logMessage("creating components...");
		
		int i = 0; 
		
		tmpCObp.doConnection(jvm_uris[i] + AbstractCVM.DCC_INBOUNDPORT_URI_SUFFIX, DynamicComponentCreationConnector.class.getCanonicalName());
		
		reflection_ibp_uris[0] = tmpCObp.createComponent(
				Controller.class.getCanonicalName(),
				new Object[]{CONTROLLER_URI,
						CONTROLLER_OBP_URI, 
						CONTROLLER_OBP2_URI, 
						CONTROLLER_OBP3_URI,
						CONTROLLER_OBP4_URI,
						CONTROLLER_OBP5_URI}) ;
		
		i++;
		tmpCObp.doDisconnection();
		tmpCObp.doConnection(jvm_uris[i] + AbstractCVM.DCC_INBOUNDPORT_URI_SUFFIX, DynamicComponentCreationConnector.class.getCanonicalName());
		
		reflection_ibp_uris[1] = tmpCObp.createComponent(
				Fridge.class.getCanonicalName(),
				new Object[]{FRIDGE_URI,
						FRIDGE_IBP_URI,
						FRIDGE_EP_URI}) ;
		
		i++;
		tmpCObp.doDisconnection();
		tmpCObp.doConnection(jvm_uris[i] + AbstractCVM.DCC_INBOUNDPORT_URI_SUFFIX, DynamicComponentCreationConnector.class.getCanonicalName());
		
		reflection_ibp_uris[2] = tmpCObp.createComponent(
				Heater.class.getCanonicalName(),
				new Object[]{HEATER_URI,
						HEATER_IBP_URI,
						HEATER_EP_URI}) ;
		
		i++;
		tmpCObp.doDisconnection();
		tmpCObp.doConnection(jvm_uris[i] + AbstractCVM.DCC_INBOUNDPORT_URI_SUFFIX, DynamicComponentCreationConnector.class.getCanonicalName());
		
		reflection_ibp_uris[3] = tmpCObp.createComponent(
				SolarPanel.class.getCanonicalName(),
				new Object[]{SP_URI,
						SP_OBP_URI}) ;
		
		i++;
		tmpCObp.doDisconnection();
		tmpCObp.doConnection(jvm_uris[i] + AbstractCVM.DCC_INBOUNDPORT_URI_SUFFIX, DynamicComponentCreationConnector.class.getCanonicalName());
		
		reflection_ibp_uris[4] = tmpCObp.createComponent(
				Ondulator.class.getCanonicalName(),
				new Object[]{ONDULATOR_URI,
						ONDULATOR_OBP_URI,
						ONDULATOR_IBP_URI}) ;
		
		i++;
		tmpCObp.doDisconnection();
		tmpCObp.doConnection(jvm_uris[i] + AbstractCVM.DCC_INBOUNDPORT_URI_SUFFIX, DynamicComponentCreationConnector.class.getCanonicalName());
		
		reflection_ibp_uris[5] = tmpCObp.createComponent(
						Battery.class.getCanonicalName(),
						new Object[]{BATTERY_URI,
								BATTERY_IBP_URI}) ;
		
		i++;
		tmpCObp.doDisconnection();
		tmpCObp.doConnection(jvm_uris[i] + AbstractCVM.DCC_INBOUNDPORT_URI_SUFFIX, DynamicComponentCreationConnector.class.getCanonicalName());
		
		reflection_ibp_uris[6] = tmpCObp.createComponent(
				ElecPanel.class.getCanonicalName(),
				new Object[]{EP_URI,
						EP_IBP_URI}) ;
		
		tmpCObp.doDisconnection();
		
		this.logMessage("components created...");
		
		this.logMessage("linking...");
		
		
		this.rop = new ReflectionOutboundPort(this) ;
		//this.addPort(rop) ;
		this.rop.localPublishPort() ;
		
		this.logMessage("linking controller...");
		
		rop.doConnection(reflection_ibp_uris[0], ReflectionConnector.class.getCanonicalName());//Controller
		
		rop.toggleTracing();
		
		this.logMessage("controller tracing done...");
		
		rop.doPortConnection(CONTROLLER_OBP_URI, FRIDGE_IBP_URI, ControllerFridgeConnector.class.getCanonicalName());
		
		this.logMessage("controller link 1/5 done...");
		
		rop.doPortConnection(CONTROLLER_OBP4_URI, HEATER_IBP_URI, ControllerHeaterConnector.class.getCanonicalName());
		
		this.logMessage("controller link 2/5 done...");
		
		rop.doPortConnection(CONTROLLER_OBP5_URI, EP_IBP_URI, ControllerEPConnector.class.getCanonicalName());
		
		this.logMessage("controller link 3/5 done...");
		
		rop.doPortConnection(CONTROLLER_OBP2_URI, BATTERY_IBP_URI, ControllerBatteryConnector.class.getCanonicalName());
		
		this.logMessage("controller link 4/5 done...");
		
		rop.doPortConnection(CONTROLLER_OBP3_URI, ONDULATOR_IBP_URI, ControllerOndulatorConnector.class.getCanonicalName());
		
		this.logMessage("controller link 5/5 done...");
		
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
		
		this.logMessage("linked...");
	}
	
	@Override
	public void			start() throws ComponentStartException
	{
		this.logMessage("starting...");
		try {
			tmpCObp = new DynamicComponentCreationOutboundPort(this);
			tmpCObp.localPublishPort();
			
			
		} catch (Exception e) {
			throw new ComponentStartException(e) ;
		}
		
		super.start() ; 
		
		this.logMessage("started...");
	}
}
