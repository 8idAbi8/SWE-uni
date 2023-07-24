package it.unipr.informatica.exam.esame_230110_abs_fact_observer.lab.v1;

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
	private final ExecutorService executorService;
	private final List<TemperatureObserver> observers;
	private boolean running;


	public TemperatureSensorImpl() {
		this.id = nextID.getAndIncrement();
		this.observers = new ArrayList<>();
		this.executorService = Executors.newFixedThreadPool(10);
		this.running = false;
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

		/*
		Runnable task = () -> {
            while (running) {
                // simulate temperature reading
                temperature = Math.random() * 100;
                notifyObservers();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        executorService.submit(task);
		 */


		// start a thread that reads the temperature
		executorService.submit(() -> {
			while (running) {
				// read the temperature
				this.temperature = readTemperature();

				// notify all the observers
				notifyObservers();

				// sleep for one second
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					// handle interrupted exception
				}
			}
		});

	}


	private double readTemperature() {
		//simulate temperature reading
		return Math.random() * 100;
	}


	@Override
	public void stop() {
		running = false;
		this.executorService.shutdownNow();
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
			//executorService.submit(() -> observer.update(this));
		}
	}

}
