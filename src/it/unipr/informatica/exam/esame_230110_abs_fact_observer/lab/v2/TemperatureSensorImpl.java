package it.unipr.informatica.exam.esame_230110_abs_fact_observer.lab.v2;

import java.util.ArrayList;
import java.util.List;
//import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// TemperatureSensorImpl è il ConcreteSubject
public class TemperatureSensorImpl implements TemperatureSensor {

	private static final AtomicInteger nextID = new AtomicInteger(1);
	private final int id;
	private double temperature;
	private final List<TemperatureObserver> observers;

	private boolean running;
	private Object mutex;
	Thread t;


	public TemperatureSensorImpl() {
		this.id = nextID.getAndIncrement();
		this.observers = new ArrayList<>();
		
		running = false;
		mutex = new Object();
	}


	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public double getTemperature() {
		return this.temperature;
	}

	@Override
	public void start() {
		running = true;
		
		t = new Thread(() -> {
			while(running) {

				// read the temperature
				this.temperature = readTemperature();

				// notify all the observers
				notifyObservers();

				// sleep for one second
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					System.err.println("attesa interrota");
				}
			}
		});
		t.start();
	}

	private double readTemperature() {
		//simulate temperature reading
		return Math.random() * 100;
	}

	public void stop() {
		synchronized (mutex) {
			running = false;
			t.interrupt();
		}		
	}

	@Override
	public void attach(TemperatureObserver o) {
		this.observers.add(o);
	}

	@Override
	public void detach(TemperatureObserver o) {
		this.observers.remove(o);		
	}

	private void notifyObservers() {
		for (TemperatureObserver observer : this.observers) {
			observer.update(this);
		}
	}

}
