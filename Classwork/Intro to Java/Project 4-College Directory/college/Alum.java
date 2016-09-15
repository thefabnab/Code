import java.util.ArrayList;

/**
 * Write a description of class Alum here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alum extends CommunityMember
{
    private int gradYear;
    private String degree;
    
    private ArrayList<String> degrees;
    
    public Alum(String firstName,String lastName, int gradYear, String degree)
    {
        super(firstName, lastName);
        degrees = new ArrayList<String>();
        
        degrees.add("B.A.");
        degrees.add("B.S.");
        degrees.add("M.A.");
        degrees.add("M.S.");
        degrees.add("PH.D");
        
        setGradYear(gradYear);
        setDegree(degree);
    }
    public void setGradYear(int gradYear)
    {
        if(gradYear >= 1960 && gradYear <=2014 || gradYear == 0 )
        {
            this.gradYear = gradYear;
        }
    }
    public void setDegree(String degree)
    {
        if(degrees.indexOf( degree.toUpperCase()) >= 0 || degree.equals(" "))
        {
            this.degree = degree.toUpperCase();
        }
    }
    public int getGradYear()
    {
        return gradYear;
    }
    public String getDegree()
    {
        return degree;
    }
        
}
