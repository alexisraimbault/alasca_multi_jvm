package components;

import java.util.concurrent.TimeUnit;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.ComponentEPI;
import interfaces.HeaterI;
import interfaces.LaunchableOfferedI;
import ports.ComponentEPObp;
import ports.HeaterIbp;
import ports.LaunchableIbp;

@OfferedInterfaces(offered = {HeaterI.class, LaunchableOfferedI.class})
@RequiredInterfaces(required = {ComponentEPI.class})
public class Heater extends AbstractComponent implements LaunchableOfferedI {
	
	private int power;
	private String state;
	private HeaterIbp ibp;
	private ComponentEPObp epObp;
	private LaunchableIbp launchIbp;
	
	protected Heater(String heaterURI, String ibpURI, String epURI, String launchUri) throws Exception {
		super(heaterURI,  1, 1) ;

		this.launchIbp = new LaunchableIbp(launchUri, this) ;
		this.launchIbp.publishPort() ;
		
		this.ibp = new HeaterIbp(ibpURI, this) ;
		this.ibp.publishPort() ;
		
		this.epObp = new ComponentEPObp(epURI, this);
		this.epObp.publishPort() ;
		
		// remove ?
		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir")) ;
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home")) ;
		}
		
		this.tracer.setTitle("heater") ;
		this.tracer.setRelativePosition(3, 1) ;
		
		this.power = 0;
		this.state = "off";
	}
	
	public int getPower() throws Exception {
		return power;
	}

	public String getState() throws Exception {
		return state;
	}

	public void switchOn() throws Exception {
		this.logMessage("switching on.") ;
		this.state = "on";
		EPDeclare();
	}

	public void switchOff() throws Exception {
		this.logMessage("switching off.") ;
		this.state = "off";
		EPDeclare();
	}

	public void setPower(int power) throws Exception {
		this.power = power;
	}
	
	public void EPRegister() throws Exception {
		this.logMessage("registring to electric panel.") ;
		this.epObp.register("heater");
	}
	
	public void EPDeclare() throws Exception {
		this.logMessage("declaring consommation to electric panel.") ;
		if(this.state == "on")
			this.epObp.setConsommation("heater", 4);
		if(this.state == "off")
			this.epObp.setConsommation("heater", 0);
	}
	
	@Override
	public void			start() throws ComponentStartException
	{
		super.start() ;
		this.logMessage("starting heater component.") ;
		
		this.scheduleTask(
				new AbstractComponent.AbstractTask() {
					@Override
					public void run() {
						try {
							((Heater)this.getTaskOwner()).EPRegister();
						} catch (Exception e) {
							throw new RuntimeException(e) ;
						}
					}
				},
				500, TimeUnit.MILLISECONDS);
		
	}



	/**
	 * @see fr.sorbonne_u.components.AbstractComponent#finalise()
	 */
	@Override
	public void			finalise() throws Exception
	{
		this.logMessage("stopping heater component.") ;
		// This is the place where to clean up resources, such as
		// disconnecting and unpublishing ports that will be destroyed
		// when shutting down.
		// In static architectures like in this example, ports can also
		// be disconnected by the finalise method of the component
		// virtual machine.
		this.ibp.unpublishPort() ;
		this.epObp.unpublishPort() ;
		// This called at the end to make the component internal
		// state move to the finalised state.  15 + on 14
		super.finalise();
	}

	@Override
	public void launchTasks() throws Exception {
		this.scheduleTask(
				new AbstractComponent.AbstractTask() {
					@Override
					public void run() {
						try {
							((Heater)this.getTaskOwner()).EPRegister();
						} catch (Exception e) {
							throw new RuntimeException(e) ;
						}
					}
				},
				500, TimeUnit.MILLISECONDS);
	}
	
}
