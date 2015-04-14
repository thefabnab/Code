package project5;

/**
 * Demonstrates the insert, delete, fetch and update operations of a singly linked
 * list structure of Student Listing objects.
 *
 * @author Derek Holtberg & Nabil Azamy
 * @version 3-16-2015
 */
public class SinglyLinkedList 
{
    private Node h;
    
    /**
     * The constructor creates a "dummy" node, and sets the student listing and next 
     * fields to null.
     */
    public SinglyLinkedList()
    {
        h = new Node();      // The "dummy" node
        h.setListing(null);  // This node will never store a student listing
        h.setNext(null);     // This node ultimately will point to first node in linked list
    }
    
    /**
     * Inserts a new Student Listing node to the end of the linked list.
     * If there is insufficient memory, return false; otherwise return true after
     * the new node is inserted.
     *
     * @param newListing a new Student Listing object
     *
     * @return A true or false value that indicates whether or not
     *         the node was inserted
     */
    public boolean insert(StudentListings newListing)
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
     * a deepCopy of the StudentListings node is returned; otherwise a null object is returned.
     *
     * @param targetKey the search key (name) used to match against a data node
     *
     * @return The matching node from the linked list
     */
    public StudentListings fetch(String targetKey)
    {
        Node p = h.getNext();            // Starts at the first node
        
        while (p != null && !( p.getListing().compareTo(targetKey) == 0) )
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
     * @return A true or false value that indicates whether or not the Student
     *         Listing node was deleted
     */
    public boolean delete(String targetKey)
    {
        Node q = h;                      // The "trailing" reference
        Node p = h.getNext();            // The "search" reference
        
        while (p != null && !( p.getListing().compareTo(targetKey) == 0) )
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
     * Updates the Student Listing node by deleting the node with the old values
     * and replacing it with a node containing new address and number values.
     *
     * @param targetKey the search key (name) used to match against a data node
     * @param newListing the updated Student Listing object
     * 
     * @return A true or false value that indicates whether or not the Student
     *         Listing node was updated
     */
    public boolean update(String targetKey, StudentListings newListing)
    {
        if ( ! delete(newListing.getName()) )
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
     * Returns the nodes in the linked list of Student Listings.
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
     * Nested class for creating Student Listing Node objects.
     */
    public class Node
    {
        private StudentListings l;   // The Student Listing data for the Node object
        private Node next;   // The address of the next Node object
        
        /**
         * A "dummy" constructor for a new Node object.  It does nothing except
         * to instantiate the object.
         */
        public Node() 
        {
        }
        
        /**
         * Sets the Student Listing of this node.
         * @param l the new Student Listing object
         */
        public void setListing(StudentListings l)
        {
            this.l = l;
        }
        
        /**
         * Returns the Student Listing of this node.
         * @return The Student Listing
         */
        public StudentListings getListing()
        {
            return l;
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
}
