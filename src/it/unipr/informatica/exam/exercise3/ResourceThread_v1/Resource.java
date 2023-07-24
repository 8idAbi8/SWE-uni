package it.unipr.informatica.exam.exercise3.ResourceThread_v1;

public interface Resource {
	public int getID();
	public int use();
	public void release();
}
