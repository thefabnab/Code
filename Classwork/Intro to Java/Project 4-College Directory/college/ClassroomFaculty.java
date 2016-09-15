import java.util.ArrayList;

/** ClassroomFaculty class definition maintains and returns faculty rank and semester credits information.
 *
 *  @author Carl B. Struck
 *  @version Copyright (c) 2003
 **/

public class ClassroomFaculty extends Faculty
{
    private String facultyRank;
    private int semesterCredits;

    // ArrayList object that maintains a list of valid ranks for classroom faculty
    private ArrayList<String> facultyRankList;

    /** The constructor sets initial values for the faculty rank and
     *  semester credits fields.  Explicit call to superclass Faculty
     *  sets initial values for fields firstName, lastName, employeeID
     *  and department.
     *
     *  @param firstName Classroom faculty member's first name from indirect superclass CommunityMember
     *  @param lastName Classroom faculty member's last name from indirect superclass CommunityMember
     *  @param annualSalary Classroom faculty member's ID number from indirect superclass Employee
     *  @param department Department in which classroom faculty member works from superclass Faculty
     *  @param facultyRank Classroom faculty member's rank
     *  @param semesterCredits Classroom faculty member's teaching load for semester in credits
     */
    public ClassroomFaculty(String firstName, String lastName, int employeeID, String department, String facultyRank, int semesterCredits)
    {
        super(firstName, lastName, employeeID, department);

        facultyRankList = new ArrayList<String>();

        facultyRankList.add("INSTRUCTOR");
        facultyRankList.add("ASSISTANT");
        facultyRankList.add("ASSOCIATE");
        facultyRankList.add("PROFESSOR");

        setFacultyRank(facultyRank);
        setSemesterCredits(semesterCredits);
    }

    /** Sets the value for the facultyRank field which must be a
     *  String contained in rankTable[] array.  Valid departments are
     *  "INSTRUCTOR", "ASSISTANT", "ASSOCIATE", and "PROFESSOR".  An
     *  empty String value indicates that the facultyRank field is empty.
     *
     *  @param facultyRank Classroom faculty member's rank
     */
    public void setFacultyRank(String facultyRank)
    {
        if (facultyRankList.indexOf( facultyRank.toUpperCase() ) >= 0 )
        {
            this.facultyRank = facultyRank.toUpperCase();
        }
    }

    /** Sets the value for the semesterCredits field which must be
     *  between zero (0) and 24.  A value of minus one (-1) indicates
     *  that semesterCredits field is empty.
     *
     *  @param semesterCredits Classroom faculty member's teaching load for semester in credits
     */
    public void setSemesterCredits(int semesterCredits)
    {
        if (semesterCredits >= -1 && semesterCredits <= 24)
        {
            this.semesterCredits = semesterCredits;
        }
    }

    /** Returns current value of the faculty rank field.
     *
     *  @return Rank of classroom faculty member as type String
     */
    public String getFacultyRank()
    {
        return facultyRank;
    }

    /** Returns current value of the semesterCredits field.
     *
     *  @return Classroom faculty member's teaching load for semester in credits as data type int
     */
    public int getSemesterCredits()
    {
        return semesterCredits;
    }

    /** Returns formatted facultyRank and semesterCredits fields
     *  including text labels.  Preceded by firstName, lastName,
     *  employeeID and  department fields from call to toString()
     *  method of superclass Faculty.
     *
     *  @return Formatted classroom faculty member's rank and teaching
     *          load for semester in credits as type String
     */
    public String toString()
    {
        return super.toString() + "\nRank: " + getFacultyRank()
            + "\nTeaching load: " + getSemesterCredits();
    }
}
