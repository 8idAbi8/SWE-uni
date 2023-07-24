package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy4;

/* questa classe implementa la abstract factory, ed è un singleton.*/


public class AgentFactoryImpl implements AgentFactory {

	// versione con Object lock
	public AgentImpl[] agents;
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
	public Agent createAgent(int n) {
		// il return type potrebbe essere cambiato in void
		AgentImpl agent = null;

		agents = new AgentImpl[n];

		synchronized (lock) {
			
			for (int i = 0; i < n; i++) {
				double state = Math.random() * 2 - 1;

				agent = new AgentImpl(idCounter, state);

				agents[idCounter] = agent;

				idCounter++;

				new Thread(agent).start();
			}
		}
		return agent;
	}


	public AgentImpl[] getAgents() {
		return agents;
	}

	
	public int getActualSize() {
		synchronized (lock) { 
			return agents.length;
		}		
	}
	
}



