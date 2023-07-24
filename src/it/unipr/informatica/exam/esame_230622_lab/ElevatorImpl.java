package it.unipr.informatica.exam.esame_230622_lab;

public class ElevatorImpl implements Elevator {

	private int blockedCalls = 0;
	private int K = Main.K;
	private final Object mutex = new Object();

	public void getIn() {

		synchronized (mutex) {
			
			blockedCalls++;
			
			while (blockedCalls < K)
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			// se nr chiamate >= K, sblocco K chiamate
			for (int i = 0; i < K; i++) {
				mutex.notify();
			}

			blockedCalls -= K;
		}
	}

	public void getOut() {
		System.out.println("Out elevator " + Thread.currentThread().getName());
		synchronized (mutex) {
			mutex.notifyAll(); // Sblocca una chiamata
		}		
	}
}
