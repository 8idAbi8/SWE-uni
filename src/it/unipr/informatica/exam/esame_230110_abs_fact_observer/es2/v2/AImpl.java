package it.unipr.informatica.exam.esame_230110_abs_fact_observer.es2.v2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class AImpl implements A {

/* l'utilizzo di un Executor è thread-safe e può aiutare a evitare di dover utilizzare la 
sincronizzazione. Gli Executor forniscono un meccanismo di sincronizzazione interna che può 
essere utilizzato per eseguire più thread in modo sicuro. 
A differenza della sincronizzazione con la keyword synchronized, l'utilizzo di un Executor 
consente agli sviluppatori di scrivere codice thread-safe senza dover utilizzare la 
sincronizzazione in ogni metodo.*/
	
	
	ExecutorService executorService = Executors.newSingleThreadExecutor();

	@Override
	public void m(int min, int max, Callback<Integer> callback) {
		
		executorService.submit(() -> {
			
			int randomNumber = ThreadLocalRandom.current().nextInt(min, max);

			try {
				Thread.sleep(randomNumber);
			} catch (InterruptedException e) {
				callback.onFailure(e);
			} 

			callback.onSuccess(randomNumber);
		});
		
		executorService.shutdown();
	}

}
