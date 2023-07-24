package it.unipr.informatica.exam.esame_230124_26.scritto.v3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorImpl implements Executor {

	private ExecutorService executorService;

	public ExecutorImpl(int nThreads) {
		this.executorService = Executors.newFixedThreadPool(nThreads);
	}

	@Override
	public void execute(Object[] mutexs, Runnable runnable) {
		this.executorService.submit(new InnerRunnable(mutexs, runnable));
	}

	private class InnerRunnable implements Runnable {

		private Object[] mutexs;
		private Runnable runnable;

		public InnerRunnable(Object[] mutexs, Runnable runnable) {
			this.mutexs = mutexs;
			this.runnable = runnable;
		}

		@Override
		public void run() {

			for (int i = 0; i < mutexs.length; i++) {
				
				synchronized (mutexs[i]) {
					
					try {
						runnable.run();

						mutexs[new Random().nextInt(mutexs.length)].notify();

						System.out.println("se non viene stampato (exception), la notify viene fatta su mutexs[random]"
								+ " altrimenti viene fatta su mutexs[i]");

					} catch (Exception e) {
						System.out.println("Eccezione catturata: " + e);
					}
				}
			}
		}
	}

	public void shutdown() {
		executorService.shutdown();		
	}
}
