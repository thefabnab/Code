package longestcommonsubsequence;

/**
 * This is a program which computes longest common subsequence problem using
 * both a recursive (O(2^n)) and a dynamic programming (O(nxm)) algorithms.
 *
 *
 * @author Nabil Azamy
 */
public class LongestCommonSubsequence
{

    /**
     * This method takes two strings and utilizes memoization to generate a 
     * dynamically programmed matrix of all possible string combinations and
     * scores their results against each other while also producing a backtracking
     * matrix to help generate a O(nxm) time LCS solution.
     * 
     * @param seq1  Sequence string 1
     * @param seq2  Sequence string 2
     * @return      LCS sequence of the two strings
     */
    public static String LCSDynamicProgramming(String seq1, String seq2)
    {
        //tracing variables
        int mismatch = 0;
        int traceLeft = 1;
        int traceRight = 2;
        int match = 3;

        //string lengths
        int seq1_len = seq1.length();
        int seq2_len = seq2.length();
        int x, y;

        //dynamic programming matrices
        int[][] score = new int[seq1_len + 1][seq2_len + 1];
        int[][] backTrack = new int[seq1_len + 1][seq2_len + 1];
        StringBuilder subsequence = new StringBuilder();

        // Initializing the scoring and backTracking arrays
        for (x = 0; x <= seq1_len; ++x)
        {
            score[x][0] = 0;
            backTrack[x][0] = traceLeft;
        }
        for (y = 0; y <= seq2_len; ++y)
        {
            score[0][y] = 0;
            backTrack[0][y] = traceRight;
        }

        // This is a dynamic programming doubly nested loop which computes both
        // the scoring and backtracking matrices.
        for (x = 1; x <= seq1_len; ++x)
        {
            for (y = 1; y <= seq2_len; ++y)
            {
                //Replaced recursive calls with memoized double nested for loop
                if (seq1.charAt(x - 1) == seq2.charAt(y - 1))
                {
                    score[x][y] = score[x - 1][y - 1] + 1;
                    backTrack[x][y] = match;
                } else
                {
                    score[x][y] = score[x - 1][y - 1] + 0;
                    backTrack[x][y] = mismatch;
                }
                if (score[x - 1][y] >= score[x][y])     //If above is >= 
                {
                    score[x][y] = score[x - 1][y];      //make = to above
                    backTrack[x][y] = traceLeft;
                }
                if (score[x][y - 1] >= score[x][y])     //If left is >=
                {
                    score[x][y] = score[x][y - 1];      //make = to left
                    backTrack[x][y] = traceRight;
                }
            }
        }

        // The backTracking while loop       
        x = seq1_len;
        y = seq2_len;
        while (x > 0 || y > 0)
        {
            if (backTrack[x][y] == match)
            {
                x--;
                y--;
                subsequence.append(seq1.charAt(x));
            } else if (backTrack[x][y] == traceLeft) //gapUp = 2 up on graph
            {
                x--;
            } else if (backTrack[x][y] == traceRight) //gapLeft = 1 go left
            {
                y--;
            }
        }
        //Prints out the Scoring Matrix
        System.out.print("\nScoring Matrix:\n     ");
        for (int i = 0; i < seq1.length(); i++)
        {
            System.out.print(String.format("%4c ", seq1.charAt(i)));
        }
        System.out.println();
        for (int i = 1; i <= seq2.length(); i++)
        {
            if (i > 0)
            {
                System.out.print(String.format("%4c ", seq2.charAt(i - 1)));
            } else
            {
                System.out.print("     ");
            }
            for (int j = 1; j <= seq1.length(); j++)
            {
                System.out.print(String.format("%4d ", score[j][i]));
            }
            System.out.println();
        }

        //Prints out the Backtracking Matrix
        System.out.print("\nBacktracking Matrix:\n     ");
        for (int i = 0; i < seq1.length(); i++)
        {
            System.out.print(String.format("%4c ", seq1.charAt(i)));
        }
        System.out.println();
        for (int i = 1; i <= seq2.length(); i++)
        {
            if (i > 0)
            {
                System.out.print(String.format("%4c ", seq2.charAt(i - 1)));
            } else
            {
                System.out.print("     ");
            }
            for (int j = 1; j <= seq1.length(); j++)
            {
                System.out.print(String.format("%4d ", backTrack[j][i]));
            }
            System.out.println();
        }
        System.out.println("\nSolution:");
        return subsequence.reverse().toString();
    }

    /**
     * This is seq1 sample recursive method for solving the LCS problem between
     * two strings. This algorithm will operate in O(2^n) time and outputs a LCS
     * string of the two strings.
     *
     * @param seq1  Sequence string 1
     * @param seq2  Sequence string 2
     * @return      LCS sequence of the two strings
     */
    public static String recursive_LCS(String seq1, String seq2)
    {
        int a_len = seq1.length();
        int b_len = seq2.length();

        // Question one: base case
        if (a_len == 0 || b_len == 0)
        {
            return "";
        } // Question two: Continue recursive call to next to last character with 
        // both strings
        else if (seq1.charAt(a_len - 1) == seq2.charAt(b_len - 1))
        {
            return recursive_LCS(seq1.substring(0, a_len - 1), seq2.substring(0,
                    b_len - 1))
                    + seq1.charAt(a_len - 1);
        } // Question three: Continue recursive call to next to last character 
        // with both strings
        else
        {
            String x = recursive_LCS(seq1, seq2.substring(0, b_len - 1));
            String y = recursive_LCS(seq1.substring(0, a_len - 1), seq2);

            if (x.length() > y.length())
            {
                return x;
            } else
            {
                return y;
            }
        }
    }

    /**
     * The main method runts through several examples of dynamically programmed
     * LCS solutions providing associated scoring and backtracking matrices.
     * There are also included two additional test cases, one called from the
     * associated paper, and another demonstrating the recursive algorithm on
     * that same set of strings.
     *
     * @param args
     */
    public static void main(String args[])
    {

        // Examples provided by Dr. Chlan
        String[] Sequences;
        String S1, S2, S3, S4;
        S1 = "ACCGGTCGACTGCGCGGAAGCCGGCCGAA";
        S2 = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";
        S3 = "ATTGCATTGCATGGGCGCGATGCATTTGGTTAATTCCTCG";
        S4 = "CTTGCTTAAATGTGCA";
        String LCS;

        Sequences = new String[]
        {
            S1, S2, S3, S4
        };

        System.out.println("****Longest Common Substring Problem****");

        for (int i = 0; i <= 2; i++)
        {
            for (int j = i + 1; j <= 3; j++)
            {
                System.out.println(
                        "The LCS Scoring Matrix, Backtracking Matrix, "
                        + "and solution for sequences S" + (i + 1)
                        + "(" + Sequences[i] + ") and S" + (j + 1) + "("
                        + Sequences[j] + "):");
                System.out.println(LCSDynamicProgramming(Sequences[i],
                        Sequences[j]));
            }
        }
        System.out.println(
                "\nIn addition, here is the example strings used in the "
                + "accompanying paper operating in O(nxm) time:");
        System.out.println(LCSDynamicProgramming("command", "combed"));

        System.out.println("\nAnd the same string computed using the recursive "
                + "algorithm operating in O(2^n) time:");
        System.out.println(recursive_LCS("command", "combed"));
    }
}
