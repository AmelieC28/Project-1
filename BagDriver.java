import java.util.Arrays;

public class BagDriver {
    public static void main(String[] args) {
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();

        bag1.add("pizza");
        bag1.add("cheese");
        bag1.add("bread");

        bag2.add("pizza");
        bag2.add("tomato");
        bag2.add("bread");
        bag2.add("pizza");

        BagInterface<String> unionBag = bag1.union(bag2);
        System.out.println("Union: " + Arrays.toString(unionBag.toArray()));

        BagInterface<String> intersectionBag = bag1.intersection(bag2);
        System.out.println("Intersection: " + Arrays.toString(intersectionBag.toArray()));

        BagInterface<String> differenceBag1 = bag1.difference(bag2);
        System.out.println("Difference (bag1 - bag2): " + Arrays.toString(differenceBag1.toArray()));

        BagInterface<String> differenceBag2 = bag2.difference(bag1);
        System.out.println("Difference (bag2 - bag1): " + Arrays.toString(differenceBag2.toArray()));
    }
}
