package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedI;

public interface SPControllerOfferedI extends OfferedI{
	public void receive(double power)throws Exception;
	public void setPolicy(String policy) throws Exception;
}
