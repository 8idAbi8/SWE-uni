package it.unipr.informatica.exam.esame_230207.es2;

public class Main {

	public static void main(String[] args) {

		// Crea due task
		Task task1 = new Task() {

			@Override
			public Object run() {	        	
				try {	                	
					int k = (int) (Math.random() * 1000);
					System.out.println("task 1 sleep for " + k);
					Thread.sleep(k);						
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				return "Task 1";
			}
		};


		Task task2 = new Task() {

			@Override
			public Object run() {	        	
				try {	        		
					int k = (int) (Math.random() * 1000);
					System.out.println("task 2 sleep for " + k);
					Thread.sleep(k);					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				return "Task 2";
			}
		};

		
		try {
			// Esegue i task in modo concorrente
			Object result = ConcurrentRunner.execute(task1, task2);
			
			System.out.println("Il task che ha terminato per primo e': " + result);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
