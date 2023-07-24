package it.unipr.informatica.exam.esame_230207.es3.commands;

import it.unipr.informatica.exam.esame_230207.es3.reciver.Stack;

public class PushCommand implements Command {
	
	private Stack stack;
	private Object value;
	
	// viene passato il Reciver
	public PushCommand(Stack stack, Object value) {
		this.stack = stack;
		this.value = value;
	}
	
	public void execute() {
		stack.push(value);
		System.out.println("PushCommand::execute(): aggungiamo un elemento: " + value);
	}
	
	public void undo() {
		Object result = stack.pop();
		System.out.println("PushCommand::undo(): rimuoviamo un elemento: " + result);		
	}
}
