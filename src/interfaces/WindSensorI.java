package interfaces;

import fr.sorbonne_u.components.interfaces.RequiredI;

public interface WindSensorI extends RequiredI {
	public void send(double wind) throws Exception;
}
