package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.LauncherI;

public class LauncherObp extends AbstractOutboundPort implements LauncherI{

	public LauncherObp(
			String uri,
			ComponentI owner
			) throws Exception
		{
			super(uri, LauncherI.class, owner);
		}

		public LauncherObp(ComponentI owner)
		throws Exception
		{
			super(LauncherI.class, owner);
		}
		
	@Override
	public void launchTasks() throws Exception {
		((LauncherI)this.connector).launchTasks() ;
	}

}
