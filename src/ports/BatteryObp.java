package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.BatteryRequiredI;

public class BatteryObp extends AbstractOutboundPort implements BatteryRequiredI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public				BatteryObp(
			String uri,
			ComponentI owner
			) throws Exception
	{
		super(uri, BatteryRequiredI.class, owner);
	}

	public				BatteryObp(ComponentI owner)
	throws Exception
	{
		super(BatteryRequiredI.class, owner);
	}

	@Override
	public void consume(double power) throws Exception {
		((BatteryRequiredI)this.connector).consume(power) ;
	}
}
