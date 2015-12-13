public class CollisionResolverLinear implements CollisionInterface {
    private HashProperties table;
    private int numSlots;
    private int numCollisions = 0;

    public CollisionResolverLinear() {
    }

    public void createTable(HashProperties _table) {
        table = _table;
        numSlots = table.getSlotNumber();
    }

    public int findFreeSlot(int startSlot) {
        int slot = startSlot;
        while (table.slotFull(slot)) {
            numCollisions++;
            slot = (slot + 1) % numSlots;
            if (slot == startSlot) return -1;  // table is full.
        }
        return slot;
    }
}
