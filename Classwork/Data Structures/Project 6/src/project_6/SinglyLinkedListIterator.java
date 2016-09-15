package project_6;

/**
 * Demonstrates the insert, delete, fetch and update operations of a singly linked
 * list structure of the professor Listing.
 *
 * @author Nabil Azamy & Derek
 */
public class SinglyLinkedListIterator 
{
    private Node h;
    public Iterator i;
    
    /**
     * The constructor creates the "dummy" node, and sets the Professor Listing and next 
     * fields to null.
     */
    public SinglyLinkedListIterator()
    {
        h = new Node();      // The "dummy" node
        h.setListing(null);  // This node will never store a Professor Listing
        h.setNext(null);     // This node ultimately will point to first node in linked list
        
        i = new Iterator();  // The iterator
    }
    
    /**
     * Inserts a new Professor Listing node to the end of the linked list.
     * If there is insufficient memory, return false; otherwise return true after
     * the new node is inserted.
     *
     * @param newListing a new Professor Listing object
     *
     * @return A true or false value that indicates whether or not
     *         the node was inserted
     */
    public boolean insert(ProfessorListing newListing)
    {
        Node n = new Node();
        
        if (n == null)
        {
            return false;
        }
        else
        {
            n.setNext( h.getNext() );
            h.setNext(n);
            n.setListing( newListing.deepCopy() );
            return true;
        }
    }
    
    /**
     * Finds and returns (fetches) a matching node from within the linked list.
     * The while loop looks for the matching node.  If there is a matching node, 
     * a deepCopy of the Listing node is returned; otherwise a null object is returned.
     *
     * @param targetKey the search key (name) used to match against a data node
     *
     * @return The matching node from the linked list
     */
    public ProfessorListing fetch(String targetKey)
    {
        Node p = h.getNext();            
        
        while ( p != null && !( p.getListing().compareTo(targetKey) == 0) )
        {
            p = p.getNext();
        }
        
        if (p != null)
        {
            return p.getListing().deepCopy();
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Deletes a node from within the linked list.  The while loop looks for the
     * matching node.  If there is a matching node, the next node is set to that 
     * of the previous node effectively "jumping over" the found node.
     *
     * @param targetKey the search key (name) used to match against a data node
     *
     * @return A true or false value that indicates whether or not the professor
     *         Listing node was deleted
     */
    public boolean delete(String targetKey)
    {
        Node q = h;                      
        Node p = h.getNext();            
        
        while ( p != null && !( p.getListing().compareTo(targetKey) == 0) )
        {
            q = p;
            p = p.getNext();
        }
        
        if (p != null)
        {
            q.setNext( p.getNext() );
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Updates the Professor Listing node by deleting the node with the old values
     * and replacing it with a node containing new address and number values.
     *
     * @param newListing the updated professor Listing object
     * 
     * @return A true or false value that indicates whether or not the professor
     *         Listing node was updated
     */
    public boolean update(ProfessorListing newListing)
    {
        if ( ! delete( newListing.getName() ) )
        {
            return false;
        }
        else if ( ! insert(newListing) )
        {
            return false;
        }
        
        return true;
    }

    /**
     * Returns the nodes in the linked list of the Professor Listings.
     *
     * @return The nodes from the linked list
     */
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        
        Node p = h.getNext();
        
        while (p != null)
        {
            output.append( p.getListing() );
            output.append("\n");
            p = p.getNext();
        }
        
        return output.toString();
    }
    
    /**
     * Nested class for creating Professor List node objects.
     */
    public class Node
    {
        private ProfessorListing professorListing;  
        private Node next;             
        
        /**
         * A "dummy" constructor for a new Node object.  It does nothing except
         * to instantiate the object.
         */
        public Node() 
        {
        }
        
        /**
         * Sets the professor Listing of this node.
         * @param professorListing the professor listing 
         */
        public void setListing(ProfessorListing professorListing)
        {
            this.professorListing = professorListing;
        }
        
        /**
         * Returns the professor listing of this node.
         * @return The professor Listing
         */
        public ProfessorListing getListing()
        {
            return professorListing;
        }
        
        /**
         * Sets the next node after this node.
         * @param next the next node after this node
         */
        public void setNext(Node next)
        {
            this.next = next;
        }
        
        /**
         * Returns the next node after this node.
         * @return The next node after this node
         */
        public Node getNext()
        {
            return next;
        }
    }

    /**
     * Nested class for creating an iterator that moves through a linked list of 
     * professor Listing node objects.
     */    
    public class Iterator
    {
        private Node ip;    // A node object for moving from one node to the next
        
        /**
         * The constructor calls the reset() method which moves the iterator
         * to the header node in the linked list.
         */
        public Iterator()
        {
            reset();
        }
        
        /**
         * Moves the iterator back to the header node in the linked list.
         */
        public void reset()
        {
            ip = h;
        }
        
        /**
         * Returns a boolean value of true if the iterator is not at the last node
         * in the linked list or false it is at the last node.
         * 
         * @return A value either true or false that indicates if the iterator is 
         *         at the last node
         */
        public boolean hasNext()
        {
            return ip.getNext() != null;
        }
        
        /**
         * Moves the iterator to the next node in the linked list of professor Listings
         * and returns that node.
         * 
         * @return The next node in the linked list of professor listings
         */
        public ProfessorListing next()
        {
            ip = ip.getNext();
            return ip.getListing().deepCopy();
        }
        
        /**
         * Updates the node in the linked list of professor Listing objects at the current
         * location of the iterator.
         * 
         * @param newListing a new professor Listing object
         */
        public void set(ProfessorListing newListing)
        {
            ip.setListing( newListing.deepCopy() );
        }
    }
}
