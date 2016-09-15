/*
 * Main class for the sorted array and listing class.
 */
package chapter2_question19;

/**
 *
 * @author Nabil Azamy and Derek Holtberg
 */
public class Project2
{

    /**
     * This is the main method for testing the sorted array class pertaining to 
     * the student listings node.
     * 
     * @param args the command line arguments
     * 
     **/
    
    public static void main(String[] args)
    {
        /**The constructor expressing the desired size of the array and the data 
         * to be held within it
         **/
        SortedArray array = new SortedArray(100, "Bill", 23, 3.4, 
                                                "Carol", 22, 3.9,
                                                "Mike", 21, 2.4,
                                                "Vick", 46, 3.9);
                                       
        //inserts demonstrating the working insert method
        array.insert("Phil", 21, 1.7);
        array.insert("Charles", 22, 2.3);
        array.insert("Rory", 23, 4.0);
        array.insert("Nick", 12, 3.2);
        array.insert("Harry", 24, 3.5);

        System.out.println( array.toString() );
        //delete demonstrating the working delete method
        array.delete("Carol");
        System.out.println( array.toString() );
        
        //fetch demonstrating the working fetch method
        System.out.println( array.fetch("Mike") );   
    }   
}
