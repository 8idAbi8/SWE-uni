package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.lab.v3;

public class Main {

	public static void main(String[] args) {
		new Main().go();		
	}

	private void go() {

		Launcher l = new LauncherImpl();
		Monitor m = l.launch(this::foo);

		/* "m" viene usato per mettersi in attesa che il runnable venga eseguito, dopodiche
		 * quando il runnable ha terminato, (lo stesso) "m" all'interno del metodo launch, fa una notifyAll().
		 * 
		 * */
		try {
			System.out.println("monitor bloccato");
			m.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("monitor sblocatto. Esecuzione di foo() terminata");

	}

	public void foo() {
		System.out.println("foo() in esecuzione");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}
