package it.unipr.informatica.exam.exercise3.ResourceThread_v2;

public class ResourceManager {
	
	private static volatile ResourceManager instance;
	private ResourceImpl[] resources;
	static final Object mutex = new Object();	// package scope in modo da essere usato da ResourceImpl
	private static final int R = 9;		
	
	private ResourceManager() {
		resources = new ResourceImpl[R];
	}
		
	public static ResourceManager getIstance() {
		if(instance == null) {
			synchronized (ResourceManager.class) {  // class descriptor
				if(instance == null) {
					instance = new ResourceManager();
				}
			}
		}
		return instance;
	}	
	
	public void start() {
		for (int i = 0; i < R; i++) {
			resources[i] = new ResourceImpl(i);
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
}
