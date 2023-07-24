package it.unipr.informatica.exam.esame_230706.scritto;

public class Main {

	public static void main(String[] args) {
		
		// Creazione delle task
		Task[] tasks = new TaskImpl[5];
		
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = new TaskImpl();
		}

		// Creazione dell'executor
		Executor executor = new ExecutorImpl();

		// Esecuzione delle task
		int executedCount = executor.execute(tasks);
		
		System.out.println("Numero di operazioni eseguite: " + executedCount);
	}
}