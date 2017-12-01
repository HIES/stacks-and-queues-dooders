import java.util.EmptyStackException;

public class Stack<E> {
    private int size;
    private Object[] stack;

	public Stack() {
	    stack = new Object[10];
	    size = 0;
    }

	public void push(E item) {
		if (size() == stack.length) {
			Object[] tempArray = stack;
			stack = new Object[stack.length * 2];
			System.arraycopy(tempArray, 0, stack, 0, tempArray.length);
		}
		stack[size++] = item;
	}

	public E pop() throws EmptyStackException {
	    if (!isEmpty()) {
            E toReturn = (E) stack[size() - 1];
            stack[size() - 1] = null;
            size--;
            return toReturn;
        } else {
	        throw new EmptyStackException();
        }
	}

	public E peek() throws EmptyStackException {
	    if (!isEmpty()) return (E) stack[size() - 1];
	    else throw new EmptyStackException();
    }

	public boolean isEmpty() {
	    return stack[0] == null;
	}

	public int size() {
	    return size;
    }
}
