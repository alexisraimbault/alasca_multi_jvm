package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.ControllerBatteryI;

public class ControllerBatteryObp extends AbstractOutboundPort implements ControllerBatteryI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControllerBatteryObp(
			String uri,
			ComponentI owner
			) throws Exception
		{
			super(uri, ControllerBatteryI.class, owner);
		}

	public ControllerBatteryObp(ComponentI owner)
	throws Exception
	{
		super(ControllerBatteryI.class, owner);
	}

	@Override
	public double getBatteryEnergy() throws Exception {
		return ((ControllerBatteryI)this.connector).getBatteryEnergy() ;
	}

}
