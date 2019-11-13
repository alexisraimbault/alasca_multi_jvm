package interfaces;

import fr.sorbonne_u.components.interfaces.RequiredI;

public interface SPControllerRequiredI extends RequiredI{
	public void store(double power)throws Exception;
	public void sell(double power)throws Exception;
}
