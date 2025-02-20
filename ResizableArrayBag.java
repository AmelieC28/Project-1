

import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T> {
    private T[] bag; // Array to store bag items
    private int numberOfEntries; // Number of items in the bag
    private static final int DEFAULT_CAPACITY = 25; // Default capacity of the bag


    // Default constructor
    public ResizableArrayBag() {
        this(DEFAULT_CAPACITY);
    }


    // Constructor with custom capacity
    @SuppressWarnings("unchecked")
    public ResizableArrayBag(int capacity) {
        bag = (T[]) new Object[capacity]; // Initialize the bag array
        numberOfEntries = 0; // Start with 0 items
    }


    @Override
    public int getCurrentSize() {
        return numberOfEntries; // Return the number of items in the bag
    }


    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0; // Check if the bag is empty
    }


    @Override
    public boolean add(T newEntry) {
        // If the bag is full, double its size
        if (numberOfEntries == bag.length) {
            bag = Arrays.copyOf(bag, bag.length * 2);
        }
        bag[numberOfEntries++] = newEntry; // Add the new item
        return true;
    }


    @Override
    public T remove() {
        if (isEmpty()) return null; // If the bag is empty, return null
        T result = bag[numberOfEntries - 1]; // Remove the last item
        bag[numberOfEntries - 1] = null; // Set the last position to null
        numberOfEntries--; // Decrease the number of items
        return result;
    }


    @Override
    public boolean remove(T anEntry) {
        // Search for the item to remove
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) {
                // Replace the item with the last item in the bag
                bag[i] = bag[numberOfEntries - 1];
                bag[numberOfEntries - 1] = null;
                numberOfEntries--; // Decrease the number of items
                return true;
            }
        }
        return false; // Item not found
    }


    @Override
    public void clear() {
        // Remove all items from the bag
        while (!isEmpty()) remove();
    }


    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        // Count how many times the item appears in the bag
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) count++;
        }
        return count;
    }


    @Override
    public boolean contains(T anEntry) {
        // Check if the item is in the bag
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) return true;
        }
        return false;
    }


    @Override
    public T[] toArray() {
        // Return a copy of the bag as an array
        return Arrays.copyOf(bag, numberOfEntries);
    }


    @Override
    public BagInterface<T> union(BagInterface<T> otherBag) {
        // Create a new bag to store the union
        ResizableArrayBag<T> resultBag = new ResizableArrayBag<>(this.numberOfEntries + otherBag.getCurrentSize());


        // Add all items from this bag
        for (T item : this.toArray()) {
            resultBag.add(item);
        }


        // Add all items from the other bag
        for (T item : otherBag.toArray()) {
            resultBag.add(item);
        }


        return resultBag;
    }


    @Override
    public BagInterface<T> intersection(BagInterface<T> otherBag) {
        // Create a new bag to store the intersection
        ResizableArrayBag<T> resultBag = new ResizableArrayBag<>();


        // Iterate through this bag
        for (T item : this.toArray()) {
            // If the item is in both bags, add it to the result bag
            if (otherBag.contains(item)) {
                int freqThis = this.getFrequencyOf(item); // Frequency in this bag
                int freqOther = otherBag.getFrequencyOf(item); // Frequency in other bag
                int minFreq = Math.min(freqThis, freqOther); // Minimum frequency


                // Add the item the minimum number of times
                for (int i = 0; i < minFreq; i++) {
                    resultBag.add(item);
                }
            }
        }
        return resultBag;
    }


    @Override
    public BagInterface<T> difference(BagInterface<T> otherBag) {
        // Create a new bag to store the difference
        ResizableArrayBag<T> resultBag = new ResizableArrayBag<>();
        ResizableArrayBag<T> tempBag = new ResizableArrayBag<>();


        // Add all items from the other bag to tempBag
        for (T item : otherBag.toArray()) {
            tempBag.add(item);
        }


        // Iterate through this bag
        for (T item : this.toArray()) {
            // If the item is not in tempBag, add it to the result bag
            if (tempBag.contains(item)) {
                tempBag.remove(item); // Remove the item from tempBag
            } else {
                resultBag.add(item); // Add the item to the result bag
            }
        }
        return resultBag;
    }
}
