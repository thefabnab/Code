package project11;


/**
 * This is an application which takes as an input a specific number of student
 * listings and uses a merge sort algorithm to organize the resulting objects
 * in increasing order by student ID number.
 * 
 *
 * @author Nabil Azamy & Derek Holtberg
 */
public class Project11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        StudentListing[] array = new StudentListing[10]; //instantiate new Student listing array
        
        //Unsorted student listings via ID
        array[0] = new StudentListing("Steve Miller", 345, 3.0);
        array[1] = new StudentListing("Rick Anderson", 113, 2.3);
        array[2] = new StudentListing("Jon Williams", 563, 3.7);
        array[3] = new StudentListing("Jan Stavington", 863, 1.4);
        array[4] = new StudentListing("Franklin Meets", 111, 3.5);
        array[5] = new StudentListing("Leslie Thompson", 589, 3.8);
        array[6] = new StudentListing("Roger Franklin", 704, 2.0);
        array[7] = new StudentListing("Jessie Disacio", 482, 2.9);
        array[8] = new StudentListing("Eve Neverson", 932, 3.4);
        array[9] = new StudentListing("Emily Hodges", 382, 4.0);
        
        System.out.println("\n******Student ID BEFORE SORT******\n");
        
        for (int i =0; i < 10; i++)
        {
            System.out.println(array[i].toString());
        }
        
        //Sort array object via student ID values
        MergeSort sortedArray = new MergeSort(array);
        sortedArray.mergeSort(0, array.length-1);
        
        System.out.println("\n******Student ID AFTER sort******\n");
        
        for (int i =0; i < 10; i++)
        {
            System.out.println(array[i].toString());
        }   
    }
}
