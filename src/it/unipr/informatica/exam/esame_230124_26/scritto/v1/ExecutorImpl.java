package it.unipr.informatica.exam.esame_230124_26.scritto.v1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorImpl implements Executor {

	private ExecutorService executorService;

	public ExecutorImpl(int nThreads) {
		this.executorService = Executors.newFixedThreadPool(nThreads);
	}	

	public void shutdown() {
		executorService.shutdown();		
	}
		

	@Override
	public void execute(Object[] mutexs, Runnable runnable) {

		ReentrantLock[] locks = new ReentrantLock[mutexs.length];
		
		for (int i = 0; i < mutexs.length; i++) {
			locks[i] = (ReentrantLock) mutexs[i];
		}
		
		this.executorService.submit(new InnerRunnable(locks, runnable));
	}

	
	private class InnerRunnable implements Runnable {

		private ReentrantLock[] locks;
		private Runnable runnable;

		public InnerRunnable(ReentrantLock[] locks, Runnable runnable) {
			this.locks = locks;
			this.runnable = runnable;
		}
		
		public void run() {
			
			for (int i = 0; i < locks.length; i++) {
				
				try {
					
					locks[i].lock();
					
					runnable.run();
					
					int randomIndex = (int) (Math.random() * locks.length);
					locks[randomIndex].unlock();
					
					System.out.println("se non viene stampato, la unlock viene fatta sull'lock[random]"
							+ " altrimenti la unlock viene fatta sull'lock[i]");
					
				} catch (Exception e) {
					System.out.println("Eccezione catturata: " + e);
				} finally {
					locks[i].unlock();
				}				
			}		
		}
	}	
}




