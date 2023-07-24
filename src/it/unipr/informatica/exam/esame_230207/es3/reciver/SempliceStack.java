package it.unipr.informatica.exam.esame_230207.es3.reciver;

import java.util.*;
import it.unipr.informatica.exam.esame_230207.es3.exception.EmptyStackException;

// reciver, colui che esegue gli ordini
public class SempliceStack implements Stack {

	private Deque<Object> stack;

	@Override
	public String toString() {
		return "SempliceStack [stack=" + stack + "]";
	}

	public SempliceStack() {
		stack = new ArrayDeque<Object>();
	}

	public void push(Object value) {
		stack.push(value);
		System.out.println("SempliceStack::push, Size: " + stack.size());
	}

	public Object pop() throws EmptyStackException {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}

		System.out.println("SempliceStack::pop(), " + stack.size());
		return stack.pop();
	}

	@Override
	public int size() {
		return stack.size();
	}
}


