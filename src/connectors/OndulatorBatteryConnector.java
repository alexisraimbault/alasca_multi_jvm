package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.BatteryOfferedI;
import interfaces.SPControllerRequiredI;

public class OndulatorBatteryConnector extends AbstractConnector implements SPControllerRequiredI{

	@Override
	public void store(double power) throws Exception {
		((BatteryOfferedI)this.offering).store(power) ;
	}

	@Override
	public void sell(double power) throws Exception {
		//TODO, money?
	}

}
