package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedI;

public interface ElecPanelOfferedI  extends OfferedI{
	public void register(String componentName)throws Exception;
	public void setConsommation(String name, double consommation) throws Exception;
	public double getTotalConsommation()throws Exception;
}
