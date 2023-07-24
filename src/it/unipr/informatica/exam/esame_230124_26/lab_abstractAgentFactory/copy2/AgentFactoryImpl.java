package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy2;

/* questa classe implementa la abstract factory, ed è un singleton.
 * Si usa un ReentrantLock per la sincronizzazione */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AgentFactoryImpl implements AgentFactory {

	public List<Agent> agents;

	// importante che parta da 0, altrimenti da problemi con i getter 
	private int idCounter = 0;

	private static final Lock lock = new ReentrantLock();

	private static AgentFactoryImpl instance;

	private AgentFactoryImpl() {
		agents = new ArrayList<>();
	}

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
	public Agent createAgent() {

		double state = Math.random() * 2 - 1;  // double fra -1 e 1

		Agent agent = new AgentImpl(idCounter, state);
				
		try {
			lock.lock();

			idCounter++;

			agents.add(agent);

			/* avvia un nuovo thread per eseguire il metodo run dell'agente appena creato, 
			 * permettendo all'agente di interagire con gli altri agenti in parallelo. */
			new Thread(agent).start();

		} finally {
			lock.unlock();
		}

		return agent;
	}


	@Override
	public Agent getAgent(int id) {
		/* sincronizzazione neccessaria, altrimenti da problemi dal metodo interact */

		try {
			lock.lock();

			for (Agent agent : agents) {

				//				System.out.println("agentid " + agent.getID() + " id " + id);
				if (agent.getID() == id) {
					return agent;
				}
			}
		} finally {
			lock.unlock();
		}

		return null;
	}


	public int getAgentCount() {
		// qui la sincronizzazione potrebbe essere non necessaria
		try {
			lock.lock();
			return agents.size();
		} finally {
			lock.unlock();
		}
	}
}



