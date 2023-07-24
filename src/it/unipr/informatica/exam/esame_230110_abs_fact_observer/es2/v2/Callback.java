package it.unipr.informatica.exam.esame_230110_abs_fact_observer.es2.v2;

public interface Callback<T> {
	
	public void onSuccess(T result);

	//	onFailure viene chiamato ogni volta che un task ha prodotto qualunque tipo di eccezione
	default public void onFailure(Throwable throwable) {  // implementazione di default del metodo del interfaccia, che non richede uno stato
		throwable.printStackTrace();   	/*void 	printStackTrace()	Prints this throwable and its backtrace to the standard error stream.*/	
	}
}
