package mypackage;


//Interface for a key-value pair. 
public interface Entry<K1,K2,V> {
    K1 getKey1( ); // returns the first key stored in this entry
    K2 getKey2( ); // return the second key stored in theis entry
    V getValue( ); // returns the value stored in this entry
}

