
public interface BagInterface<T> {
    int getCurrentSize();
    boolean isEmpty();
    boolean add(T newEntry);
    T remove();
    boolean remove(T anEntry);
    void clear();
    int getFrequencyOf(T anEntry);
    boolean contains(T anEntry);
    T[] toArray();
    BagInterface<T> union(BagInterface<T> otherBag);
    BagInterface<T> intersection(BagInterface<T> otherBag);
    BagInterface<T> difference(BagInterface<T> otherBag);
}

