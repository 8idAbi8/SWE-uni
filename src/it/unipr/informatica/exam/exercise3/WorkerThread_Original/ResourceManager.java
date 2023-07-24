package it.unipr.informatica.exam.exercise3.WorkerThread_Original;

public class ResourceManager {
	
	private static volatile ResourceManager instance;
	private InnerResource[] resources;
	private final Object mutex;	
	private static final int R = 9;		
	
	private ResourceManager() {
		mutex = new Object();
		
		resources = new InnerResource[R];	
		
		for (int i = 0; i < R; i++) {
			resources[i] = new InnerResource(i);
		}
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
	
	
	// costriuamo le singole risorse
	private class InnerResource implements Resource {
		
		private boolean free;
		private int id;
		
		private InnerResource(int id) {
			free = true;   	// risorse inizialmente libere (non bloccate)
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
	}	
}
