package it.unipr.informatica.exam.exercise3.WorkerRunnable;

public interface Resource {
	public int getID();
	public int use();
	public void release();
}
