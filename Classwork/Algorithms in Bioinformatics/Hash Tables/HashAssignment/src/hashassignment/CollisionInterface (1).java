//Changed
/**
 * This module defines an interface and factory for a hash table collision
 * resolver.
 */

public interface CollisionInterface
{

    public void createTable(HashProperties _table);

    public int findFreeSlot(int startSlot);
}
