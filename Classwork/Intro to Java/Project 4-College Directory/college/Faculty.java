import java.util.ArrayList;

/** Faculty class definition maintains and returns department information.
 *
 *  @author Carl B. Struck
 *  @version Copyright (c) 2003
 **/
public class Faculty extends Employee
{
    private String department;

    // ArrayList that maintains a list of valid department names for faculty members
    private ArrayList<String> departmentList;

    /** The constructor sets initial value for the department field.
     *  Explicit call to superclass Employee sets initial values for
     *  fields firstName, lastName and employeeID.  Instantiates and
     *  adds departments to the 'departmentList' ArrayList.
     *
     *  @param firstName Faculty member's first name from indirect superclass CommunityMember
     *  @param lastName Faculty member's last name from indirect superclass CommunityMember
     *  @param annualSalary Faculty member's ID number from superclass Employee
     *  @param department Department in which faculty member works
     */
    public Faculty(String firstName, String lastName, int employeeID,
                   String department)
    {
        super(firstName, lastName, employeeID);

        departmentList = new ArrayList<String>();

        departmentList.add("CENTRAL");
        departmentList.add("COMPUTER");
        departmentList.add("ENGLISH");
        departmentList.add("MATH");
        departmentList.add("SCIENCE");

        setDepartment(department);
    }

    /** Sets the value for the department field which must be a String
     *  contained in 'departmentList' ArrayList.  Valid departments are
     *  "CENTRAL", "COMPUTER", "ENGLISH", "MATH", and "SCIENCE".  An
     *  empty String value indicates that the department field is empty.
     *
     *  @param department Faculty member's department
     */
    public void setDepartment(String department)
    {
        if ( departmentList.indexOf( department.toUpperCase() ) >= 0 )
        {
            this.department = department.toUpperCase();
        }
    }

    /** Returns current value of the department field.
     *
     *  @return Department of faculty member as type String
     */
    public String getDepartment()
    {
        return department;
    }


    /** Returns formatted department field including text labels.
     *  Preceded by firstName, lastName and employeeID fields from call
     *  to toString() method of superclass Employee.
     *
     *  @return Formatted department field as type String
     */
    public String toString()
    {
        return super.toString() + "\nDepartment: " + getDepartment();
    }
}

