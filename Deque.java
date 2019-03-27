import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {


    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    private Node first;
    private Node last;

    private int size;
    public Deque() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if(item == null) throw new IllegalArgumentException();

        Node newNode = new Node();
        newNode.item = item;
        if(size == 0){
            last = newNode;
            first = newNode;
        }else{
            newNode.next = first;
            newNode.previous = null;
            first.previous = newNode;
            first = newNode;
        }

        size++;
    }

    public void addLast(Item item) {
        if(item == null) throw new IllegalArgumentException();

        Node newNode = new Node();
        newNode.item = item;

        if(size == 0){
            first = newNode;
            last = newNode;
        }else{
            last.next = newNode;
            newNode.previous = last;
            newNode.next = null;
            last = newNode;
        }
        size++;

    }
    public Item removeFirst() {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        Node oldFirst = first;
        first = first.next;
        oldFirst.next = null;
        size--;

        if(size == 0){
            first = null;
            last = null;
        }else{
            first.previous = null;
        }
        return item;
    }

    public Item removeLast() {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = last.item;

        Node oldLast = last;
        last = last.previous;
        oldLast.previous = null;

        size--;
        if(size == 0){
            last = null;
            first = null;
        }else{
            last.next = null;
        }

        return item;
    }
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
