package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        Ns.addLast(1000);
        Ns.addLast(2000);
        Ns.addLast(4000);
        Ns.addLast(8000);
        Ns.addLast(16000);
        Ns.addLast(32000);;
        Ns.addLast(64000);
        Ns.addLast(128000);

        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        for (int i = 0; i < Ns.size(); i++) {
            int N = Ns.get(i);
            int opCount = 10000;
            SLList<Integer> list = new SLList<>();
            for (int j = 0; j < N; j++) {
                list.addFirst(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < opCount; j++) {
                list.getLast();
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(opCount);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
