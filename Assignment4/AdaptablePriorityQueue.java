
public interface AdaptablePriorityQueue<K1,K2,V> {
    Entry<K1,K2,V> insert(K1 key1,K2 key2, V value) throws IllegalArgumentException;
    void remove(Entry<K1,K2,V> entry) throws IllegalArgumentException;
    void replaceKey1(Entry<K1,K2,V> entry, K1 key1) throws IllegalArgumentException;
    void replaceKey2(Entry<K1,K2,V> entry, K2 key2) throws IllegalArgumentException;
    void replaceValue(Entry<K1,K2,V> entry, V value) throws IllegalArgumentException;
}
