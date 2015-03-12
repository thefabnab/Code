package project3;

/**
 * Class built to establish a Unsorted Optimized Array class
 *
 * @author Nabil Azamy & Derek Holtberg
 * @param <T>
 */
public class UOA<T> 
{
    //class variables
    private int next;
    final int SIZE;
    private T[] data;

    /**
     * No argument constructor which establishes the size of the UOA size to 100
     *
     */
    public UOA() 
    {
        this(100);
    }

    /**
     * A constructor requiring the input of the size variable to establish array
     * size in a generic fashion.
     *
     * @param size  size of UOA array to be initiated 
     */
    public UOA(int size) 
    {
        next = 0;
        SIZE = size;
        data = (T[]) new Object[SIZE];

    }

    /**
     * A method designed to check if there is enough room within the designated
     * UOA and inserts it within the next available position.
     *
     * @param newNode
     * @return
     */
    public boolean insert(T newNode) 
    {
        KeyMode node = (KeyMode) newNode;

        if (next >= SIZE) 
        {
            return false;
        }

        data[next] = (T) node.deepCopy();

        if (data[next] == null) 
        {
            return false;
        }

        next++;
        return true;
    }

    /**
     * Fetches a matching element from within the array. The while loop looks 
     * for the matching array element. If there is a matching element, a String 
     * with a deepCopy of the element is returned; otherwise the String "No 
     * match" is returned.
     *
     * @param targetKey the search key (name) used to match against a data
     * element
     *
     * @return The matching element from the array along with its index number
     */
    public KeyMode fetch(Object targetKey) 
    {
        KeyMode node = (KeyMode) data[0];

        int index = 0;

        while (index < next && node.compareTo(targetKey) != 0) 
        {
            index++;
            node = (KeyMode) data[index];
        }

        if (index >= next) 
        {
            return null;
        }

        node = node.deepCopy();

        if (index != 0) 
        {
            T temp = data[index - 1];
            data[index - 1] = data[index];
            data[index] = temp;
        }

        return node;
    }

    /**
     * This method removes an element from the UOA. It first finds where the
     * element is and moves all other elements up one position.
     *
     * @param targetKey the search key (professor name) used to match against data
     * element
     *
     * @return A true or false value that indicates whether or not the Professor
     * Listing element was deleted
     */
    public boolean delete(Object targetKey) 
    {
        KeyMode node = (KeyMode) data[0];

        int index = 0;

        while (index < next && node.compareTo(targetKey) != 0) 
        {
            index++;
            node = (KeyMode) data[index];
        }

        if (index >= next) 
        {
            return false;
        }

        data[index] = data[next - 1];
        next--;
        data[next] = null;

        return true;
    }

    /**
     * Serves to update the UOA listing in question, in this case the Professor
     * Listing. It replaces the old object with a new one.
     *
     * @param targetKey the search key for the Professor Listing name field
     * @param newNode the updated Professor Listing object
     *
     * @return A true or false value that indicates whether or not the Professor
     * Listing element was updated
     */
    public boolean update(String targetKey, T newNode) 
    {
        if (delete(targetKey) == false) 
        {
            return false;
        } 
        else if (insert(newNode) == false) 
        {
            return false;
        } 
        else 
        {
            return true;
        }
    }

    /**
     * Returns a user readable version of the object
     *
     * @return The elements of the array along with their index numbers
     */
    @Override
    public String toString() 
    {
        StringBuilder output = new StringBuilder();

        for (int index = 0; index < next; index++) 
        {
            output.append("***data[");
            output.append(index);
            output.append("]*** \n");
            output.append(data[index]);
            output.append("\n");
        }

        return output.toString() + "\n";
    }

}
