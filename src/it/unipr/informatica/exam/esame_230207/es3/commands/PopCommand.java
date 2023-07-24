package it.unipr.informatica.exam.esame_230207.es3.commands;

import it.unipr.informatica.exam.esame_230207.es3.reciver.Stack;

public class PopCommand implements Command {

	private Stack stack;	// Reciver
	public Object value;
	
	public PopCommand(Stack stack) {
		this.stack = stack;			
	}
	
	public void execute() {
		this.value = stack.pop(); // stack.size() - 1;
		System.out.println("PopCommand::execute(): rimuoviamo un elemento: " + value);
	}
	
	public void undo() {
		System.out.println("PopCommand::undo(): aggungiamo un elemento: " + value);
		stack.push(value);
	}
}
