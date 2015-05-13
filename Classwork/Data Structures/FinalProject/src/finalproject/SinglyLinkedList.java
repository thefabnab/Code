package finalproject;

/**
 * Demonstrates the insert, delete, fetch and update operations of a singly linked
 * list structure of Employee Listing objects.
 *
 * @author Derek Holtberg & Nabil Azamy
 * @version 5/12/15
 */
public class SinglyLinkedList 
{
    private Node h;
    private String rank; //Establishes name of rank within the company
    
    /**
     * The constructor creates a "dummy" node, and sets the employee listing and next 
     * fields to null.
     * 
     */
    
    public SinglyLinkedList()
    {
        h = new Node();      // The "dummy" node
        h.setListing(null);  // This node will never store an employee listing
        h.setNext(null);     // This node ultimately will point to first node in linked list
    }
    
    /**
     * Similar to the no argument constructor, this second constructor creates a "dummy"
     * node, and sets the first employee listing to null however it also takes the
     * rank argument as well. The rank argument establishes the position within the company
     * this singly linked list takes.
     *
     * @param rank  The position within the company this linked list takes
     */
    public SinglyLinkedList(String rank)
    {
        this.rank = rank;    // The rank within the company
        h = new Node();      // The "dummy" node
        h.setListing(null);  // This node will never store an employee listing
        h.setNext(null);     // This node ultimately will point to first node in linked list
    }
    
    /**
     * Inserts a new Employee Listing node to the end of the linked list.
     * If there is insufficient memory, return false; otherwise return true after
     * the new node is inserted.
     *
     * @param newListing a new Employee Listing object
     *
     * @return A true or false value that indicates whether or not
     *         the node was inserted
     */
    public boolean insert(EmployeeListing newListing)
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
     * a deepCopy of the Employee Listing node is returned; otherwise a null object is returned.
     *
     * @param targetKey the search key (name) used to match against a data node
     *
     * @return The matching node from the linked list
     */
    public EmployeeListing fetch(String targetKey)
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
     * @return A true or false value that indicates whether or not the Employee
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
     * Updates the Employee Listing node by deleting the node with the old values
     * and replacing it with a node containing new address and number values.
     *
     * @param targetKey the search key (name) used to match against a data node
     * @param newListing the updated Employee Listing object
     * 
     * @return A true or false value that indicates whether or not the Employee
     *         Listing node was updated
     */
    public boolean update(String targetKey, EmployeeListing newListing)
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
     * Returns the nodes in the linked list of Employee Listings.
     *
     * @return The nodes from the linked list
     */
    @Override
    public String toString()
    {
        StringBuilder outputl = new StringBuilder();
        
        Node p = h.getNext();
        
        while (p != null)
        {
            outputl.append( p.getListing() );
            outputl.append("\n");
            p = p.getNext();
        }
        
        return outputl.toString();
    }
    
    /**
     * Returns the rank of the Singly Linked Object. The rank is then used to
     * be pre-processed and stored within the hash table object.
     *
     * @return  The rank of the SinglyLinkedList object
     */
    public String getKey()
    {
        return rank;
    }
    
    /**
     * Returns a deep copy of the singly linked list object to be used within
     * the hash table object
     *
     * @return  A clone of the Singly Linked List object
     */
    public SinglyLinkedList deepCopy()
    {
        SinglyLinkedList clone = new SinglyLinkedList(rank);
        return clone;
    }
    
    /**
     * Compares the rank variable to a specific target key. It is used when comparing
     * One singly linked List to another.
     *
     * @param targetKey A comparison argument
     * @return  A return of 0 indicates a match
     */
    public int compareTo(String targetKey)
    {
        return ( rank.compareTo(targetKey) );
    }
    
    /**
     * Nested class for creating an Employee Listing Node objects.
     */
    public class Node
    {
        private EmployeeListing l;   // The Employee Listing data for the Node object
        private Node next;   // The address of the next Node object
        
        /**
         * A "dummy" constructor for a new Node object.  It does nothing except
         * to instantiate the object.
         */
        public Node() 
        {
            
        }
        
        /**
         * Sets the Employee Listing of this node.
         * @param l the new Employee Listing object
         */
        public void setListing(EmployeeListing l)
        {
            this.l = l;
        }
        
        /**
         * Returns the Employee Listing of this node.
         * @return The Employee Listing
         */
        public EmployeeListing getListing()
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
