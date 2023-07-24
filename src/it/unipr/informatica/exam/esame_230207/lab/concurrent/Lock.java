package it.unipr.informatica.exam.esame_230207.lab.concurrent;

public interface Lock {	
	public void lock() throws InterruptedException;
	public void unlock();
}
