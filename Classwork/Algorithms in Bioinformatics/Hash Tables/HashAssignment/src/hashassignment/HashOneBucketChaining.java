import java.io.PrintStream;

public class HashOneBucketChaining implements HashProperties
{

    private HashingTypes hashFunction;
    private int tableSize;
    private int table[];
    private CollisionInterface collisionResolver;
    private int numCollisions = 0;

    public HashOneBucketChaining(HashingTypes _hashFunction, int _tableSize,
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

    public class LinkedList
    {

        private Node head;
        private int numNodes;

        public LinkedList(Object dat)
        {
            head = new Node(dat);
        }

        public void addAtHead(Object dat)
        {
            Node temp = head;
            head = new Node(dat);
            head.next = temp;
        }

        public void addAtTail(Object dat)
        {
            Node temp = head;
            while (temp.next != null)
            {
                temp = temp.next;
            }

            temp.next = new Node(dat);
        }

        public void addAtIndex(int index, Object dat)
        {
            Node temp = head;
            Node holder;
            for (int i = 0; i < index - 1 && temp.next != null; i++)
            {
                temp = temp.next;
            }
            holder = temp.next;
            temp.next = new Node(dat);
            temp.next.next = holder;
        }

        public void deleteAtIndex(int index)
        {
            Node temp = head;
            for (int i = 0; i < index - 1 && temp.next != null; i++)
            {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }

        public void printList()
        {
            Node temp = head;
            while (temp != null)
            {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }

        public int getSize()
        {
            return numNodes;
        }

        class Node
        {
            Node next;
            Object data;

            Node(Object dat)
            {
                data = dat;
            }
        }
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
