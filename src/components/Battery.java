package components;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.BatteryOfferedI;
import interfaces.BatteryRequiredI;
import ports.BatteryIbp;
import ports.OndulatorIbp;
import ports.OndulatorObp;

@OfferedInterfaces(offered = {BatteryOfferedI.class})
@RequiredInterfaces(required = {BatteryRequiredI.class})
public class Battery extends AbstractComponent {
	public double energy;
	
	private BatteryIbp ibp;
	
	protected Battery(String batteryURI, String ibpURI) throws Exception {
		super(batteryURI,  1, 1) ;
		
		this.ibp = new BatteryIbp(ibpURI, this) ;
		this.ibp.publishPort() ;
		
		this.energy = 0;
		
		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir")) ;
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home")) ;
		}
		
		this.tracer.setTitle("Battery") ;
		this.tracer.setRelativePosition(1, 2) ;
		
	}

	public void store(double power) {
		energy += power;
		this.logMessage("storing " + power + " energy...");
	}

	public Double getEnergy() {
		return energy;
	}
	
	@Override
	public void			start() throws ComponentStartException
	{
		super.start() ;
		this.logMessage("starting Battery component.") ;
		
	}

	/**
	 * @see fr.sorbonne_u.components.AbstractComponent#finalise()
	 */
	@Override
	public void			finalise() throws Exception
	{
		this.logMessage("stopping Battery component.") ;
		// This is the place where to clean up resources, such as
		// disconnecting and unpublishing ports that will be destroyed
		// when shutting down.
		// In static architectures like in this example, ports can also
		// be disconnected by the finalise method of the component
		// virtual machine.
		this.ibp.unpublishPort() ;

		// This called at the end to make the component internal
		// state move to the finalised state.
		super.finalise();
	}
}
