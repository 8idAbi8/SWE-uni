package it.unipr.informatica.exam.esame_230124_26.lab_abstractAgentFactory.copy5;

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
		
		running = true;
		
		Object[] agents = AgentFactoryImpl.getInstance().getAgents();

		while(running) {	
			
			try {
//				int randomAgent = (int)(Math.random() * AgentFactoryImpl.getInstance().getAgentCount());
//				int randomAgent = new Random().nextInt(AgentFactoryImpl.getInstance().getAgentCount());
				int randomAgentIdx = new Random().nextInt(AgentFactoryImpl.getInstance().getActualSize());
//				System.out.println(AgentFactoryImpl.getInstance().getActualSize());
				Object agent = agents[randomAgentIdx];
				
				interact(((AgentImpl) agent).getState());

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
