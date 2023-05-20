package Code;

import java.util.Map;

public class Pair<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;


    /**
     * This will create a single object with key value pairs
     * @param key: Key to identify the value
     * @param value: value
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V previousValue = this.value;
        this.value = value;
        return previousValue;
    }

    @Override
    public String toString() {
        return "key=" + key + ", value=" + value;
    }
}
