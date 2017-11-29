import java.util.NoSuchElementException;

public class Queue<E> {
    private Node head;
    private Node tail;
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

	public E dequeue() {
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

	public E peek() {
        if (!isEmpty()) return tail.data;
        else throw new NoSuchElementException();
    }

}
