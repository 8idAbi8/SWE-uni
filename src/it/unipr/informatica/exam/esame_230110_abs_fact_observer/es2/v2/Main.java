package it.unipr.informatica.exam.esame_230110_abs_fact_observer.es2.v2;


public class Main {
	public static  void main(String[] args) {
		
		A a = new AImpl();
		
		Callback<Integer> callback = new Callback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
				System.out.println("Random number: " + result);
			}
		};
		
		a.m(500, 1000, callback);
		
//	si puo scrivere anche cosi		
//		   a.m(500, 1000, (Integer result) -> {
//		        System.out.println("Random number: " + result);
//		    });
//		}
		
	}
}
