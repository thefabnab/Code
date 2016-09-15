package project4;

/**
 * A queue class that accepts generic objects and establishes a Queue data structure
 *
 * @author Derek Holtberg & Nabil Azamy
 * @version 3/9/15
 */
public class Queue<T> 
{
    private T[] data;
    
    private int numOfNodes;
    private int front;
    private int rear;
    
    /**
     * The maximum size of the queue--a constant.
     */
    public final int SIZE;
    
    /**
     * Creates a default stack of 100 phone Listing objects.
     */
    public Queue()
    {
        this(100);
    }
    
    /**
     * Creates a new empty queue of phone Listing objects set to the size of 
     * the 'size' parameter.
     * 
     * @param size the size of the new queue
     */
    public Queue(int SIZE)
    {
        this.SIZE = SIZE;
        numOfNodes = 0;
        front = 0;
        rear = 0;
        data = ( T[] )new Object[SIZE];
    }
    
    
    /**
     * Inserts (enqueues) a new node into the rear (back) of the queue.  Returns 
     * boolean value true if the enqueue operation was successful; returns false 
     * if there was an overflow error.
     * 
     * @param newNode the new node inserted into rear of the queue
     * @return A boolean value that indicates if enqueue operation was successful
     */
    public boolean enque(T newNode)
    {
        KeyMode node = (KeyMode) newNode;
        
        if (numOfNodes == SIZE)
        {
            System.out.println("***** Stack Overflow *****");
            return false;
        }
        else
        {
            numOfNodes++;
            data[rear] = (T) node.deepCopy();
            rear = (rear + 1) % SIZE; // circular queue
            return true;
        }
    }
    
    /**
     * Removes (dequeues) and returns the node currently at the 
     * front of the queue.  Returns -1 if there was an underflow error.
     * 
     * @return the generic node removed from the front of the queue
     */
    public T deque()
    {
        int temp;
        
        if (numOfNodes == 0)
        {
            System.out.println("***** Stack Underflow *****");
            return null;
        }
        else
        {
            temp = front;
            front = (front + 1) % SIZE; // circular queue
            numOfNodes--;
            return data[temp];
        }
    }
    
    /**
     * Returns the list of generic objects from the queue.
     * 
     * @return the generic objects from the queue
     */
    @Override
    public String toString()
    {
        StringBuilder list = new StringBuilder();
        
        int index = front;
        
        for (int ctr = 1; ctr <= numOfNodes; ctr++) 
        {
            list.append(data[index]);
            index = (index + 1) % SIZE;
        }
        
        return list.toString();
    }    
}
