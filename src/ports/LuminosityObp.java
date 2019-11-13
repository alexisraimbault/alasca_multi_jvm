package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.LuminositySensorI;
import interfaces.SPControllerOfferedI;

public class LuminosityObp extends AbstractOutboundPort implements LuminositySensorI{

	public				LuminosityObp(
			String uri,
			ComponentI owner
			) throws Exception
	{
		super(uri, LuminositySensorI.class, owner);
	}

	public				LuminosityObp(ComponentI owner)
	throws Exception
	{
		super(LuminositySensorI.class, owner);
	}

	@Override
	public void send(double wind) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
