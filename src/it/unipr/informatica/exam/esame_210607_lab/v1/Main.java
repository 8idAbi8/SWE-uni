package it.unipr.informatica.exam.esame_210607_lab.v1;
 
public class Main {

	public static void main(String[] args) {
		int n = 20;		// nr thread (agents)
		int k = 50;		// nr chiamate
		
		AgentManagerImpl agentManager = AgentManagerImpl.getInstance();
		
		double averageValue = agentManager.startAgentsAndWait(n, k);
		
		try { // aspetto che finiscano le stampe dei thread
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("\naverageValue: " + averageValue);
	}
}
