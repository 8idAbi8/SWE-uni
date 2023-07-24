package it.unipr.informatica.exam.esame_230110_abs_fact_observer.es2.v1;


public class Main {
	public static  void main(String[] args) {
		
		A a = new AImpl();
		
		Callback<Integer> callback = new Callback<>() {			
			@Override
			public void onSuccess(Integer result) {
				System.out.println("Random number: " + result);
			}
		};
		
			
		for (int i = 0; i < 5; i++) {
			a.m(500, 1000, callback);
//			System.out.println(Thread.currentThread().getName() + " calls A::m()");
//			System.out.println(i);
		}

//	si puo scrivere anche cosi		
//		   a.m(500, 1000, (Integer result) -> {
//		        System.out.println("Random number: " + result);
//		    });
//		}
		
//		System.out.println(Thread.currentThread().getName() + " fine");
	}

}
