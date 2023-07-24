package it.unipr.informatica.exam.esame_230706.scritto;

public class ExecutorImpl implements Executor {
	
	private Object mutex = new Object();
	private boolean done = false;
	private int index = 0;

	public int execute(Task[] tasks) {
		
		for( ; index < tasks.length; index++) {		
			
			synchronized (mutex) {
				new Thread(() -> {
					tasks[index].run(mutex);	
				}).start();			
			}
		}
		
		while(!done) {
			try {
				System.out.println("entro in wait");
				mutex.wait();
				System.out.println("esco da wait");
				done = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return index;
	}
}
