package it.unipr.informatica.exam.esame_220208.v1;

public class Bimpl implements B {
	@Override
	public void mb1() {
		System.out.println(Thread.currentThread().getName() + " chiama mb1()");
	}

	@Override
	public void mb2() {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + ": fine attesa mb2()");
		
	}
}
