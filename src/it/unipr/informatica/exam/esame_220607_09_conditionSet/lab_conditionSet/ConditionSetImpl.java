package it.unipr.informatica.exam.esame_220607_09_conditionSet.lab_conditionSet;

import java.util.ArrayList;
import java.util.List;

public class ConditionSetImpl implements ConditionSet {

	private List<Object> objects;
	private final Object mutex;

	public ConditionSetImpl() {
		this.mutex = new Object();
		objects = new ArrayList<>();
	}

	@Override
	public void add(Object object) {
		synchronized (mutex) {
			objects.add(object);
		}
	}

	@Override
	public  Object await() throws InterruptedException {			
		synchronized (mutex) {			
			while (!objects.isEmpty()) {
				mutex.wait();
			}
				return Thread.currentThread().getName();
		}		
	}
}




