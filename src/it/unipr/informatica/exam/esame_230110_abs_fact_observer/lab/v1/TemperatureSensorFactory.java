package it.unipr.informatica.exam.esame_230110_abs_fact_observer.lab.v1;

/* TemperatureSensorFactory è una concrete factory che produce/crea 
 * l'unico (concrete) product (TemperatureSensor) della abstract factory
 * */

public class TemperatureSensorFactory implements AbstractFactory {	
	@Override
	public TemperatureSensor createTemperatureSensor() {
		return new TemperatureSensorImpl();
	}
}