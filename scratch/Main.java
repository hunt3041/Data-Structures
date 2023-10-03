import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a new doubly linked list of strings
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        // Add elements to the list
        list.addLast("apple");
        list.addLast("banana");
        list.addLast("cherry");
        
        Iterator<String> itr = list.iterator();

        
        while(itr.hasNext()){
            if(itr.next() == "banana"){
                itr.remove();
            }
        }
        Iterator<String> iter = list.iterator();
        System.out.println("printing new list:");
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
        
}

  