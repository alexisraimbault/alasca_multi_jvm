package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedI;

public interface MeteoStationI extends OfferedI  {
	public void receiveWind(double wind)throws Exception;
	public void receiveLuminosity(double luminosity)throws Exception;
}
