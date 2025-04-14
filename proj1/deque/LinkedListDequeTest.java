package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

        @Test
        public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    public void multipleParamTest() {
        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        assertNull("Should return null when removeFirst is called on an empty Deque,", lld1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", lld1.removeLast());
    }

    @Test
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void testGet() {
        LinkedListDeque<Integer> d = new LinkedListDeque<>();
        d.addFirst(0);
        d.addLast(1);
        d.addLast(2);
        d.addLast(3);

        assertEquals(0, (long) d.get(0));
        assertEquals(1, (long) d.get(1));
        assertEquals(2, (long) d.get(2));
        assertEquals(3, (long) d.get(3));
    }

    @Test
    public void testDequeWithSameElementEquals() {
        LinkedListDeque<Integer> d1 = new LinkedListDeque<>();
        d1.addLast(1);
        d1.addLast(2);
        d1.addLast(3);

        LinkedListDeque<Integer> d2 = new LinkedListDeque<>();
        d2.addLast(1);
        d2.addLast(2);
        d2.addLast(3);

        assertEquals(d1, d2);
    }

    @Test
    public void testDequeToSameReferenceEquals() {
        LinkedListDeque<Integer> d1 = new LinkedListDeque<>();
        d1.addLast(100);
        d1.addLast(200);
        LinkedListDeque<Integer> d2 = d1;

        assertEquals(d1, d2);
    }

    @Test
    public void testDequeWithDifferentElementNotEqual() {
        LinkedListDeque<Integer> d1 = new LinkedListDeque<>();
        d1.addLast(1);
        d1.addLast(2);
        d1.addLast(3);

        LinkedListDeque<Integer> d2 =  new LinkedListDeque<>();
        d2.addLast(1);
        d2.addLast(4);
        d2.addLast(5);

        assertNotEquals(d1, d2);
    }
}
