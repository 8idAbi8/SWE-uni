package it.unipr.informatica.exam.esame_230606.es2;

import it.unipr.informatica.exam.esame_230606.es2.versioniCorrette.AImpl4;

public class Test {	
	
	public static void main(String[] args) {
		
		A a = new AImpl4();

        Object obj1 = new String("Hello");
        Object obj2 = "World";

        Thread thread1 = a.m1(obj1);
        Thread thread2 = a.m1(obj2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Main: Fine");
	}
}
