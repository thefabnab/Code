public class CollisionResolverChaining implements CollisionInterface {

    private HashProperties table;
    private int numSlots;
    private int numCollisions = 0;
    
    public CollisionResolverChaining() {
    }
    
    public void createTable(HashProperties _table) {
        table = _table;
        numSlots = table.getSlotNumber();
    
    }
    
    public int findFreeSlot(int startSlot) {
        int slot = startSlot;
        int i = 0;
        while (table.slotFull(slot)) {
            numCollisions++;
            i++;
            slot = (slot + (1/2*i + 1/2*i^2)) % numSlots;
            
        }
        return slot;
    
    }



}