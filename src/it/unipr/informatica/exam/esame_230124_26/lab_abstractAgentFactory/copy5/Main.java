package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy5;

public class Main {

	public static void main(String[] args) {

		int N = 10;
		
		AgentFactoryImpl agentFactory = AgentFactoryImpl.getInstance();
		agentFactory.createAgent(N);
				
		try {
			System.out.println("sleep for 1sec");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Object[] agents =  AgentFactoryImpl.getInstance().getAgents();
				
		for (Object agent : agents) {
			System.out.println("Agent " + ((Agent) agent).getID() + " state: " + ((Agent) agent).getState());
		}
		
		for (int i = 0; i < N; i++) {
			 ((Agent) agents[i]).stop();
		}
	}
}
