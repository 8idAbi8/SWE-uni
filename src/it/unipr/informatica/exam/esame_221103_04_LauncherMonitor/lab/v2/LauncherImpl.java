package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.lab.v2;

public class LauncherImpl implements Launcher {	

	@Override
	public Monitor launch(Runnable r) {
		
		InnerMonitor m = new InnerMonitor();
		
		// attiva un thread per ogni chiamata
		new Thread(() -> {
			try {
				System.out.println("parte run()");
				r.run();

				System.out.println("finito run(), sblocco monitor");			
				m.unlock();

			} catch (Throwable th) {	// la run() puo lanciare eccezioni unchecked
				th.printStackTrace();
			}
		}).start();

		
		System.out.println(Thread.currentThread().getName() + " ritorna \"immediatamente\" un monitor");
		
		return m;	// ritorna "immediatamente" un oggetto di tipo monitor
	}

	
	/* si puo fare tranquillamente anche una classe esterna MonitorImpl (vedi v3) */
	private class InnerMonitor implements Monitor {
		private boolean locked;
		private Object mutex;
		
		public InnerMonitor() {
			locked = true;			// il monitor appena creato è in stato bloccato
			mutex = new Object();
		}
		
		public void unlock() {
			synchronized(mutex) {
				locked = false;  // sbloccato
				
				// notifichiamo chi è in wait() che locked diventi false
				mutex.notifyAll();
			}
		}

		@Override
		public void await() throws InterruptedException {
			synchronized (mutex) {
				while (locked) {
					mutex.wait();
				}
			}			
		}		
	}
}
