/**
  Objects of this class implement one slot in a hash table that has multiple
  buckets per slot.
*/
public class HashTableSlot {
    public int buckets[];        // value of -1 means no data

    public HashTableSlot(int bucketSize) {
        buckets = new int[bucketSize];
        for (int i = 0; i < bucketSize; ++i) {
            buckets[i] = -1;
        }
    }
}