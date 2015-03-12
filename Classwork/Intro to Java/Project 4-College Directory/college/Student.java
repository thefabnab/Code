
/**
 * Class Student collects student ID and GPA information along with the superclass variables of first name and last name
 * 
 * @author Nabil Azamy 
 * @version 10/19/14
 */
public class Student extends CommunityMember
{
    //instance variables
    private int studentID;
    private double gradePointAverage;
    
    /**
     * No argument constructor
     */
    public Student()
    {
        this(null,null, 0, 0);
    }
    
   
    /**
     * The constructor sets initial value for the studentID and gradePointAverage values.
     * Explicit call to superclass CommunityMember sets initial valeus for fields firstName and lastName.
     * 
     * @param   firstName   Student's first name from superclass CommunityMember.
     * @param lastName  Student's last name from superlass CommunityMember.
     * @param studentID Student's student ID number.
     * @param gradePointAverage Student's grade point average
     */
    public Student(String firstName, String lastName, int studentID, double gradePointAverage)
    {
        super(firstName, lastName);
        setStudentID(studentID);
        setGradePointAverage(gradePointAverage);
    }
    
    /**
     * Student ID Set method
     * 
     * @param studentID StudentID number
     */
    public void setStudentID(int studentID)
    {
        if ((studentID > 7000000 && studentID <= 9999999) || studentID == 0)
        {
            this.studentID = studentID;
        }
    }
    
    /**
     * Grade Point Average set method
     * 
     * @param gradePointAverage Student GPA
     */
    public void setGradePointAverage(double gradePointAverage)
    {
        if (( gradePointAverage >= 0 && gradePointAverage <= 4.0))
        {
            this.gradePointAverage = gradePointAverage;
        }
    }
    
    /**
     * Student ID get method
     * 
     * @return studentID    Value of student ID
     */
    public int getStudentID()
    {
        return studentID;
    }
    
    /**
     * Grade Point Average get method
     * 
     * @return gradePointAverage    Student GPA value
     */
    public double getGradePointAverage()
    {
        return gradePointAverage;
    }
    
    /**
     * To String method to provide information user readout of what class does
     * 
     * @return      Produces a return of the getStudentID and getGradePointAverage values appended to the super class
     * get statements
     */
    public String toString()
    {
        return super.toString() + "\nStudent ID: " + getStudentID() + "\nGrade Point Average: " + getGradePointAverage();
    }
}
