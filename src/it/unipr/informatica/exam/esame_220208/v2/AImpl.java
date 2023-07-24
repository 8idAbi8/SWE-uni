package it.unipr.informatica.exam.esame_220208.v2;

public class AImpl implements A {

	private Object mutex = new Object();
	private static volatile int count;

	@Override
	public void ma(B b) throws InterruptedException {

		System.out.println(Thread.currentThread().getName() + " in da house\n");

		// attesa che entrambi i thread abbiano notificato 
		new Thread(() -> {
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
		}, "t1").start();

		// Creazione e attivazione di due nuovi thread
		new Thread(() -> {
			synchronized (mutex) {
				b.mb1();

				count++;
				System.out.println(Thread.currentThread().getName() +
						" notifica, count: " + count);
				mutex.notify();
			}			

		}, "t2").start();

		new Thread(() -> {
			synchronized (mutex) {
				b.mb2();

				count++;
				System.out.println(Thread.currentThread().getName() +
						" notifica, count: " + count);
				mutex.notify();
			}

		}, "t3").start();		
	}
}
