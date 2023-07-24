package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy4;

public class Main {

	public static void main(String[] args) {

		int N = 10;
		
		AgentFactoryImpl agentFactory = AgentFactoryImpl.getInstance();
		agentFactory.createAgent(N);
				
		try {
			System.out.println("sleep for 2sec");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Agent[] agents =  AgentFactoryImpl.getInstance().getAgents();
				
		for (Agent agent : agents) {
			System.out.println("Agent " + agent.getID() + " state: " + agent.getState());
		}
		
		for (int i = 0; i < N; i++) {
			 agents[i].stop();
		}
	}
}
