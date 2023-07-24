package it.unipr.informatica.exam.esame_230110_abs_fact_observer.lab.v1;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		// create 10 temperature sensors
		AbstractFactory factory = new TemperatureSensorFactory();
		List<TemperatureSensor> sensors = new ArrayList<>();

		// create sensors
		for (int i = 0; i < 10; i++) {
			TemperatureSensor sensor = factory.createTemperatureSensor();  // return new TemperatureSensorImpl();
			sensors.add(sensor);
			//			sensor.start(); 
			//			System.out.println("Main_Temperature of sensor "+sensor.getID()+" is "+sensor.getTemperature());
		}

		// create observer
		TemperatureObserver observer = new TemperatureObserverImpl();
		
		// attach observer to sensors
        for (TemperatureSensor sensor : sensors) {
            sensor.attach(observer);
        }


		// start sensors
		for (TemperatureSensor sensor : sensors) {
			sensor.start();
		}


		// wait one minute
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//stampa delle temperature dei sensori
//        for (TemperatureSensor sensor : sensors) {
//            System.out.println("Sensor " + sensor.getID() + ": " + sensor.getTemperature() + " degrees");
//        }

		// stop all the sensors
		sensors.forEach(TemperatureSensor::stop);
	}

}
