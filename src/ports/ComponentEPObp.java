package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.ComponentEPI;

public class ComponentEPObp extends AbstractOutboundPort implements ComponentEPI {
	
	public ComponentEPObp(
			String uri,
			ComponentI owner
			) throws Exception
		{
			super(uri, ComponentEPI.class, owner);
		}

	public ComponentEPObp(ComponentI owner)
	throws Exception
	{
		super(ComponentEPI.class, owner);
	}

	@Override
	public void register(String componentName) throws Exception {
		
		((ComponentEPI)this.connector).register(componentName) ;
	}

	@Override
	public void setConsommation(String name, double consommation) throws Exception {
		((ComponentEPI)this.connector).setConsommation(name, consommation) ;
	}
}
