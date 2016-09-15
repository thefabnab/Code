
/**
 * This is a quick program to provide you with the most up-to-date value of your stock!
 * 
 * @Nabil Azamy 
 * @v9.14.14-1
 */

public class Stock
{

    // instance variables
    private String symbol;
    private String name;
    private double currentPrice;
    private double closingPrice;

    public Stock(String newSymbol, String newName)
    {
        // initialise variables for new instance
        symbol = newSymbol;
        name = newName;
    }

    public void today(double newCurrentPrice)
    {
        // current price of todays stock
        currentPrice = newCurrentPrice;
    }

    public void yesterday(double newClosingPrice)
    {
        // yesterdays closing price
        closingPrice = newClosingPrice;
    }

    public double getChangePercent()
    {
        // statement calculating the difference between stock values all values output are positive
        return ((currentPrice - closingPrice) / closingPrice) * 100;

    }

    /**
     * If statement to produce a print ouput displaying the status of your stock today
     */
    public void printOut()
    {
        if(getChangePercent() > 0)
        {
            System.out.println("Yay! Your shares of " + name + " have increased by " + getChangePercent());
        }
        else
        {
            System.out.println("Oh no! Your shares of " + name + " have decreased by " + getChangePercent());    
        }
    }
}
