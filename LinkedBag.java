

public class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    private class Node {
        private T data;
        private Node next;

        private Node(T data, Node nextNode) {
            this.data = data;
            this.next = nextNode;
        }
    }

    public LinkedBag() {
        firstNode = null;
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
        Node newNode = new Node(newEntry, firstNode);
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        T result = firstNode.data;
        firstNode = firstNode.next;
        numberOfEntries--;
        return result;
    }

    @Override
    public boolean remove(T anEntry) {
        Node currentNode = firstNode;
        Node previousNode = null;

        while (currentNode != null) {
            if (currentNode.data.equals(anEntry)) {
                if (previousNode == null) {
                    firstNode = currentNode.next;
                } else {
                    previousNode.next = currentNode.next;
                }
                numberOfEntries--;
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.data.equals(anEntry)) count++;
            currentNode = currentNode.next;
        }
        return count;
    }

    @Override
    public boolean contains(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.data.equals(anEntry)) return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while (currentNode != null) {
            result[index++] = currentNode.data;
            currentNode = currentNode.next;
        }
        return result;
    }

    @Override
    public BagInterface<T> union(BagInterface<T> otherBag) {
        LinkedBag<T> resultBag = new LinkedBag<>();

        Node currentNode = this.firstNode;
        while (currentNode != null) {
            resultBag.add(currentNode.data);
            currentNode = currentNode.next;
        }

        for (T item : otherBag.toArray()) {
            resultBag.add(item);
        }

        return resultBag;
    }

    @Override
    public BagInterface<T> intersection(BagInterface<T> otherBag) {
        LinkedBag<T> resultBag = new LinkedBag<>();
        LinkedBag<T> tempBag = new LinkedBag<>();
        
        Node currentNode = this.firstNode;
        while (currentNode != null) {
            if (otherBag.contains(currentNode.data) && !tempBag.contains(currentNode.data)) {
                resultBag.add(currentNode.data);
                tempBag.add(currentNode.data);
            }
            currentNode = currentNode.next;
        }
        return resultBag;
    }

    @Override
    public BagInterface<T> difference(BagInterface<T> otherBag) {
        LinkedBag<T> resultBag = new LinkedBag<>();
        
        Node currentNode = this.firstNode;
        while (currentNode != null) {
            if (!otherBag.contains(currentNode.data)) {
                resultBag.add(currentNode.data);
            }
            currentNode = currentNode.next;
        }
        return resultBag;
    }
}
