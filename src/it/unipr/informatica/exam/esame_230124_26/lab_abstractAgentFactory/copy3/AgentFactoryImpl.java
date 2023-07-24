package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy3;

/* questa classe implementa la abstract factory, ed è un singleton.*/

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AgentFactoryImpl implements AgentFactory {

	public AgentImpl[] agents;
	private int idCounter = 0;
	private final Lock lock = new ReentrantLock();

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
		// perche il risultato non viene usato
		AgentImpl agent = null;

		agents = new AgentImpl[n];

		try {
			lock.lock();

			for (int i = 0; i < n; i++) {
				double state = Math.random() * 2 - 1;

				agent = new AgentImpl(idCounter, state);

				agents[idCounter] = agent;

				idCounter++;

				new Thread(agent).start();
			}

		} finally {
			lock.unlock();
		}

		return agent;
	}


	public AgentImpl[] getAgents() {
		return agents;
	}

	
	public int getActualSize() {

		try {
			lock.lock();
			return agents.length;
		} finally {
			lock.unlock();
		}
	}		
}



