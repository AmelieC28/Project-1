import static org.junit.Assert.*;
import org.junit.Test;


public class LinkedBagTest {


    @Test
    public void testUnion() {
        // Create bag1 and add items
        LinkedBag<String> bag1 = new LinkedBag<>();
        bag1.add("pizza");
        bag1.add("cheese");
        bag1.add("bread");


        // Create bag2 and add items
        LinkedBag<String> bag2 = new LinkedBag<>();
        bag2.add("pizza");
        bag2.add("tomato");
        bag2.add("bread");
        bag2.add("pizza");


        // Perform union of bag1 and bag2
        BagInterface<String> unionBag = bag1.union(bag2);


        // Check size of union bag
        assertEquals(7, unionBag.getCurrentSize());


        // Check frequency of each item in union bag
        assertEquals(3, unionBag.getFrequencyOf("pizza"));
        assertEquals(1, unionBag.getFrequencyOf("cheese"));
        assertEquals(2, unionBag.getFrequencyOf("bread"));
        assertEquals(1, unionBag.getFrequencyOf("tomato"));
    }


    @Test
    public void testIntersection() {
        // Create bag1 and add items
        LinkedBag<String> bag1 = new LinkedBag<>();
        bag1.add("pizza");
        bag1.add("cheese");
        bag1.add("bread");


        // Create bag2 and add items
        LinkedBag<String> bag2 = new LinkedBag<>();
        bag2.add("pizza");
        bag2.add("tomato");
        bag2.add("bread");
        bag2.add("pizza");


        // Perform intersection of bag1 and bag2
        BagInterface<String> intersectionBag = bag1.intersection(bag2);


        // Check size of intersection bag
        assertEquals(2, intersectionBag.getCurrentSize());


        // Check frequency of each item in intersection bag
        assertEquals(1, intersectionBag.getFrequencyOf("pizza"));
        assertEquals(1, intersectionBag.getFrequencyOf("bread"));
    }


    @Test
    public void testDifference() {
        // Create bag1 and add items
        LinkedBag<String> bag1 = new LinkedBag<>();
        bag1.add("pizza");
        bag1.add("cheese");
        bag1.add("bread");


        // Create bag2 and add items
        LinkedBag<String> bag2 = new LinkedBag<>();
        bag2.add("pizza");
        bag2.add("tomato");
        bag2.add("bread");
        bag2.add("pizza");


        // Perform difference (bag1 - bag2)
        BagInterface<String> differenceBag1 = bag1.difference(bag2);


        // Check size of difference bag
        assertEquals(1, differenceBag1.getCurrentSize());


        // Check frequency of each item in difference bag
        assertEquals(1, differenceBag1.getFrequencyOf("cheese"));


        // Perform difference (bag2 - bag1)
        BagInterface<String> differenceBag2 = bag2.difference(bag1);


        // Check size of difference bag
        assertEquals(2, differenceBag2.getCurrentSize());


        // Check frequency of each item in difference bag
        assertEquals(1, differenceBag2.getFrequencyOf("tomato"));
        assertEquals(1, differenceBag2.getFrequencyOf("pizza"));
    }
}

