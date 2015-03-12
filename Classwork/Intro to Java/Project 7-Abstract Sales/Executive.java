
/**
 * Executive class establishing the class parameters.
 * 
 * @author Nabil Azamy
 * @version Version 1
 */
public final class Executive extends Employee implements SalaryConstants
{
    /**
     * No arg constructor
     */
    public Executive()
    {
        super(null,null,0.0);
    }
    
    /**
     * Constructor returning values representing the executive specific employee
     * 
     * @param name  Executive name
     * @param address   Executive address
     * @param salary    Executive salary
     */
    public Executive(String name, String address, double salary)
    {
        super(name, address, salary);
    }
    
    /**
     * Calculates employee additional income taking into account the executive bonus rate.
     * 
     * @return  employee additional salary as a function of bonus rate
     */
    public double getAdditionalIncome()
    {
        return getSalary()*BONUS_RATE;
    }
    
}
