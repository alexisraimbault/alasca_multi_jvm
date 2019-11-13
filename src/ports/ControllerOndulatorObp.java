package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.ControllerBatteryI;
import interfaces.ControllerOndulatorI;

public class ControllerOndulatorObp extends AbstractOutboundPort implements ControllerOndulatorI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControllerOndulatorObp(
			String uri,
			ComponentI owner
			) throws Exception
		{
			super(uri, ControllerOndulatorI.class, owner);
		}

		public ControllerOndulatorObp(ComponentI owner)
		throws Exception
		{
			super(ControllerOndulatorI.class, owner);
		}

	@Override
	public void setOndulatorPolicy(String policy) throws Exception {
		((ControllerOndulatorI)this.connector).setOndulatorPolicy(policy) ;
	}
}
