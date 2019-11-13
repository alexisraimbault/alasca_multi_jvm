package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.ControllerEPI;
import interfaces.ElecPanelOfferedI;

public class ControllerEPConnector extends AbstractConnector implements ControllerEPI{
	
	@Override
	public double getTotalConsommation() throws Exception {
		return ((ElecPanelOfferedI)this.offering).getTotalConsommation() ;
	}
}
