package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.ControllerHeaterI;
import interfaces.HeaterI;

public class ControllerHeaterConnector extends AbstractConnector implements ControllerHeaterI{

	@Override
	public int getHeaterPower() throws Exception {
		return ((HeaterI)this.offering).getPower() ;
	}

	@Override
	public String getHeaterState() throws Exception {
		return ((HeaterI)this.offering).getState() ;
	}

	@Override
	public void switchHeaterOn() throws Exception {
		((HeaterI)this.offering).switchOn() ;
	}

	@Override
	public void switchHeaterOff() throws Exception {
		((HeaterI)this.offering).switchOff() ;
		
	}

	@Override
	public void setHeaterPower(int power) throws Exception {
		((HeaterI)this.offering).setPower(power) ;
	}

}
