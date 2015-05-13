package finalproject;

/**
 * The Employee Listing Class creates an Employee object which incorporates the
 * elements of the employee status within the company: ID, Name, Email, and
 * where they work. However, not their status within the company in terms of rank.
 *
 * @author Nabil Azamy & Derek Holtberg
 * @version 5/12/15
 */
public class EmployeeListing 
{
    private double id;
    private String name;
    private String email;
    private String workplace;
    
    /**
     * This constructor establishes the values for the employee ID, name, email,
     * and their workplace.
     *
     * @param id        a double representing employee ID
     * @param name      Employee Name
     * @param email     Employee Email
     * @param workplace Where the employee works
     */
    public EmployeeListing(double id, String name, String email, String workplace)
    {
        this.id = id;
        this.email = email;
        this.name = name;
        this.workplace = workplace;
    }
    
    /**
     * Returns a deep copy of a perspective Employee Listing object to maintain
     * encapsulation.
     *
     * @return  A Employee Listing object
     */
    public EmployeeListing deepCopy()
    {
        EmployeeListing clone = new EmployeeListing(id,name,email,workplace);
        return clone;
    }
    
    /**
     * A comparison method which compares the employee's name
     * to a designated target key.
     *
     * @param targetKey the argument being searched against the employees name
     * @return          A return of 0 indicates a positive match
     */
    public int compareTo(String targetKey)
    {
        return ( name.compareTo(targetKey) );
    }
    
    /**
     * Returns the Employee Listing objects name
     *
     * @return  The employee's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * The toString method returns a string representation of the Employee Listing
     * class. It includes their Name, ID, Email, and workplace.
     * 
     * @return String representation of the employee listing object
     */
    @Override
    public String toString()
    {
       StringBuilder output = new StringBuilder();

       output.append("Employee Name: ");
       output.append(name);
       output.append("\t\tID: ");
       output.append(id);
       output.append("\t\tEmail: ");
       output.append(email);
       output.append("\t\tWorkplace: ");
       output.append(workplace);
       return output.toString();
    }
}
