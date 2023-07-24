package it.unipr.informatica.exam.esame_220208.v3;

public class Bimpl implements B {
	@Override
	public void mb1() {
		try {
			Thread.sleep((long) Math.random() * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " chiama mb1() e ritorna");
	}

	@Override
	public void mb2() {
		System.out.println(Thread.currentThread().getName() + " chiama mb2()");
		try {
			Thread.sleep((long) Math.random() * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + ": fine attesa mb2()");
		
	}
}
