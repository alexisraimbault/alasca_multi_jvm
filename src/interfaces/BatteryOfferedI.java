package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedI;

public interface BatteryOfferedI extends OfferedI{
	public void store(double power)throws Exception;
	double getEnergy() throws Exception;
}