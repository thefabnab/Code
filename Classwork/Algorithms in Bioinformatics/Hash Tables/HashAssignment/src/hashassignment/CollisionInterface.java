
/**
 * This interface defines the methods to determine collisions using the varying
 * algorithms, linear, quadratic, and the custom.
 *
 * @author Nabil Azamy
 */

public interface CollisionInterface
{

    public void createTable(HashProperties _table);

    public int findFreeSlot(int startSlot);
}
