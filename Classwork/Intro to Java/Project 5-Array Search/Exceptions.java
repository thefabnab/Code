import java.util.*;

/**
 *A quick little program that generages a random array and searches your guessed number
 *for where in the array that value is located, and returns it.
 * 
 * @author Nabil Azamy
 * @version 10/20/14v1
 */
public class Exceptions
{
    /**
     * Input an int value to find a value within the array.
     * validates user input with exception classes 
     * InputMismatchException and ArrayIndexOutOfBoundsException.
     * 
     * @param args The String[] array command line parameter
     * 
     * @throws InputMismatchException prints statement if user does not enter an integer
     * @throws ArrayIndexOutOfBoundsException prints statement if user does not choose a number between 0 and 100
     */

    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);

        int[] interval = new int[100];
        Random dom = new Random();
        for (int i = 0; i < interval.length; i++) interval[i] = dom.nextInt(100) + 1;

        try
        {
            System.out.print("Enter index from 0 to 100:");
            int index = reader.nextInt();

            System.out.println( interval[index]);
        }
        //exceptions below
        catch (InputMismatchException ex)
        {
            System.out.println(ex + "(Enter an integer buddy)");
        }

        catch (ArrayIndexOutOfBoundsException ex)
        {
            System.out.println ( ex + "(Please enter an index within range)");
        }

        catch (Exception ex)
        {
            System.out.println (ex);
        }
    }
}