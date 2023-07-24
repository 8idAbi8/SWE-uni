package it.unipr.informatica.exam.esame_230124_26.scritto.v2;

import java.util.Random;

public class ExecutorImpl implements Executor {
	
	private Random random = new Random();	
	private final Object lock = new Object();
	
	@Override
	  public void execute(Object[] mutexs, Runnable runnable) {
	    new Thread(() -> {
//	    	for (int i = 0; i < mutexs.length; i++) {
				synchronized (lock) {
				      runnable.run();
				      
				      int randomIndex = random.nextInt(mutexs.length);
				      Object selectedMutex = mutexs[randomIndex];
				     
//				      System.out.println(randomIndex);				      
				      synchronized (selectedMutex) {
				        selectedMutex.notify();
				      }
				    }
//			}
	    }).start();		
	  }
}




