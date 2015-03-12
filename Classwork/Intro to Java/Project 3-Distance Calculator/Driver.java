
/**
 * Driver class to output class MyPoint1 information
 * 
 * @author Nabil Azamy 
 * @version 10.15.14v1
 */
public class Driver
{
    public static void main (String[] args)
    {
        MyPoint1 first = new MyPoint1(0,0);
        MyPoint1 second = new MyPoint1(10, 30.5);
        
        System.out.println( "The distance between the points " + first + "and " + second + "is " + second.distance(first));
    }
}
