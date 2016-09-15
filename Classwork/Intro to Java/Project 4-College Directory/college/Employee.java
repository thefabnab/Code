/** Employee class definition maintains and returns employee ID number information.
 *
 *  @author Carl B. Struck
 *  @version Copyright (c) 2003
 **/

public class Employee extends CommunityMember
{
    private int employeeID;

    /** The constructor sets initial value for the employeeID field.
     *  Explicit call to superclass CommunityMember sets initial values for 
     *  fields firstName and lastName.
     *  
     *  @param firstName Employee's first name from superclass CommunityMember.
     *  @param lastName Employee's last name from superclass CommunityMember.
     *  @param employeeID Employee's ID number.
     */
    public Employee(String firstName, String lastName, int employeeID)
    {
        super(firstName, lastName);
        setEmployeeID(employeeID);
    }

    /** Sets the value for the employeeID field which must be between 1000 and 9999.
     *  A value of zero (0) indicates that employeeID field is empty.
     *  
     *  @param employeeID Employee's ID number.
     */
    public void setEmployeeID(int employeeID)
    {
        if ( (employeeID > 1000 && employeeID <= 9999)
           || employeeID == 0 )
        {
            this.employeeID = employeeID;
        }
    }

    /** Returns current value of the employeeID field.
     * 
     *  @return ID number of employee as data type int.
     */
    public int getEmployeeID()
    {
        return employeeID;
    }

    /** Returns formatted employeeID field including text labels.
     *  Preceded by firstName and lastName fields from call to toString() method
     *  of superclass CommunityMember.
     *  
     *  @return Formatted employee ID number field as type String.
     */
    public String toString()
    {
        return super.toString() + "\nEmployee ID: " + getEmployeeID();
    }
}

