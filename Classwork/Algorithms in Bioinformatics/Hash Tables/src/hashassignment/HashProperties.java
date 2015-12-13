//Changed
import java.io.PrintStream;

public interface HashProperties
{

    public void put(int i);

    public int getHashTableSize();

    public int getSlotNumber();

    public boolean slotFull(int i);

    public int getCollisions();

    public void print(PrintStream ps);

}
