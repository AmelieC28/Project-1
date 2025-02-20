import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;

    public ResizableArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ResizableArrayBag(int capacity) {
        bag = (T[]) new Object[capacity];
        numberOfEntries = 0;
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean add(T newEntry) {
        if (numberOfEntries == bag.length) {
            bag = Arrays.copyOf(bag, bag.length * 2);
        }
        bag[numberOfEntries++] = newEntry;
        return true;
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        T result = bag[numberOfEntries - 1];
        bag[numberOfEntries - 1] = null;
        numberOfEntries--;
        return result;
    }

    @Override
    public boolean remove(T anEntry) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) {
                bag[i] = bag[numberOfEntries - 1];
                bag[numberOfEntries - 1] = null;
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        while (!isEmpty()) remove();
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) count++;
        }
        return count;
    }

    @Override
    public boolean contains(T anEntry) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) return true;
        }
        return false;
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(bag, numberOfEntries);
    }

    @Override
    public BagInterface<T> union(BagInterface<T> otherBag) {
        ResizableArrayBag<T> resultBag = new ResizableArrayBag<>(this.numberOfEntries + otherBag.getCurrentSize());
        for (T item : this.toArray()) {
            resultBag.add(item);
        }
        for (T item : otherBag.toArray()) {
            resultBag.add(item);
        }
        return resultBag;
    }

    @Override
    public BagInterface<T> intersection(BagInterface<T> otherBag) {
        ResizableArrayBag<T> resultBag = new ResizableArrayBag<>();
        ResizableArrayBag<T> tempBag = new ResizableArrayBag<>();

        for (T item : this.toArray()) {
            if (otherBag.contains(item) && !tempBag.contains(item)) {
                resultBag.add(item);
                tempBag.add(item);
            }
        }
        return resultBag;
    }

    @Override
    public BagInterface<T> difference(BagInterface<T> otherBag) {
        ResizableArrayBag<T> resultBag = new ResizableArrayBag<>();
        ResizableArrayBag<T> tempBag = new ResizableArrayBag<>();

        for (T item : otherBag.toArray()) {
            tempBag.add(item);
        }

        for (T item : this.toArray()) {
            if (tempBag.contains(item)) {
                tempBag.remove(item);
            } else {
                resultBag.add(item);
            }
        }
        return resultBag;
    }
}
