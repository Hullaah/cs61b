package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> c;

    public MaxArrayDeque(Comparator<T> c) {
        this.c = c;
    }

    public T max() {
        return max(c);
    }

    public T max(Comparator<T> comparer) {
        T max  = get(0);
        for (var x: this) {
            if (comparer.compare(max, x) < 0) {
                max = x;
            }
        }
        return max;
    }
}
