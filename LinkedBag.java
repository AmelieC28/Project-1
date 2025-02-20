public class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode; // First node in the linked list
    private int numberOfEntries; // Number of items in the bag


    // Inner class for Node
    private class Node {
        private T data; // Data stored in the node
        private Node next; // Reference to the next node


        // Constructor for Node
        private Node(T data, Node nextNode) {
            this.data = data;
            this.next = nextNode;
        }
    }


    // Constructor for LinkedBag
    public LinkedBag() {
        firstNode = null; // Initialize the first node as null
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
        // Create a new node and add it to the front of the linked list
        Node newNode = new Node(newEntry, firstNode);
        firstNode = newNode; // Update the first node
        numberOfEntries++; // Increase the number of items
        return true;
    }


    @Override
    public T remove() {
        if (isEmpty()) return null; // If the bag is empty, return null
        T result = firstNode.data; // Remove the first node's data
        firstNode = firstNode.next; // Update the first node to the next node
        numberOfEntries--; // Decrease the number of items
        return result;
    }


    @Override
    public boolean remove(T anEntry) {
        Node currentNode = firstNode;
        Node previousNode = null;


        // Search for the item to remove
        while (currentNode != null) {
            if (currentNode.data.equals(anEntry)) {
                // If the item is found, remove it
                if (previousNode == null) {
                    firstNode = currentNode.next; // If it's the first node, update firstNode
                } else {
                    previousNode.next = currentNode.next; // Otherwise, bypass the current node
                }
                numberOfEntries--; // Decrease the number of items
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return false; // Item not found
    }


    @Override
    public void clear() {
        firstNode = null; // Clear the first node
        numberOfEntries = 0; // Reset the number of items
    }


    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        Node currentNode = firstNode;


        // Count how many times the item appears in the bag
        while (currentNode != null) {
            if (currentNode.data.equals(anEntry)) count++;
            currentNode = currentNode.next;
        }
        return count;
    }


    @Override
    public boolean contains(T anEntry) {
        Node currentNode = firstNode;


        // Check if the item is in the bag
        while (currentNode != null) {
            if (currentNode.data.equals(anEntry)) return true;
            currentNode = currentNode.next;
        }
        return false;
    }


    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries]; // Create an array to store the items
        int index = 0;
        Node currentNode = firstNode;


        // Copy items from the linked list to the array
        while (currentNode != null) {
            result[index++] = currentNode.data;
            currentNode = currentNode.next;
        }
        return result;
    }


    @Override
    public BagInterface<T> union(BagInterface<T> otherBag) {
        LinkedBag<T> resultBag = new LinkedBag<>();


        // Add all items from this bag
        Node currentNode = this.firstNode;
        while (currentNode != null) {
            resultBag.add(currentNode.data);
            currentNode = currentNode.next;
        }


        // Add all items from the other bag
        for (T item : otherBag.toArray()) {
            resultBag.add(item);
        }


        return resultBag;
    }


    @Override
    public BagInterface<T> intersection(BagInterface<T> otherBag) {
        LinkedBag<T> resultBag = new LinkedBag<>();
        LinkedBag<T> tempBag = new LinkedBag<>();


        // Iterate through this bag
        Node currentNode = this.firstNode;
        while (currentNode != null) {
            // If the item is in both bags and not already added, add it to the result bag
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


        // Create a temporary bag to track elements from otherBag
        LinkedBag<T> tempBag = new LinkedBag<>();
        Node otherCurrentNode = ((LinkedBag<T>) otherBag).firstNode;
        while (otherCurrentNode != null) {
            tempBag.add(otherCurrentNode.data);
            otherCurrentNode = otherCurrentNode.next;
        }


        // Iterate through this bag and add elements to resultBag if they are not in tempBag
        Node currentNode = this.firstNode;
        while (currentNode != null) {
            if (!tempBag.contains(currentNode.data)) {
                resultBag.add(currentNode.data);
            } else {
                tempBag.remove(currentNode.data);
            }
            currentNode = currentNode.next;
        }


        return resultBag;
    }
}

