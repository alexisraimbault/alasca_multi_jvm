import components.HouseDeploy;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.cvm.AbstractDistributedCVM;

public class DistributedCVM extends		AbstractDistributedCVM {

	protected static String		HOUSE_DEPLOY_JVM_URI = "jvm-1" ;
	
	protected HouseDeploy	hd ;

	public				DistributedCVM(
		String[] args,
		int xLayout,
		int yLayout
		) throws Exception
	{
		super(args, xLayout, yLayout) ;
	}

	/**
	 * @see fr.sorbonne_u.components.cvm.AbstractDistributedCVM#initialise()
	 */
	@Override
	public void			initialise() throws Exception
	{
		super.initialise() ;
	}

	/**
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true				// no more preconditions.
	 * post	true				// no more postconditions.
	 * </pre>
	 * 
	 * @see fr.sorbonne_u.components.cvm.AbstractDistributedCVM#instantiateAndPublish()
	 */
	@Override
	public void			instantiateAndPublish() throws Exception
	{
		if (thisJVMURI.equals(HOUSE_DEPLOY_JVM_URI)) {
			
			String[] jvm_uris = {"jvm-1", "jvm-1", "jvm-1", "jvm-1", "jvm-1", "jvm-1", "jvm-1"};
			
			this.hd = new HouseDeploy("deploy-uri", jvm_uris);
			this.hd.toggleTracing() ;
			this.hd.toggleLogging() ;
		}

		super.instantiateAndPublish();
	}
	
	@Override
	public void			start() throws Exception
	{
		super.start() ;
		if (thisJVMURI.equals(HOUSE_DEPLOY_JVM_URI)) {
			this.hd.runTask(
				new AbstractComponent.AbstractTask() {
						@Override
						public void run() {
							try {
								((HouseDeploy)this.getTaskOwner()).
														dynamicDeploy() ;
							} catch (Exception e) {
								throw new RuntimeException(e) ;
							}
						}
					}) ;
		}
}

	public static void	main(String[] args)
	{
		try {
			DistributedCVM dda = new DistributedCVM(args, 2, 5) ;
			dda.startStandardLifeCycle(1000000L) ;
			Thread.sleep(5000L) ;
			System.exit(0) ;
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}
	}
//-----------------------------------------------------------------------------

}
