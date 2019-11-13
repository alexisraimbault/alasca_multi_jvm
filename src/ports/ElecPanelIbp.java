package ports;

import components.Battery;
import components.ElecPanel;
import components.Fridge;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.ElecPanelOfferedI;
import interfaces.FridgeI;

public class ElecPanelIbp extends AbstractInboundPort implements ElecPanelOfferedI{

	public				ElecPanelIbp(
			String uri,
			ComponentI owner
			) throws Exception
	{
		super(uri, ElecPanelOfferedI.class, owner);
	}

	public				ElecPanelIbp(ComponentI owner)
	throws Exception
	{
		super(ElecPanelOfferedI.class, owner);
	}

	@Override
	public void register(String componentName) throws Exception {
		//System.out.println("test");
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((ElecPanel)this.getServiceOwner()).register(componentName) ;
						return null;
					}
				}) ;
	}

	@Override
	public void setConsommation(String name, double consommation) throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((ElecPanel)this.getServiceOwner()).setConsommation(name, consommation) ;
						return null;
					}
				}) ;
	}

	@Override
	public double getTotalConsommation() throws Exception {
		return this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Double>() {
					@Override
					public Double call() throws Exception {
						return ((ElecPanel)this.getServiceOwner()).getTotalConsommation() ;
					}
				}) ;
	}
}
