package project4;

/**
 * Student listing class contains within it the information pertaining to the 
 * individual student informations; name, id, and GPA.
 * 
 * @author Derek Holtberg and Nabil Azamy
 */
public class Ints implements KeyMode
{
    
    private int number1;
    private int number2;
    
    /**
     * Empty Constructor initializes the StudentListing
     */
    public Ints()
    {
    }
    
    /** Constructor used in the driver class
     * 
     * @param number1
     * @param number2
     */
    
    public Ints(int number1, int number2)
    {
        this.number1 = number1;
        this.number2 = number2;
    }
    
    /**
     * Makes a deep copy of the StudentListing entry
     * @return
     */
    @Override
    public Ints deepCopy()
    {
        Ints clone = new Ints(number1, number2);
        return clone;
    }
    
    /**
     * compares listings for use in the sorted array class
     * 
     * @param targetKey is the index number of the listing
     * @return 
     */
    
    public int compareTo(String targetKey)
    {
        if (number1 == Integer.parseInt(targetKey))
        {
                
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * Method returns listing to a String.
     * @return 
     */
    
    @Override
    public String toString()
    {
       StringBuilder output = new StringBuilder();
       
       output.append("\tNumber One: ");
       output.append(number1 + "\n");
       output.append("\tNumber Two: ");
       output.append(number2 + "\n");
       output.append("\n");
       return output.toString();
    }
}

