package it.unipr.informatica.exam.exercise3.WorkerRunnable;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < Worker.W; i++) 
			new Thread(new Worker(i)).start();
	}	
}
