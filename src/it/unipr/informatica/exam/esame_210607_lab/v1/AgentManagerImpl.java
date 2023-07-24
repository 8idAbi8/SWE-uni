package it.unipr.informatica.exam.esame_210607_lab.v1;

public class AgentManagerImpl implements AgentManager {

	private int agentsCount;  	  // nr thread (agents), n
	private int totalCalls;		  // nr chiamate, k
	
	private InnerAgent[] agents;
	private final Object mutex;

	private static volatile AgentManagerImpl instance;

	private AgentManagerImpl() {
		// to prevent instantiating by Reflection call
		if (instance != null)
			throw new IllegalStateException("Already initialized.");

		mutex = new Object();
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

		agents = new InnerAgent[n];

		System.out.println(Thread.currentThread().getName() + " starting " + n + " agents");
		for (int i = 0; i < n; i++) {
			agents[i] = new InnerAgent();
			agents[i].start();
		}		
		System.out.println(agents.length + " agenti partiti");

		synchronized (mutex) {
			
			while(InnerAgent.updateCounter < totalCalls) {

				try {
					System.out.println(Thread.currentThread().getName() + " va in wait finche' updateCounter == totalCalls");
					mutex.wait();			
					
//					System.out.println(Thread.currentThread().getName() + " e' stato notificato da InnerAgent.update()");
//					System.out.println("updateCounter:" + InnerAgent.updateCounter + " - totalCalls:" + totalCalls);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		// Terminazione degli agenti
		System.out.println("Terminazione degli agenti");
		for (InnerAgent agent : agents) {
			agent.interrupt();
		}

		// Reset degli agenti
		agents = null;
		agentsCount = 0;		

		return InnerAgent.averageValue; 
	}

	/* L'oggetto di sincronizzazione utilizzato nel metodo synchronized è 
	 * l'oggetto stesso sul quale il metodo è invocato. -->  synchronized(this) */
	public synchronized Agent getRandomAgent(Agent requestingAgent) {  
		if (agentsCount <= 0)
			return null;

		int randomIndex = (int) (Math.random() * agentsCount);

		while (agents[randomIndex] == requestingAgent) {
			randomIndex = (int) (Math.random() * agentsCount);
		}
		
		return agents[randomIndex];
	}


	private class InnerAgent extends Thread implements Agent {

		private double value;
		private double sumValues = 0;
		private static double averageValue;		
		private static volatile int updateCounter;

		private InnerAgent() {
			this.value = Math.random();
		}

		@Override
		public double getValue() {
			synchronized (agents) {
				return value;
			}
		}

		@Override
		public double update(double value) {	
			synchronized (mutex) {
									
				updateCounter++;
				
				this.value = (this.value + value)/2;
				sumValues += this.value;
				averageValue = sumValues / (agentsCount * totalCalls);
				
				/* quando updateCounter == totalCalls notifichiamo while(InnerAgent.updateCounter < totalCalls) di
				   startAgentsAndWait(n, k) (main thread) , in modo che verifichi la condizione e prosegui l'esecuzione.
				*/
				if(updateCounter == totalCalls)
					mutex.notify();  

				return value;
			}
		}

		@Override
		public void run() {
			try {
				while(!Thread.interrupted()) {  // va bene anche for(;;) o while(true)
					
					int r = (int) (Math.random() * 50);

					try {
						Thread.sleep(100 + r);
					} catch (InterruptedException e) {
//						System.err.println(Thread.currentThread().getName() + " interrupted");
						return;
					}

					Agent randomAgent = AgentManagerImpl.getInstance().getRandomAgent(this); // getRandomAgent()  // get a randoma agent
					
					if (randomAgent != null) {
						synchronized (mutex) {
							value = randomAgent.update(value);							
//							System.out.println(randomAgent.getValue()); 		
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
}