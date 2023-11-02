
public class Main {
    public static void main(String[] args){
        
        HeapAdaptablePriorityQueue<String, Integer> priQueue = new HeapAdaptablePriorityQueue<>();


        priQueue.insert("e", 102);
        priQueue.insert("c", 55);
        priQueue.insert("a", 9);
        priQueue.insert("b", 12);
        priQueue.insert("d", 67);
        

        System.out.println(priQueue.size());
        System.out.println(priQueue.min().getValue());

        

    }


    
}
