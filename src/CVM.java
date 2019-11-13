
import components.Battery;
import components.Controller;
import components.ElecPanel;
import components.Fridge;
import components.Heater;
import components.Ondulator;
import components.SolarPanel;
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

public class				CVM
extends		AbstractCVM
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

	public				CVM() throws Exception
	{
		super() ;
	}
	
	protected String	controllerURI ;
	protected String	fridgeURI ;
	protected String	spURI ;
	protected String	ondulatorURI ;
	protected String	batteryURI ;
	protected String	heaterURI ;
	protected String	epURI ;

	/**
	 * instantiate the components, publish their port and interconnect them.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	!this.deploymentDone()
	 * post	this.deploymentDone()
	 * </pre>
	 * 
	 * @see fr.sorbonne_u.components.cvm.AbstractCVM#deploy()
	 */
	@Override
	public void			deploy() throws Exception
	{
		assert	!this.deploymentDone() ;

		// --------------------------------------------------------------------
		// Configuration phase
		// --------------------------------------------------------------------

		// debugging mode configuration; comment and uncomment the line to see
		// the difference
//		AbstractCVM.DEBUG_MODE.add(CVMDebugModes.PUBLIHSING) ;
//		AbstractCVM.DEBUG_MODE.add(CVMDebugModes.CONNECTING) ;
//		AbstractCVM.DEBUG_MODE.add(CVMDebugModes.COMPONENT_DEPLOYMENT) ;

		// --------------------------------------------------------------------
		// Creation phase
		// --------------------------------------------------------------------

		// create the component
		this.controllerURI =
			AbstractComponent.createComponent(
					Controller.class.getCanonicalName(),
					new Object[]{CONTROLLER_URI,
							CONTROLLER_OBP_URI, 
							CONTROLLER_OBP2_URI, 
							CONTROLLER_OBP3_URI,
							CONTROLLER_OBP4_URI,
							CONTROLLER_OBP5_URI}) ;
		assert	this.isDeployedComponent(this.controllerURI) ;
		// make it trace its operations
		this.toggleTracing(this.controllerURI) ;
		this.toggleLogging(this.controllerURI) ;

		this.fridgeURI =
			AbstractComponent.createComponent(
					Fridge.class.getCanonicalName(),
					new Object[]{FRIDGE_URI,
							FRIDGE_IBP_URI,
							FRIDGE_EP_URI}) ;
		assert	this.isDeployedComponent(this.fridgeURI) ;
		// make it trace its operations
		this.toggleTracing(this.fridgeURI) ;
		this.toggleLogging(this.fridgeURI) ;
		
		this.heaterURI =
				AbstractComponent.createComponent(
						Heater.class.getCanonicalName(),
						new Object[]{HEATER_URI,
								HEATER_IBP_URI,
								HEATER_EP_URI}) ;
			assert	this.isDeployedComponent(this.heaterURI) ;
			// make it trace its operations
			this.toggleTracing(this.heaterURI) ;
			this.toggleLogging(this.heaterURI) ;
		
		this.spURI =
			AbstractComponent.createComponent(
					SolarPanel.class.getCanonicalName(),
					new Object[]{SP_URI,
							SP_OBP_URI}) ;
		assert	this.isDeployedComponent(this.spURI) ;
		// make it trace its operations
		this.toggleTracing(this.spURI) ;
		this.toggleLogging(this.spURI) ;
		
		this.ondulatorURI =
			AbstractComponent.createComponent(
					Ondulator.class.getCanonicalName(),
					new Object[]{ONDULATOR_URI,
							ONDULATOR_OBP_URI,
							ONDULATOR_IBP_URI}) ;
		assert	this.isDeployedComponent(this.ondulatorURI) ;
		// make it trace its operations
		this.toggleTracing(this.ondulatorURI) ;
		this.toggleLogging(this.ondulatorURI) ;
		
		this.batteryURI =
			AbstractComponent.createComponent(
					Battery.class.getCanonicalName(),
					new Object[]{BATTERY_URI,
							BATTERY_IBP_URI}) ;
		assert	this.isDeployedComponent(this.batteryURI) ;
		// make it trace its operations
		this.toggleTracing(this.batteryURI) ;
		this.toggleLogging(this.batteryURI) ;
		
		this.epURI =
				AbstractComponent.createComponent(
						ElecPanel.class.getCanonicalName(),
						new Object[]{EP_URI,
								EP_IBP_URI}) ;
			assert	this.isDeployedComponent(this.epURI) ;
			// make it trace its operations
			this.toggleTracing(this.epURI) ;
			this.toggleLogging(this.epURI) ;
				
		// --------------------------------------------------------------------
		// Connection phase
		// --------------------------------------------------------------------

		// do the connection
		this.doPortConnection(
				this.controllerURI,
				CONTROLLER_OBP_URI,
				FRIDGE_IBP_URI,
				ControllerFridgeConnector.class.getCanonicalName()) ;
		
		this.doPortConnection(
				this.controllerURI,
				CONTROLLER_OBP4_URI,
				HEATER_IBP_URI,
				ControllerHeaterConnector.class.getCanonicalName()) ;
		
		this.doPortConnection(
				this.controllerURI,
				CONTROLLER_OBP5_URI,
				EP_IBP_URI,
				ControllerEPConnector.class.getCanonicalName()) ;
		
		this.doPortConnection(
				this.spURI,
				SP_OBP_URI,
				ONDULATOR_IBP_URI,
				SPOndulatorConnector.class.getCanonicalName()) ;
		
		this.doPortConnection(
				this.ondulatorURI,
				ONDULATOR_OBP_URI,
				BATTERY_IBP_URI,
				OndulatorBatteryConnector.class.getCanonicalName()) ;
		
		this.doPortConnection(
				this.controllerURI,
				CONTROLLER_OBP2_URI,
				BATTERY_IBP_URI,
				ControllerBatteryConnector.class.getCanonicalName()) ;
		
		this.doPortConnection(
				this.controllerURI,
				CONTROLLER_OBP3_URI,
				ONDULATOR_IBP_URI,
				ControllerOndulatorConnector.class.getCanonicalName()) ;
		
		this.doPortConnection(
				this.heaterURI,
				HEATER_EP_URI,
				EP_IBP_URI,
				ComponentEPConnector.class.getCanonicalName()) ;
		
		this.doPortConnection(
				this.fridgeURI,
				FRIDGE_EP_URI,
				EP_IBP_URI,
				ComponentEPConnector.class.getCanonicalName()) ;
	
		// --------------------------------------------------------------------
		// Deployment done
		// --------------------------------------------------------------------

		super.deploy();
		assert	this.deploymentDone() ;
	}

	/**
	 * @see fr.sorbonne_u.components.cvm.AbstractCVM#finalise()
	 */
	@Override
	public void				finalise() throws Exception
	{
		// Port disconnections can be done here for static architectures
		// otherwise, they can be done in the finalise methods of components.
		this.doPortDisconnection(
				this.controllerURI,
				CONTROLLER_OBP_URI) ;

		super.finalise();
	}

	/**
	 * disconnect the components and then call the base shutdown method.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true				// no more preconditions.
	 * post	true				// no more postconditions.
	 * </pre>
	 * 
	 * @see fr.sorbonne_u.components.cvm.AbstractCVM#shutdown()
	 */
	@Override
	public void				shutdown() throws Exception
	{
		assert	this.allFinalised() ;
		// any disconnection not done yet can be performed here

		super.shutdown();
	}

	public static void		main(String[] args)
	{
		try {
			// Create an instance of the defined component virtual machine.
			CVM a = new CVM() ;
			// Execute the application.
			a.startStandardLifeCycle(1000000L) ;
			// Give some time to see the traces (convenience).
			Thread.sleep(5000L) ;
			// Simplifies the termination (termination has yet to be treated
			// properly in BCM).
			System.exit(0) ;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
//-----------------------------------------------------------------------------

