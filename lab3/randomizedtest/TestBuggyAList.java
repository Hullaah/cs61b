package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
      AListNoResizing<Integer> list1 = new AListNoResizing<>();
      BuggyAList<Integer> list2 = new BuggyAList<>();

        list1.addLast(4);
        list2.addLast(4);

        list1.addLast(5);
        list2.addLast(5);

        list1.addLast(6);
        list2.addLast(6);

        assertEquals(list1.removeLast(), list2.removeLast());
        assertEquals(list1.removeLast(), list2.removeLast());
        assertEquals(list1.removeLast(), list2.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = L2.size();
                assertEquals(size, size2);
            } else if (operationNumber == 2 && L.size() > 0) {
                int last = L.getLast();
                int last2 = L2.getLast();
                assertEquals(last2, last);
            } else if (operationNumber == 3 && L.size() > 0)  {
                int last = L.removeLast();
                int last2 = L2.removeLast();
                assertEquals(last, last2);
            }
        }
    }
}
