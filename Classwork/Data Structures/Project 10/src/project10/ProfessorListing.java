package project10;

/**
 * Class objects are for Professor Listing with name, department, expertise, and email fields.
 *
 * @author Nabil Azamy & Derek Holtberg
 */
public class ProfessorListing
{
    private String name;
    private String department;
    private String expertise;
    private String email;
    
    /**
     * No argument constructor for ProfessorListing class
     */
    public ProfessorListing()
    {
        
        this("","","","");
    }
    
    /**
     * The Professor listing constructor creates a professor object with their respective
     * name, department, expertise, and email
     *
     * @param name professor's email
     * @param department professor's department
     * @param expertise professor's area of expertise
     * @param email professor's email
     */
    public ProfessorListing(String name, String department, String expertise, 
            String email)
    {
        this.name= name;
        this.department= department;
        this.expertise= expertise;
        this.email= email;
    
    }
    
    /**
     * The set name method establishes the professors name
     *
     * @param name professors name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Returns the name field of a Professor Listing object.
     *
     * @return The name of a Professor Listing object
     */
    public String getName()
    {
        return name;
    }

    /**
     * A  getkey method which returns the object professor's name to be used as
     * a key for the data structure
     *
     * @return professor name
     */
    public String getKey()
    {
        return name;
    }
    /**
     * Updates the department number field of a Professor Listing object
     * 
     * @param department the professor's department
     */
    public void setDepartment(String department)
    {
        this.department = department;
    }
    
    /**
     * Returns the department of a professor Listing object.
     * 
     * @return The department  of a professor Listing object
     */
    public String getDepartment()
    {
        return department;
    }
    
    /**
     * Updates the expertise field of a professor Listing object
     * 
     * @param expertise the expertise of a professor Listing object
     */
    public void setExpertise(String expertise)
    {
        this.expertise = expertise;
    }
    
    /**
     * Returns the expertise field of a professor Listing object.
     * 
     * @return The Expertise of a professor Listing object
     */
    public String getExpertise()
    {
        return expertise;
    }
    
    /**
     * Updates the email field of a professor Listing object
     * 
     * @param email the email of a professor Listing object
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     * Returns the email field of a professor Listing object.
     * 
     * @return The email of a professor Listing object
     */
    public String getEmail()
    {
        return email;
    }
    
    
    /**
     * Creates and returns a deep copy (not just the address) of the current
     * professor Listing element.
     *
     * @return A deep copy of the current professor Listing element
     */
    public ProfessorListing deepCopy()
    {
        ProfessorListing clone = new ProfessorListing(name, department, expertise, email);
        return clone;
    }

    /**
     * Returns a positive number if the name field is greater than the targetKey
     * parameter, zero (0) if they are equal and a negative number if the name
     * is less than the targetKey.
     *
     * @param targetKey the search key used to match against the name field
     *
     * @return An integer indicating if the the name field is greater than,
     *         equal to or less than the targetKey parameter
     */
    public int compareTo(String targetKey)
    {
        return ( name.compareTo(targetKey) );
    }

    /**
     * Returns a String with the name, department, expertise and email for a Professor Listing
     * each preceded by a descriptive label.
     *
     * @return The name, department, expertise, and email of a professor listing
     */
    @Override
    public String toString()
    {
       StringBuilder output = new StringBuilder();

       output.append("Name: ");
       output.append(name);
       output.append("\t\tDepartment: ");
       output.append(department);
       output.append("\t\tExpertise: ");
       output.append(expertise);
       output.append("\t\tEmail: ");
       output.append(email);

       return output.toString();
    }
}
