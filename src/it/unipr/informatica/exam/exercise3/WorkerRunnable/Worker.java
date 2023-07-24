package it.unipr.informatica.exam.exercise3.WorkerRunnable;

public class Worker implements Runnable {
	
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
				// acquisiamo le (3) risorse
				Resource[] resources = resourceManager.acquire(id);  // wait if necessary
				
				// use and print
				logger.useAndPrint(resources[0], resources[1], resources[2]);
				
				// rilasciamo le risorse
				resources[0].release();
				resources[1].release();
				resources[2].release();
				
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}		
	}
}
