package components;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import interfaces.HeaterI;

@OfferedInterfaces(offered = {HeaterI.class})
public class MeteoStation extends AbstractComponent {
	
	
	protected MeteoStation(String meteoURI, String ibpURI) throws Exception {
		super(meteoURI,  1, 1) ;
		
		
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
		
		this.tracer.setTitle("MeteoStation") ;
		this.tracer.setRelativePosition(3, 1) ;
	}
}
