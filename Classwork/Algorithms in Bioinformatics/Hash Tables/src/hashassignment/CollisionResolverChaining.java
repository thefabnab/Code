
/**
 * This class is the implementation of the Chaining method for collision
 * resolution within hashed maps. The create table establishes the table object
 * which contains the hashed list and surrounding methods for manipulation. The
 * find free slot method also searches the hash structure for a open site and
 * either implements a new linked list object to hold the hashed object or adds
 * to the end of the list
 *
 * @author Nabil Azamy
 */
public class CollisionResolverChaining implements CollisionInterface
{

    private HashProperties table;
    private int numSlots;
    private int numCollisions = 0;

    public CollisionResolverChaining()
    {
    }

    public void createTable(HashProperties _table)
    {
        table = _table;
        numSlots = table.getSlotNumber();

    }

    public int findFreeSlot(int startSlot)
    {
        int slot = startSlot;
        int i = 0;
        while (table.slotFull(slot))
        {
            numCollisions++;
            i++;
            slot = (slot + (1 / 2 * i + 1 / 2 * i ^ 2)) % numSlots;

        }
        return slot;

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

}
