package it.unipr.informatica.exam.esame_220607_09_conditionSet.es1.dynamicAttach;

/*
 * In questo esempio, si crea un'istanza di MyServiceImpl e si utilizza il metodo attach per 
 * creare una versione decoupled di questa istanza.
 * Successivamente si effettuano chiamate al metodo doSomething dell'originale 
 * e della versione decoupled.
 * In questo modo il metodo doSomething dell'originale sarà eseguito normalmente mentre quello 
 * decoupled sarà eseguito in un thread dedicato e il valore di ritorno sarà restituito 
 * sincronamente.
 */

public class Main {
	public static void main(String[] args) {
		
		MyService myService = new MyServiceImpl();
		MyService decoupledService = (MyService) new DecoupledAspect<>(myService).attach();

		// chiamata al metodo del servizio originale
		myService.doSomething();

		// chiamata al metodo del servizio decoupled
		decoupledService.doSomething();
		
		DecoupledAspect.shutdown();		
	}
}

interface MyService {
	void doSomething();
}

class MyServiceImpl implements MyService {
	@Override
	public void doSomething() {
		System.out.println("doSomething()");
	}
}
