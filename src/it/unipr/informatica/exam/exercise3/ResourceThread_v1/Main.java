package it.unipr.informatica.exam.exercise3.ResourceThread_v1;

public class Main {
	private final static int R = 9;		// nr risorse che si vogliono creare
	
	public static void main(String[] args) throws InterruptedException {
		
		ResourceManager manager = ResourceManager.getIstance(R);
		
		manager.start();
		
		Thread.sleep(3500);
		System.out.println("interruzione thread");
		
		manager.stop();

	}	
}
