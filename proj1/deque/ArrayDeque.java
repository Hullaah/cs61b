package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private final static double MIN_USAGE_FACTOR = 0.25;
    private T[] items;
    private int first, last;
    private int size;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        last = 7;
    }

    @Override
    public void addFirst(T item) {
        size++;
        resize();
        if (first >= items.length) {
            first = 0;
        }
        items[first++] = item;
    }

    @Override
    public void addLast(T item) {
        size++;
        resize();
        if (last < 0) {
            last = items.length - 1;
        }
        items[last--] = item;
    }

    @Override
    public T removeFirst() {
        if (!isEmpty()) {
            size--;
            resize();
            if (first == 0) {
                first = items.length;
            }
            T item = items[first - 1];
            items[--first] = null;
            return item;
        } else {
            return null;
        }
    }


    @Override
    public T removeLast() {
        if (!isEmpty()) {
            size--;
            resize();
            if (last == items.length - 1) {
                last = -1;
            }
            T item = items[last + 1];
            items[++last] = null;
            return item;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index < first) {
            return items[first - 1 - index];
        } else {
            return items[items.length - 1 - (index - first)];
        }
    }

    @Override
    public void printDeque() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(get(i));
            sb.append(", ");
        }
        if (size != 0) {
            sb.append(get(size - 1));
        }
        sb.append("]");
        System.out.println(sb);
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        ArrayDeque<T> that = (ArrayDeque<T>)  obj;
        if (this.size != that.size)
            return false;
        int i = 0;
        for (var item: this) {
            if (!item.equals(that.get(i)))
                return false;
            i++;
        }
        return true;
    }

    private void resize() {
        final double USAGE_FACTOR = (double) size / items.length;
        if (USAGE_FACTOR < MIN_USAGE_FACTOR && items.length >= 16) {
            T[] arr = (T[]) new Object[items.length / 2];
            int newLast = arr.length - (items.length - last);
            if (first < last) {
                if (first > 0) System.arraycopy(items, 0, arr, 0, first - 1);
                if (newLast + 1 < arr.length)
                    System.arraycopy(items, last, arr, newLast + 1, items.length - last - 1);
            } else {
                System.arraycopy(items, last + 1, arr, 0, size);
                newLast = arr.length - 1;
                first = size - 1;
            }
            items = arr;
            last = newLast;
        } else if (USAGE_FACTOR > 1.0) {
            T[] arr = (T[]) new Object[items.length * 2];
            int newLast = arr.length - (items.length - last);
            if (first > 0) System.arraycopy(items, 0, arr, 0, first - 1);
            if (newLast + 1 < arr.length)
                System.arraycopy(items, last + 1, arr, newLast + 1, items.length - last - 1);
            items = arr;
            last = newLast;
        }
    }

    private class DequeIterator implements Iterator<T> {
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public T next() {
            return get(i++);
        }
    }
}
