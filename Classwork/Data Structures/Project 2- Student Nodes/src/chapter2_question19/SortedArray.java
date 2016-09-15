/*
 * Sorted array class which provides the node ordering structure
 * Nabil Azamy and Derek Holtberg
 */  
package chapter2_question19;

public class SortedArray
{
    private int next;              // position where next element will be inserted
    int SIZE;                // number of elements in the array

    private StudentListings[] data;  // the array of Listing objects

    public SortedArray(){
        this.SIZE=100;
        this.next=0;
    
    }
    
    /**
     * The constructor starts with the next element of the array set to insert
     * at zero (0), the size of the array at 100 and instantiates the StudentListings
     * array. Inserts the first four Listing elements into the array in alphabetical
     * name sequence.
     * 
     * @param SIZE the size of the desired node array
     * @param name0 student name
     * @param id0 student ID
     * @param gpa0 student grade point average
     * @param name1 student name
     * @param id1 student ID
     * @param gpa1 student grade point average
     * @param name2 student name
     * @param id2 student ID
     * @param gpa2 student grade point average
     * @param name3 student name
     * @param id3 student ID
     * @param gpa3 student grade point average
     */
    public SortedArray(int SIZE, String name0, double id0, double gpa0,
                       String name1, double id1, double gpa1,
                       String name2, double id2, double gpa2,
                       String name3, double id3, double gpa3)
    {
        this.SIZE=SIZE;

        data = new StudentListings[SIZE];

        data[0] = new StudentListings(name0, id0, gpa0).deepCopy();
        data[1] = new StudentListings(name1, id1, gpa1).deepCopy();
        data[2] = new StudentListings(name2, id2, gpa2).deepCopy();
        data[3] = new StudentListings(name3, id3, gpa3).deepCopy();

        next = 4;
    }

    /**
     * Inserts a new phone Listing element in alphabetical name sequence
     * within the array.
     *
     * @param name the name of the new person in the phone listing
     * @param id
     * @param gpa
     */
    public void insert(String name, double id, double gpa)
    {
        int low = 0;
        int high = next - 1;
        int index = (low + high ) / 2;

        String targetKey = name;
        
        while ( ! (data[index].compareTo(targetKey) >= 0
                && data[index - 1].compareTo(targetKey) <= 0) )
        {
            if ( data[index].compareTo(targetKey) >= 0 )
            {
                high = index - 1; // Move high down to eliminate upper half of array
            }
            else
            {
                low = index + 1;  // Move low up to to elimiate lower half of array
            }
            index = (high + low) / 2;
        }
        for (int j = next; j >= index; j--)
        {
            data[j] = data[j - 1];
        }
        next++;
        data[index] = new StudentListings(name, id, gpa).deepCopy();   
    }

    /**
     * Finds and returns (fetches) a matching element from within the array.
     * The while loop looks for the matching array element after which a
     * deepCopy of the element is returned.
     *
     * @param targetKey the search key (name) used to match against a data element
     *
     * @return The matching element from the array along with its index number
     */
    public String fetch(String targetKey)
    {
        int low = 0;
        int high = next - 1;
        int index = (low + high) / 2;

        while ( data[index].compareTo(targetKey) != 0 )
        {
            if ( data[index].compareTo(targetKey) >= 0 && high != low)
            {
                high = index - 1;
            }
            else
            {
                low = (low + high) / 2;
            }
            index = (low + high) / 2;
        }
        return  "data[" + index + "] = " + data[index].deepCopy() + "\n";
    }

    /**
     * Deletes an element from within the array.  The while loop looks for the
     * matching array element.  The for loop moves each of the subsequent
     * elements up one position to fill in the blank element.
     *
     * @param targetKey the search key (name) used to match against a data element
     */
    public void delete(String targetKey)
    {
        int low = 0;
        int high = next - 1;
        int index = (low + high) / 2;
        while ( data[index].compareTo(targetKey) != 0 )
        {
            if ( data[index].compareTo(targetKey) >= 0 )
            {
                high = index - 1; // Move high down to eliminate upper half of array
            }
            else
            {
                low = index + 1;  // Move low up to to elimiate lower half of array
            }
            index = (high + low) / 2;
        }
        for (int j = index; j < next - 1; j++)
        {
            data[j] = data[j + 1];
        }
        next--;
        data[next] = null;
    }
    /**
     * Updates a phone Listing element by deleting the element with the old values
     * and replacing it with an element containing new address and number values.
     *
     * @param targetKey
     * @param id
     * @param gpa
     */
     public void update(String targetKey, double id, double gpa)
     {         
        delete(targetKey);
	insert(targetKey, id, gpa);           
     }
    /**
     * Returns the elements of the array in a list concatenated to the index
     * number of each element.
     *
     * @return The elements of the array along with their index numbers
     */
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();

        for (int index = 0; index < next; index++)
        {
            output.append("data[");
            output.append(index);
            output.append("] = ");
            output.append(data[index]);
            output.append("\n");
        }
        return output.toString() + "\n";
    } 
}

