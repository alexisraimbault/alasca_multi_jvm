package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.BatteryOfferedI;
import interfaces.ControllerBatteryI;
import interfaces.ControllerOndulatorI;
import interfaces.SPControllerOfferedI;

public class ControllerOndulatorConnector extends AbstractConnector implements ControllerOndulatorI
{
	@Override
	public void setOndulatorPolicy(String policy) throws Exception {
		((SPControllerOfferedI)this.offering).setPolicy(policy) ;
	}
}
