package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.ComponentEPI;
import interfaces.ElecPanelOfferedI;

public class ComponentEPConnector extends AbstractConnector implements ComponentEPI{

	@Override
	public void register(String componentName) throws Exception {
		
		((ElecPanelOfferedI)this.offering).register(componentName) ;
	}

	@Override
	public void setConsommation(String name, double consommation) throws Exception {
		((ElecPanelOfferedI)this.offering).setConsommation(name, consommation) ;
	}

}
