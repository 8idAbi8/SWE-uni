package it.unipr.informatica.exam.exercise3.WorkerThread_Original_v2;

public class Logger {
	
	private static volatile Logger instance;
		
	private Logger() {
		
	}
	
	public static Logger getIstance() {
		if(instance == null) {
			synchronized (Logger.class) {  // class descriptor
				if(instance == null) {
					instance = new Logger();
				}
			}
		}
		return instance;
	}
	
	public void useAndPrint(Resource r1, Resource r2, Resource r3) {
		// ResourceManager.InnerResource.use()
		int t = r1.use() + r2.use() + r3.use();
		
		System.out.println(t);
	}	
}
