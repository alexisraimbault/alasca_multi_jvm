package interfaces;

import fr.sorbonne_u.components.interfaces.RequiredI;

public interface ControllerBatteryI extends RequiredI{
	public double getBatteryEnergy() throws Exception;
}
