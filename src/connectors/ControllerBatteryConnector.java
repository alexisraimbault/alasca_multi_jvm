package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.BatteryOfferedI;
import interfaces.ControllerBatteryI;

public class ControllerBatteryConnector extends AbstractConnector implements ControllerBatteryI
{
	@Override
	public double getBatteryEnergy() throws Exception {
		return ((BatteryOfferedI)this.offering).getEnergy() ;
	}
}
