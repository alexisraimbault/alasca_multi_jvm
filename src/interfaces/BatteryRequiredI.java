package interfaces;

import fr.sorbonne_u.components.interfaces.RequiredI;

public interface BatteryRequiredI extends RequiredI{
	public void consume(double power)throws Exception;
}
