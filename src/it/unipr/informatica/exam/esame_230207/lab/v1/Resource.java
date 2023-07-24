package it.unipr.informatica.exam.esame_230207.lab.v1;

public interface Resource {
	public int getID();
	public void acquire() throws InterruptedException;
	public void release();
	public int use();
}
