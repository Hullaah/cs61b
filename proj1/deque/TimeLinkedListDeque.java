package deque;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeLinkedListDeque {
    private static void printTimingTable(ArrayDeque<Integer> ns, ArrayDeque<Double> times, ArrayDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < ns.size(); i += 1) {
            int N = ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        System.out.println("addLast()");
        timeAddLast();
        System.out.println("addFirst()");
        timeAddFirst();
        System.out.println("removeFirst()");
        timeRemoveFirst();
        System.out.println("removeLast()");
        timeRemoveLast();
        System.out.println("get()");
        timeGet();
        System.out.println("size()");
        timeSize();
    }

    public static void timeAddLast() {
        ArrayDeque<Integer> ns = new ArrayDeque<>();
        ns.addLast(1000);
        ns.addLast(2000);
        ns.addLast(4000);
        ns.addLast(8000);
        ns.addLast(16000);
        ns.addLast(32000);
        ns.addLast(64000);
        ns.addLast(128000);

        ArrayDeque<Double> times = new ArrayDeque<>();
        ArrayDeque<Integer> opCounts = new ArrayDeque<>();

        for (int i = 0; i < ns.size(); i++) {
            int N = ns.get(i);
            LinkedListDeque<Integer> list = new LinkedListDeque<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < N; j++) {
                list.addLast(j);
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(N);
        }
        printTimingTable(ns, times, opCounts);
    }

    public static void timeAddFirst() {
        ArrayDeque<Integer> ns = new ArrayDeque<>();
        ns.addLast(1000);
        ns.addLast(2000);
        ns.addLast(4000);
        ns.addLast(8000);
        ns.addLast(16000);
        ns.addLast(32000);
        ns.addLast(64000);
        ns.addLast(128000);

        ArrayDeque<Double> times = new ArrayDeque<>();
        ArrayDeque<Integer> opCounts = new ArrayDeque<>();

        for (int i = 0; i < ns.size(); i++) {
            int N = ns.get(i);
            LinkedListDeque<Integer> list = new LinkedListDeque<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < N; j++) {
                list.addFirst(j);
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(N);
        }
        printTimingTable(ns, times, opCounts);
    }
    public static void timeRemoveFirst() {
        ArrayDeque<Integer> ns = new ArrayDeque<>();
        ns.addLast(1000);
        ns.addLast(2000);
        ns.addLast(4000);
        ns.addLast(8000);
        ns.addLast(16000);
        ns.addLast(32000);
        ns.addLast(64000);
        ns.addLast(128000);

        ArrayDeque<Double> times = new ArrayDeque<>();
        ArrayDeque<Integer> opCounts = new ArrayDeque<>();

        for (int i = 0; i < ns.size(); i++) {
            int N = ns.get(i);
            LinkedListDeque<Integer> list = new LinkedListDeque<>();
            for (int j = 0; j < N; j++) {
                list.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < N; j++) {
                list.removeFirst();
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(N);
        }
        printTimingTable(ns, times, opCounts);
    }

    public static void timeRemoveLast() {
        ArrayDeque<Integer> ns = new ArrayDeque<>();
        ns.addLast(1000);
        ns.addLast(2000);
        ns.addLast(4000);
        ns.addLast(8000);
        ns.addLast(16000);
        ns.addLast(32000);
        ns.addLast(64000);
        ns.addLast(128000);

        ArrayDeque<Double> times = new ArrayDeque<>();
        ArrayDeque<Integer> opCounts = new ArrayDeque<>();

        for (int i = 0; i < ns.size(); i++) {
            int N = ns.get(i);
            LinkedListDeque<Integer> list = new LinkedListDeque<>();
            for (int j = 0; j < N; j++) {
                list.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < N; j++) {
                list.removeLast();
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(N);
        }
        printTimingTable(ns, times, opCounts);
    }

    public static void timeGet() {
        ArrayDeque<Integer> ns = new ArrayDeque<>();
        ns.addLast(1000);
        ns.addLast(2000);
        ns.addLast(4000);
        ns.addLast(8000);
        ns.addLast(16000);
        ns.addLast(32000);
        ns.addLast(64000);
        ns.addLast(128000);

        ArrayDeque<Double> times = new ArrayDeque<>();
        ArrayDeque<Integer> opCounts = new ArrayDeque<>();

        for (int i = 0; i < ns.size(); i++) {
            int N = ns.get(i);
            LinkedListDeque<Integer> list = new LinkedListDeque<>();
            for (int j = 0; j < N; j++) {
                list.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < N; j++) {
                list.get(i);
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(N);
        }
        printTimingTable(ns, times, opCounts);
    }

    public static void timeSize() {
        ArrayDeque<Integer> ns = new ArrayDeque<>();
        ns.addLast(1000);
        ns.addLast(2000);
        ns.addLast(4000);
        ns.addLast(8000);
        ns.addLast(16000);
        ns.addLast(32000);
        ns.addLast(64000);
        ns.addLast(128000);

        ArrayDeque<Double> times = new ArrayDeque<>();
        ArrayDeque<Integer> opCounts = new ArrayDeque<>();

        for (int i = 0; i < ns.size(); i++) {
            int N = ns.get(i);
            LinkedListDeque<Integer> list = new LinkedListDeque<>();
            for (int j = 0; j < N; j++) {
                list.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            list.size();
            times.addLast(sw.elapsedTime());
            opCounts.addLast(1);
        }
        printTimingTable(ns, times, opCounts);
    }
}
