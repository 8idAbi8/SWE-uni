package it.unipr.informatica.exam.esame_230622_lab;

public class WorkerManager {

	static int W = 50;
	private InnerWorker[] workers = new InnerWorker[W];
	private Elevator elevator;
	
	private static volatile WorkerManager instance = null;

	
	private WorkerManager() {
		elevator = new ElevatorImpl();
	}
	
	public static WorkerManager getInstance() {
		if (instance == null) {
			synchronized (WorkerManager.class) {
				if (instance == null)
					instance = new WorkerManager();
			}
		}
		return instance;		
	}

	// crea e fa "partire" gli worker
	public void createWorkers() {
		for (int i = 0; i < W; i++) {
			workers[i]  = new InnerWorker();
			new Thread(workers[i]::execute).start();
		}
	}

		
	private class InnerWorker implements Worker {

		@Override
		public void execute() {
			elevator.getIn();
			
			System.out.println("In elevator: " + Thread.currentThread().getName());
			
			// tra 100 e 250
			long rand = (long) (Math.random() * 150 + 100);
			
			try {
				Thread.sleep(rand);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			elevator.getOut();
		}

		@Override
		public Elevator getElevator() {
			return elevator;
		}		
	}
}
