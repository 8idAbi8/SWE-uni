package it.unipr.informatica.exam.esame_230207.es3.invoker;

import java.util.ArrayList;
import java.util.List;
import it.unipr.informatica.exam.esame_230207.es3.commands.Command;
import it.unipr.informatica.exam.esame_230207.es3.commands.PopCommand;

// invoker
public class StackCommandInvoker {
	
	private List<Command> commandHistory = new ArrayList<>();

	public void executeCommand(Command command) {	// onButtonWasPushed
		System.out.println("Invoker::executeCommand()");		
		command.execute();
		commandHistory.add(command);	// push
	}

	public void undoLastCommand() {			// undoButtonWasPushed
		if (!commandHistory.isEmpty()) {
			System.out.println("Invoker::undoLastCommand()");
			Command lastCommand = commandHistory.remove(commandHistory.size() - 1);	// pop
			lastCommand.undo();
		}
	}	
}
