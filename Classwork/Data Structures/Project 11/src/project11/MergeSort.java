package project11;


/**
 * Instantiates in unsorted student listing array and performs a merge sort.
 * 
 * @author Nabil Azamy & Derek Holtberg
 */
public class MergeSort 
{
    private StudentListing[] items;  // The StudentListing array to be sorted
    
    /**
     * Instantiates the Student listing array to be sorted 
     * 
     * @param n 
     */
    public MergeSort(StudentListing[] n)
    {
        items = n;
    }

    /**
     * If the sub-array is equal to or more than one element, split the array into 
     * two and call the recursive mergeSort(int, int) method.  Stop when the base case 
     * is reached, e.g. an array of one (1) item to be sorted.  Then begin calling the
     * merge(int, int, int) method to merge the sorted sub-arrays.
     *
     * @param left the index of the left-most element in the array or sub-array
     * @param right the index of the right-most element in the array or sub-array
     */
    
    public void mergeSort(int left, int right) 
    {
        if ( (right - left + 1) > 1)  // Stop when base case is reached
        {
            int middle = (left + right) / 2;

            mergeSort(left, middle);
            mergeSort(middle + 1, right);

            merge(left, middle, right);
        }
    }

    /**
     * Merge the two sub-arrays into a single sorted array.  The left and right lists
     * are merged in ascending sequence.  When either the left or right list is completed, 
     * finish the process by copying the rest of the other list to the merged array.
     * At the end copy the temp list to the permanent array.
     *
     * @param left the index of the left-most element in the left array or sub-array
     * @param middle the index of the right-most element in the left array or sub-array 
              (the index of the left-most element in the right array or sub-array is middle + 1)
     * @param right the index of the right-most element in the right array or sub-array
     */
    
    private void merge(int left, int middle, int right) 
    {
        int leftIndex = left;
        int rightIndex = middle + 1;
        int tempIndex = left;
        StudentListing[] temp = new StudentListing[items.length];

        while (leftIndex <= middle && rightIndex <= right)  // Merge left and right lists in sequential order
        {
            if ( items[leftIndex].getID() <= items[rightIndex].getID() ) 
            {
                temp[tempIndex++] = items[leftIndex++];
            } 
            else 
            {
                temp[tempIndex++] = items[rightIndex++];
            }
        }

        if (leftIndex == middle + 1) // If left list is complete and right list is not, finish merging right elements
        {
            while (rightIndex <= right) 
            {
                temp[tempIndex++] = items[rightIndex++];
            }
        } 
        else                         // If right list is complete and left list is not, finish merging left elements
        {
            while (leftIndex <= middle) 
            {
                temp[tempIndex++] = items[leftIndex++];
            }
        }

        for (int index = left; index <= right; index++) 
        {
            items[index] = temp[index];
        }
    }

    /**
     * Returns the size of the student listing array minus 1 (items.length - 1) which is the index 
     * of the right-most element.
     * 
     * @return the index of the last element in the array
     */
    
    public int getLastIndex()
    {
        return items.length - 1;
    }
    
    /**
     * Returns the list of integer items in the array.
     * 
     * @return the list of int's in the array
     */
    
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        
        for (StudentListing value : items)
        {
            output.append(value);
            output.append(" ");
        }
        
        output.append("\n");
        
        return output.toString();
    }
}
