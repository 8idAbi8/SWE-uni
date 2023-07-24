package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.lab.v2;

public class Main {

	public static void main(String[] args) {
		new Main().go();		
	}

	private void go() {

		Launcher l = new LauncherImpl();
		Monitor m = l.launch(this::foo);

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
