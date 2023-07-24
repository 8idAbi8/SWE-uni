package it.unipr.informatica.exam.esame_220208.v2;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		A a1 = new AImpl();
		B b = new Bimpl();

		a1.ma(b);

		//		System.out.println("back to main: le chiamate a mb1() sono terminate");
	}
}
