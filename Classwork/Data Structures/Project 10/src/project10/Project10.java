package project10;

/**
 * This project is a Professor Listings application which stores the inputted data
 * into a binary tree via the binary tree class data structure.
 *
 * @author Nabil Azamy & Derek Holtberg
 */
public class Project10 
{

    /**
     * The Professor Listing main method which demonstrates the insert, show all, update,
     * fetch and delete operations of the BinaryTree data structure
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       BinaryTree tree = new BinaryTree();
        System.out.println("***** Empty Structure *****");
        //return an empty structure
        tree.showAll();
        
        // Insert nodes
        System.out.println("***** Insert test *****");
        System.out.println( tree.insert( new ProfessorListing("Will Stanton", "Physics", "Condensed Matter", "SteveJive@gmail.com") ) );
        System.out.println( tree.insert( new ProfessorListing("Steve Ray", "Gym", "Volleyball", "JackJohnson@james.com") ) );
        System.out.println( tree.insert( new ProfessorListing("Alice Jordan", "Chemistry", "Oragnic Chemistry", "AllieMallie@gmail.com") ) );
        System.out.println( tree.insert( new ProfessorListing("Beatrice Brandon", "Biology", "Genomics", "BranBio@gmail.com") ) );
        System.out.println( tree.insert( new ProfessorListing("Jason Williams", "English", "Creative Writing", "WillShake4Funds@gmail.com") ) );
        System.out.println( tree.insert( new ProfessorListing("Fran Stevenson", "Math", "Applied Statistics", "FranLibenz@gmail.com") ) );
        System.out.println( tree.insert( new ProfessorListing("Igor Thompson", "Engineering", "Mechanical Engineering", "ThompsonForce@gmail.com") ) );
        System.out.println( tree.insert( new ProfessorListing("Agnas Norris", "Psychology", "BioPsych", "BioNorrisFreud@gmail.com") ) );
        System.out.println( tree.insert( new ProfessorListing("Emily Borris", "Music", "Romantic Period ", "EMBo@gmail.com") ) );
        System.out.println( tree.insert( new ProfessorListing("Vicky Allis", "Computer Science", "Java", "AllisFormat@gmail.com") ) );
        
        // Show all nodes using the recursive binary tree method
        System.out.println("\n***** Show all *****");
        tree.showAll();
        
        // Fetch nodes
        System.out.println("\n***** Fetch test *****");
        System.out.println( tree.fetch("Agnas Norris") );
        System.out.println( tree.fetch("Beatrice Brandon") );
        System.out.println( tree.fetch("Fran Stevenson") );
        System.out.println( tree.fetch("Vicky Allis") );
        
        // Delete nodes
        System.out.println("\n***** Delete test *****");
        System.out.println("Delete a node with NO children");
        System.out.println( tree.delete("Emily Borris") );
        System.out.println("Delete a node with ONE children");
        System.out.println( tree.delete("Alice Jordan") );
        System.out.println("Delete a node with TWO children");
        System.out.println( tree.delete("Steve Ray") );
        
        // After Deletion \
        System.out.println("\n**** Show all after delete *****");
        tree.showAll();
        
        // Update node
        System.out.println("***** Update test *****");
        System.out.println( tree.update(new ProfessorListing("Jason Williams", "Biology", "Condensed Matter", "SteveJive@gmail.com") ) );
        
        // After Update
        System.out.println("\n**** Show all after delete *****");
        tree.showAll();
    }
    
}
