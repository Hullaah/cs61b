package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        T item;
        Node next;
        Node prev;

        private T get(int index) {
            if (index == 0)
                return item;
            return next.get(index - 1);
        }
    }

    private final Node sentinel = new Node();
    private int size;

    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node node = new Node();
        node.item = item;
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node node = new Node();
        node.item = item;
        node.next = sentinel;
        node.prev = sentinel.prev;
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    @Override
    public T removeFirst() {
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (size > 0)
            size--;
        return item;
    }

    @Override
    public T removeLast() {
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (size > 0)
            size--;
        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        int i = 0;
        for (Node ptr = sentinel.next; ptr != sentinel; ptr = ptr.next, i++) {
            if (i == index) {
                return ptr.item;
            }
        }
        return null;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0)
            return null;
        return sentinel.next.get(index);
    }

    @Override
    public void printDeque() {
        StringBuilder sb = new StringBuilder("[");
        Node ptr = sentinel.next;
        while (ptr.next != sentinel) {
            sb.append(ptr.item);
            sb.append(", ");
            ptr = ptr.next;
        }
        if (ptr != sentinel) {
            sb.append(ptr.item);
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
        LinkedListDeque<T> that = (LinkedListDeque<T>) obj;
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

    private class DequeIterator implements Iterator<T> {
        Node node = sentinel.next;

        @Override
        public boolean hasNext() {
            return node != sentinel;
        }

        @Override
        public T next() {
            T item = node.item;
            node = node.next;
            return item;
        }
    }
}
