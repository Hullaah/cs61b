package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    T max;

    MaxArrayDeque(Comparator<T> c) {
        max = get(0);
        for (var x: this) {
            if (c.compare(max, x) < 0) {
                max = x;
            }
        }
    }

    public T max() {
        return max;
    }

    public T max(Comparator<T> c) {
        T max  = get(0);
        for (var x: this) {
            if (c.compare(max, x) < 0) {
                max = x;
            }
        }
        return max;
    }
}
