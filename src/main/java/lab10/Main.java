package lab10;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Generics ===");
        genericsDemo();

        System.out.println();
        System.out.println("=== Wildcards ===");
        wildcardsDemo();
    }

    private static void genericsDemo() {
        // Box<T>: type-safe, no casts needed
        Box<Integer> numbers = Box.of(5, 42, 17);
        int max = numbers.max();
        System.out.println(numbers + " max = " + max);

        Box<String> words = Box.of("pear", "apple", "banana");
        System.out.println(words + " max = " + words.max());

        // Generic method map(): Box<String> -> Box<Integer>
        Box<Integer> lengths = words.map(String::length);
        System.out.println("lengths = " + lengths);

        // Compile-time safety: this would not compile
        // numbers.add("text");

        // Pair<K, V>: two type parameters
        Pair<String, Integer> age = new Pair<>("Max", 22);
        Pair<Integer, String> swapped = age.swap();
        System.out.println(age + " swapped -> " + swapped);
    }

    private static void wildcardsDemo() {
        List<Integer> ints = List.of(1, 2, 3);
        List<Double> doubles = List.of(1.5, 2.5);
        List<String> strings = List.of("a", "b");

        // ? : any list
        System.out.print("printAll: ");
        WildcardUtils.printAll(ints);
        WildcardUtils.printAll(strings);

        // ? extends Number: read-only producer
        System.out.println("sum(ints) = " + WildcardUtils.sum(ints));
        System.out.println("sum(doubles) = " + WildcardUtils.sum(doubles));
        // WildcardUtils.sum(strings); // does not compile: String is not a Number

        // ? super Integer: write-only consumer
        List<Number> numbers = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        WildcardUtils.fillWithIntegers(numbers, 3);
        WildcardUtils.fillWithIntegers(objects, 2);
        System.out.println("numbers = " + numbers + ", objects = " + objects);

        // PECS: copy from List<Integer> into List<Number>
        WildcardUtils.copy(ints, numbers);
        System.out.println("after copy = " + numbers);

        // Wildcard together with a custom generic class
        List<Pair<String, ?>> pairs = List.of(
                new Pair<>("id", 1),
                new Pair<>("name", "Max"));
        WildcardUtils.printKeys(pairs);
    }
}
