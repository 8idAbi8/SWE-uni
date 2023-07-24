package it.unipr.informatica.exam.esame_230207.lab.v1;

import java.util.List;

/* Questa concrete factory è anche una Singleton */

public class ActorResourceFactory implements AbstractFactory {
	
	private static ActorResourceFactory factory;
	
	private ActorResourceFactory() {
		// blank
	}
	
	public static ActorResourceFactory getInstance() {
		if (factory == null) {
			synchronized (ActorResourceFactory.class) {
				if (factory == null)
					factory = new ActorResourceFactory();
			}
		}		
		return factory;
	}
	
	@Override
	public Actor createActor(int id, List<Actor> allActors, List<Resource> allResources) {
		return new Actor(id, allActors, allResources);
	}

	@Override
	public Resource createResource() {
		return new ResourceImpl();
	}	
}