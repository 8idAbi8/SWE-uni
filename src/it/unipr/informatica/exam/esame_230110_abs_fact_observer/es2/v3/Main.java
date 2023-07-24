package it.unipr.informatica.exam.esame_230110_abs_fact_observer.es2.v3;


public class Main {
	public static  void main(String[] args) {

		A a = new AImpl();

		Callback<Integer> callback = new Callback<>() {			
			@Override
			public void onSuccess(Integer result) {
				System.out.println("Random number: " + result);
			}
		};

		Object lock = a.m(500, 1000, callback);

		// main aspetta che il thread all'interno di m() finisca, per poi terminare
		boolean bloccato = true;
		while(bloccato)
		try {
			synchronized (lock) {
				lock.wait();
				bloccato = false; // se viene risvegliato, si sblocca
			}				
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " fine");
	}
}