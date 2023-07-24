package it.unipr.informatica.exam.esame_230207.es3.reciver;

import java.util.ArrayDeque;
import java.util.Deque;

import it.unipr.informatica.exam.esame_230207.es3.exception.EmptyStackException;

/* classe non funzionante...  */
public class SimpleStack implements Stack {

	@Override
	public String toString() {
		return "SimpleStack [stack=" + stack + "]";
	}

	//	private List<Object> stack;
	private Deque<Object> stack;
	
	// private Command undoCommand;

	private Object mutex;

	public SimpleStack() {
		stack = new ArrayDeque<>(); 	
//		undoCommand = new noCommand();
		mutex = new Object();
	}

	public void push(Object value) {
		
		synchronized (mutex) {
			
			stack.push(value);  // stack.add(value);

//			undoCommand = new PushCommand(stack, value);

			// se è il primo elemento, sveglio un consumatore
			if (stack.size() == 1) {
				stack.notify();
			}
		}
	}

	
	public Object pop() throws EmptyStackException {
		synchronized (mutex) {
			// se non ce nessun elemento, il consumatore va in wait
			if (stack.isEmpty()) {  // (stack.size() == 0)
				try {
					stack.wait();
				} catch (InterruptedException | EmptyStackException e) {
					e.printStackTrace();
				}
			}

			// toglie e ritorna l'ultimo elemento dell'array, 
			// ovvero l'elemento sulla testa della pila
			Object result = stack.pop();  //.remove(stack.size() - 1);

			// se c'e almeno 1 elemento... allorra.. 
			// il consumatore corrente dopo aver consumato un elemento,
			// sveglia un altro consumatore
			if (stack.size() > 0) 
				stack.notify();

			return result;
		}
	}

	@Override
	public int size() {
		return stack.size();
	}	
}
