package it.unipr.informatica.exam.esame_220607_09_conditionSet.lab_conditionSet;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		// creiamo il manager
		WorkerManager workerManager = WorkerManager.getInstance();
		
		// creiamo i worker e li facciamo partire
		workerManager.startWorkers();
		
		while (true) {
			try {
				workerManager.awaitWorker();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
	}
}