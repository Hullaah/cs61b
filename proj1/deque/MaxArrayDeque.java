package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    T max;
    Comparator<T> c;

    public MaxArrayDeque(Comparator<T> c) {
        this.c = c;
    }

    public T max() {
        return max;
    }

    public T max(Comparator<T> c) {
        T largest  = get(0);
        for (var x: this) {
            if (c.compare(largest, x) < 0) {
                largest = x;
            }
        }
        return largest;
    }

    @Override
    public void addFirst(T item) {
        super.addFirst(item);
        max = max(c);
    }

    @Override
    public void addLast(T item) {
        super.addLast(item);
        max = max(c);
    }

    @Override
    public T removeFirst() {
        T first = super.removeFirst();
        max = max(c);
        return first;
    }

    @Override
    public T removeLast() {
        T last = super.removeLast();
        max = max(c);
        return last;
    }
}
