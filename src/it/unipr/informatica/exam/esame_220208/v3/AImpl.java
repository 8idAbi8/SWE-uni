package it.unipr.informatica.exam.esame_220208.v3;

public class AImpl implements A {

	private Object mutex = new Object();
	private static volatile int count;
	
	@Override
	public void ma(B b) throws InterruptedException {

		System.out.println(Thread.currentThread().getName() + " in da house\n");
		
		Thread waiter = new Thread(() -> {
			// Mettendosi in attesa che entrambi i thread abbiano completato l'esecuzione del metodo mb1()
			synchronized (mutex) {
				while (count < 2) {
					try {
						System.out.println(Thread.currentThread().getName() 
								+ " va in wait() con count:" + count);
						
						mutex.wait();
						
						System.out.println(Thread.currentThread().getName() 
								+ " esce dal wait(), count: " + count);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() 
						+ " esce dal while, count: " + count);
			} 
		}, "waiter");
		
		
		// Creazione e attivazione di due nuovi thread
		Thread t1 = new Thread(() -> {
			
			b.mb1(); // esecuzione concorrente tra mb1 e mb2 (perche chiamate fuori da synchronized)
			
			synchronized (mutex) {	
				count++;
				System.out.println(Thread.currentThread().getName() +
						" notifica, count: " + count);
				mutex.notify();
			}			
			
		}, "t1");

		Thread t2 = new Thread(() -> {
			
			b.mb2();	// esecuzione concorrente tra mb1 e mb2 (perche chiamate fuori da synchronized)
			
			synchronized (mutex) {
				count++;
				System.out.println(Thread.currentThread().getName() +
						" notifica, count: " + count);
				mutex.notify();
			}
			
		}, "t2");

		waiter.start();
		t1.start();
		t2.start();
		
		waiter.join();
		t1.join();
		t2.join();
	}
}
