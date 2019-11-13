package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.ControllerEPI;

public class ControllerEPObp extends AbstractOutboundPort implements ControllerEPI {
	
	public ControllerEPObp(
			String uri,
			ComponentI owner
			) throws Exception
		{
			super(uri, ControllerEPI.class, owner);
		}

		public ControllerEPObp(ComponentI owner)
		throws Exception
		{
			super(ControllerEPI.class, owner);
		}

		@Override
		public double getTotalConsommation() throws Exception {
			return ((ControllerEPI)this.connector).getTotalConsommation() ;
		}
}
