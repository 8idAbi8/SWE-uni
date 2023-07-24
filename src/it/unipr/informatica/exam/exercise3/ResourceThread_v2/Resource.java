package it.unipr.informatica.exam.exercise3.ResourceThread_v2;

public interface Resource {
	public int getID();
	public int use();
	public void release();
}
