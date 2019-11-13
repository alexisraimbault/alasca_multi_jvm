package ports;

import components.Fridge;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.FridgeI;

public class FridgeIbp extends AbstractInboundPort implements FridgeI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public				FridgeIbp(
			String uri,
			ComponentI owner
			) throws Exception
	{
		super(uri, FridgeI.class, owner);
	}

	public				FridgeIbp(ComponentI owner)
	throws Exception
	{
		super(FridgeI.class, owner);
	}

	@Override
	public double getTemperature() throws Exception {
		return this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Double>() {
					@Override
					public Double call() throws Exception {
						return ((Fridge)this.getServiceOwner()).getTemperature() ;
					}
				}) ;
	}

	@Override
	public String getState() throws Exception {
		return this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<String>() {
					@Override
					public String call() throws Exception {
						return ((Fridge)this.getServiceOwner()).getState() ;
					}
				}) ;
	}

	@Override
	public void switchOn() throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((Fridge)this.getServiceOwner()).switchOn() ;
						return null;
					}
				}) ;
	}

	@Override
	public void switchOff() throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((Fridge)this.getServiceOwner()).switchOff() ;
						return null;
					}
				}) ;
	}

	@Override
	public void freeze() throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((Fridge)this.getServiceOwner()).freeze() ;
						return null;
					}
				}) ;
	}

	@Override
	public void rest() throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((Fridge)this.getServiceOwner()).rest() ;
						return null;
					}
				}) ;
	}

}
