package finalproject;

/**
 * A hashed data structure class that uses both a fold-shifting pre-processing
 * and linear quotient collision algorithms to store Singly Linked Lists containing
 * Employee Listing objects.
 *
 * @author Nabil Azamy & Derek Holtberg
 * @version 5/12/15
 */
public class Hashed 
{
    final int SIZE;
    int n = 0;                    // The number of nodes in the structure
    int defaultQuotient = 9967;   // The default 4k + 3 prime
    double loadingFactor = 0.75;
    SinglyLinkedList deleted;              // The dummy node, v2 (v1 = null)
    private SinglyLinkedList[] data;       // The primary storage array of Singly Linked Lists
    
    /**
     * The No-Argument constructor establishes the size of the array variable. 
     *
     */
    public Hashed()
    {
        SIZE = 100;
    }
    
    /**
     * Sets the number of nodes in the hashed structure and instantiates an array of 
     * Singly Linked List objects to that size.
     * 
     * @param length the number of nodes in the structure
     */
    public Hashed(int length)
    {
        int pct = (int) ( ( loadingFactor - 1) * 100.0);
        SIZE = fourKPlus(length, pct);
        data = new SinglyLinkedList[SIZE];
        deleted = new SinglyLinkedList();
        
        for (int index = 0; index < SIZE; index++)
        {
            data[index] = null;
        }
    }
    
    /**
     * Inserts a Singly Linked List object into the hashed table. Returns boolean value 
     * true if the node was inserted or false if the insert failed.
     * 
     * @param newSinglyLinkedListing    A singly linked listing to be inserted into the structure
     * @return A boolean value that indicates whether or not the insert was successful
     */
    public boolean insert(SinglyLinkedList newSinglyLinkedListing)
    {
        boolean hit = false;
        int pass, q, offset, ip;
        
        int pseudoKey = stringToInt( newSinglyLinkedListing.getKey() );
        
        if ( (double) n  / SIZE < loadingFactor)
        {
            pass = 0;
            q = pseudoKey / SIZE;
            offset = q;
            ip = pseudoKey % SIZE;
            
            if (q % SIZE == 0)
            {
                offset = defaultQuotient;
            }
            
            while (pass < SIZE)
            {
                if (data[ip] == null || data[ip] == deleted)
                {
                    hit = true;
                    break;
                }
                
                ip = (ip + offset) % SIZE;
                pass++;
            }
            
            if (hit)
            {
                data[ip] = newSinglyLinkedListing;
                n++;
                return true ;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Returns a deep copy of a Singly Linked List Object from the hashed table. 
     * A null value is returned if the target key does not match an element in 
     * the table.
     * 
     * @param targetKey rank field of a Singly Linked List object
     * @return A Singly Linked List object from the array
     */
    public SinglyLinkedList fetch(String targetKey)
    {
        boolean hit = false;
        int pass, q, offset, ip;
        
        int pseudoKey = stringToInt(targetKey);
        pass = 0;
        q = pseudoKey / SIZE;
        offset = q;
        ip = pseudoKey % SIZE;
        
        if (q % SIZE == 0)
        {
            offset = defaultQuotient;
        }
        
        while (pass < SIZE)
        {
            if (data[ip] ==  null)
            {
                break;
            }
            
            if ( data[ip].compareTo(targetKey) == 0)
            {
                hit = true;
                break;
            }
            
            ip = (ip + offset) % SIZE;
            pass++;
        }
        
        if (hit)
        {
            return data[ip];
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Deletes a Singly Linked List node from the hashed table.  Returns the value true if 
     * the target key matches an element in the table and the node is deleted; 
     * otherwise returns false.
     * 
     * @param targetKey rank field of a Singly Linked object
     * @return A boolean value which indicates if the node was deleted
     */
    public boolean delete(String targetKey)
    {
        boolean hit = false;
        int pass, q, offset, ip;
        
        int pseudoKey = stringToInt(targetKey);

        pass = 0;
        q = pseudoKey / SIZE;
        offset = q;
        ip = pseudoKey % SIZE;
        
        if (q % SIZE == 0)
        {
            offset = defaultQuotient;
        }
        
        while (pass < SIZE)
        {
            if (data[ip] ==  null)
            {
                break;
            }
            
            if ( data[ip].compareTo(targetKey) == 0)
            {
                hit = true;
                break;
            }
            
            ip = (ip + offset) % SIZE;
            pass++;
        }
        
        if (hit)
        {
            data[ip] = null;
            n--;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Updates a Singly Linked List node from the hashed table.  Returns the value true
     * if the target key matches an element in the table and the node is updated;
     * otherwise returns false.
     * 
     * @param newSinglyLinkedListing
     * @return A boolean value that indicates if the node was updated
     */
    public boolean update(SinglyLinkedList newSinglyLinkedListing)
    {
        if ( ! delete(newSinglyLinkedListing.getKey() ) )
        {
            return false;
        }
        else if ( ! insert(newSinglyLinkedListing) )
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Returns the nodes in the hashed table of Singly Linked Lists.
     *
     * @return The nodes from the hashed table
     */
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        
        for (int index = 0; index < data.length; index++)
        {
            if (data[index] != null) 
            {
                output.append("******"+ data[index].getKey() + "******");
                output.append("\n");
                output.append(data[index]);
                output.append("\n");

            }
        }
        
        return output.toString();
    }

    /**
     * Calculates and returns a prime number that is the optimized size of the primary 
     * storage area.
     * 
     * @param n
     * @param pct
     * @return A prime number that is the optimized size of the primary storage area
     */
    public static int fourKPlus(int n, int pct)
    {
        boolean fkp3 = false;
        boolean aPrime = false;
        int prime, highDivisor, d;
        double pctd = pct;
        
        prime = (int) (n * (1.0 + (pctd / 100.0) ) );
        
        if (prime % 2 == 0)
        {
            prime++;
        }
        
        while (! fkp3)
        {
            while (! aPrime)
            {
                highDivisor = (int) (Math.sqrt(prime) +0.5);
                
                for (d = highDivisor; d > 1; d--)
                {
                    if (prime % d == 0)
                    {
                        break;
                    }
                }
                
                if (d != 1)
                {
                    prime += 2;
                }
                else
                {
                    aPrime = true;
                }
                
                if ( (prime - 3) % 4 == 0 )
                {
                    fkp3 = true;
                }
                else
                {
                    prime += 2;
                    aPrime = false;
                }
            }
        }
        
        return prime;
    }
    
    /**
     * Uses a Fold-Shifting preprocessing algorithm to convert a Listing name to 
     * a pseudo key integer.
     * 
     * @param targetKey rank field of a Singly Linked List object
     * @return The pseudo key generated from the target key (the name)
     */
    public static int stringToInt(String targetKey)
    {
        int pseudoKey = 0;
        int n = 1;
        int cn = 0;
        char c[] = targetKey.toCharArray();
        int grouping = 0;
        
        while ( cn < targetKey.length() )
        {
            grouping = grouping << 8;
            grouping += c[cn];
            cn++;
            
            if ( n == 4 || cn == targetKey.length() )
            {
                pseudoKey += grouping;
                n = 0;
                grouping = 0;
            }
            
            n++;
        }
        
        return Math.abs(pseudoKey);
    }
}
