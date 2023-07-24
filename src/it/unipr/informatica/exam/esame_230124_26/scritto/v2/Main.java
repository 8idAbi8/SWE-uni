package it.unipr.informatica.exam.esame_230124_26.scritto.v2;

public class Main {
	public static void main(String[] args) {

		ExecutorImpl executor = new ExecutorImpl();

		Object[] mutexs = new Object[3];
		for (int i = 0; i < mutexs.length; i++) {
			mutexs[i] = new Object();
		}

		// Crea un oggetto Runnable che stamperà un messaggio di prova
		Runnable task1 = new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

		//				executor.shutdown();		
	}
}
