import java.util.ArrayList;

/**
 * A subclass of Student, Graduate class provides student information pertaining to both what graduate program they
 * are enrolled in, along with their GRE score and the parent fields of first name, last name, student ID, and grade point
 * average.
 * 
 * @author Nabil Azamy 
 * @version 10/19/14
 */
public class Graduate extends Student
{
    private int greScore;
    private String program;
    private ArrayList<String> programList;
    /**
     * No argument constructor
     */
    public Graduate()
    {
        this( null, null, 0, 0, 0, null);
    }
    
    /**
     * The constructor sets initial value for the studentID and gradePointAverage values.
     * Explicit call to superclass CommunityMember sets initial valeus for fields firstName and lastName.
     * 
     * @param   firstName   Student's first name from superclass CommunityMember.
     * @param lastName  Student's last name from superlass CommunityMember.
     * @param studentID Student's student ID number.
     * @param gradePointAverage Student's grade point average
     * @param greScore  Student's GRE score
     * @param program   the program the current student is enrolled in
     */
    
    public Graduate(String firstName, String lastName, int studentID, double gradePointAverage, int greScore, String program)
    {
        super(firstName, lastName, studentID, gradePointAverage);
        
        programList = new ArrayList<String>();
    
        programList.add("masters");
        programList.add("doctorate");
    
        setGreScore(greScore);
        setProgram(program);
    }
    
    /**
     * Set method designed to establish students GRE score for the range 400 - 1600 and 0
     */
    public void setGreScore(int greScore)
    {
        if ((greScore >= 400 && greScore <= 1600) || greScore == 0)
        {
            this.greScore = greScore;
        }
    }
    
    /**
     * Set program to determine student program name
     */
    public void setProgram(String program)
    {
        if (programList.indexOf(program) >= 0)
        {
            this.program = program;
        }
    }
    
    /**
     *  get method to obtain GRE score
     *  
     *  @return greScore    returns GRE score for individual student
     */
    public int getGreScore()
    {
        return greScore;
    }
    
    /**
     * get method to obtain student program information
     * 
     * @return  program returns program name for individual student
     */
    public String getProgram()
    {
        return program;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "\nGre Score: " + getGreScore() + "\nProgram: " + getProgram();
    }
    
}
