package mypackage;
public interface PriorityQueue<K1,K2,V> {
    int size( );
    boolean isEmpty( );
    Entry<K1,K2,V> insert(K1 key1, K2 key2, V value) throws IllegalArgumentException;
    Entry<K1,K2,V> min( );
    Entry<K1,K2,V> removeMin( );
}
