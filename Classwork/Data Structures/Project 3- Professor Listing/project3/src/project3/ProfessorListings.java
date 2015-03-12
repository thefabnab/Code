package project3;

import javax.swing.JOptionPane;

/**
 * The ProfessorListings class is designed to produce the object characteristics
 * for a Professor, their name, department, area of expertise, and email.
 *
 * @author Nabil Azamy & Derek Holtberg
 */
public class ProfessorListings implements KeyMode 
{
    //Professor variables
    private String name;
    private String department;
    private String expertise;
    private String email;

    /**
     * No argument constructor
     *
     */
    public ProfessorListings() 
    {

        this("", "", "", "");
    }

    /**
     * Constructor for professor listings which establishes the professors 
     * attributes
     *
     * @param name  professors name
     * @param department    professors department
     * @param expertise professors area of expertise
     * @param email     professors contact information
     */
    public ProfessorListings(String name, String department, String expertise,
            String email) 
    {
        this.name = name;
        this.department = department;
        this.expertise = expertise;
        this.email = email;

    }
    /**
     * Method returning a readable notation of the professor object's parameters 
     * 
     * @return readout of Professor specifics 
     */
    @Override
    public String toString() 
    {
        return ("Professor name: " + name
                + "\nDepartment: " + department
                + "\nExpertise: " + expertise
                + "\nEmail: " + email + "\n");
    }

    /**
     * Method to provide a deep copy of a ProfessorListing object for usage in
     * various manipulations
     *
     * @return  a deep copy of the ProfessorListings object
     */
    public ProfessorListings deepCopy() 
    {
        ProfessorListings clone = new ProfessorListings(name, department,
                expertise, email);
        return clone;
    }

    /**
     * A method designed to examine if one object parameter, in this case, the
     * target key (the professors name) is identical to one another.
     *
     * @param targetKey a generic object representation of the professors name
     * @return  a return int either validating the comparison or not (boolean)
     */
    public int compareTo(Object targetKey) 
    {
        return (name.compareTo((String) targetKey));
    }

    /**
     * Method to return the name of the professor listing
     *
     * @return
     */
    public String getName() 
    {
        return name;
    }

    /**
     * A method to ask the user to input the information pertaining to a new
     * professor listing
     *
     */
    public void input() 
    {
        name = JOptionPane.showInputDialog("Enter a name");
        department = JOptionPane.showInputDialog("Enter a department");
        expertise = JOptionPane.showInputDialog("Enter an expertise");
        email = JOptionPane.showInputDialog("Enter an email");

    }
}
