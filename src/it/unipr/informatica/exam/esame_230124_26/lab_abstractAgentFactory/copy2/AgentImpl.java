package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy2;

import java.util.Random;

public class AgentImpl implements Agent {

	private final int id;
	private double state;
	private boolean running = false;

	public AgentImpl(int id, double state) {
		this.id = id;
		this.state = state;
	}

	@Override
	public void run() {
		/*
		 * Questo codice rappresenta il metodo run() della classe AgentImpl che estende 
		 * l'interfaccia Runnable. Il metodo run() esegue un ciclo infinito in cui 
		 * all'interno del ciclo:
		 * Viene generato un numero intero casuale (randomAgent) che rappresenta l'indice 
		 * di un agente presente nell'abstract factory AgentFactory.
		 * Viene chiamato il metodo interact() passando come argomento lo stato dell'agente 
		 * casualmente selezionato.
		 * Viene effettuata una pausa di 2 millisecondi prima di continuare il ciclo 
		 * utilizzando il metodo Thread.sleep(2).
		 * In caso di eccezioni InterruptedException o AgentException, vengono stampate le 
		 * informazioni dell'eccezione utilizzando il metodo e.printStackTrace();
		 * 
		 * In sostanza, questo codice simula un comportamento di interazione tra agenti, 
		 * in cui ogni agente seleziona un altro agente a caso e interagisce con esso, attende 2 millisecondi e ripete questo processo all'infinito.
		 * */
		
		running = true;

		while(running) {
			
			
			try {
				/*
				// sostituzioni per aumentare la comprensione
				AgentFactoryImpl agentFactory = AgentFactoryImpl.getInstance();
				int agentsSize = agentFactory.getAgentCount();
				int randomAgentIdx = new Random().nextInt(agentsSize);
				Agent randomAgent = agentFactory.getAgent(randomAgentIdx);
				
				interact(randomAgent.getState());
				 * */
				
				int randomAgent = new Random().nextInt(AgentFactoryImpl.getInstance().getAgentCount());
				
				interact(AgentFactoryImpl.getInstance().getAgent(randomAgent).getState());

				Thread.sleep(2);
			} catch (InterruptedException | AgentException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public double interact(double state) throws AgentException {
		double previousState = this.state;
		this.state = this.state + 0.5 * (state - this.state);
		return previousState;
	}

	@Override
	public void stop() {
		running = false;
		Thread.currentThread().interrupt();
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public double getState() {
		return state;
	}
}
