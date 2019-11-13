package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedI;

public interface FridgeI extends OfferedI {
	public double getTemperature() throws Exception;
	public String getState() throws Exception;
	public void switchOn() throws Exception;
	public void switchOff() throws Exception;
	public void freeze() throws Exception;
	public void rest() throws Exception;
}
