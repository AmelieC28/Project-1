public interface BagInterface<T> {
    // Get the current number of items in the bag
    int getCurrentSize();


    // Check if the bag is empty
    boolean isEmpty();


    // Add a new item to the bag
    boolean add(T newEntry);


    // Remove and return a random item from the bag
    T remove();


    // Remove a specific item from the bag
    boolean remove(T anEntry);


    // Remove all items from the bag
    void clear();


    // Get the frequency of a specific item in the bag
    int getFrequencyOf(T anEntry);


    // Check if the bag contains a specific item
    boolean contains(T anEntry);


    // Convert the bag to an array
    T[] toArray();


    // Perform the union of this bag and another bag
    BagInterface<T> union(BagInterface<T> otherBag);


    // Perform the intersection of this bag and another bag
    BagInterface<T> intersection(BagInterface<T> otherBag);


    // Perform the difference of this bag and another bag
    BagInterface<T> difference(BagInterface<T> otherBag);
}



