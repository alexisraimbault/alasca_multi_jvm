package ports;

import components.Ondulator;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.SPControllerOfferedI;

public class OndulatorIbp extends AbstractInboundPort implements SPControllerOfferedI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public				OndulatorIbp(
			String uri,
			ComponentI owner
			) throws Exception
	{
		super(uri, SPControllerOfferedI.class, owner);
	}

	public				OndulatorIbp(ComponentI owner)
	throws Exception
	{
		super(SPControllerOfferedI.class, owner);
	}

	@Override
	public void receive(double power) throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((Ondulator)this.getServiceOwner()).receive(power) ;
						return null;
					}
				}) ;
	}

	@Override
	public void setPolicy(String policy) throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((Ondulator)this.getServiceOwner()).setPolicy(policy) ;
						return null;
					}
				}) ;
	}
}
