package it.unipr.informatica.exam.exercise3.WorkerThread_Original_v2;

public class Worker extends Thread {
	
	private int id;
	public static final int W = 9;
		
	public Worker(int id) {
		if(id < 0 || id >= W)
			throw new IllegalArgumentException("id < 0 || id >= W");
		
		this.id = id;
	}	
	
	@Override
	public void run() {
		
		ResourceManager resourceManager = ResourceManager.getIstance();
		
		Logger logger = Logger.getIstance();
		
		for(;;) {
			
			try {
				// acquisiamo le risorse  // aspetta finche non acquisisce 3 risorse contigue
				Resource[] resources = resourceManager.acquire(id); 
				
				// use and print ->  ResourceManager.InnerResource.use()
				logger.useAndPrint(resources[0], resources[1], resources[2]);
				
				// rilasciamo le risorse
				resources[0].release();
				resources[1].release();
				resources[2].release();
				
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				System.err.println(Thread.currentThread().getName() + " interrupted");
				return;
			}			
		}		
	}
}
