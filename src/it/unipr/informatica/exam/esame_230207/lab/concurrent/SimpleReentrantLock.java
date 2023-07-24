package it.unipr.informatica.exam.esame_230207.lab.concurrent;

public class SimpleReentrantLock implements Lock {

	private Thread owner;
	private int counter;
	private Object mutex;
	
	public SimpleReentrantLock() {
		this.owner = null;
		this.counter = 0;
		mutex = new Object();
	}
	
	@Override
	public  void lock() throws InterruptedException {
		
		Thread currentThread = Thread.currentThread();
				
		synchronized (mutex) {
		
			if (counter < 0) // check se counter sia andato in overflow
				throw new IllegalMonitorStateException("counter < 0");
			
			/*	Il while mette in wait tutti i thread diversi dal currentThread.
			 *  Se l'owner e' il currentThread, continua a tenere il lock */
			while (owner != null && owner != currentThread) 
				mutex.wait();	// se la wait fallisce viene lanciata un exception che sblocca il lock
			
			
//			l'owner inizialmente e' null, dopodiche diventa il currentThread
			if(owner == null)
				owner = currentThread;
			
			counter++;     // conta il nr di lock 
		}
	}

	@Override
	public void unlock() {		
		synchronized (mutex) {			
			if (counter <= 0)
				throw new IllegalStateException("counter == 0 : lock is already free!");			
			if (owner != Thread.currentThread())
				throw new IllegalStateException("no permission to unlock other thread's lock!");
			
			counter--;

			if (counter == 0) {
				owner = null;
				mutex.notify();
			}
		}		
	}
	
	public synchronized boolean isCurrentThreadOwner() {
		return owner == Thread.currentThread();	
	}	
}
