package it.unipr.informatica.exam.esame_220208.v1;

public class AImpl implements A {

	private Object mutex = new Object();
	private static volatile int count;
	
	@Override
	public void ma(B b) throws InterruptedException {

		// Creazione e attivazione di due nuovi thread
		new Thread(() -> {
			synchronized (mutex) {
				b.mb1();
				
				count++;
				mutex.notifyAll();
			}			
			b.mb2();
			
		}).start();

		new Thread(() -> {
			synchronized (mutex) {
				b.mb1();
				
				count++;
				mutex.notifyAll();
			}
			b.mb2();
			
		}).start();

		// Mettendosi in attesa che entrambi i thread abbiano completato l'esecuzione del metodo mb1()
		synchronized (mutex) {
			while (count < 2) {
				mutex.wait();
			}
		}        
	}
}
