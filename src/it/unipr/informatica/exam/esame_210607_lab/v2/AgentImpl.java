package it.unipr.informatica.exam.esame_210607_lab.v2;

public class AgentImpl extends Thread implements Agent {

	private volatile double value;
	public static volatile int updateCounter = 0;
	public static volatile double averageValue;
	public static volatile double sumValues = 0;
	private Object mutex;	

	
	public AgentImpl(Object mutex) {
		this.value = Math.random();
		this.mutex = mutex;
	}

	@Override
	public double getValue() {
		synchronized (mutex) {
			return value;
		}
	}

	@Override
	public double update(double value) {			
		synchronized (mutex) {			
			
			updateCounter++;			
			this.value = (this.value + value)/2;	
			
			sumValues += value;
			averageValue = sumValues / (AgentManagerImpl.agentsCount * AgentManagerImpl.totalCalls);

			if (updateCounter == AgentManagerImpl.totalCalls)
				mutex.notify();

			return value;
		}		
	}
	/*
	 * Aspetta 100+r millisecondi, con r numero casuale in [0..50].
	 * Chiama update(value) su un altro agente scelto casualmente e sostituisce value 
	 * con il valore di ritorno di update(value).
	 * */
	@Override
	public void run() {		
		try {
			for(;;) { 
				int r = (int) (Math.random() * 50);

				try {
					Thread.sleep(100 + r);
				} catch (InterruptedException e) {
//					System.err.println(Thread.currentThread().getName() + " Interrupted");
					return;
				}
				
				Agent randomAgent = AgentManagerImpl.getInstance().getRandomAgent(this); // getRandomAgent()  // get a randoma agent
				
				if (randomAgent != null) {
					synchronized (mutex) {
						value = randomAgent.update(value);
//						update(value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
