
/**
 * A class to establish the name, address, and salary specifics of all employees, both
 * salespersons and executives.
 * 
 * @author Nabil Azamy 
 * @version Version 1
 */
public abstract class Employee
{
    //Instance variables
    private String name;
    private String address;
    private double salary;
    
    /**
     * No argument constructor
     */
    public Employee()
    {
        this(null,null, 0.0);
    }
    
    /**
     * Constructor for initial value for employee Name, Address, and Salary
     * 
     * @param Name  Employees name
     * @param Address   Employee address
     * @param Salary    Employee Salary
     */
    public Employee( String name, String address, double salary)
    {
        setName(name);
        setAddress(address);
        setSalary(salary);
    }
    
    /**
     * Set method for Employee Name
     * 
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Set method for Employee Address
     * 
     * @param address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    /**
     * Set method for Employee Salary
     * 
     * @param salary
     */
    public void setSalary(double salary)
    {
        try
        {
            if(salary > 0)
            {
                this.salary = salary;
            }
        }
        catch (NumberFormatException ex)
        {
            System.out.println(ex + " (You must enter a number greater than zero)");
        }
    }
    
    /**
     *  Get Method for Employee Name
     *  
     *  @return name    returns Employee Name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get Method for Employee Address
     * 
     * @return address  returns Employee Address
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * Get Method for Employee Salary
     * 
     * @return salary   returns employee Salary
     */
    public double getSalary()
    {
        return salary;
    }
    
    /**
     * abstract method call to arrive at additional income
     */
    public abstract double getAdditionalIncome();
    
    /**
     * Method returning employee total salary
     * 
     * @return      returns total employee salary
     */
    public double getTotalSalary()
    {
        return getSalary() + getAdditionalIncome();
    }
    
    /**
     * toString method producing employee information
     * 
     * @return  toString returning class readout
     */
    public String toString()
    {
        return "Employee name: \t" + name + "\n" +
        "Employee address: \t" + address + "\n" +
        "Employee base salary: \t" + salary + "\n" +
        "Employee total salary: \t" + getTotalSalary();
    }
}
