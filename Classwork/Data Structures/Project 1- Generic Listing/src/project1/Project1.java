package project1;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This code allows us to take user input and create an array listing 
 * based on the users parameters that he sets.
 *
 * @author Nabil Azamy and Derek Holtberg
 */
public class Project1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int listingNumber;
        Scanner inputNumber  = new Scanner(System.in);
        Scanner inputType  = new Scanner(System.in);
        
        System.out.println("Hello! Will you be inputing your age listings as"
                + "Double (D) or Integer (I) types? \n"
                + "Please choose eithe D or I:");

        String in = inputType.next();

        System.out.println("How many listings will you be inputting?");
        listingNumber = Integer.parseInt(inputNumber.next());
        Listing[] list = new Listing[listingNumber];

        choice(in, listingNumber,list);
        reverseArray(listingNumber, list);
        System.out.println("Your listings are as follows: ");
        System.out.println(Arrays.toString(list));
    }
    /**
     * This method creates and inputs all the info into an array index based 
     * on your choices.
     * 
     * @param in is the user choice of either Double or integer.
     * @param listingNumber the index size of the array.
     * @param list the new array that is created.
     */
    public static void choice(String in, int listingNumber, Listing[] list)
    {     
        Scanner inputAge = new Scanner(System.in);
        Scanner inputName = new Scanner(System.in);
        
        if("D".equals(in))
        {
            for(int i = 0; i < listingNumber; i++)
            {
                System.out.println("Please input listing number " + (i+1) + "'s "
                        + "name:");
                String name = inputName.next();

                System.out.println("Please input listing number " +(i+1)+ "'s "
                        + "age (Double):");

                double age = Double.parseDouble(inputAge.next());

                Listing<Double> temp = new Listing<>(age, name);
                list[i] = temp;

            }
        }

        else if("I".equals(in))
        {
            for(int i = 0; i < listingNumber; i++)
            {
                System.out.println("Please input listing number " +(i+1) +"'s "
                        + "name:");
                String name = inputName.next();

                System.out.println("Please input listing number" + i + "'s "
                        + "age (Integer): ");

                int age = Integer.parseInt(inputAge.next());

                Listing<Integer> temp = new Listing<>(age, name);
                list[i] = temp;

            }
        }
    }
    
    /**
     * This method displays all the items in your index in reverse order.
     * 
     * @param listingNumber is the index number of the array.
     * @param list is the array name.
     * @return 
     */
    public static Listing[] reverseArray(int listingNumber, Listing[] list)
    {
        Listing temp;
        for (int i = 0; i < listingNumber / 2; i++) {
            temp = list[i];
            list[i] = list[listingNumber - 1 - i];
            list[listingNumber - 1 - i] = temp;
        }
        return list;
    }
}
