package components;

import java.util.HashMap;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.ElecPanelOfferedI;
import interfaces.LaunchableOfferedI;
import ports.ElecPanelIbp;
import ports.LaunchableIbp;

@OfferedInterfaces(offered = {ElecPanelOfferedI.class, LaunchableOfferedI.class})
public class ElecPanel  extends AbstractComponent implements LaunchableOfferedI {
	public ElecPanelIbp ibp;
	public HashMap<String, Double> components;
	private LaunchableIbp launchIbp;
	
	protected ElecPanel(String epURI, String ibpURI, String launchUri) throws Exception {
		super(epURI,  1, 1) ;

		this.launchIbp = new LaunchableIbp(launchUri, this) ;
		this.launchIbp.publishPort() ;
		
		this.ibp = new ElecPanelIbp(ibpURI, this) ;
		this.ibp.publishPort() ;
		
		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir")) ;
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home")) ;
		}
		
		this.tracer.setTitle("ElecPanel") ;
		this.tracer.setRelativePosition(0, 0) ;
		
		components = new HashMap<String, Double>();
	}


	public Double getTotalConsommation() {
		double total = 0;
		for(Double c : components.values())
			total += c;
		return total;
	}


	public void setConsommation(String name, double consommation) {
		this.logMessage("setting consommation for component : " + name + " to : " + consommation);
		components.put(name, consommation);
	}


	public void register(String componentName) {
		this.logMessage("registring component : " + componentName);
		components.put(componentName, 0.0);
	}
	
	@Override
	public void			start() throws ComponentStartException
	{
		super.start() ;
		this.logMessage("starting electric panel component.") ;
		
	}



	/**
	 * @see fr.sorbonne_u.components.AbstractComponent#finalise()
	 */
	@Override
	public void			finalise() throws Exception
	{
		this.logMessage("stopping Electric Panel component.") ;
		// This is the place where to clean up resources, such as
		// disconnecting and unpublishing ports that will be destroyed
		// when shutting down.
		// In static architectures like in this example, ports can also
		// be disconnected by the finalise method of the component
		// virtual machine.
		this.ibp.unpublishPort() ;

		// This called at the end to make the component internal
		// state move to the finalised state.  15 + on 14
		super.finalise();
	}


	@Override
	public void launchTasks() throws Exception {
		// nothing
		
	}

}
