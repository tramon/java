package lab10;

/**
 * Generics example: a class with two independent type parameters.
 */
public class Pair<K, V> {

    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    /** Returns a new pair with key and value swapped (types swap too). */
    public Pair<V, K> swap() {
        return new Pair<>(value, key);
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

}
