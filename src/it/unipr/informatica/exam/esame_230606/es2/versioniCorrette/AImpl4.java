/**
 * 
 */
package it.unipr.informatica.exam.esame_230606.es2.versioniCorrette;

import it.unipr.informatica.exam.esame_230606.es2.A;

/*
 * e meglio usare un oggetto "mutex" o meglio AImpl.class come oggetto 
 * di sincronizzazione?
 * 
 * La scelta dipende dal contesto e dai requisiti specifici del tuo codice.
 * 
 * Se desideri garantire la mutua esclusione solo tra i thread generati dalla 
 * stessa istanza di AImpl, puoi utilizzare un oggetto specifico come mutex. 
 * Questo può essere utile se hai più istanze di AImpl e desideri che i thread 
 * generati da ciascuna istanza siano in mutua esclusione solo con altri thread 
 * generati dalla stessa istanza.
 * 
 * D'altra parte, se desideri garantire la mutua esclusione tra tutti i thread 
 * generati da qualsiasi istanza di AImpl, allora utilizzare AImpl.class come 
 * oggetto di sincronizzazione è più appropriato. 
 * Questo garantisce che tutti i thread generati da tutte le istanze di AImpl 
 * siano in mutua esclusione tra loro.
 * 
 * In generale, se hai solo una singola istanza di AImpl o desideri garantire 
 * la mutua esclusione tra tutti i thread generati da qualsiasi istanza di AImpl,
 * l'utilizzo di AImpl.class come oggetto di sincronizzazione può essere una 
 * scelta più semplice e appropriata. 
 * Tuttavia, se hai requisiti più specifici sulle relazioni di mutua esclusione 
 * tra le istanze di AImpl, puoi utilizzare un oggetto specifico come mutex. 
 * */


/*
 * Nel contesto delle specifiche fornite, dove si richiede che la mutua 
 * esclusione coinvolga tutti e solo i thread restituiti da `m1`, 
 * indipendentemente dall'oggetto su cui viene chiamato `m1`, è preferibile 
 * utilizzare `AImpl.class` come oggetto di sincronizzazione.
 * 
 * Poiché il requisito è che la mutua esclusione coinvolga tutti i thread 
 * restituiti da `m1`, indipendentemente dall'oggetto su cui è chiamato `m1`, 
 * l'utilizzo di `AImpl.class` come oggetto di sincronizzazione garantisce che 
 * tutti i thread generati da tutte le istanze di `AImpl` siano in 
 * mutua esclusione tra loro. 
 * 
 * Questo approccio è coerente con la specifica e soddisfa il requisito 3.
 * Pertanto, nel caso delle specifiche fornite, utilizzare `AImpl.class` come 
 * oggetto di sincronizzazione è più appropriato rispetto all'utilizzo di un 
 * oggetto specifico come `mutex`.
 * */
public class AImpl4 implements A {

	@Override
	public Thread m1(Object o) {
		
		return new Thread(() -> {
			synchronized (AImpl4.class) {
				m2(o);
			}			
		});
	}

	private void m2(Object o) {
		System.out.println(Thread.currentThread().getName()  + "- m2(o): " + o);
	}
}
