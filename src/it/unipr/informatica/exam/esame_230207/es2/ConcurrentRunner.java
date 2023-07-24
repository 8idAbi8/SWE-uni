package it.unipr.informatica.exam.esame_230207.es2;

public abstract class ConcurrentRunner {
	
	private static Object mutex = new Object();
	private static Object result = null;
	
	private static boolean done = false;

	public static Object execute(Task t1, Task t2) throws InterruptedException {
		
		// Crea due thread per eseguire i task
		Thread t1Thread = new Thread(() -> {
			try {
				// è importante che le run() siano fatte all'esterno dei blocchi sync
				// altrimenti le loro esecuzioni non sarano concorrenti 
				result = t1.run();
				
				synchronized (mutex) {
					done = true;
					mutex.notify();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		});
				
		Thread t2Thread = new Thread(() -> {
			try {
				result = t2.run();
				
				synchronized (mutex) {
					done = true;
					mutex.notify();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		// Avvia i thread
		t1Thread.start();
		t2Thread.start();

		synchronized (mutex) {
			while (!done) {
				mutex.wait();
			}
		}
		
		return result;
	}
}
