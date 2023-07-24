package it.unipr.informatica.exam.esame_230124_26.scritto.v3;

public class Main {
	public static void main(String[] args) {
		// Crea un array di oggetti mutua esclusione
		Object[] mutexs = new Object[3];
		mutexs[0] = new Object();
		mutexs[1] = new Object();
		mutexs[2] = new Object();

		// Crea un'istanza della classe ExecutorImpl
		ExecutorImpl executor = new ExecutorImpl(2);

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

		// Chiude l'executor
		executor.shutdown();
	}
}
