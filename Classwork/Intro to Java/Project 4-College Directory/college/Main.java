import javax.swing.JOptionPane;
/**
 * A main method to help publish graduate student information
 * 
 * @author Nabil Azamy 
 * @version 10/21/14
 */
public class Main
{
    public static void main(String[] args)
    {
        Graduate a1 = new Graduate();
        
        String parseFirstName, parseLastName, parseProgram;
        int parseStudentID, parseGreScore;
        double parseGradePointAverage;
        
        parseFirstName = JOptionPane.showInputDialog("Enter Your First Name: ");
        parseLastName = JOptionPane.showInputDialog("Enter Your Last Name: ");
        parseProgram = JOptionPane.showInputDialog("Enter Your Program Name \n(masters or doctorate): ");
        parseStudentID = Integer.parseInt(JOptionPane.showInputDialog("Enter Your Student ID Number \n(7000000 - 9999999): "));
        parseGreScore = Integer.parseInt(JOptionPane.showInputDialog("Enter Your GRE Score \n(400 - 1600): "));
        parseGradePointAverage = Double.parseDouble(JOptionPane.showInputDialog("Enter Your Grade Point Average \n(0 - 4.0): "));
        
        a1.setFirstName(parseFirstName);
        a1.setLastName(parseLastName);
        a1.setProgram(parseProgram);
        a1.setStudentID(parseStudentID);
        a1.setGreScore(parseGreScore);
        a1.setGradePointAverage(parseGradePointAverage);
        
        JOptionPane.showMessageDialog(null, "Name: \t" + a1.getFirstName() + " " + a1.getLastName() + 
        "\nStudent ID: \t" + a1.getStudentID() + "\nProgram: \t" + a1.getProgram() + "\nGRE Score: \t" + a1.getGreScore()
        + "\nGPA: \t" + a1.getGradePointAverage());
        
        
    }
}
