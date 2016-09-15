package project3;

/**
 * Interface for establishing the methods and object types required for the 
 * ProfessorListings class.
 *
 * @author Nabil Azamy & Derek Holtberg
 */
public interface KeyMode 
{
    public KeyMode deepCopy();

    public int compareTo(Object targetKey);

    @Override
    public String toString();
}
