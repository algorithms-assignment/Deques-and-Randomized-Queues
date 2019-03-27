import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int size; // Size of the queue

    private class RandomIterator implements Iterator<Item> {

        private int[] indexes;
        private int indexes_length;

        public RandomIterator() {
            indexes = new int[size];
            indexes_length = size;
            for (int i = 0; i < size; i++) {
                indexes[i] = i;
            }

            // Initialize array of sequential integers starting from 0 to size-1;
        }

        @Override
        public boolean hasNext() {
            return indexes_length != 0;
        }

        @Override
        public Item next() {
            if (indexes_length == 0) {
                throw new NoSuchElementException();
            }
            int index = StdRandom.uniform(indexes_length);
            Item item = array[indexes[index]];
            indexes[index] = indexes[--indexes_length];
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public RandomizedQueue() {
        array = (Item[]) new Object[16];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == array.length) {
            resize(); // doubling the size of the array
        }
        array[size++] = item;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size <= array.length / 4) {
            shrink(); // Dividing the size of the array by 2;
        }
        int index = StdRandom.uniform(size);
        Item item = array[index];
        array[index] = array[--size];
        array[size] = null;
        return item;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return array[StdRandom.uniform(size)];
    }

    private void resize() {
        int length = 2 * array.length;
        Item[] newArray = (Item[]) new Object[length];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private void shrink() {
        int length = array.length / 2;
        Item[] newArray = (Item[]) new Object[length];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

}