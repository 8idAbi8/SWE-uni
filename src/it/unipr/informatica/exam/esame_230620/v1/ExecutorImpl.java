package it.unipr.informatica.exam.esame_230620.v1;

public class ExecutorImpl implements Executor {
	private Object mutex;
	private boolean done;
	private int index;

	public int execute(Operation[] ops) {
		mutex = new Object();
		done = false;
		index = 0;
		
		for( ; index < ops.length; index++) {
			synchronized (mutex) {

				System.err.println("index: " + index);
				new Thread(() -> {
					System.out.println("index: " + index);
					ops[index].run(mutex);
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
