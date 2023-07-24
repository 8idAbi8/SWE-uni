package it.unipr.informatica.exam.esame_220607_09_conditionSet.lab_conditionSet;

public class WorkerManager {

	private static final int NUM_WORKERS = 50;
	private ConditionSet[] conditionSet;
	private Worker[] workers;

	private volatile static WorkerManager instance;


	private WorkerManager() throws InterruptedException {
		conditionSet = new ConditionSetImpl[NUM_WORKERS];		
		workers = new Worker[NUM_WORKERS];				
	}


	public static WorkerManager getInstance() throws InterruptedException {
		if(instance == null)
			synchronized(WorkerManager.class) {
				if(instance == null)
					instance = new WorkerManager();
			}
		return instance;
	}


	public void startWorkers() {
		
		for(int i = 0; i < NUM_WORKERS; ++i) {
			
			conditionSet[i] = new ConditionSetImpl();			
			
			Worker worker = new Worker(i, conditionSet[i]);			
			workers[i] = worker;
			worker.start();
		}	
	}

	public synchronized void awaitWorker() throws InterruptedException {

		Object object = null;
		
		for(;;) {
			for(ConditionSet conditionSet : this.conditionSet) {
				object = conditionSet.await();				
			}				
			System.out.println(object + " has notified.");
		}		
	}
}
