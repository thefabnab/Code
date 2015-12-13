import java.io.PrintStream;

public class HashOneBucket implements HashProperties
{

    private HashingTypes hashFunction;
    private int tableSize;
    private int table[];
    private CollisionInterface collisionResolver;
    private int numCollisions = 0;

    public HashOneBucket(HashingTypes _hashFunction, int _tableSize,
            CollisionInterface _collisionResolver)
    {
        hashFunction = _hashFunction;
        tableSize = _tableSize;
        table = new int[_tableSize];
        for (int i = 0; i < _tableSize; ++i)
        {
            table[i] = -1;
        }
        collisionResolver = _collisionResolver;
        collisionResolver.createTable(this);
    }

    public int getHashTableSize()
    {
        return tableSize;
    }

    public int getSlotNumber()
    {
        return tableSize;
    }

    public boolean slotFull(int i)
    {
        // Every time we return true, that constitutes a collision
        if (table[i] != -1)
        {
            numCollisions++;
        }

        return table[i] != -1;
    }

    public void put(int data)
    {
        // Get the hash value
        int hash = hashFunction.hash(data);
        // Limit its size
        int startSlot = hash % tableSize;

        // Resolve collisions
        int finalSlot = collisionResolver.findFreeSlot(startSlot);
        table[finalSlot] = data;
    }

    public int getCollisions()
    {
        return numCollisions;
    }

    /**
     * In the cases where we have one bucket / slot, we are supposed to print
     * the table five elements across.
     */
    public void print(PrintStream ps)
    {
        for (int i = 0; i < tableSize; i++)
        {
            ps.format("%6d", table[i]);
            if (i % 5 == 4)
            {
                ps.print("\n");
            }
        }
    }
}
