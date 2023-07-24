package it.unipr.informatica.exam.esame_220607_09_conditionSet.es2;

public class Main {

	/* un esempio di come potrebbe essere implementato un metodo main che effettua 
	 * 2 chiamate a ma() in modo concorrente utilizzando la classe Thread:
	 */
	public static void main(String[] args) throws InterruptedException {

		System.out.println(Thread.currentThread().getName() + ": started");

		A a = new AImpl();			

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + 
							": chiama ma()");
					a.ma();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		//		    Thread t2 = new Thread(new Runnable() {
		//		        public void run() {
		//		            try {
		//		                a.ma();
		//		            } catch (InterruptedException e) {
		//		                e.printStackTrace();
		//		            }
		//		        }
		//		    });

		t1.start();
		//		    t2.start();
		t1.join();
		//		    t2.join();

		System.out.println(Thread.currentThread().getName() + ": finished");
	}
}
