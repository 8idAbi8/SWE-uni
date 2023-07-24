package it.unipr.informatica.exam.esame_230606.es3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

	private Object[] elements;
	private int size;
	private int front;
    private int rear;

	public QueueImpl() {
		elements = new Object[10];
		size = 0;
		front = 0;
		rear = -1;
	}

	// metodi di Queue
	@Override
	public void add(Object value) {
		
		if (size == elements.length) {
            // Queue is full, resize the array
            Object[] newElements = new Object[2 * elements.length];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }

        rear = (rear + 1) % elements.length;
        elements[rear] = value;
        size++;
        System.out.println("size: " + size);
	}

	@Override
	public Object remove() throws IllegalStateException {
		
		if (size <= 0) {
            throw new IllegalStateException("Queue is empty");
        }

        Object value = elements[front];
        front = (front + 1) % elements.length;
        size--;

        return value;
	}

	// metodo derivato dall'estensione di Iterable<T>
	@Override
	public Iterator<Object> iterator() {  // create Iterator()
		return new QueueIterator();
	}

	
	// concreteIterator for QueueImpl
	private class QueueIterator implements Iterator {

		private int currentIndex;

		public QueueIterator() {
			currentIndex = front;
		}

		public boolean hasNext() {
			System.out.println("currentIndex != rear+1: " + currentIndex +  " - " + (rear+1));
	        
			return currentIndex != rear + 1;
		}

		public Object next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException("No more elements in the queue");
			}

			Object value = elements[currentIndex];
			currentIndex = (currentIndex + 1) % (elements.length + 1); 
			
			/* % ... + 1; per permettere a currentIndex di riuscire ad equiparare elements.length
			 * 
			 *  es: avendo elements.length di default a 10, se aggiungiamo 10 elementi,
			 *  currentIndex % 10 non raggiunge mai la size(=rear+1), ma varia da 0 a 9.
			 *  Cosi facendo, hasNext continua a ciclare forever. 
			 * */
			
			return value;
		}
	}
}
