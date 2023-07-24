package it.unipr.informatica.exam.esame_230110_abs_fact_observer.es2.v3;

import java.util.concurrent.ThreadLocalRandom;

public class AImpl implements A {

	private final Object lock = new Object();

	@Override
	public Object m(int min, int max, Callback<Integer> callback) {
		
		new Thread(() -> {
			synchronized (lock) {
				int randomNumber = ThreadLocalRandom.current().nextInt(min, max);
				
				try {
					System.out.println(Thread.currentThread().getName() + " sleeps for ms: " + randomNumber);
					Thread.sleep(randomNumber);
				} catch (InterruptedException e) {
					callback.onFailure(e);
				}
				
				callback.onSuccess(randomNumber);
				
				// notifica main, quando l'operazione e' conclusa
				lock.notify();
			}
		}).start();
		
		return lock;
	}
}
