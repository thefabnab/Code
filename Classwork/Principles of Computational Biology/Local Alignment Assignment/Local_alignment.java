package local_alignment;

/**
 * A local alignment algorithm which takes an input sequence string 1 and 2 and
 * outputs their best alignment according to a Needleman Wunsch scoring matrix.
 * These are aligned according using the following values: Match = +5 Mismatch =
 * -4 Gap = -4
 *
 * @author Nabil Azamy
 */
public class Local_alignment 
{

    //Variables

    String seq1;
    String seq2;
    int[][] score_matrix;
    int score;
    String alignment1 = "";
    String alignment2 = "";

    /**
     * Main method which accepts sequence inputs via sequence1 and sequence2
     * which then create a scoring matrix and alignment.
     *
     * @param args
     */
    public static void main(String[] args) 
    {
        String sequence1 = "GCTGGAAGGCAT";
        String sequence2 = "GCAGAGCACG";

        Local_alignment lo = new Local_alignment(); //create new alignment object
        lo.matrix(sequence1, sequence2); //produce a nxm scoring matrix
        lo.scoring(); //score the alignment
        lo.trace_seq(); //reverse trace the highest scoring sequence
        lo.printMatrix(); //prints scoring matrix
        lo.printseq(); //prints sequence alignment
    }

    /**
     * Method establishing matrix of size sequence1 x sequence2
     *
     * @param sequence1
     * @param sequence2
     */
    void matrix(String sequence1, String sequence2) 
    {
        seq1 = sequence1;
        seq2 = sequence2;

        score_matrix = new int[seq2.length() + 1][seq1.length() + 1];
        for (int i = 0; i <= seq2.length(); i++) 
        {
            score_matrix[i][0] = 0;
        }
        for (int j = 0; j <= seq1.length(); j++) 
        {
            score_matrix[0][j] = 0;
        }
    }

    /**
     * Method follows a stepwise algorithm path to score each individual 2x2
     * block of the scoring matrix to arrive at a scored matrix which takes into
     * account matches, mismatches, and 'gaps' formed within the alignment.
     *
     */
    void scoring() 
    {
        int scoreD;
        int scoreL;
        int scoreU;
        for (int i = 1; i <= seq2.length(); i++) 
        {
            for (int j = 1; j <= seq1.length(); j++) 
            {
                //score match/mismatch
                if ((score_matrix[i - 1][j - 1] + weight(i, j)) < 0) 
                {
                    scoreD = 0;
                } else 
                {
                    scoreD = score_matrix[i - 1][j - 1] + weight(i, j);
                }
                //score gap
                if ((score_matrix[i - 1][j] - 4) < 0) 
                {
                    scoreL = 0;
                } else 
                {
                    scoreL = score_matrix[i - 1][j] - 4;
                }
                //score gap
                if ((score_matrix[i][j - 1] - 4) < 0) 
                {
                    scoreU = 0;
                } else 
                {
                    scoreU = score_matrix[i][j - 1] - 4;
                }

                int Max_DL = Math.max(scoreD, scoreL);
                int Max_all = Math.max(Max_DL, scoreU);
                score_matrix[i][j] = Max_all;
            }
        }
    }

    /**
     * Compares one base to another to establish a match/mismatch status and
     * assigns a score based on that comparison
     *
     * @param i
     * @param j
     * @return
     */
    int weight(int i, int j) 
    {
        //weighted score for match
        if (seq2.charAt(i - 1) == seq1.charAt(j - 1)) 
        {
            return 5;
        } //weighted score for mismatch
        else {
            return -4;
        }
    }

    /**
     * A back tracking method which takes a scored matrix and traces it back
     * from the bottom right segment back up until it reaches the top left of
     * the matrix
     *
     * Method also produces a reverse alignment based off the trace algorithm
     *
     */
    void trace_seq() 
    {
        int j = seq1.length();
        int i = seq2.length();

        while (i > 0 && j > 0) 
        {
            //Case of match
            if (score_matrix[i][j] == score_matrix[i - 1][j - 1]
                    + weight(i, j)) {
                alignment2 = alignment2 + seq2.charAt(i - 1);
                alignment1 = alignment1 + seq1.charAt(j - 1);
                i--;
                j--;
            } //Case of gap in sequence 2
            else if (score_matrix[i][j] == score_matrix[i][j - 1]) 
            {
                alignment2 = alignment2 + "-";
                alignment1 = alignment1 + seq1.charAt(j - 1);
                j--;
            } //Case of gap in sequence 1
            else 
            {
                alignment2 = alignment2 + seq2.charAt(i - 1);
                alignment1 = alignment1 + "-";
                i--;
            }
        }
    }

    /**
     * Reverses and prints out the two alignment sequences including gaps and
     * mismatches.
     */
    void printseq() 
    {
        alignment1 = new StringBuffer(alignment1).reverse().toString();
        alignment2 = new StringBuffer(alignment2).reverse().toString();
        System.out.println("\nAlignment: \n" + alignment1 + "\n" + alignment2);
    }

    /**
     * Prints out scoring matrix complete with the sequences being aligned
     */
    void printMatrix() 
    {
        System.out.print("\nScoring Matrix:\n          ");
        for (int i = 0; i < seq1.length(); i++) 
        {
            System.out.print(String.format("%4c ", seq1.charAt(i)));
        }
        System.out.println();
        for (int i = 0; i <= seq2.length(); i++) 
        {
            if (i > 0) 
            {
                System.out.print(String.format("%4c ", seq2.charAt(i - 1)));
            } else 
            {
                System.out.print("     ");
            }
            for (int j = 0; j < seq1.length() + 1; j++) 
            {
                System.out.print(String.format("%4d ", score_matrix[i][j]));
            }
            System.out.println();
        }
    }
}
