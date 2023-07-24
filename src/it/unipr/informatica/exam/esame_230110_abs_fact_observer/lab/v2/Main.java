package it.unipr.informatica.exam.esame_230110_abs_fact_observer.lab.v2;


public class Main {

	public static void main(String[] args) {

		AbstractFactory factory = new TemperatureSensorFactory();

		// subject
		TemperatureSensor sensor = factory.createTemperatureSensor();  // return new TemperatureSensorImpl();

		// attach observers to the subject
		for (int i = 0; i < 10; i++) {
			sensor.attach(new TemperatureObserverImpl());
		}

		// sensor start to getting data, and updates his state
		// then notify the observers
		sensor.start(); 



		// wait one minute
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//stampa delle temperature dei sensori
		//        for (TemperatureSensor sensor : sensors) {
		//            System.out.println("Sensor " + sensor.getID() + ": " + sensor.getTemperature() + " degrees");
		//        }

		// stop all the sensor
		System.err.println("stop sensor..........");
		sensor.stop();
	}
}
