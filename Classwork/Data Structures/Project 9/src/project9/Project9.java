package project9;

/**
 * 
 *
 * @author Nabil Azamy & Derek Holtberg
 */
public class Project9 
{

    /**
     * The Student Listings main method which demonstrates the insert, show all,
     * fetch and delete operations of the BinaryTree data structure
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       BinaryTree tree = new BinaryTree();
        
        // Insert nodes
        System.out.println("***** Insert test *****");
        System.out.println( tree.insert( new StudentListings("Will", 1, 3.0) ) );
        System.out.println( tree.insert( new StudentListings("Ted", 2, 2.4) ) );
        System.out.println( tree.insert( new StudentListings("Zack", 3, 1.5) ) );
        System.out.println( tree.insert( new StudentListings("Bill", 4, 3.8) ) );
        System.out.println( tree.insert( new StudentListings("Vick", 5, 2.7) ) );
        System.out.println( tree.insert( new StudentListings("Ann", 6, 4.0) ) );
        System.out.println( tree.insert( new StudentListings("Mike", 7, 1.6) ) );
        System.out.println( tree.insert( new StudentListings("Carol", 8, 2.4) ) );
        System.out.println( tree.insert( new StudentListings("Sally", 9, 3.6) ) );
        System.out.println( tree.insert( new StudentListings("Pat", 10, 3.9) ) );
        
        // Show all nodes
        System.out.println("\n***** Show all *****");
        tree.showAll();
        
        // Fetch nodes
        System.out.println("\n***** Fetch test *****");
        System.out.println( tree.fetch("Carol") );
        System.out.println( tree.fetch("Sally") );
        System.out.println( tree.fetch("Ted") );
        System.out.println( tree.fetch("Teddy") );
        
        // Delete nodes
        System.out.println("\n***** Delete test *****");
        System.out.println("Delete a node with NO children");
        System.out.println( tree.delete("Carol") );
        System.out.println("Delete a node with ONE children");
        System.out.println( tree.delete("Sally") );
        System.out.println("Delete a node with TWO children");
        System.out.println( tree.delete("Ted") );
        
        System.out.println("\n**** Show all after delete *****");
        tree.showAll();
    }
    
}
