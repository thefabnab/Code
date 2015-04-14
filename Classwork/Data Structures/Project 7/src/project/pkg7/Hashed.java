package project.pkg7;

/**
 * A hashed data structure class that uses both a fold-shifting pre-processing
 * and linear quotient collision algorithms.
 *
 * @author Nabil Azamy & Derek Holtberg
 */
public class Hashed 
{
    final int SIZE;
    int n = 0;                    // The number of nodes in the structure
    int defaultQuotient = 9967;   // The default 4k + 3 prime
    double loadingFactor = 0.75;
    StudentListings deleted;              // The dummy node, v2 (v1 = null)
    private StudentListings[] data;       // The primary storage array of phone Listings
    
    public Hashed()
    {
        SIZE = 50;
    }
    
    /**
     * Sets the number of nodes in the hashed structure and instantiates an array of 
     * Student objects to that size.
     * 
     * @param length the number of nodes in the structure
     */
    public Hashed(int length)
    {
        int pct = (int) ( ( loadingFactor - 1) * 100.0);
        SIZE = fourKPlus(length, pct);
        data = new StudentListings[SIZE];
        deleted = new StudentListings();
        
        for (int index = 0; index < SIZE; index++)
        {
            data[index] = null;
        }
    }
    
    /**
     * Inserts a Student object into the hashed table. Returns boolean value 
     * true if the node was inserted or false if the insert failed.
     * 
     * @param newStudentListing
     * @return A boolean value that indicates whether or not the insert was successful
     */
    public boolean insert(StudentListings newStudentListing)
    {
        boolean hit = false;
        int pass, q, offset, ip;
        
        int pseudoKey = stringToInt( newStudentListing.getKey() );
        
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
                data[ip] = newStudentListing.deepCopy();
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
     * Returns a deep copy of a Student Object from the hashed table. 
     * A null value is returned if the target key does not match an element in 
     * the table.
     * 
     * @param targetKey name field of a Student
     * @return A phone Listing object from the array
     */
    public StudentListings fetch(String targetKey)
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
            return data[ip].deepCopy();
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Deletes a Student node from the hashed table.  Returns the value true if 
     * the target key matches an element in the table and the node is deleted; 
     * otherwise returns false.
     * 
     * @param targetKey name field of a Student object
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
     * Updates a phone Listing node from the hashed table.  Returns the value true
     * if the target key matches an element in the table and the node is updated;
     * otherwise returns false.
     * 
     * @param newStudentListing
     * @return A boolean value that indicates if the node was updated
     */
    public boolean update(StudentListings newStudentListing)
    {
        if ( ! delete(newStudentListing.getKey() ) )
        {
            return false;
        }
        else if ( ! insert(newStudentListing) )
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Returns the nodes in the hashed table of Student listings.
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
     * @param targetKey name field of a Student Listing
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
