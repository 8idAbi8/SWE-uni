package it.unipr.informatica.exam.exercise3.ResourceThread_v1;

public class ResourceManager {
	
	private static volatile ResourceManager instance;
	private InnerResource[] resources;
	private final Object mutex;	
	private final int R;		
	
	private ResourceManager(int R) {
		resources = new InnerResource[R];
		mutex = new Object();	
		this.R = R;
	}
		
	public static ResourceManager getIstance(int R) {
		if(instance == null) {
			synchronized (ResourceManager.class) {  // class descriptor
				if(instance == null) {
					instance = new ResourceManager(R);
				}
			}
		}
		return instance;
	}	
	
	public void start() {
		for (int i = 0; i < R; i++) {
			resources[i] = new InnerResource(i);
			resources[i].start();
		}
	}
	
	public void stop() {
		for (int i = 0; i < R; i++) {
			resources[i].interrupt();
		}
	}
	
	
	public Resource[] acquire(int id) throws InterruptedException {
		if (id < 0 || id >= R)
			throw new IllegalArgumentException();
		
		synchronized (mutex) {
			// se almeno una di queste rissorse non è libera, si aspetta che si libera/liberino
			while (!resources[id].free || !resources[(id + 1) % R].free || !resources[(id + 2) % R].free) {
				mutex.wait();
			}
				
			
			// se sono tutte e 3 libere, le blocchiamo
			resources[id].free = resources[(id + 1) % R].free = resources[(id + 2) % R].free = false;
		}
		
		return new Resource[] { resources[id], resources[(id + 1) % R], resources[(id + 2) % R] };		
	}
	
	
	//costriuamo le singole risorse
	private class InnerResource extends Thread implements Resource {

		private boolean free;
		private int id;
		
		private InnerResource(int id) {
			free = true;
			this.id = id;
		}
		
		@Override
		public int getID() {
			return id;
		}

		// la use puo essere chiamata, soltanto se la rissorsa su cui viene chiamata è bloccata
		@Override
		public int use() {
			
			// le due classi usano lo stesso mutex per controllare contemporaneamente lo stato delle 3 risorse e non avere race condition
			synchronized (mutex) {	
				if (free) 
					throw new IllegalStateException("free");
				
				// id + 0-99 estremi inclusi
				return  id + (int) Math.floor(Math.random() * 100);
			}
		}

		@Override
		public void release() {
			synchronized (mutex) {
				free = true;	
				mutex.notifyAll();
			}			
		}
		
		@Override
		public void run() {
//			ResourceManager resourceManager = ResourceManager.getIstance(R);  // same as instance
			
			Logger logger = Logger.getIstance();
			
			for(;;) {				
				try {
					// acquisiamo le risorse  // aspetta finche non acquisisce 3 risorse contigue
//					Resource[] resources = instance.acquire(id);  			   // in questo momento instance è gia stata creata
					Resource[] resources = ResourceManager.this.acquire(id);  // OuterClass.this è uguale a instance
					
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
}
