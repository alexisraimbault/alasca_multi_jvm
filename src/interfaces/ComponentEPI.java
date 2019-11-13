package interfaces;

import fr.sorbonne_u.components.interfaces.RequiredI;

public interface ComponentEPI extends RequiredI{
	public void register(String componentName)throws Exception;
	public void setConsommation(String name, double consommation) throws Exception;
}
