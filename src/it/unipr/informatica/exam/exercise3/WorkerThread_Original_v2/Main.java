package it.unipr.informatica.exam.exercise3.WorkerThread_Original_v2;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		Worker[] workers = new Worker[Worker.W];
		
		for (int i = 0; i < Worker.W; i++) {
			workers[i] = new Worker(i);				
			workers[i].start(); 	// Worker::run()
		}			
		
		System.out.println(Thread.currentThread().getName() + " va in sleep");
		Thread.sleep(3500);		
		System.out.println(Thread.currentThread().getName() + 
				" si risvegli e interrompe gli worker");
		
		for (Worker worker : workers) {
			worker.interrupt();
		}		
	}	
}
