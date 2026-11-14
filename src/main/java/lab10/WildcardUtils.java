package lab10;

import java.util.List;

/**
 * Wildcard examples: unbounded (?), upper bounded (? extends T)
 * and lower bounded (? super T).
 *
 * Rule of thumb (PECS): Producer Extends, Consumer Super.
 */
public final class WildcardUtils {

    private WildcardUtils() {
    }

    /** Unbounded wildcard: works with a list of anything, we only read as Object. */
    public static void printAll(List<?> list) {
        for (Object o : list) {
            System.out.print(o + " ");
        }
        System.out.println();
    }

    /**
     * Upper bounded wildcard: the list is a PRODUCER of Numbers.
     * Accepts List&lt;Integer&gt;, List&lt;Double&gt;, List&lt;Number&gt;...
     * Note: List&lt;Number&gt; would NOT accept List&lt;Integer&gt; (generics are invariant).
     */
    public static double sum(List<? extends Number> numbers) {
        double total = 0;
        for (Number n : numbers) {
            total += n.doubleValue();
        }
        return total;
    }

    /**
     * Lower bounded wildcard: the list is a CONSUMER of Integers.
     * Accepts List&lt;Integer&gt;, List&lt;Number&gt;, List&lt;Object&gt;.
     */
    public static void fillWithIntegers(List<? super Integer> target, int count) {
        for (int i = 1; i <= count; i++) {
            target.add(i);
        }
    }

    /** PECS in one method: read from an "extends" source, write to a "super" destination. */
    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }

    /** Wildcard with a custom generic class from this lab. */
    public static void printKeys(List<? extends Pair<String, ?>> pairs) {
        for (Pair<String, ?> pair : pairs) {
            System.out.print(pair.getKey() + " ");
        }
        System.out.println();
    }
}
