package components;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.LaunchableOfferedI;
import interfaces.SPControllerOfferedI;
import interfaces.SPControllerRequiredI;
import ports.LaunchableIbp;
import ports.OndulatorIbp;
import ports.OndulatorObp;

@OfferedInterfaces(offered = {SPControllerOfferedI.class, LaunchableOfferedI.class})
@RequiredInterfaces(required = {SPControllerRequiredI.class})
public class Ondulator extends AbstractComponent implements LaunchableOfferedI {
	public String policy;//storage
	
	private OndulatorObp towardsBattery;
	private OndulatorIbp ibp;
	private LaunchableIbp launchIbp;
	
	
	protected Ondulator(String ondulatorURI, String obpURI, String ibpURI, String launchUri) throws Exception {
		super(ondulatorURI,  1, 1) ;

		this.launchIbp = new LaunchableIbp(launchUri, this) ;
		this.launchIbp.publishPort() ;
		
		this.towardsBattery = new OndulatorObp(obpURI, this) ;
		this.towardsBattery.localPublishPort() ;
		
		this.ibp = new OndulatorIbp(ibpURI, this) ;
		this.ibp.publishPort() ;
		
		this.policy = "default";
		
		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir")) ;
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home")) ;
		}
		
		this.tracer.setTitle("SP policy") ;
		this.tracer.setRelativePosition(1, 1) ;
		
	}

	public void receive(double power) throws Exception {
		this.logMessage("received " + power + " energy...");
		//use policy to decide what to do
		this.logMessage("sending " + power + " energy to the battery");
		this.towardsBattery.store(power);
	}

	public void setPolicy(String policy) {
		this.logMessage("setting policy to " + policy + "...");
		this.policy = policy;
	}
	
	@Override
	public void			start() throws ComponentStartException
	{
		super.start() ;
		this.logMessage("starting SP policy component.") ;
		
	}

	/**
	 * @see fr.sorbonne_u.components.AbstractComponent#finalise()
	 */
	@Override
	public void			finalise() throws Exception
	{
		this.logMessage("stopping SP policy component.") ;
		// This is the place where to clean up resources, such as
		// disconnecting and unpublishing ports that will be destroyed
		// when shutting down.
		// In static architectures like in this example, ports can also
		// be disconnected by the finalise method of the component
		// virtual machine.
		this.towardsBattery.unpublishPort() ;
		this.ibp.unpublishPort() ;

		// This called at the end to make the component internal
		// state move to the finalised state.
		super.finalise();
	}

	@Override
	public void launchTasks() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
