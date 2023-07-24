package it.unipr.informatica.exam.esame_230207.lab.v1;

import java.util.List;
import java.util.Random;

public class Actor {
	
	private static final Random random = new Random();

	private final int id;
	private final List<Actor> actors;
	private final List<Resource> resources;
	
	private final Thread workingThread;
	
	public Actor(int id, List<Actor> actors, List<Resource> resources) {
		
		this.id = id;
		this.actors = actors;
		this.resources = resources;
		
		/* current thread che esegue mainCycle */
		workingThread = new Thread(this::mainCycle);
		workingThread.start();
	}
	
	public void deliver(Message message) {		
		if (message.getValue() <= 0) {
			System.err.println("message.getValue() <= 0");
			return;
		}
			
		
		System.out.println("Actor n" + id + " Received message with value: " + message.getValue() + " about");
		synchronized (this) {
			getRandomActor().deliver(message.decremented());
		}
	}
	
	private Actor getRandomActor() {
		int randIdx;
		do {
			randIdx = random.nextInt(actors.size());
		} while (randIdx != id);

		return actors.get(randIdx);
	}

	public void stop() {
		workingThread.interrupt();
	}
	
	private void mainCycle() {
		
		final int maxResources = 10; 
		
		while (true) {
			//0..9 resources
			int resCount = random.nextInt(maxResources);
			
			List<Resource> myResources = resources.subList(id*maxResources, id*maxResources + resCount);
			int sum = 0; 
			System.out.println("Actor n" + id + " will use resources: (" + id*maxResources + ".." + (id*maxResources + resCount) + ")");
			
			try {
				for (Resource resource : myResources) {
					resource.acquire();
					sum += resource.use();
				}
				
				Thread.sleep(sum);
				
				myResources.forEach(Resource::release);
				
				getRandomActor().deliver(new Message(sum));
			}
			catch (Throwable e) {
				//If thread got interrupted -> return from the mainCycle
				if (e instanceof InterruptedException) {
					System.err.println("Thread nr" + id + " was terminated!");
					return;
				}
				else
					System.err.println("Thread n°" + id + " catched an exception while managing resource in: (" + id*maxResources + ".." + (id*maxResources+resCount) + ")");
			}
		}
	}
}
