package app;

public class ServiceNotAvailable extends Exception {

	private static final long serialVersionUID = 6681603862217184006L;
	
	public ServiceNotAvailable(){
		System.err.println("The service \"CoreDatasetService\" is not available.");
		System.exit(1);
	}

}
