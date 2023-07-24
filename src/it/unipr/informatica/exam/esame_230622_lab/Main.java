package it.unipr.informatica.exam.esame_230622_lab;

public class Main {

	static int K = 10;
	
	public static void main(String[] args) {
		WorkerManager manager = WorkerManager.getInstance();
		
		// crea e fa "partire" gli worker
		manager.createWorkers();		
	}
}
