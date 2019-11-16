package components;


import java.util.concurrent.TimeUnit;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import fr.sorbonne_u.components.exceptions.PostconditionException;
import interfaces.FridgeI;
import interfaces.LaunchableOfferedI;
import interfaces.ComponentEPI;
import ports.ComponentEPObp;
import ports.FridgeIbp;
import ports.LaunchableIbp;

@OfferedInterfaces(offered = {FridgeI.class, LaunchableOfferedI.class})
@RequiredInterfaces(required = {ComponentEPI.class})
public class Fridge extends AbstractComponent implements LaunchableOfferedI {
	private double temperature;
	private String state;
	private FridgeIbp ibp;
	private LaunchableIbp launchIbp;

	/*
	 * plaque temperature
	 * 
	 * on -> 3
	 * off -> ambiante
	 * freeze -> -3
	 * 
	 * t(n+1) = t(n) + (tPlaque - t(n) / 13)
	 * 
	 */
	
	private ComponentEPObp epObp;
	
	
	protected Fridge(String fridgeURI, String ibpURI, String epURI, String launchUri) throws Exception {
		super(fridgeURI,  1, 1) ;

		this.launchIbp = new LaunchableIbp(launchUri, this) ;
		this.launchIbp.publishPort() ;
		
		this.temperature = 15;
		this.state = "off";
		
		//toléré de 0 - 7
		//préférence 4 - 5
		
		this.ibp = new FridgeIbp(ibpURI, this) ;
		this.ibp.publishPort() ;
		
		this.epObp = new ComponentEPObp(epURI, this);
		this.epObp.publishPort() ;
		
		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir")) ;
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home")) ;
		}
		
		assert	this.isPortExisting(ibpURI) :
			new PostconditionException("The component must have a "
					+ "port with URI " + ibpURI) ;
		assert	this.findPortFromURI(ibpURI).
					getImplementedInterface().equals(FridgeI.class) :
					new PostconditionException("The component must have a "
							+ "port with implemented interface URIProviderI") ;
		assert	this.findPortFromURI(ibpURI).isPublished() :
					new PostconditionException("The component must have a "
							+ "port published with URI " + ibpURI) ;
		
		this.tracer.setTitle("fridge") ;
		this.tracer.setRelativePosition(3, 0) ;
		
	}

	public double getTemperature() {
		return temperature;
	}
	
	public String getState() {
		return state;
	}
	
	public void switchOn() throws Exception {
		this.logMessage("switching on...");
		this.state = "on";
		EPDeclare();
	}

	public void switchOff() {
		this.logMessage("switching off...");
		this.state = "off";
	}

	public void freeze() throws Exception {
		this.logMessage("freezing...");
		this.state = "freeze";
		EPDeclare();
	}

	public void rest() throws Exception {
		this.logMessage("resting...");
		this.state = "on";
		EPDeclare();
	}
	
	protected void updateTemperature() {
		if(this.state == "on")
		{
			this.temperature += ((3 - this.temperature)/13);
			this.logMessage("temperature : " + this.temperature) ;
		}
		if(this.state == "freeze")
		{
			this.temperature += ((-3 - this.temperature)/13);
			this.logMessage("temperature : " + this.temperature) ;
		}
	}
	
	public void EPRegister() throws Exception {
		this.logMessage("registring to electric panel.") ;
		this.epObp.register("fridge");
	}
	
	public void EPDeclare() throws Exception {
		this.logMessage("declaring consommation to electric panel.") ;
		if(this.state == "on")
			this.epObp.setConsommation("fridge", 1);
		if(this.state == "off")
			this.epObp.setConsommation("fridge", 0);
		if(this.state == "freeze")
			this.epObp.setConsommation("fridge", 2);
	}
	
	
	@Override
	public void			start() throws ComponentStartException
	{
		super.start() ;
		this.logMessage("starting fridge component.") ;
		
		// Schedule the first service method invocation in one second.
		this.scheduleTask(
			new AbstractComponent.AbstractTask() {
				@Override
				public void run() {
					try {
						((Fridge)this.getTaskOwner()).EPRegister();
					} catch (Exception e) {
						throw new RuntimeException(e) ;
					}
				}
			},
			500, TimeUnit.MILLISECONDS);
		
		this.scheduleTaskWithFixedDelay(		
				new AbstractComponent.AbstractTask() {
					@Override
					public void run() {
						try {
							((Fridge)this.getTaskOwner()).updateTemperature();
						} catch (Exception e) {
							throw new RuntimeException(e) ;
						}
					}
				}, 4000, 1000 // délai entre la fin d'une exécution et la suivante
				,TimeUnit.MILLISECONDS) ;
	}



	/**
	 * @see fr.sorbonne_u.components.AbstractComponent#finalise()
	 */
	@Override
	public void			finalise() throws Exception
	{
		this.logMessage("stopping fridge component.") ;
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
							((Fridge)this.getTaskOwner()).EPRegister();
						} catch (Exception e) {
							throw new RuntimeException(e) ;
						}
					}
				},
				500, TimeUnit.MILLISECONDS);
			
			this.scheduleTaskWithFixedDelay(		
					new AbstractComponent.AbstractTask() {
						@Override
						public void run() {
							try {
								((Fridge)this.getTaskOwner()).updateTemperature();
							} catch (Exception e) {
								throw new RuntimeException(e) ;
							}
						}
					}, 4000, 1000 // délai entre la fin d'une exécution et la suivante
					,TimeUnit.MILLISECONDS) ;
	}
}
//-----------------------------------------------------------------------------


