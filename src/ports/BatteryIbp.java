package ports;

import components.Battery;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.BatteryOfferedI;

public class BatteryIbp extends AbstractInboundPort implements BatteryOfferedI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public				BatteryIbp(
			String uri,
			ComponentI owner
			) throws Exception
	{
		super(uri, BatteryOfferedI.class, owner);
	}

	public				BatteryIbp(ComponentI owner)
	throws Exception
	{
		super(BatteryOfferedI.class, owner);
	}

	@Override
	public void store(double power) throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((Battery)this.getServiceOwner()).store(power) ;
						return null;
					}
				}) ;
	}
	
	@Override
	public double getEnergy() throws Exception {
		return this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Double>() {
					@Override
					public Double call() throws Exception {
						return ((Battery)this.getServiceOwner()).getEnergy() ;
					}
				}) ;
	}
}


