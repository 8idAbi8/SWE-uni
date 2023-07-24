package it.unipr.informatica.exam.esame_230207.es3.client;

import it.unipr.informatica.exam.esame_230207.es3.commands.PopCommand;
import it.unipr.informatica.exam.esame_230207.es3.commands.PushCommand;
import it.unipr.informatica.exam.esame_230207.es3.exception.EmptyStackException;
import it.unipr.informatica.exam.esame_230207.es3.invoker.StackCommandInvoker;
import it.unipr.informatica.exam.esame_230207.es3.reciver.SempliceStack;
import it.unipr.informatica.exam.esame_230207.es3.reciver.Stack;

public class Main {

	public static void main(String[] args) {

		// invoker
		StackCommandInvoker invoker = new StackCommandInvoker();
		
		// reciver
		Stack stack = new SempliceStack();

		// object command
		PushCommand pushCommand1 = new PushCommand(stack, "Elemento 1");
		PushCommand pushCommand2 = new PushCommand(stack, "Elemento 2");
		PushCommand pushCommand3 = new PushCommand(stack, "Elemento 3");
		
		PopCommand popCommand;
		
		// execute()
		invoker.executeCommand(pushCommand1);
		invoker.executeCommand(pushCommand2);
		invoker.executeCommand(pushCommand3);
		
		
		// rimuovo un elemento dallo stack
		try {
			//Object popped = stack.pop();			
			
			// nel momento in cui si crea l'oggetto commando Pop, si fa anche la pop() sullo stack
			// percui l'execute() è gia stata fatta, e il metodo executeCommand non fa niente
			invoker.executeCommand(popCommand = new PopCommand(stack));  
			System.out.println("Elemento rimosso: " + popCommand.value); // popped);
		} catch (EmptyStackException e) {
			System.out.println("Lo stack è vuoto!");
		}

		// rimuovo tutti gli elementi dallo stack
		while (!(stack.size() == 0)) {
//			Object popped = stack.pop();
//			System.out.println("Elemento rimosso: " + popped);
			
			invoker.executeCommand(popCommand = new PopCommand(stack));  
			System.out.println("Elemento rimosso: " + popCommand.value);
		}

		// Prova a rimuovere un elemento da uno stack vuoto
		// eccezione
		try {
			invoker.executeCommand(popCommand = new PopCommand(stack));  
			System.out.println("Elemento rimosso: " + popCommand.value);
		} catch (EmptyStackException e) {
			System.err.println("Lo stack e' vuoto!");
		}
		
		invoker.undoLastCommand(); // aggiungiamo 1
		invoker.undoLastCommand(); // aggiungiamo 2
		invoker.executeCommand(pushCommand3);	// aggiungiamo 3
		invoker.undoLastCommand(); // rimuoviamo 3
	}
}
