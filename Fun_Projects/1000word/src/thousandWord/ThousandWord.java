package thousandWord;

/**
 *
 *
 * @author thefabnab
 */
public class ThousandWord
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        for (double num = 1; num < 100; num++)
        {
            double sroot = Math.sqrt(num);
            System.out.println("Square root of " + num
                    + " is " + sroot);
        }
    }

}
