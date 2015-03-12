
/**
 * A series of constructors to test out various instances of the regularpolygon object.
 * 
 * @author Nabil Azamy
 * @version v3.14.15
 */
public class ThreePolygons
{
   /**
    *  Instances poly1,poly2, and poly3 each have differing data field values to test the RegularPolygon class
    */
    public static void main(String [] args)
    {
        RegularPolygon poly1 = new RegularPolygon();
        RegularPolygon poly2 = new RegularPolygon(6,4);
        RegularPolygon poly3 = new RegularPolygon(10,4,5.6,7.8);
        
        System.out.println("Perimeter of first polygon is: " + poly1.getPerimeter() + " Area is: " + poly1.getArea());
        System.out.println("Perimeter of second polygon is: " + poly2.getPerimeter() + " Area is: " + poly2.getArea());
        System.out.println("Perimeter of third polygon is: " + poly3.getPerimeter() + " Area is: " + poly2.getArea());
    }
}
