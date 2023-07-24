package it.unipr.informatica.exam.exercise3.WorkerThread_Original;

public interface Resource {
	public int getID();
	public int use();
	public void release();
}
