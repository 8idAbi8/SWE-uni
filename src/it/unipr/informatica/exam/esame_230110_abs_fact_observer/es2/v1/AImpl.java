package it.unipr.informatica.exam.esame_230110_abs_fact_observer.es2.v1;

import java.util.concurrent.ThreadLocalRandom;

public class AImpl implements A {

	private final Object lock = new Object();

	/*La synchronized viene usata nella classe AImpl per assicurare che il codice delle 
	* callback venga eseguito in mutua esclusione. Ciò significa che un thread non può 
	* interferire con un altro thread mentre esegue una callback.Ciò assicura che non ci 
	* siano conflitti tra i thread e che l'esecuzione del codice sia eseguita correttamente.*/

	/*sarebbe meglio usare un oggetto dedicato per la sincronizzazione invece che su this, 
	* poiché questo potrebbe contenere variabili non thread-safe. Utilizzando un oggetto 
	* dedicato, è possibile assicurarsi che non ci siano collisioni tra le variabili del 
	* thread quando viene eseguito il codice del metodo m. Inoltre, utilizzare un oggetto 
	* dedicato per la sincronizzazione aiuta a mantenere le variabili thread-safe, in quanto 
	* tutti i thread che tentano di accedere all'oggetto sincronizzato verranno bloccati 
	* fino a quando non verrà rilasciato.*/	

	@Override
	public void m(int min, int max, Callback<Integer> callback) {
		
		new Thread(() -> {
			synchronized (lock) {
				int randomNumber = ThreadLocalRandom.current().nextInt(min, max);
				
				try {
					System.out.println(Thread.currentThread().getName() + " sleeps for ms: " + randomNumber);
					Thread.sleep(randomNumber);
				} catch (InterruptedException e) {
					callback.onFailure(e);
				}
				
				callback.onSuccess(randomNumber);
			}
		}).start();
	}
}
