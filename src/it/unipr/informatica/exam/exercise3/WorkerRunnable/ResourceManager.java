package it.unipr.informatica.exam.exercise3.WorkerRunnable;

public class ResourceManager {
	
	private static volatile ResourceManager instance;
	private InnerResource[] resources;
	private final Object mutex;	
	private static final int R = 9;
		
	
	private ResourceManager() {
		resources = new InnerResource[R];
		mutex = new Object();
		
		// crea risorse e le inserisce nell'array delle risorse
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
	
	/* il metodo acquire(id), cerca di acquisire tre risorse consecutive, id, id+1, id+2, e ritornarle in un array.*/
	public Resource[] acquire(int id) throws InterruptedException {
		if (id < 0 || id >= R)
			throw new IllegalArgumentException();
		
		// synch per accedere dati condivisi (come resources[], free) in mutua esclusione
		synchronized (mutex) {
			// se almeno una di queste rissorse non è libera, si aspetta che si libera/liberino
			while (!resources[id].free || !resources[(id + 1) % R].free || !resources[(id + 2) % R].free)
				mutex.wait();	// rilascia il mutex
			
			// se sono tutte e 3 libere, le blocchiamo
			resources[id].free = resources[(id + 1) % R].free = resources[(id + 2) % R].free = false;
			
		}
		
		return new Resource[] { resources[id], resources[(id + 1) % R], resources[(id + 2) % R] };		
	}
	
	
	//costriuamo le singole risorse
	private class InnerResource implements Resource {

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
			
			/* le due classi usano lo stesso mutex per controllare contemporaneamente lo stato 
			 * delle 3 risorse e non avere race condition.			
			*/
			synchronized (mutex) {	// synch perche var condivisa: free
				// dopo l'acquisizione della risorsa, "free" diventa false
				if (free) 
					throw new IllegalStateException("free");
				
				// id + 0-99 estremi inclusi
				return  id + (int) Math.floor(Math.random() * 99);
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
