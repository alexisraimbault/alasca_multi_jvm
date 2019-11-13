package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.SolarPanelI;

public class SPObp extends AbstractOutboundPort implements SolarPanelI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public				SPObp(
			String uri,
			ComponentI owner
			) throws Exception
	{
		super(uri, SolarPanelI.class, owner);
	}

	public				SPObp(ComponentI owner)
	throws Exception
	{
		super(SolarPanelI.class, owner);
	}

	@Override
	public void provide(double power) throws Exception {
		((SolarPanelI)this.connector).provide(power) ;
	}
}
