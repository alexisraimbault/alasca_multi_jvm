package ports;

import components.Heater;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.HeaterI;

public class HeaterIbp extends AbstractInboundPort implements HeaterI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeaterIbp(
		String uri,
		ComponentI owner
		) throws Exception
	{
		super(uri, HeaterI.class, owner);
	}

	public HeaterIbp(ComponentI owner) throws Exception
	{
		super(HeaterI.class, owner);
	}

	@Override
	public int getPower() throws Exception {
		return this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Integer>() {
					@Override
					public Integer call() throws Exception {
						return ((Heater)this.getServiceOwner()).getPower();
					}
				}) ;
	}

	@Override
	public String getState() throws Exception {
		return this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<String>() {
					@Override
					public String call() throws Exception {
						return ((Heater)this.getServiceOwner()).getState();
					}
				}) ;
	}

	@Override
	public void switchOn() throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((Heater)this.getServiceOwner()).switchOn();
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
						((Heater)this.getServiceOwner()).switchOff();
						return null;
					}
				}) ;
		
	}

	@Override
	public void setPower(int power) throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((Heater)this.getServiceOwner()).setPower(power);
						return null;
					}
				}) ;
		
	}
}
