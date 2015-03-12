
/**
 * Class designed to determine distance between any two coordinates.
 * 
 * @author Nabil Azamy
 * @version 10.15.14v1
 */
public class MyPoint1
{
    /**
     * Instance variables
     */
    private double x1;
    private double y1;
    
    /**
     * No argument constructor
     */
    
    public MyPoint1()
    {
        this(0,0);
    }
    
    /**
     * Constructor that refers to specific coordinates
     * 
     */
    
    public MyPoint1(double x, double y)
    {
        this.x1 = x;
        this.y1 = y;
    }
    
    /**
     * Calculating the distance between the two points with x and y coordinates
     * 
     * @return  the distance between two points
     * @param x the x position of the instanced variable
     * @param y the y position of the instanced variable
     */
    
    public double distance(double x, double y)
    {
        return Math.sqrt((this.x1 - x)*(this.x1 - x) + (this.y1 - y)*(this.y1 - y));
    }
    
    /**
     * Calculates the distance between to objects MyPoint1 and Point using the previous distance method.
     * 
     * @return the distance between two objects
     * @param Point  the instanced MyPoint1 object
     */
    
    public double distance(MyPoint1 Point)
    {
        return Point.distance(this.x1, this.y1);
    }
    
    /**
     * Distance from (0,0)
     */
    public double distance()
    {
        return this.distance(0,0);
    }
    
    /**
     * Returns a constructor statement for an instance of MyPoint1
     */
    
    public String statement()
    {
        return String.format("MyPoint1(%f, %f)", this.x1, this.y1);
    }
    
    /**
     * A quick overview of what this class does
     */
    
    public String toString()
    {
        return String.format("(%f, %f)", this.x1, this.y1);
    }
    
    
    
}
