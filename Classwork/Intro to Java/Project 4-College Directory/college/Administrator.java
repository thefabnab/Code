/** Administrator class definition maintains and returns title information.
 *
 *  @author Carl B. Struck
 *  @version Copyright (c) 2003
 **/

public class Administrator extends Faculty
{
    private String title;

    /** The constructor sets initial value for the title field.
     *  Explicit call to superclass Faculty sets initial values for fields firstName, lastName, employeeID and department.
     *  
     *  @param firstName Administrator's first name from indirect superclass CommunityMember.
     *  @param lastName Administrator's last name from indirect superclass CommunityMember.
     *  @param annualSalary Administrator's ID number from indirect superclass Employee.
     *  @param department Department in which administrator works from superclass Faculty.
     *  @param title Administrator's title.
     */
    public Administrator(String firstName, String lastName, int employeeID, String department, String title)
    {
        super(firstName, lastName, employeeID, department);
        setTitle(title);
    }

    /** Sets the value for the title field converted to all uppercase letters.
     *  An empty String value indicates that the title field is empty.
     *  
     *  @param title Administrator's title.
     */
    public void setTitle(String title)
    {
        this.title = title.toUpperCase();
    }

    /** Returns current value of the title field.
     * 
     *  @return Title of administrator as type String.
     */
    public String getTitle()
    {
        return title;
    }

    /** Returns formatted title field including text labels.
     *  Preceded by firstName, lastName, employeeID and department fields from
     *  call to toString() method of superclass Faculty.
     *  
     *  @return Formatted department field as type String.
     */
    public String toString()
    {
        return super.toString() + "\nTitle: " + getTitle();
    }
}
