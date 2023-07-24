package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy2;

// questa è la AbstractFactory 

public interface AgentFactory {
	public Agent createAgent();

	public Agent getAgent(int id);
}
