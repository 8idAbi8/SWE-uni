package it.unipr.informatica.exam.esame_230706.scritto;

public class TaskImpl implements Task {
	
	public void run(Object mutex) {
		
		System.out.println("inizio operazione");
		
		try {
			Thread.sleep((long) Math.random() * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (mutex) {
			mutex.notify();
		}
		System.out.println("fine operazione");
	}
}
