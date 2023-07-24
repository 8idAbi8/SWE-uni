package it.unipr.informatica.exam.esame_230110_abs_fact_observer.lab.v2;

import java.util.concurrent.atomic.AtomicInteger;

public class TemperatureObserverImpl implements TemperatureObserver {

	 private static AtomicInteger nextId = new AtomicInteger(1);	   
    private int id;
    
	public TemperatureObserverImpl() {
		this.id = nextId.getAndIncrement();
	}
	
	@Override
	public void update(TemperatureSensor s) {
		double temperature = s.getTemperature();
	    System.out.println("Temperature of obserrver-" + id + " is " + temperature);
	}

}
