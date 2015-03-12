import javax.swing.JOptionPane;
/**
 * A driver displaying the information for both Executive and Salesperson employees.
 * 
 * @author Nabil Azamy
 * @version version 1
 */
public class Driver
{
    public static void main(String[] args)
    {
        Employee[] workers =
            {
                new Salesperson(),
                new Executive(),
            };

        for (Employee w : workers)
        {
            if (w == workers[0])
            {

                w.setName(JOptionPane.showInputDialog("Enter Salesperson Name:"));
                w.setAddress(JOptionPane.showInputDialog("Enter Salesperson Address:"));
                //w.setSales(Integer.parseInt(JOptionPane.showInputDialog("Enter Salesperson Sales number:")));
                //I couldn't get the setSales method to work.  I tried casting but couldn't get it to work.
                w.setSalary(Double.parseDouble(JOptionPane.showInputDialog("Enter Salesperson Salary:")));
                JOptionPane.showMessageDialog(null, w);
            }            
            else
            {
                w.setName(JOptionPane.showInputDialog("Enter Executive Name:"));
                w.setAddress(JOptionPane.showInputDialog("Enter Executive Address:"));
                w.setSalary(Double.parseDouble(JOptionPane.showInputDialog("Enter Executive Salary:")));
                JOptionPane.showMessageDialog(null, w);
            }

        }

        System.exit(0);
    }
}
