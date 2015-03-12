
/**
 * Going to make a circle calculator
 * 
 * @Nabil 
 * @1.0
 */
public class Circle
{
    // instance variables are
    private double radius = 1;

    /**
     * Constructor for objects of class Circle
     */
    public Circle()
    {
        
    }
    
    Circle(double newRadius)
    {
        radius = newRadius;
    }
    
    double getArea()
    {
        return radius * radius * Math.PI;
    }
    
    double getPerimeter()
    {
        return 2 * radius * Math.PI;
    }
    
    void setRadius( double radius)
    {
        this.radius = radius;
    }
}
