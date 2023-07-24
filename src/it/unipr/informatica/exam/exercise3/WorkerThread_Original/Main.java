package it.unipr.informatica.exam.exercise3.WorkerThread_Original;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < Worker.W; i++) 
			new Worker(i).start();				// Worker::run()
	}	
}
