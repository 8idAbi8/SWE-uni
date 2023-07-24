package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.es2;

// m1 e m2 devono essere eseguiti in mutua esclusione
// m2 e m3 devono essere eseguiti in mutua esclusione

public class AImpl {  

	private Object m1m2;
	private Object m2m3;
	
	
	public AImpl() {
		m1m2 = new Object();
		m2m3 = new Object();		
	}
	
	
	private void go() {		
		
		for(int i = 0; i < 3; ++i) {
			Thread thread = new Thread(this::stampa); 
			thread.start();
		}		
	}
	
	
	// private inner class
	private void stampa() {
		
//		Thread current = Thread.currentThread();
//		String name = current.getName();
		
		for(int i = 1; i < 4; ++i)
			if(i==1)			m1();
			else if(i == 2)		m2();
			else 				m3();
		
   /*commenta il for sopra e scommenta questo sotto per vedere l'effetto della synchronized */
//		for(int i = 1; i < 4; ++i)
//			System.out.println(name);
		
	}
	

//	@Override
	public void m1() {
		synchronized (m1m2) {
			System.out.println("m1");
		}
	}
	
//	@Override
	public void m2() {
		synchronized (m1m2) {
			synchronized (m2m3) {
				System.out.println("m2");
			}			
		}
	}
	
//	@Override
	public void m3() {
		synchronized (m2m3) {
			System.out.println("m3");
		}	
	}

	
	public static void main(String[] args) {
		new AImpl().go();
	}
	
}
