package it.unipr.informatica.exam.esame_230110_abs_fact_observer.lab.v2;

/* TemperatureSensor è un Abstract Product per il pattern AF;
 * TemperatureSensor rappresenta l'interfaccia Subject per il pattern Observer */
public interface AbstractFactory {
	public TemperatureSensor createTemperatureSensor();
}

