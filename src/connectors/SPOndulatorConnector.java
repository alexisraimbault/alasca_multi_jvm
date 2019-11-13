package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.SPControllerOfferedI;
import interfaces.SolarPanelI;

public class SPOndulatorConnector extends AbstractConnector implements SolarPanelI{

	@Override
	public void provide(double power) throws Exception {
		((SPControllerOfferedI)this.offering).receive(power) ;
	}
}
