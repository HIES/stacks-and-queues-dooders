import org.junit.Before;
import org.junit.Test;
import java.lang.ref.WeakReference;
import static org.junit.Assert.*;

public class StackTest {

    static Stack<String> moes;
    static Stack<Integer> nums;

    @Before
    public void setup()
    {
        moes = new Stack<String>();
        nums = new Stack<Integer>();
    }

    @Test
    public void testEmptyStack()
    {
        assertTrue("A new stack should be empty.",0 == moes.size());
        assertTrue("A new stack should be empty.",0 == nums.size());
    }

    @Test
    public void testIsEmpty()
    {
        assertTrue("A brand new stack should return true when calling isEmpty().", moes.isEmpty());
        moes.push("item");
        assertFalse("After pushing an element to an empty stack, isEmpty() should return false.", moes.isEmpty());
    }

    @Test
    public void testPushSingleElementStack()
    {
        moes.push("first");
        assertTrue("A single push should increase size to 1.",1 == moes.size());

        nums.push(new Integer(1234));
        assertTrue("A single push should increase size to 1.",1 == nums.size());
    }

    @Test
    public void testPushDoubleElementStack()
    {
        moes.push("first");
        moes.push("second");
        assertTrue("Pushing two elements should increase size to 2.", 2 == moes.size());

        nums.push(new Integer(1234));
        nums.push(new Integer(-6789));
        assertTrue("Pushing two elements should increase size to 2.",2 == nums.size());
    }

    @Test
    public void testPopOnceFromDoubleStack()
    {
        moes.push("first");
        moes.push("second");
        assertEquals("Pop should return the most recent element pushed to the stack.", "second", moes.pop());
        assertTrue("After popping an element from a stack, the size should decrease.", 1 == moes.size());

        nums.push(new Integer(567));
        nums.push(new Integer(999));
        assertTrue("Pop should return the most recent element pushed to the stack.", 999 == nums.pop());
        assertTrue("After popping an element from a stack, the size should decrease.", 1 == nums.size());
    }

    @Test
    public void testPopTwiceFromDoubleStack()
    {
        moes.push("first");
        moes.push("second");
        moes.pop();
        assertEquals("Popping a second time should return the first element in the stack.", "first", moes.pop());
        assertTrue("After popping a second element from a stack, the size should decrease.", 0 == moes.size());

        nums.push(new Integer(34));
        nums.push(new Integer(-44));
        nums.pop();
        assertTrue("Popping a second time should return the first element in the stack.", 34 == nums.pop());
        assertTrue("After popping a second element from a stack, the size should decrease.", 0 == nums.size());
    }

    @Test (expected = java.util.EmptyStackException.class)
    public void testPopAnEmptyStack()
    {
        moes.pop();
        nums.pop();
    }

    @Test
    public void testPeek()
    {
        moes.push("first");
        moes.push("second");
        moes.push("third");
        assertEquals("A peek should return the element at the top of the stack.", "third", moes.peek());
        assertTrue("A peek should not remove the top element from the stack.", 3 == moes.size());

        nums.push(7);
        assertEquals("A peek should return the first element of the stack.", new Integer(7), nums.peek());
    }

    @Test (expected = java.util.EmptyStackException.class)
    public void testPeekAnEmptyStack()
    {
        moes.peek();
        nums.peek();
    }

    @Test
    public void testFillHeavyStack()
    {
        for (int i = 0; i < 10000; i++)
            nums.push(new Integer(i));
        assertTrue("Failed to crated a stack with 10,000 elements.", nums.size() == 10000);

        for (int i = 0; i < 1000000; i++)
            moes.push(new String("ragu"));
        assertTrue("Failed to crated a stack with 1,000,000 elements.", moes.size() == 1000000);
    }

    @Test
    public void testFreeMemoryAfterPop()
    {
        Object value = new Object();
        WeakReference<Object> reference = new WeakReference<>(value);

        Stack<Object> stack = new Stack();
        stack.push(value);
        value = null;

        stack.pop();
        Runtime.getRuntime().gc();
        assertNull("To truly pop an element from the stack means to remove it/make it null. Simply adjusting the top reference isn't sufficient.", reference.get());
    }
}
