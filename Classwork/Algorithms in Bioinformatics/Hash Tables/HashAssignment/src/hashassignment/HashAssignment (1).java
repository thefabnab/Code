//Changed
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class HashAssignment
{

    static String testdatafile = "LabHashingInput.txt";
    static List<Integer> testdata = new ArrayList<>();

    public static void main(String[] args)
    {
        StringBuffer sb = new StringBuffer();

        try
        {
            BufferedReader input = new BufferedReader(new FileReader(
                    testdatafile));
            String line;
            int i = 0;

            while (input.ready())
            {
                String current = input.readLine();
                if (!"".equals(current) && i > 3)
                {
                    testdata.add(Integer.parseInt(current));
                } else
                {
                    i++;
                }
            }
        } catch (IOException | NumberFormatException e)
        {
            System.err.println(e.toString());
            return;
        }
        System.out.println(
                "Number of items to be hashed:  " + testdata.size());

        CollisionInterface linear = new CollisionResolverLinear();
        CollisionInterface quadratic = new CollisionResolverQuadratic();
        CollisionInterface chain = new CollisionResolverChaining();

        hash_test(
                "Division modulo 120 on a table size of 120 slots (bucket size = 1), linear probing",
                new HashOneBucket(HashingTypes.div_mod_120, 120, linear));
        hash_test(
                "Division modulo 120 on a table size of 120 slots (bucket size = 1), quadratic probing",
                new HashOneBucket(HashingTypes.div_mod_120, 120, quadratic));
        hash_test("Division modulo 120 on a table size of 120 slots (bucket size = 1), chaining",
                  new HashOneBucket(HashingTypes.div_mod_120, 120, chaining));

        hash_test(
                "Division modulo 113 on a table size of 120 slots (bucket size = 1), linear probing",
                new HashOneBucket(HashingTypes.div_mod_113, 120, linear));
        hash_test(
                "Division modulo 113 on a table size of 120 slots (bucket size = 1), quadratic probing",
                new HashOneBucket(HashingTypes.div_mod_113, 120, quadratic));
        //hash_test("Division modulo 113 on a table size of 120 slots (bucket size = 1), chaining",
        //          new HashOneBucket(HashFunction.div_mod_113, 120, chaining));

        hash_test(
                "Division modulo 41 on a table size of 40 slots (bucket size = 3), (120 total slots)  linear probing",
                new HashMultiBucket(HashingTypes.div_mod_41, 120, 3, linear));
        hash_test(
                "Division modulo 41 on a table size of 40 slots (bucket size = 3), (120 total slots)  quadratic probing",
                new HashMultiBucket(HashingTypes.div_mod_41, 120, 3,
                        quadratic));

        hash_test(
                "Custom hash function on a table size of 211 slots (bucket size = 1), linear probing",
                new HashOneBucket(HashingTypes.custom, 120, linear));
        hash_test(
                "Custom hash function on a table size of 211 slots (bucket size = 1), quadratic probing",
                new HashOneBucket(HashingTypes.custom, 120, quadratic));

        //hash_test("Custom hash function on a table size of 120 slots (bucket size = 1), chaining",
        //          new HashOneBucket(HashFunction.custom, 120, chaining));
    }

    public static void hash_test(String title, HashProperties table)
    {
        System.out.println("****  Hashing Scheme: " + title + " ****\n");

        for (int i = 0; i < testdata.size(); ++i)
        {
            table.put(testdata.get(i));
        }

        table.print(System.out);
        System.out.println("\nCollisions:  " + table.getCollisions());
    }
}
