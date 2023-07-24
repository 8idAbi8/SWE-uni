package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.lab.v4;

/* "Attesa su piu condizioni": chiamiamo await() su tutti i monitor aggiunti al MonitorSet.
 *  In un problema di "Attesa su piu condizioni", ovviamente non possiamo fare tutto 
 *  con un singolo thread, poiche la prima await() non ci permetterebbe piu di aggiungere
 *  altri oggetti al monitorSet...
 *  
 *  Dobbiamo fare dei thread per ogni await(). Quando almeno uno dei thread si sblocca,
 *  verra sbloccata l'await() del monitor...
 *  */

public class Main {

	public static void main(String[] args) {

		Launcher launcher = new LauncherImpl();

		MonitorSet monitorSet = new MonitorSetImpl();
		
		int n = 5;
		
		for (int i = 0; i < n; i++) {
			Monitor monitor = launcher.launch(Main::work);
			monitorSet.add(monitor);	// ci mettiamo in await() su monitor, all'interno di add()
		}
	}

	private static void work() {
		int k = (int) Math.ceil(Math.random() * 100);
		
		try {
			Thread.sleep(1000 + 10 * k);
			System.out.println(Thread.currentThread().getName() + " -> k: " + k);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
