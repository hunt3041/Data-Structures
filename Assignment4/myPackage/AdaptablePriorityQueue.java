package myPackage;
public interface AdaptablePriorityQueue<K, V> {
    Entry<K, V> insert(K key, V value) throws IllegalArgumentException;
    void remove(Entry<K, V> entry) throws IllegalArgumentException;
    void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException;
    void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException;
}
