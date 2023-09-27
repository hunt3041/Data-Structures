import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a new doubly linked list of strings
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        // Add elements to the list
        list.addLast("apple");
        list.addLast("banana");
        list.addLast("cherry");
        
        Iterator<String> itr = list.iterator();

        
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        
    }
}
  