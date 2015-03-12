
/**
 * An easy method for figuring out the area and perimeter of a regular polygon.
 * 
 * @author Nabil Azamy 
 * @version v2.4.3.2.5.6.7.8.6.5.4.3.42.1
 */
public class RegularPolygon
{
    // instance variables - stated with default values
    private int sides = 3;
    private double sideLength = 1.0;
    private double x = 0;
    private double y = 0;

    /**
     *  No arg constructor that creates a polygon with the default values
     */
    public RegularPolygon()
    {
    }
    
    /**
     * Constructor that creates a regular polygon at the following values:
     */
    public RegularPolygon(int sides, double sideLength)
    {
        this.sides = sides;
        this.sideLength = sideLength;
        this.x = 0;
        this.y = 0;
    }
    /**
     * Constructor that creates a regular polygon with a varying number of sides, length, and position both in x any y positions
     */
    public RegularPolygon(int sides, double sideLength, double x, double y)
    {
        this.sides = sides;
        this.sideLength = sideLength;
        this.x = x;
        this.y = y;
    }
    
    /**
     * Mutator/get/set methods for all the instance variables
     */
    public int getSides()
    {
        return this.sides;
    }
    
    public double getSideLength()
    {
        return this.sideLength;
    }
    
    public double getX()
    {
        return this.x;
    }
    
    public double getY()
    {
        return this.y;
    }
    
    public void setSides(int newSides)
    {
        this.sides = newSides;
    }
    
    public void setSideLength(double newSideLength)
    {
        this.sideLength = newSideLength;
    }
    
    public void setX(double newX)
    {
        this.x = newX;
    }
    
    public void setY(double newY)
    {
        this.y = newY;
    }
    
    /**
     * Method to arrive at polygon parimeter length by multiplying the length of its sides by the number of sides
     * @return double   the length of the perimeter of the regular polygon
     */
    public double getPerimeter()
    {
        return this.sides * this.sideLength;
    }
    
    /**
     * Constructor that creates a regular polygon at the following values
     * @return  double  the area contained within the regular polygon
     */
    public double getArea()
    {
        return (this.sides * Math.pow(this.sideLength, 2))/(4 * Math.tan((Math.PI)/this.sides));
    }

}
