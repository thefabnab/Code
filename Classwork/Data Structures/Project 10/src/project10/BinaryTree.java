package project10;

/**
 * Demonstrates the insert, delete, fetch, update, and recursive show-all operations of a binary tree data
 * structure of the Professor Listing objects.
 *
 * @author Nabil Azamy & Derek Holtberg
 * @version 4/20/2015
 */
public class BinaryTree 
{
    TreeNode root;  // The initial null TreeNode for the BinaryTree object.
    
    /**
     * 
     */
    public BinaryTree()
    {
        root = null;
    }
    
    /**
     * Inserts a Professor Listing node into the BinaryTree.  Returns a boolean value
     * that indicates whether or not the node was inserted.
     * 
     * @param newListing a new Professor Listing object
     * @return a boolean value that indicates if the node was inserted
     */
    public boolean insert(ProfessorListing newListing)
    {
        TreeNodeWrapper parent = new TreeNodeWrapper();
        TreeNodeWrapper child = new TreeNodeWrapper();
        TreeNode n = new TreeNode();
        
        if (n == null)  // out of memory
        {
            return false;
        }
        else  // insert the node
        {
            n.setNode(newListing);
            n.setLeftChild(null);
            n.setRightChild(null);
            
            if (this.root == null)  // tree is empty
            {
                this.root = n;
            }
            else
            {
                findNode( newListing.getKey(), parent, child );
                
                if ( newListing.getKey().compareTo( parent.get().node.getKey() ) < 0 )
                {
                    parent.get().setLeftChild(n);
                }
                else
                {
                    parent.get().setRightChild(n);
                }
            }
        }
        
        return true;
    }
    
    /**
     * Returns a Professor Listing node from the BinaryTree.  Returns 'null' if a matching 
     * node is not found.
     * 
     * @param targetKey the target name of a Student Listing object
     * @return a Professor Listing node
     */
    public ProfessorListing fetch(String targetKey)
    {
        boolean found;
        
        TreeNodeWrapper parent = new TreeNodeWrapper();
        TreeNodeWrapper child = new TreeNodeWrapper();
        
        found = findNode(targetKey, parent, child);
        
        if (found)
        {
            return child.get().getNode().deepCopy();
        }
        else
        {
            return null;
        }
    }

    /**
     * Deletes a Professor Listing node that matches the target key from the BinaryTree.
     * Returns a boolean value that indicates whether or not the node was deleted.
     * 
     * @param targetKey
     * @return a boolean value that indicates if the node was deleted
     */
    public boolean delete(String targetKey)
    {
        boolean found;
        
        TreeNodeWrapper parent = new TreeNodeWrapper();
        TreeNodeWrapper child = new TreeNodeWrapper();
        
        TreeNode largest;
        TreeNode nextLargest;

        found = findNode(targetKey, parent, child);
        
        if( ! found )//found == false)  // node not found
        {
            return false;
        }
        else                 // identify the case number (type of case)
        {
            // Case 1 : deleted node has no children
            if (child.get().getLeftChild() == null && child.get().getRightChild() == null)
            {
                if( parent.get().getLeftChild() == child.get() )  // deleted node is a left child
                {
                    parent.get().setLeftChild(null); 
                }
                else	                                        // deleted node is a right child
                {
                    parent.get().setRightChild(null);
                }
            }
            // Case 2 : deleted node has 1 child
            else if( child.get().getLeftChild() == null || child.get().getRightChild() == null)
            {
                if ( parent.get().getLeftChild() == child.get() )  // deleted node is a left child
                {
                    if ( child.get().getLeftChild() != null)  // deleted node has a left child
                    {
                        parent.get().setLeftChild( child.get().getLeftChild() );
                    }
                    else
                    {
                        parent.get().setLeftChild( child.get().getRightChild() );
                    }
                }
                else                                              // deleted node is a right child
                {
                    if( child.get().getLeftChild() != null)            // deleted node has a left child
                    {
                        parent.get().setRightChild( child.get().getLeftChild() );
                    }
                    else
                    {
                        parent.get().setRightChild( child.get().getRightChild() );
                    }
                }
            }
            else // Case 3:  deleted node has two children
            {
                nextLargest = child.get().getLeftChild();
                largest = nextLargest.getRightChild();
                
                if (largest != null)  // left child has a right subtree
                {
                    while( largest.getRightChild() != null)  // move down right subtree
                    {
                        nextLargest = largest;
                        largest = largest.getRightChild();
                    }
                    
                    child.get().setNode( largest.getNode() );
                    nextLargest.setRightChild( largest.getLeftChild() );
                }
                else                 // left child does not have a right subtree
                {
                    nextLargest.setRightChild( child.get().getRightChild() ); // save the right subtree
                    
                    if( parent.get().getLeftChild() == child.get() )  // deleted node is a left child
                    {
                        parent.get().setLeftChild(nextLargest);  // jump around deleted node
                    }    
                    else	                       // deleted node is a right child
                    {
                        parent.get().setRightChild(nextLargest);     // jump around deleted node
                    }
                }
            }
        }
        
        return true;
    }
    
    /**
     * Updates a Professor Listing node that matches the target key from the listing object
     * parameter.  Returns a boolean value that indicates whether or not the node was updated.
     * 
     * @param newListing a Professor Listing node to be updated
     * @return a boolean value that indicates if the node was updated
     */
    public boolean update(ProfessorListing newListing)
    {
        String targetKey = newListing.getKey();
        
        if ( ! delete(targetKey) )
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
     * Finds the address of a Professor Listing node that matches the target key in the BinaryTree.
     * Returns a boolean value that indicates whether or not the node was found.
     * 
     * @param targetKey the target name of a Professor Listing object
     * @param parent the parent to this node
     * @param child the child to this node
     * @return a boolean value that indicates if the node was found
     */
    private boolean findNode(String targetKey, TreeNodeWrapper parent, TreeNodeWrapper child)
    {
        parent.set(root) ;
        child.set(root) ;

        if( root == null)  // tree is empty
        {
            return true;
        }

        while(child.get() != null)
        {
            if( targetKey.compareTo( child.get().getNode().getKey() ) == 0 )  // node found
            {
                return true;
            }
            else
            {
                parent.set( child.get() );
                
                if( targetKey.compareTo( child.get().getNode().getKey() ) < 0)
                {
                    child.set( child.get().getLeftChild() );
                }
                else
                {
                    child. set( child.get().getRightChild() ) ;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Displays a string representation of each of the Professor Listing nodes from the
     * BinaryTree object.  Returns an 'empty' message if the tree contain no nodes.
     */
    public void showAll()
    {
        if (this.root == null)
        {
            System.out.println("The structure is empty");
        }
        else
        {
            lnrOutputTraversal(this.root);
        }
    }
    
    /**
     * A recursive method that traverses through the nodes of a BinaryTree object
     * returning a string representations of each of the nodes in the tree.
     * 
     * @param root the root of a 'sub' tree in the TreeNode object
     */
    public void lnrOutputTraversal(TreeNode root)
    {
        if (root.getLeftChild() != null)
        {
            lnrOutputTraversal( root.getLeftChild() );
        }
        
        System.out.println( root.getNode() );
        
        if (root.getRightChild() != null)
        {
            lnrOutputTraversal( root.getRightChild() );
        }
    }
    
    /**
     * Manages the properties of a Professor Listing node including the address locations 
     * of the child nodes to the left and right of this node
     */
    public class TreeNode
    {
        private ProfessorListing node;         // address of a node containing Professor name, department, expertise, and email
	private TreeNode leftChild;   // address of the left child to this node
	private TreeNode rightChild;  // address of the right child to this node
        
	public TreeNode()
        {
        }
        
        /**
         * Assigns a deep copy of a new Professor Listing to the TreeNode object
         * 
         * @param newListing a new Professor Listing object
         */
        public void setNode(ProfessorListing newListing)
        {
            node = newListing.deepCopy();
        }
        
        /**
         * Returns a TreeNode with a Professor Listing object
         * 
         * @return a Professor Listing node
         */
        public ProfessorListing getNode()
        {
            return node;
        }
        
        /**
         * Updates the address of the node of left child for TreeNode of a Professor Listing object
         * 
         * @param leftChild the address of the left child node for this TreeNode
         */
        public void setLeftChild(TreeNode leftChild)
        {
            this.leftChild = leftChild;
        }

        /**
         * Returns the address of the node of left child for this TreeNode of a Professor Listing object
         * 
         * @return the address of the left child node for this TreeNode
         */
        public TreeNode getLeftChild()
        {
            return leftChild;
        }

        /**
         * Updates the address of the node of right child for TreeNode of a Professor Listing object
         * 
         * @param rightChild the address of the right child node for this TreeNode
         */
        public void setRightChild(TreeNode rightChild)
        {
            this.rightChild = rightChild;
        }

        /**
         * Returns the address of the node of right child for this TreeNode of a Professor Listing object
         * 
         * @return the address of the right child node for this TreeNode
         */
        public TreeNode getRightChild()
        {
            return rightChild;
        }
    }
    
    /**
     * A class which provides a tree node additional methods
     * 
     */
    public class TreeNodeWrapper
    {
        private TreeNode treeRef;
        
        /**
         * The default, null constructor setting the initial tree node to null
         */
        public TreeNodeWrapper()
        {
            //treeRef = null;
            set(null);
        }
        
        /**
         * A getter which to return the null value of the initial tree node
         * 
         * @return which states the value of the treeRef variable, normally set to null
         */
        public TreeNode get()
        {
            return treeRef;
        }
        
        /**
         * This setter method sets the value of the initial tree node from an input reference
         * 
         * @param treeRef 
         */
        public void set(TreeNode treeRef)
        {
            this.treeRef = treeRef;
        }
    }
}
