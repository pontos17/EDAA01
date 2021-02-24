package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}


	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e); 
		if (size == 0) {
			n.next = n;
		} else {
			n.next = last.next;
			last.next = n;
		}
		last=n;
		size ++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (size == 0) {
			return null;
		}
		return last.next.element; 
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		
		if (last == null) {
			return null;
		}
   		QueueNode<E> q =last.next ;
		last.next = last.next.next;
		size --;
			if (size == 0) {
				last = null;
			}
		
		return q.element;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	/**
	 * Append the specified queue to this queue
	 * pos: all elements from the specified queue are appended
	 * to this queue. The specified queue (q) is empty after the call.
	 * @param q the queue to append
	 * @throws IllegelArgumentException if this queue and q are identical
	 */
	public void append (FifoQueue<E> q) {
		
		if (this == q) {
			throw new IllegalArgumentException();
		}
		if (last == null) {
			last = q.last;
			size = q.size;
		}
		else if (q.last != null) {
			QueueNode<E> temp = last.next;
			last.next = q.last.next;
			q.last.next = temp;
			last = q.last;
			size += q.size;
		}
		q.last = null;
		q.size = 0; 
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	private class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		private int counter; 
		
		private QueueIterator() {
			pos = last;
			counter = 0; 
		}

		public boolean hasNext() { 
			if (size <= 1 || counter >= size) {
				return false;
			}
			return true;
		}
		
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			pos = pos.next;
			counter++;
			return pos.element;
		}
		/* if(hasNext(){
		 		pos = pos.next;
				counter++;
				return pos.element;
			} else {
				throw new NoSuchElementException();
			}
		 */
		
	}

}
