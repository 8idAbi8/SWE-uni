package it.unipr.informatica.exam.esame_230207.es3.reciver;

import java.util.ArrayDeque;
import java.util.Deque;

import it.unipr.informatica.exam.esame_230207.es3.commands.Command;
import it.unipr.informatica.exam.esame_230207.es3.commands.PopCommand;
import it.unipr.informatica.exam.esame_230207.es3.commands.PushCommand;
import it.unipr.informatica.exam.esame_230207.es3.exception.EmptyStackException;

/* classe  un mix fra un invoker e un reciver. */

public class StackWithUndo implements Stack {

	private Stack stack;
	private Deque<Command> undoStack;
	
	public StackWithUndo() {
		stack = new SimpleStack();
		undoStack = new ArrayDeque<>();
	}
	
	@Override
	public void push(Object value) {
		Command pushCommand = new PushCommand(stack, value);
		pushCommand.execute();
		undoStack.push(pushCommand);  // aggiungo comando nello stack degli undo
	}

	@Override
	public Object pop() throws EmptyStackException {
		PopCommand popCommand = new PopCommand(stack);
		popCommand.execute();
		undoStack.push(popCommand);
		
		return popCommand.value;
	}

	@Override
	public int size() {
		return stack.size();
	}

	public void undo() {
		if (!undoStack.isEmpty()) {
			Command undoCommand = undoStack.pop();  // prendiamo l'ultimo commando inserito
			undoCommand.undo();
			
		}
	}
}
