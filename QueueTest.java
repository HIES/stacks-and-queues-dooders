import org.junit.Before;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class QueueTest {

    static Queue<String> q;
    static Queue<Double> q2;

    @Before
    public void setup()
    {
        q = new Queue<>();
        q2 = new Queue<>();
    }

    @Test
    public void testEmptyQueue()
    {
        assertTrue("A new queue should not contain any elements.", q.isEmpty());
        assertTrue("A new queue should not contain any elements.", q2.isEmpty());
    }

    @Test
    public void testIsEmptyOnNotEmpty()
    {
        q.enqueue("first in line");
        assertFalse("When an item is enqueued the queue is no longer empty.", q.isEmpty());
    }

    @Test
    public void testOneEnqueue()
    {
        q.enqueue("first");
        assertTrue("An enqueue should increase the size of the queue.", 1 == q.size());
        q2.enqueue(new Double(3.14));
        assertTrue("An enqueue should increase the size of the queue.", 1 == q2.size());
    }

    @Test
    public void testOneDequeue()
    {
        q.enqueue("first");
        assertEquals("Dequeue of one item should return the single item.", "first", q.dequeue());
        assertTrue("The size should decrease after an element is dequeued.", q.isEmpty());

        q2.enqueue(3.14);
        assertEquals("Dequeue of one item should return the single item.", new Double(3.14), q2.dequeue());
        assertTrue("The size should decrease after an element is dequeued.", q2.isEmpty());
    }

    @Test
    public void testTwoEnqueue()
    {
        q.enqueue("first");
        q.enqueue("second");
        assertTrue("Enqueue of two items should yield a size of 2.", 2 == q.size());

        q2.enqueue(3.14);
        q2.enqueue(3.14159);
        assertTrue("Enqueue of two items should yield a size of 2.", 2 == q2.size());
    }

    @Test
    public void testTwoDequeue()
    {
        q.enqueue("first");
        q.enqueue("second");
        assertEquals("Incorrect element for first dequeue of a 2-element queue.", "first", q.dequeue());
        assertEquals("Incorrect element for first dequeue of a 2-element queue.", "second", q.dequeue());

        q2.enqueue(123.45);
        q2.enqueue(56789.0);
        assertEquals("Incorrect element for first dequeue of a 2-element queue.", new Double(123.45), q2.dequeue());
        assertEquals("Incorrect element for first dequeue of a 2-element queue.", new Double(56789.0), q2.dequeue());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyDequeue()
    {
        q.dequeue();
        q2.dequeue();

        q.enqueue("one"); q.enqueue("two"); q.enqueue("three");
        q.dequeue(); q.dequeue(); q.dequeue(); q.dequeue();
    }

    @Test
    public void testPeek()
    {
        q.enqueue("oh hi there");
        assertTrue("What's going on with your size method?", 1 == q.size());
        assertEquals("Peek does not return the front element correctly.", "oh hi there", q.peek());
        assertTrue("Peek should not change the size of the queue.", 1 == q.size());

        q.enqueue("oh hi there");
        q.enqueue("It's me again.");
        assertTrue("What's going on with your size method?", 3 == q.size());
        assertEquals("Peek does not return the front element correctly.", "oh hi there", q.peek());
        assertTrue("Peek should not change the size of the queue.", 3 == q.size());

        q2.enqueue(5.4321);
        q2.enqueue(9.876);
        assertTrue(2 == q2.size());
        assertEquals("Peek does not return the front of the queue correctly.", new Double(5.4321), q2.peek());
        assertTrue("Peek should not modify the size of the queue.", 2 == q2.size());

    }

    @Test (expected = java.util.NoSuchElementException.class)
    public void testEmptyPeek()
    {
        q.peek();
        q2.peek();
    }

    @Test
    public void testFreeMemoryAfterPop()
    {
        Object value = new Object();
        WeakReference<Object> reference = new WeakReference<>(value);

        Queue<Object> queue = new Queue();
        queue.enqueue(value);
        value = null;

        queue.dequeue();
        Runtime.getRuntime().gc();
        assertNull("To truly dequeue an element from the queue means to remove it/make it null. Simply adjusting the front reference isn't sufficient.", reference.get());
    }

}
