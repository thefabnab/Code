
/**
 * This module defines all of the hashing functions.
 */
public interface HashingTypes {

    public int hash(int data);

    public static HashingTypes div_mod_120 = new HashingTypes() {
        public int hash(int data) {
            return data % 120;
        }
    };
    public static HashingTypes div_mod_113 = new HashingTypes() {
        public int hash(int data) {
            return data % 113;
        }
    };
    public static HashingTypes div_mod_41 = new HashingTypes() {
        public int hash(int data) {
            return data % 41;
        }
    };

    public static HashingTypes custom = new HashingTypes() {
        public int hash(int data) {

            return foldShift(data) % fourKPlus(120);

        }

        public int fourKPlus(int slots) {

            boolean fkp3 = false;
            boolean aPrime = false;
            int prime, highDivisor, d;
            double pctd = 75; //default loading factor
            prime = (int) (slots * (1.0 + (pctd / 100.0)));

            if (prime % 2 == 0) {
                prime++;
            }
            while (!fkp3) {
                while (!aPrime) {
                    highDivisor = (int) (Math.sqrt(prime) + 0.5);

                    for (d = highDivisor; d > 1; d--) {
                        if (prime % d == 0) {
                            break;
                        }
                    }
                    if (d != 1) {
                        prime += 2;
                    } else {
                        aPrime = true;
                    }

                    if ((prime - 3) % 4 == 0) {
                        fkp3 = true;
                    } else {
                        prime += 2;
                        aPrime = false;
                    }
                }
            }
            return prime;
        }
    };
    
    public static int foldShift(int data) {
        int pseudoKey = 0;
        int n = 1;
        int cn = 0;
        int grouping = 0;
        String targetKey;

        targetKey = Integer.toString(data);

        char c[] = targetKey.toCharArray();

        while (cn < targetKey.length()) {
            grouping = grouping << 8;
            grouping += c[cn];
            cn++;

            if (n == 4 || cn == targetKey.length()) {
                pseudoKey += grouping;
                n = 0;
                grouping = 0;
            }
            n++;
        }
        return Math.abs(pseudoKey);
    }
}
