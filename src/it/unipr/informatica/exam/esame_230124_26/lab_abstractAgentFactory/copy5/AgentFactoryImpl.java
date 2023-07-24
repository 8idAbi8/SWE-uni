package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy5;

/* questa classe implementa la abstract factory, ed è un singleton.*/

public class AgentFactoryImpl implements AgentFactory {

	public Object[] agents;
	private int idCounter = 0;
//	private static final Object lock = new Object();

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

		synchronized (agents) {
			
			for (int i = 0; i < n; i++) {
				double state = Math.random() * 2 - 1;

				Agent agent = new AgentImpl(idCounter, state);

				agents[idCounter] = agent;

				idCounter++;

				new Thread(agent).start();
			}
		}
	}


	public Object[] getAgents() {
		return agents;
	}

	
	public int getActualSize() {
		synchronized (agents) { 
			return agents.length;
		}		
	}	
}



