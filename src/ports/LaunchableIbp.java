package ports;

import components.Ondulator;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.LaunchableOfferedI;

public class LaunchableIbp extends AbstractInboundPort implements LaunchableOfferedI{
	
	public LaunchableIbp(
			String uri,
			ComponentI owner
			) throws Exception
		{
			super(uri, LaunchableOfferedI.class, owner);
		}

		public LaunchableIbp(ComponentI owner) throws Exception
		{
			super(LaunchableOfferedI.class, owner);
		}

		@Override
		public void launchTasks() throws Exception {
			this.owner.handleRequestSync(
					new AbstractComponent.AbstractService<Void>() {
						@Override
						public Void call() throws Exception {
							((LaunchableOfferedI)this.getServiceOwner()).launchTasks() ;
							return null;
						}
					}) ;
		}
}
