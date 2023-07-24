package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.lab.v4;

public class LauncherImpl implements Launcher {
	
	@Override
	public Monitor launch(Runnable r) {
		
		MonitorImpl m = new MonitorImpl();
		
		new Thread(() -> {
			try {
				r.run();
				m.unlock();
			} catch (Throwable th) {	// la run() puo lanciare eccezioni unchecked
				th.printStackTrace();
			}
		}).start();

		return m;	// ritorna "immediatamente" un oggetto di tipo monitor
	}
}
