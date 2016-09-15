package chapter2_question19;

/**
 * Student listing class contains within it the information pertaining to the 
 * individual student informations; name, id, and GPA.
 * 
 * @author Derek Holtberg and Nabil Azamy
 */
public class StudentListings {
    
    private String name;
    private double id;
    private double gpa;
    
    /**
     * Empty Constructor initializes the StudentListing
     */
    public StudentListings()
    {
    }
    
    /** Constructor used in the driver class
     * 
     * @param name persons name
     * @param id number for listing
     * @param gpa grade point average of student
     */
    
    public StudentListings(String name, double id, double gpa)
    {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }
    
    /**
     * Makes a deep copy of the StudentListing entry
     * @return
     */
    public StudentListings deepCopy()
    {
        StudentListings clone = new StudentListings(name, id, gpa);
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
     * Method returns listing to a String.
     * @return 
     */
    
    @Override
    
    public String toString()
    {
       StringBuilder output = new StringBuilder();

       output.append("Name: ");
       output.append(name);
       output.append("\tStudent ID: ");
       output.append(id);
       output.append("\tGrade Point Average: ");
       output.append(gpa);
       return output.toString();
    }
}
