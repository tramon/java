package lab10;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Generics example: a type-safe container with a bounded type parameter
 * and generic methods.
 *
 * @param <T> type of the stored value; must be comparable to itself
 */
public class Box<T extends Comparable<T>> {

    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public T get(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }

    /** Uses the bound {@code T extends Comparable<T>} to call compareTo. */
    public T max() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Box is empty");
        }
        T best = items.get(0);
        for (T item : items) {
            if (item.compareTo(best) > 0) {
                best = item;
            }
        }
        return best;
    }

    /** Generic method: introduces its own type parameter R. */
    public <R extends Comparable<R>> Box<R> map(Function<T, R> mapper) {
        Box<R> result = new Box<>();
        for (T item : items) {
            result.add(mapper.apply(item));
        }
        return result;
    }

    /** Generic static method with an explicit type parameter. */
    public static <E extends Comparable<E>> Box<E> of(E first, E second, E third) {
        Box<E> box = new Box<>();
        box.add(first);
        box.add(second);
        box.add(third);
        return box;
    }

    @Override
    public String toString() {
        return "Box" + items;
    }
}
