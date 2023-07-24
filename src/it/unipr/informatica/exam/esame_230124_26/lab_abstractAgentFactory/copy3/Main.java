package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy3;

public class Main {

	public static void main(String[] args) {

		int N = 10;
		
		AgentFactory agentFactory = AgentFactoryImpl.getInstance();
		
		agentFactory.createAgent(N);
				
		try {
			System.out.println("sleep for 1sec");
			Thread.sleep(1000);
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
