/** CommunityMember class definition maintains and returns first name and last name information.
 *
 *  @author Carl B. Struck
 *  @version Copyright (c) 2003
 */

public class CommunityMember extends Object
{
    private String firstName;
    private String lastName;

    /**
     * No parameter constructor
     */
    public CommunityMember()
    {
        this(null,null);
    }
    
    
    /** The constructor sets initial values for the firstName and lastName fields.
     * 
     *  @param firstName Member's first name.
     *  @param lastName Member's last name.
     */
    public CommunityMember(String firstName, String lastName)
    {
        setFirstName(firstName);
        setLastName(lastName);
    }

    /** Sets the value for the firstName field converted to all uppercase letters.
     *  An empty String value indicates that the first name field is empty.
     *  
     *  @param firstName Member's first name.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /** Sets the value for the lastName field converted to all uppercase letters.
     *  An empty String value indicates that the last name field is empty.
     *  
     *  @param lastName Member's first name.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /** Returns current value of the firstName field.
     * 
     *  @return First name of member as type String.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /** Returns current value of the lastName field.
     * 
     *  @return Last name of member as type String.
     */
    public String getLastName()
    {
        return lastName;
    }

    /** Returns formatted firstName and lastName fields including text
     *  labels.
     *
     *  @return Formatted first name and last name fields as type String
     */
    public String toString()
    {
        return "Name: " + getFirstName() + " " + getLastName();
    }
}
