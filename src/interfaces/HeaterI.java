package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedI;

public interface HeaterI extends OfferedI {
	
	public int getPower() throws Exception;
	public String getState() throws Exception;
	public void switchOn() throws Exception;
	public void switchOff() throws Exception;
	public void setPower(int power) throws Exception;
}
