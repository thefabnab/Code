/*
 * Test Class for Project 4
 * 
 */
package project4;

/**
 * This is an app to demonstrate the usage of the Queue restricted data structure
 * in a generic environment for both the StudentListings and Ints class.
 *
 * @author Derek Holtberg & Nabil Azamy
 */
public class Project4 
{

    /**
     * Main method to test out the Queue restricted data structure
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        Queue q = new Queue(2);
        
        System.out.println("\n***** Attempt to dequeue a node from empty queue *****");
        System.out.println( q.deque() );
        
        System.out.println("\n***** Enqueue two Student Listing nodes; last one causes overflow *****");
        System.out.println( q.enque( new StudentListings("Bill", 12345, 4.0) ) );
        System.out.println( q.enque( new StudentListings("Al", 13453, 3.6) ) );
        
        System.out.println("\n***** Display the Student Listing queue *****");
        System.out.println(q);
        
        System.out.println("\n***** Dequeue node Bill from the queue *****");
        System.out.println( q.deque() );
        
        System.out.println("\n***** Display the queue *****");
        System.out.println(q);
        
        System.out.println("\n***** Dequeue remaining node; last one causes underflow *****");
        System.out.println( q.deque() );
        System.out.println( q.deque() );
        
        
        System.out.println("\n***** Attempt to queue 3 ints *****");
        System.out.println( q.enque( new Ints(12,23) ));
        System.out.println( q.enque( new Ints(24,39) ));
        System.out.println( q.enque( new Ints(17,30) ));
        
        System.out.println("\n***** Display the int queue *****");
        System.out.println(q);
        
        System.out.println("\n***** Dequeue int one from the queue *****");
        System.out.println( q.deque() );
        
        System.out.println("\n***** Display the int queue *****");
        System.out.println(q);
        
        System.out.println("\n***** Dequeue remaining int's; last one causes underflow *****");
        System.out.println( q.deque() );
        System.out.println( q.deque() );
        
        
        
    }
    
}
