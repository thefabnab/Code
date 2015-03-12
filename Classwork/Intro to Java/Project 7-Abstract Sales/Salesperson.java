
/**
 * A class establishing the salesperson class parameters. 
 * 
 * @author Nabil Azamy 
 * @version Version 1
 */
public final class Salesperson extends Employee implements SalaryConstants
{
    //Instance variables
    private static int sales;
    
    /**
     * No argument constructor
     */
    public Salesperson()
    {
        this(null,null, 0.0, 0);
    }
    
    /**
     * Constructor accounting for Salespersons parameters.
     * 
     * @param name  Salesperson name
     * @param address   Salesperson address
     * @param salary    Salesperson salary
     * @param sales     Salesperson salary
     */
    public Salesperson(String name, String address, double salary, int sales)
    {
        super( name, address, salary);
        setSales(sales);
    }
    
    /**
     * Set method establishing salesperson sales value
     */
    public void setSales(int sales)
    {
        try
        {
            if(sales > 0)
            {
                this.sales = sales;
            }
        }
        catch (NumberFormatException ex)
        {
            System.out.println(ex + " (You must enter a number greater than zero)");
        }

    }
    
    /**
     * Get method returning number of sales made by employee
     * 
     * @return Sales of salesperson
     */
    public int getSales()
    {
        return sales;
    }
    
    /**
     * Method calculating and returning the additional income earned for a salesperson
     * 
     * @return
     */
    public double getAdditionalIncome()
    {
        return sales*COMMISSION_RATE;
    }

}
