package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.es2;

// l'esercizio richesto
// m1 e m2 devono essere eseguiti in mutua esclusione
// m2 e m3 devono essere eseguiti in mutua esclusione


public class A_Impl implements A {

	private Object lock_m1m2;
	private Object lock_m2m3;	
	
	public A_Impl() {
		lock_m1m2 = new Object();
		lock_m2m3 = new Object();		
	}	
	
	@Override
	public void m1() {
		synchronized (lock_m1m2) {
			System.out.println("m1 acquired the lock m1m2");
		}
	}
	
	@Override
	public void m2() {
		synchronized (lock_m1m2) {
			System.out.println("m2 acquired the lock m1m2");
			synchronized (lock_m2m3) {
				System.out.println("m2 acquired the lock m2m3");
			}			
		}
	}
	
	@Override
	public void m3() {
		synchronized (lock_m2m3) {
			System.out.println("m3 acquired the lock m2m3");
		}	
	}
}
