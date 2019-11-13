package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.ControllerFridgeI;
import interfaces.ControllerHeaterI;

public class ControllerHeaterObp extends AbstractOutboundPort implements ControllerHeaterI{
	
	public ControllerHeaterObp(
			String uri,
			ComponentI owner
			) throws Exception
		{
			super(uri, ControllerHeaterI.class, owner);
		}

		public ControllerHeaterObp(ComponentI owner)
		throws Exception
		{
			super(ControllerHeaterI.class, owner);
		}

		@Override
		public int getHeaterPower() throws Exception {
			return ((ControllerHeaterI)this.connector).getHeaterPower() ;
		}

		@Override
		public String getHeaterState() throws Exception {
			return ((ControllerHeaterI)this.connector).getHeaterState() ;
		}

		@Override
		public void switchHeaterOn() throws Exception {
			((ControllerHeaterI)this.connector).switchHeaterOn() ;
		}

		@Override
		public void switchHeaterOff() throws Exception {
			((ControllerHeaterI)this.connector).switchHeaterOff() ;
		}

		@Override
		public void setHeaterPower(int power) throws Exception {
			((ControllerHeaterI)this.connector).setHeaterPower(power) ;
		}
}
