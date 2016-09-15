/*
 * This project is used to make a listing of names and ages and
 * inputs them into an array.
 */

package project1;

/**
 * Generic Listing Class.
 * 
 * @author Nabil Azamy and Derek Holtberg
 * @param <T>
 */
public class Listing <T> 
{
    private T age;
    private String name;
    
    /**
     * The constructor 
     * 
     * @param age is the age of the person.
     * @param name is the name of the person.
     */
    public Listing(T age, String name)
    {
        this.age = age;
        this.name = name;
    }
    
    /**
     * Input method to input new listing objects.
     * 
     * @param name of the person.
     * @param age of the person.
     */
    public void input(String name, T age)
    {
        setName(name);
        setAge(age);
    }
    /**
    * toString method that displays the name and age values.
    * @Override
    */
    public String toString()
    {
        return "Name: " + name + " Age: " + age + " \n";
    }
    
    /**
     * setName method that assigns the name variable a value.
     * 
     * @param name of the person
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     *setAge method that assigns the age variable a value.
     * 
     * @param age of the person
     */
    public void setAge(T age)
    {
        this.age = age;
    }
    
    /**
     * returns the value of the name variable.
     * 
     * @return the name of the person.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * returns the value of the age variable.
     * 
     * @return the age of the person
     */
    public T getAge()
    {
        return age;
    }
}
