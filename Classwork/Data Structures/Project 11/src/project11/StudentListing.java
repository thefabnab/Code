package project11;

/**
 * Student listing class contains within it the information pertaining to the 
 * individual student informations; name, id, and GPA.
 * 
 * @author Derek Holtberg and Nabil Azamy
 */
public class StudentListing
{
    
    private String name;
    private double id;
    private double gpa;
    
    /**
     * Empty Constructor initializes the StudentListing
     */
    public StudentListing()
    {
        
    }
    
    /** Constructor used in the driver class
     * 
     * @param name persons name
     * @param id number for listing
     * @param gpa grade point average of student
     */
    
    public StudentListing(String name, double id, double gpa)
    {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }
    
    /**
     * Makes a deep copy of the StudentListing entry
     * @return
     */
    public StudentListing deepCopy()
    {
        StudentListing clone = new StudentListing(name, id, gpa);
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
        return ( name.compareTo(targetKey) );
    }
    
    /**
     * get name method returns the name in the listing.
     * @return 
     */
    
    public String getName()
    {
        return name;
    }
    
    /**
     * get ID method returns the student ID in the listing
     *
     * @return student ID
     */
    
    public double getID()
    {
        return id;
    }
    
    /**
     * Method returns student listing to a String.
     * @return 
     */
    
    @Override
    public String toString()
    {
       StringBuilder output = new StringBuilder();

       output.append("\nStudent ID: ");
       output.append(id);
       output.append("\tStudent Name: ");
       output.append(name);
       output.append("\tGrade Point Average: ");
       output.append(gpa);
       return output.toString();
    }
}
