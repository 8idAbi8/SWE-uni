package it.unipr.informatica.exam.esame_230207.es3.client;

import it.unipr.informatica.exam.esame_230207.es3.commands.Command;
import it.unipr.informatica.exam.esame_230207.es3.commands.PopCommand;
import it.unipr.informatica.exam.esame_230207.es3.commands.PushCommand;
import it.unipr.informatica.exam.esame_230207.es3.exception.EmptyStackException;
import it.unipr.informatica.exam.esame_230207.es3.invoker.StackCommandInvoker;
import it.unipr.informatica.exam.esame_230207.es3.reciver.SempliceStack;
import it.unipr.informatica.exam.esame_230207.es3.reciver.Stack;

public class ClientTest {
	
	public static void main(String[] args) {

		// invoker
		StackCommandInvoker invoker = new StackCommandInvoker();

		// reciver
		Stack stack = new SempliceStack();
		
		// comando PushCommand per inserire un valore nello stack
		Command pushCommand = new PushCommand(stack, "Java");

		// Esegui il comando
		invoker.executeCommand(pushCommand);   //pushCommand.execute(); -> stack.push(value);
		invoker.executeCommand(new PushCommand(stack, 8));
		
		// Stampa lo stack
		System.out.println(stack.toString());

		// Crea un nuovo comando PopCommand per rimuovere l'ultimo valore dallo stack
		Command popCommand = new PopCommand(stack);

		// Esegui il comando
		try {
			invoker.executeCommand(popCommand); // popCommand.execute();
//			invoker.executeCommand(popCommand); invoker.executeCommand(popCommand);
		} catch (EmptyStackException e) {
			System.err.println("Lo stack e' vuoto");
		}

		// Stampa lo stack
		System.out.println(stack);

		// Annulla l'ultimo comando eseguito, cioè l'operazione di pop
		invoker.undoLastCommand(); //popCommand.undo();

		// Stampa lo stack
		System.out.println(stack);
		
		invoker.executeCommand(new PushCommand(stack, "Concurrency"));
		System.out.println(stack);
		
		invoker.undoLastCommand();
		System.out.println(stack);
	}
}

