package it.unipr.informatica.exam.exercise3.ResourceThread_v2;

public class ResourceImpl extends Thread implements Resource {

	boolean free;		// package scope, usato da ResourceManager::acquire()
	private int id;
	private final Object mutex;

	public ResourceImpl(int id) {
		free = true;
		this.id = id;
		mutex = ResourceManager.mutex;
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

