package it.unipr.informatica.exam.esame_230207.es3.reciver;

import it.unipr.informatica.exam.esame_230207.es3.exception.EmptyStackException;

public interface Stack {
	public void push(Object value);
	public Object pop() throws EmptyStackException;
	public int size();
}
