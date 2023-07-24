package it.unipr.informatica.exam.exercise3.ResourceThread_v2;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		ResourceManager manager = ResourceManager.getIstance();
		
		manager.start();
		
		Thread.sleep(3500);
		System.out.println("interruzione thread");
		
		manager.stop();
	}	
}
