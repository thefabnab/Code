package project8;

import java.util.Arrays;
/**
 * A recursive ordered array program
 *
 * @author Derek and Nabil
 */
public class Project8 
{
    /**
     * This recursive binary search algorithm is designed to search through an
     * ordered array and produce the position within the array the searchValue
     * can be found within.
     * 
     * @param array         The array to be searched within
     * @param searchValue   The value to be searched for within the array
     * @param startSearch   The position within the array to start the search for searchValue
     * @param endSearch     The position within the array to end the search for searchValue
     * @return 
     */
    private static int binarySearch(int[] array, int searchValue, 
            int startSearch, int endSearch) 
    {
        if ((endSearch - startSearch) <= 1) 
        {
            if (array[startSearch] == searchValue) 
            {
                return startSearch;
            }
            if (array[endSearch] == searchValue) 
            {
                return endSearch;
            }
            return -1; //If search fails
        }
        int midPoint = (startSearch + endSearch) / 2; //establishes midpoint in array search
        if (array[midPoint] > searchValue) 
        {
            return binarySearch(array, searchValue, 0, midPoint);
        } 
        else 
        {
            return binarySearch(array, searchValue, midPoint, endSearch);
        }
    }

    public static void main(String[] args) 
    {
        int size = 1000; //To determining searched array size
        int[] arr = new int[size];
        int search = 10;
        
        for (int i = 0; i < size; i++) //Loop to produce an ordered array of size size
        {
            arr[i] = i+2; //I added the + 2 to the loop to stagger the index vs index[] values
        }
        //binary search through the array from 0 to length size-1 searching for location of searchValue
        System.out.println("For an array of size " + size + " the value of " + search + " can be found in position:");
        System.out.println(binarySearch(arr,search,0,arr.length-1)); 
        
    }
}