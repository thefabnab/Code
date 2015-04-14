/*
 * Interface that is utilized by the ints class and StudentListings class. 
 * 
 */
package project4;

/**
 *
 * @author Derek Holtberg & Nabil Azamy
 */
public interface KeyMode
{
    public KeyMode deepCopy();
    
    @Override
    public String toString();
}
