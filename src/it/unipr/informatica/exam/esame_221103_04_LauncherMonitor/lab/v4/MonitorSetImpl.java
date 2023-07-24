package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.lab.v4;

import java.util.HashSet;
import java.util.Set;

public class MonitorSetImpl implements MonitorSet {

	private Set<Monitor> monitors;
	private final Object mutex;
	int count = 0;
	
	public MonitorSetImpl() {
		monitors = new HashSet<>(100);
		mutex = new Object();
	}

	
	@Override
	public boolean add(Monitor m) {
		
		synchronized (mutex) {
			if (!monitors.contains(m)) {  // --> gli oggetti all'interno dell'insieme non vengono replicati
				monitors.add(m);
								
//				System.out.println("nr monitor: " + (++count));
				
				// ci mettiamo in attesa per ogni monitor aggiunto
				new Thread(() -> {
					try {
						m.await();  // Monitor.await() si sblocca perche è stata fatta una notify() o c'è stata un interruzione
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					// anche se c'è un eccezione, dobbiamo togliere il monitor da monitors
					synchronized (mutex) {
						monitors.remove(m); // 
						
						mutex.notifyAll();
					}
					
				}).start();
								
				// m è stato aggiunto
				return true;
			}

			// m non viene aggiunto al set 
			return false;			
		}		
	}

	
	@Override
	public void await() throws InterruptedException {
		synchronized (mutex) {
			while (!monitors.isEmpty()) {   // se abbiamo almeno un monitor in attesa,MonitorSet ha un await che blocca
				mutex.wait();
			}
		}
	}
}
