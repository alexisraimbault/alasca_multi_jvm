package components;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.cvm.AbstractCVM;

public class LuminositySensor extends AbstractComponent {

	
	protected LuminositySensor(String luxURI, String ibpURI) throws Exception {
		super(luxURI,  1, 1) ;
		
		
		/*this.ibp = new HeaterIbp(ibpURI, this) ;
		this.ibp.publishPort() ;
		
		this.epObp = new ComponentEPObp(epURI, this);
		this.epObp.publishPort() ;*/
		
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		// remove ?
		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir")) ;
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home")) ;
		}
		
		this.tracer.setTitle("luminosity sensor") ;
		this.tracer.setRelativePosition(3, 1) ;
	}
}
