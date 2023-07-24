package it.unipr.informatica.exam.esame_210607_lab.v2;


public class AgentManagerImpl implements AgentManager {

	public static int agentsCount;  // static per essere visto da AgentImpl
	public static int totalCalls; 
	private AgentImpl[] agents;

	private static final Object mutex = new Object();

	private static volatile AgentManagerImpl instance;

	private AgentManagerImpl() {
		// to prevent instantiating by Reflection call
		if (instance != null)
			throw new IllegalStateException("Already initialized.");
	}

	public static AgentManagerImpl getInstance() {
		if (instance == null) {
			synchronized (AgentManagerImpl.class) {
				if (instance == null)
					instance = new AgentManagerImpl();
			}
		}
		return instance;		
	}

	/*
	 * una volta fatte K chiamate al metodo update(double), gli agenti terminano e la 
	 * chiamata startAgentsAndWait(N, K) ritorna la media dei valori delle proprietà 
	 * value degli agenti.
	 * 
	 * Si noti che il metodo startAgentsAndWait(int, int) rimane in attesa se lo agent 
	 * manager è già in attesa che un gruppo di agenti termini.
	 * 
	 * In più, si noti che il metodo startAgentsAndWait(int, int) ritorna immediatamente 
	 * -1 se almeno uno dei thread degli agenti viene interrotto prima che vengano 
	 * effettuate tutte le chiamate a update(double).
	 * */
	@Override
	public double startAgentsAndWait(int n, int k) {

		agentsCount = n;
		totalCalls = k;

		agents = new AgentImpl[n];

		for (int i = 0; i < n; i++) {
			agents[i] = new AgentImpl(mutex);
			agents[i].start();
		}

		System.out.println("creati gli agenti");		

		synchronized (mutex) {
			while (AgentImpl.updateCounter < k) {
				try {
					System.out.println("entro in wait");
					mutex.wait(); 
					System.out.println("esco da wait");
				} catch (InterruptedException e) {
					// Thread interrotto
					return -1;
				}  
			} 
		}

		// Terminazione degli agenti
		for (AgentImpl agent : agents) {
			agent.interrupt();
		}

		// Reset degli agenti
		agents = null;
		agentsCount = 0;

		return AgentImpl.averageValue; 
	}


	public Agent getRandomAgent(Agent requestingAgent) {
		synchronized (mutex) {
			if (agentsCount <= 0)
				return null;

			int randomIndex = (int) (Math.random() * AgentManagerImpl.agentsCount);

			while (agents[randomIndex] == requestingAgent) {
				randomIndex = (int) (Math.random() * AgentManagerImpl.agentsCount);
			}

			return agents[randomIndex];
		}		
	}
}
