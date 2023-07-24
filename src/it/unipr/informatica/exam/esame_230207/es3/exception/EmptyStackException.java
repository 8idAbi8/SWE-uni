package it.unipr.informatica.exam.esame_230207.es3.exception;

public class EmptyStackException extends RuntimeException {

	/**
	 * The serializable class EmptyStackException declare a 
	 * static final serialVersionUID field of type long.
	 */
	private static final long serialVersionUID = -8259487682007798056L;

	public EmptyStackException() {
		System.err.println("EmptyStackException()");
	}
	
	public EmptyStackException(Throwable cause) {
		super(cause);
	}
	
	
}
