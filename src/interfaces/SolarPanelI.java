package interfaces;

import fr.sorbonne_u.components.interfaces.RequiredI;

public interface SolarPanelI extends RequiredI {
	public void provide(double power) throws Exception;
}
