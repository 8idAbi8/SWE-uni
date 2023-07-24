package it.unipr.informatica.exam.esame_220607_09_conditionSet.lab_conditionSet;

import java.util.Random;

public class Worker extends Thread {

	private final int id; 
	private final ConditionSet objectConditionSet;
	
	public Worker(int id, Object objectConditionSet) {
		this.id = id;
		this.objectConditionSet = (ConditionSet) objectConditionSet;
	}


	@Override
	public void run() {  

		while(true) {
			try {
				int sleepTime = new Random().nextInt(21) + 20;
				System.out.println("sleep for: " + sleepTime + " msec");
				Thread.sleep(sleepTime);

				objectConditionSet.add(this);

				synchronized (objectConditionSet) {
					objectConditionSet.notify();
				}


			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
	}

	public int get_Id() {
		return id;
	}
}


