package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.lab.v3;

public class MonitorImpl implements Monitor {
	// il monitor appena creato è in stato bloccato
	private boolean locked;

	private Object mutex;
	
	public MonitorImpl() {
		locked = true;
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
			while (locked) {	// se bloccato, aspetta
				mutex.wait();
			}
		}			
	}
}


