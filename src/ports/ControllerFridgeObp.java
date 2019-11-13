package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.ControllerFridgeI;

public class ControllerFridgeObp extends AbstractOutboundPort implements ControllerFridgeI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControllerFridgeObp(
			String uri,
			ComponentI owner
			) throws Exception
		{
			super(uri, ControllerFridgeI.class, owner);
		}

		public ControllerFridgeObp(ComponentI owner)
		throws Exception
		{
			super(ControllerFridgeI.class, owner);
		}

	@Override
	public double getFridgeTemperature() throws Exception {
		return ((ControllerFridgeI)this.connector).getFridgeTemperature() ;
	}

	@Override
	public void switchFridgeOn() throws Exception {
		((ControllerFridgeI)this.connector).switchFridgeOn() ;
		
	}

	@Override
	public void switchFridgeOff() throws Exception {
		((ControllerFridgeI)this.connector).switchFridgeOff() ;
		
	}

	@Override
	public String getFridgeState() throws Exception {
		return ((ControllerFridgeI)this.connector).getFridgeState() ;
	}

	@Override
	public void freezeFridge() throws Exception {
		((ControllerFridgeI)this.connector).freezeFridge() ;
	}

	@Override
	public void restFridge() throws Exception {
		((ControllerFridgeI)this.connector).restFridge() ;
	}

}
