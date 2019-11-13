package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.SPControllerRequiredI;

public class OndulatorObp extends AbstractOutboundPort implements SPControllerRequiredI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public				OndulatorObp(
			String uri,
			ComponentI owner
			) throws Exception
	{
		super(uri, SPControllerRequiredI.class, owner);
	}

	public				OndulatorObp(ComponentI owner)
	throws Exception
	{
		super(SPControllerRequiredI.class, owner);
	}

	@Override
	public void store(double power) throws Exception {
		((SPControllerRequiredI)this.connector).store(power) ;
		
	}

	@Override
	public void sell(double power) throws Exception {
		((SPControllerRequiredI)this.connector).sell(power) ;
		
	}
}
