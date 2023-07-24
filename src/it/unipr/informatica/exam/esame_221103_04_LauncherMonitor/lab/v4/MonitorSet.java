package it.unipr.informatica.exam.esame_221103_04_LauncherMonitor.lab.v4;

// definisce un set di monitor
public interface MonitorSet {
	public boolean add(Monitor m);
	
	public void await() throws InterruptedException;
}
