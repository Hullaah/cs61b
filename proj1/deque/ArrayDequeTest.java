package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {

        ArrayDeque<String> arrd1 = new ArrayDeque<String>();

        assertTrue("A newly initialized arrdeque should be empty", arrd1.isEmpty());
        arrd1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, arrd1.size());
        assertFalse("arrd1 should now contain 1 item", arrd1.isEmpty());

        arrd1.addLast("middle");
        assertEquals(2, arrd1.size());

        arrd1.addLast("back");
        assertEquals(3, arrd1.size());

        System.out.println("Printing out deque: ");
        arrd1.printDeque();
    }

    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> arrd1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("arrd1 should be empty upon initialization", arrd1.isEmpty());

        arrd1.addFirst(10);
        // should not be empty
        assertFalse("arrd1 should contain 1 item", arrd1.isEmpty());

        arrd1.removeFirst();
        // should be empty
        assertTrue("arrd1 should be empty after removal", arrd1.isEmpty());
    }

    @Test
    public void removeEmptyTest() {
        ArrayDeque<Integer> arrd1 = new ArrayDeque<>();
        arrd1.addFirst(3);

        arrd1.removeLast();
        arrd1.removeFirst();
        arrd1.removeLast();
        arrd1.removeFirst();

        int size = arrd1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    public void multipleParamTest() {
        ArrayDeque<String>  arrd1 = new ArrayDeque<String>();
        ArrayDeque<Double>  arrd2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> arrd3 = new ArrayDeque<Boolean>();

        arrd1.addFirst("string");
        arrd2.addFirst(3.14159);
        arrd3.addFirst(true);

        String s = arrd1.removeFirst();
        double d = arrd2.removeFirst();
        boolean b = arrd3.removeFirst();
    }

    @Test
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> arrd1 = new ArrayDeque<Integer>();

        assertNull("Should return null when removeFirst is called on an empty Deque,", arrd1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", arrd1.removeLast());
    }

    @Test
    public void bigArrDequeTest() {
        ArrayDeque<Integer> arrd1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            arrd1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) arrd1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) arrd1.removeLast(), 0.0);
        }
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> d = new ArrayDeque<>();
        d.addFirst(0);
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);

        assertEquals(3, (long) d.get(0));
        assertEquals(0, (long) d.get(1));
        assertEquals(1, (long) d.get(2));
        assertEquals(2, (long) d.get(3));
    }

    @Test
    public void testDequeWithSameElementEquals() {
        ArrayDeque<Integer> d1 = new ArrayDeque<>();
        d1.addLast(1);
        d1.addLast(2);
        d1.addLast(3);

        ArrayDeque<Integer> d2 = new ArrayDeque<>();
        d2.addLast(1);
        d2.addLast(2);
        d2.addLast(3);

        assertEquals(d1, d2);
    }

    @Test
    public void testDequeToSameReferenceEquals() {
        ArrayDeque<Integer> d1 = new ArrayDeque<>();
        d1.addLast(100);
        d1.addLast(200);
        ArrayDeque<Integer> d2 = d1;

        assertEquals(d1, d2);
    }

    @Test
    public void testDequeWithDifferentElementNotEqual() {
        ArrayDeque<Integer> d1 = new ArrayDeque<>();
        d1.addLast(1);
        d1.addLast(2);
        d1.addLast(3);

        ArrayDeque<Integer> d2 =  new ArrayDeque<>();
        d2.addLast(1);
        d2.addLast(4);
        d2.addLast(5);

        assertNotEquals(d1, d2);
    }

    @Test
    public void randomizedTest() {
        int N = 10000;
        ArrayDequeNoResizing<Integer> L1 = new ArrayDequeNoResizing<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int randomVal = StdRandom.uniform(0, 8);
            if (randomVal == 0) {
                int val = StdRandom.uniform(100);
                L1.addFirst(val);
                L2.addFirst(val);
            } else if (randomVal == 1) {
                int val = StdRandom.uniform(100);
                L1.addLast(val);
                L2.addLast(val);
            } else if (randomVal == 2) {
                var first1 = L1.removeFirst();
                var first2 = L2.removeFirst();
                assertEquals(first1, first2);
            } else if (randomVal == 3) {
                var last1 = L1.removeLast();
                var last2 = L2.removeLast();
                assertEquals(last1, last2);
            } else if (randomVal == 4 && !L2.isEmpty()) {
                int index = StdRandom.uniform(L2.size());
                assertEquals(L1.get(index), L2.get(index));
            } else if (randomVal == 5) {
                assertEquals(L1.size(), L2.size());
            } else if (randomVal == 6) {
                L1.printDeque();
                L2.printDeque();
            } else {
                assertEquals(L1.isEmpty(), L2.isEmpty());
            }
        }
    }
}
