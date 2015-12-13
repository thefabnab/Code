
/**
 * This class is the implementation of the quadratic method for collision
 * resolution within hashed maps. The create table establishes the table object
 * which contains the hashed list and surrounding methods for manipulation. The
 * find free slot method also searches the hash structure on a quadratic scale
 * for an open site to insert the object. It also increments and keeps track of
 * the instances of collisions within the hash structure.
 *
 * @author Nabil Azamy
 */
public class CollisionResolverQuadratic implements CollisionInterface
{

    private HashProperties table;
    private int numSlots;
    private int numCollisions = 0;

    public CollisionResolverQuadratic()
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

}
