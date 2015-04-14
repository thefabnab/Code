package project5;

import javax.swing.JOptionPane;

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
     * @param id number for student listing
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
     * compares listings for use in the Singly linked lists class
     * 
     * @param targetKey is the index number of the student listing
     * @return 
     */
    
    public int compareTo(String targetKey)
    {
        return ( name.compareTo(targetKey) );
    }
    
    /**
     * get name method returns the name in the student listing.
     * @return 
     */
    
    public String getName()
    {
        return name;
    }
    
    public void input() 
    {
        name = JOptionPane.showInputDialog("Enter Student name");
        id = Double.parseDouble(JOptionPane.showInputDialog("Enter Student ID"));
        gpa = Double.parseDouble(JOptionPane.showInputDialog("Enter Student GPA"));

    }
    
    /**
     * Method returns student listing to a String.
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
