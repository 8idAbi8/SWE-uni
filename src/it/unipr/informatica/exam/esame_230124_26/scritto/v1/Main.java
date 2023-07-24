package it.unipr.informatica.exam.esame_230124_26.scritto.v1;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
	public static void main(String[] args) {

		ExecutorImpl executor = new ExecutorImpl(2);

		Object[] mutexs = new Object[3];
		for (int i = 0; i < mutexs.length; i++) {
			mutexs[i] = new ReentrantLock();
		}

		// Crea un oggetto Runnable che stamperà un messaggio di prova
		Runnable task1 = new Runnable() {
			public void run() {
				System.out.println("Eseguo task 1");
			}
		};

		Runnable task2 = new Runnable() {
			public void run() {
				System.out.println("Eseguo task 2");
			}
		};

		// Esegue i due task in modo concorrente utilizzando l'executor
		executor.execute(mutexs, task1);
		executor.execute(mutexs, task2);

		executor.shutdown();

	}
}
