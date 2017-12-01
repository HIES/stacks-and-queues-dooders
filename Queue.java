import java.util.NoSuchElementException;

public class Queue<E> {
    private Node head; // Head is the back of the queue 
    private Node tail; // Tail is the front of the queue
    private int size;

    private class Node {
        private E data;
        private Node next;
        private Node previous;

        public Node(E data) {
            this.data = data;
        }
    }

	public Queue() {
        size = 0;
	}

	public int size() {
        return size;
	}

	public boolean isEmpty() {
    	return head == null;
	}

	public void enqueue(E item) {
	    if (isEmpty()) {
	        head = tail = new Node(item);
        } else {
	        Node tempNode = new Node(item);
	        tempNode.next = head;
	        head.previous = tempNode;
	        head = tempNode;
        }
        size++;
    }

	public E dequeue() throws NoSuchElementException {
        if (!isEmpty()) {
            if (size() == 1) {
                E toReturn = tail.data;
                head = tail = null;
                size--;
                return toReturn;
            }
            E toReturn = tail.data;
            tail = tail.previous;
            tail.next = null;
            size--;
            return toReturn;
        } else {
            throw new NoSuchElementException();
        }
    }

	public E peek() throws NoSuchElementException {
        if (!isEmpty()) return tail.data;
        else throw new NoSuchElementException();
    }

}
