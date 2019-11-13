package components;

import java.util.concurrent.TimeUnit;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.SolarPanelI;
import ports.SPObp;

@RequiredInterfaces(required = {SolarPanelI.class})
public class SolarPanel extends AbstractComponent {
	
	private SPObp towardsOndulator;
	private int energy;
	
	protected SolarPanel(String solarPanelURI, String obpURI) throws Exception {
		super(solarPanelURI,  1, 1) ;
		
		this.energy = 10;
		
		this.towardsOndulator = new SPObp(obpURI, this) ;
		this.towardsOndulator.localPublishPort() ;
		
		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir")) ;
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home")) ;
		}
		
		this.tracer.setTitle("Solar Panel") ;
		this.tracer.setRelativePosition(2, 0) ;
	}
	

	protected void sendEnergy() throws Exception {
		this.logMessage("providing " + this.energy + "...");
		this.towardsOndulator.provide(this.energy);
		this.energy = 0;
	}

	
	@Override
	public void			start() throws ComponentStartException
	{
		super.start() ;
		this.logMessage("starting Solar panel component.") ;
		
		
		this.scheduleTask(
				new AbstractComponent.AbstractTask() {
					@Override
					public void run() {
						try {
							((SolarPanel)this.getTaskOwner()).sendEnergy();
						} catch (Exception e) {
							throw new RuntimeException(e) ;
						}
					}
				},
				2000, TimeUnit.MILLISECONDS);
	}

	/**
	 * @see fr.sorbonne_u.components.AbstractComponent#finalise()
	 */
	@Override
	public void			finalise() throws Exception
	{
		this.logMessage("stopping Solar panel component.") ;
		// This is the place where to clean up resources, such as
		// disconnecting and unpublishing ports that will be destroyed
		// when shutting down.
		// In static architectures like in this example, ports can also
		// be disconnected by the finalise method of the component
		// virtual machine.
		this.towardsOndulator.unpublishPort() ;

		// This called at the end to make the component internal
		// state move to the finalised state.
		super.finalise();
	}
	
}
