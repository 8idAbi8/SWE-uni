package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy2;

public class Main {

	public static void main(String[] args) {

		int N = 10;
		
		AgentFactoryImpl istanza = AgentFactoryImpl.getInstance();
		
		for (int i = 0; i < N; i++) {
			istanza.createAgent();
		}
		
		
		try {
			System.out.println("sleep for 2sec");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < N; i++) {
			System.out.println("Agent " + i + " state: " + AgentFactoryImpl.getInstance().getAgent(i).getState());
		}
		
		for (int i = 0; i < N; i++) {
			AgentFactoryImpl.getInstance().getAgent(i).stop();
		}
	}
}
