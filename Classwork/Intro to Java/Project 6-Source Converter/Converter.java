import java.io.PrintWriter;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

/**
 * A quick little new line to end of line converter
 * 
 * @author Nabil Azamy
 * @version v3.0
 */
public class Converter
{
    /**
     * Using the JFileChooser the user picks the file they'd like to be converted from new line to end
     * of line.  The file is then scanned and put into a string, maintaining its formatting and has its
     * { positioning changed via a replaceAll method.
     * 
     * @param args The String[] array command line parameter
     * 
     * @throws IOException if some sort of I/O exception has occurred
     */
    public static void main(String[] args) throws IOException
    {
        String s ="";

        try
        {
            JFileChooser fileChooser = new JFileChooser("App_Data");

            if (fileChooser.showOpenDialog(null) 
                    == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                
                Scanner inReader = new Scanner(file);
                inReader.useDelimiter("\\Z");  
                s = inReader.next();
                s = s.replaceAll("\\)\\s*\\{", ") {");
                s = s.replaceAll("\\s*\\{", " {");
                System.out.println(s);
                
                PrintWriter output = new PrintWriter("edited.txt");
                output.println(s);
                inReader.close();
                output.close();
            }
        }

        catch (IOException ex)
        {
            System.out.println("You didn't pick the correct type of file " + ex);
        }

        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}