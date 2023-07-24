package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy1;

/* questa classe implementa la abstract factory, ed è un singleton.*/

public class AgentFactoryImpl implements AgentFactory {

	public Agent[] agents;
	private int idCounter = 0;
	private static final Object lock = new Object();

	private static AgentFactoryImpl instance;

	private AgentFactoryImpl() {}

	public static AgentFactoryImpl getInstance() {
		if(instance == null) {
			synchronized (AgentFactoryImpl.class) {
				if(instance == null)
					instance = new AgentFactoryImpl();
			}
		}
		return instance;			
	}


	@Override
	public void createAgent(int n) {
		
		agents = new AgentImpl[n];

		synchronized (lock) {
			
			// creazione di n agent e memorizzazione in agents
			for (int i = 0; i < n; i++) {
				double state = Math.random() * 2 - 1;
				
				Agent agent = new AgentImpl(idCounter, state);
				agents[idCounter] = agent;

				idCounter++;
			}
			
			// start degli agent
			for (int i = 0; i < n; i++) {
				new Thread((Runnable) agents[i]).start();
			}
		}
	}

	public Agent[] getAgents() {
		return agents;
	}
	
	public int getActualSize() {
		synchronized (lock) { 
			return agents.length;
		}		
	}	
}



