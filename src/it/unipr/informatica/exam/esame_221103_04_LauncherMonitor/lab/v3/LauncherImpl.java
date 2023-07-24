package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.lab.v3;

public class LauncherImpl implements Launcher {

	@Override
	public Monitor launch(Runnable r) {

		MonitorImpl m = new MonitorImpl();
		
		// attiva un thread per ogni chiamata
		new Thread(() -> {
			try {
				System.out.println("parte run()");
				r.run();

				System.out.println("finito run(), sblocco monitor");			
				m.unlock();

			} catch (Throwable th) {	// la run() puo lanciare eccezioni unchecked
				th.printStackTrace();
			}
		}).start();

		System.out.println(Thread.currentThread().getName() + " ritorna \"immediatamente\" un monitor");

		return m;	// ritorna "immediatamente" un oggetto di tipo monitor
	}
}
