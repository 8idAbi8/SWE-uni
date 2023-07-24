package it.unipr.informatica.exam.esame_230110_abs_fact_observer.lab.v1;

// TemperatureSensor è un Abstract Product per il pattern AF
// TemperatureSensor rappresenta l'interfaccia Subject per il pattern Observer
public interface TemperatureSensor {
	public int getID(); 
	public double getTemperature(); 
	public void start(); 
	public void stop(); 
	public void attach(TemperatureObserver o); 
	public void detach(TemperatureObserver o);
}
