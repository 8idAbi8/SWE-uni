package it.unipr.informatica.exam.esame_230620.v1;

public class Main {

	public static void main(String[] args) {
		// Creazione delle operazioni
		Operation[] operations = new OperationImpl[5];
		for (int i = 0; i < operations.length; i++) {
			operations[i] = new OperationImpl();
		}

		// Creazione dell'executor
		Executor executor = new ExecutorImpl();

		// Esecuzione delle operazioni
		int executedCount = executor.execute(operations);
		System.out.println("Numero di operazioni eseguite: " + executedCount);
	}
}