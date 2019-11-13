package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.LuminositySensorI;
import interfaces.WindSensorI;

public class WindObp extends AbstractOutboundPort implements WindSensorI{
	
	public				WindObp(
			String uri,
			ComponentI owner
			) throws Exception
	{
		super(uri, WindSensorI.class, owner);
	}

	public				WindObp(ComponentI owner)
	throws Exception
	{
		super(WindSensorI.class, owner);
	}

	@Override
	public void send(double wind) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
