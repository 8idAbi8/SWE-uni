/**
 * 
 */
package it.unipr.informatica.exam.esame_230207.lab.v1;

import java.util.List;

public interface AbstractFactory {
	public Actor createActor(int id, List<Actor> allActors, List<Resource> allResources);
	public Resource createResource();
}
