package it.unipr.informatica.exam.esame_220607_09_conditionSet.es2;

public class BImpl implements B {

	public Object mb1() {

		Object lock = new Object();

		new Thread(() -> {

			System.out.println(Thread.currentThread().getName() + ": mb1() chiama mb2()");

			mb2(lock);

			System.out.println(Thread.currentThread().getName() + ": mb2() ritornato in mb1()");

		}).start();

		System.out.println(Thread.currentThread().getName() + ": che chiama mb1() prosegue e ritorna");

		return lock;
	}

	public void mb2(Object lock) {
		try {
			long rand = (long) (Math.random() * 1000);
			System.out.println(Thread.currentThread().getName() + 
					": mb2() is sleeping for " + rand + " milliseconds");
			Thread.sleep(rand);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + 
					": mb2() finisce, segnalando con notify()");
			lock.notify();
		}
	}
}
