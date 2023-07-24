package it.unipr.informatica.exam.esame_230124_26.scritto.v1;

public interface Executor {
	public void execute(Object[] mutexs, Runnable runnable);
}
