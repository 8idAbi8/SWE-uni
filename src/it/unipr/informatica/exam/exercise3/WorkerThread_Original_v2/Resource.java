package it.unipr.informatica.exam.exercise3.WorkerThread_Original_v2;

public interface Resource {
	public int getID();
	public int use();
	public void release();
}
