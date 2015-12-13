/**
 *  An application demonstrating the flexibility of the hashed data structure it will
 * cover both alternative hashing methods (linear, quadratic, chaining, and a custom),
 * and using varying bucket sizes for the purposes of address stacking.
 */

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashAssignment
{
    
    static String testdatafile = "LabHashingInput.txt";
    static List<Integer> testdata = new ArrayList<>();

    public static void main(String[] args)
    {
        StringBuffer sb = new StringBuffer();
        int i = 0;

        try
        {
            //import sample dataset
            BufferedReader input = new BufferedReader(new FileReader(
                    testdatafile));
            while (input.ready())
            {
                String current = input.readLine();
                if (!"".equals(current) && i > 3)
                {
                    testdata.add(Integer.parseInt(current));
                } 
                else
                {
                    i++;
                }
            }
        } 
        catch (IOException | NumberFormatException e)
        {
            System.err.println(e.toString());
            return;
        }
        //establish test parameters
        System.out.println(
                "Number of items to be hashed:  " + testdata.size());

        CollisionInterface linear = new CollisionResolverLinear();
        CollisionInterface quadratic = new CollisionResolverQuadratic();
        CollisionInterface chain = new CollisionResolverChaining();

        //Modulo 120 tests: 1,2, and 3
        hash_test(
                "Division modulo 120 on a table size of 120 slots (bucket size = 1), linear probing",
                new HashOneBucket(HashingTypes.div_mod_120, 120, linear));
        hash_test(
                "Division modulo 120 on a table size of 120 slots (bucket size = 1), quadratic probing",
                new HashOneBucket(HashingTypes.div_mod_120, 120, quadratic));
        hash_test("Division modulo 120 on a table size of 120 slots (bucket size = 1), chaining",
                  new HashOneBucket(HashingTypes.div_mod_120, 113, chain));
        
        //Modulo 113 tests: 4,5, and 6
        hash_test(
                "Division modulo 113 on a table size of 120 slots (bucket size = 1), linear probing",
                new HashOneBucket(HashingTypes.div_mod_113, 120, linear));
        hash_test(
                "Division modulo 113 on a table size of 120 slots (bucket size = 1), quadratic probing",
                new HashOneBucket(HashingTypes.div_mod_113, 120, quadratic));
        hash_test("Division modulo 113 on a table size of 120 slots (bucket size = 1), chaining",
                  new HashOneBucket(HashingTypes.div_mod_113, 113, chain));

        //Bucket = 3 Modulo 41 tests: 7, and 8
        hash_test(
                "Division modulo 41 on a table size of 40 slots (bucket size = 3), (120 total slots)  linear probing",
                new HashMultiBucket(HashingTypes.div_mod_41, 120, 3, linear));
        hash_test(
                "Division modulo 41 on a table size of 40 slots (bucket size = 3), (120 total slots)  quadratic probing",
                new HashMultiBucket(HashingTypes.div_mod_41, 120, 3,
                        quadratic));
        
        //Custom hashing tests: 9, 10, and 11
        hash_test(
                "Custom hash function on a table size of 120 slots (bucket size = 1), linear probing",
                new HashOneBucket(HashingTypes.custom, 120, linear));
        hash_test(
                "Custom hash function on a table size of 120 slots (bucket size = 1), quadratic probing",
                new HashOneBucket(HashingTypes.custom, 120, quadratic));

        hash_test("Custom hash function on a table size of 120 slots (bucket size = 1), chaining",
                  new HashOneBucket(HashingTypes.custom, 113, chain));
    }

    public static void hash_test(String title, HashProperties table)
    {
        System.out.println("****  Hashing Scheme: " + title + " ****\n");

        for (int i = 0; i < testdata.size(); ++i)
        {
            table.put(testdata.get(i));
        }

        //table.print(System.out);
        System.out.println("\nCollisions:  " + table.getCollisions());
    }
}
