package interfaces;

import fr.sorbonne_u.components.interfaces.RequiredI;

public interface ControllerHeaterI extends RequiredI{
	public int getHeaterPower() throws Exception;
	public String getHeaterState() throws Exception;
	public void switchHeaterOn() throws Exception;
	public void switchHeaterOff() throws Exception;
	public void setHeaterPower(int power) throws Exception;
}
