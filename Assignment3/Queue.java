

public class Queue<T> {
    private int size; 
    private DoublyLinkedList<T> list = new DoublyLinkedList<>();

    // default constructor
    public Queue(){};

    // constructor with argument
    public Queue(DoublyLinkedList<T> list){
        this.list = list;
        this.size = list.size();
    }

    // return the size of the queue
    public int size(){return this.size;}

    // returns if the queue is empty or not
    public boolean isEmpty(){return this.size == 0;}

    // enqueues an element to the list
    public void enqueue(T element){
        this.list.addLast(element);
        this.size += 1;
        // System.out.println("From Queue(queue): queueing: " + element);
    }

    // removes the first element in queue
    public T dequeue(){
        T element = this.list.removeFirst();
        this.size -= 1;
        // System.out.println("From Queue(dequeue): dequeueing: " + element);
        return element;


    }

    // returns the first element in the queue
    public T getFirst(){return this.list.first();}
}
