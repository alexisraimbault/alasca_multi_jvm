package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.LaunchableOfferedI;
import interfaces.LauncherI;

public class LaunchConnector extends AbstractConnector implements LauncherI{

	@Override
	public void launchTasks() throws Exception {
		((LaunchableOfferedI)this.offering).launchTasks() ;
	}

}
