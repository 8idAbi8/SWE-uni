package it.unipr.informatica.exam.esame_230110_abs_fact_observer.lab.v1;

public class TemperatureObserverImpl implements TemperatureObserver {

	@Override
	public void update(TemperatureSensor s) {
		double temperature = s.getTemperature();
	    System.out.println("Temperature of sensor " + s.getID() + " is " + temperature);
	}

}
