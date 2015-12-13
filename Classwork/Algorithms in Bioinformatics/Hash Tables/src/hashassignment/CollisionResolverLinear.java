
/**
 * This class is the implementation of the linear method for collision
 * resolution within hashed maps. The create table establishes the table object
 * which contains the hashed list and surrounding methods for manipulation. The
 * find free slot method also searches the hash structure linearlly for an open
 * site to insert the object. It also increments and keeps track of the
 * instances of collisions within the hash structure.
 *
 * @author Nabil Azamy
 */

public class CollisionResolverLinear implements CollisionInterface
{

    private HashProperties table;
    private int numSlots;
    private int numCollisions = 0;

    public CollisionResolverLinear()
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
        while (table.slotFull(slot))
        {
            numCollisions++;
            slot = (slot + 1) % numSlots;
            if (slot == startSlot)
            {
                return -1;  // table is full.
            }
        }
        return slot;
    }

}
