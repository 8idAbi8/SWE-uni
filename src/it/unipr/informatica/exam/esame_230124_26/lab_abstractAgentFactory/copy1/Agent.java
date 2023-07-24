package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy1;

public interface Agent extends Runnable {
	public int getID();
	public double getState();
	public double interact(double state) throws AgentException;
	public void stop();
}