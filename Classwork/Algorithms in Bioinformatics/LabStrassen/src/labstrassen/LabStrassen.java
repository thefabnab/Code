package labstrassen;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

/**
 * This is a program which implements the Strassen algorithm for matrix
 * multiplication. Specifically, this implementation is designed for matrices of
 * power of 2 dimensions.
 *
 * @author Nabil Azamy
 * @version 1.0 September 28th 2015
 */
public class LabStrassen {

    /**
     * Main method producing an instantiation of the Strassean matrix
     * multiplication algorithm. This algorithm
     *
     * @throws FileNotFoundException if filename not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        List<int[][]> matrices = new ArrayList();
        int arrsize;
        int[][] product;
        File f = new File("matrix.txt");  //name of file within project directory

        LabStrassen b = new LabStrassen();

        arrsize = b.matrixSize(f); //reads file to discover matrix size
        matrices = b.importMatrix(f, arrsize); //imports size and produces list of two matrices

        //Print matrix A and B
        System.out.println("Matrix A:\n");
        b.printArray(matrices.get(0));
        System.out.println("\nMatrix B:\n");
        b.printArray(matrices.get(1));

        product = b.divideNconquer(matrices.get(0), matrices.get(1)); //multiply matrices
        System.out.println("\nProduct:\n");
        b.printArray(product); //prints result
    }

    /**
     * Establishes the size of the matrix being calculated via scanning the
     * first line. Since the two matrices are of same dimension this value would
     * be indicitive of the matrix size.
     *
     * @param f file containing two matrices
     * @return returns length of
     * @throws FileNotFoundException
     */
    public int matrixSize(File f) throws FileNotFoundException {
        Scanner scan;
        String line;
        String[] splitLine;

        //scans first line of text
        scan = new Scanner(f);
        line = scan.nextLine();
        splitLine = line.split(" ");
        return splitLine.length;

    }

    /**
     * This method instantiates the two matrices being multiplied and scans
     * their input into two 2 dimensional arrays. These arrays are then placed
     * in a list for ease of access. This method provides for ease of matrix
     * access in future use.
     *
     * @param f Matrix input file
     * @param size Length of the side of square matrix
     * @return List containing the two arrays of matrix A and B
     * @throws FileNotFoundException if no file is found
     */
    public List<int[][]> importMatrix(File f, int size) throws FileNotFoundException {
        List<int[][]> matrix_list;
        Scanner scan;
        String line;
        String[] splitLine;
        matrix_list = new ArrayList<>();
        int[][] arrayA = new int[size][size];
        int[][] arrayB = new int[size][size];

        scan = new Scanner(f);
        for (int i = 0; i < size * 2 + 1; i++) {
            line = scan.nextLine();
            splitLine = line.split(" ");
            for (int j = 0; j < size; j++) {
                if (i < size) {
                    arrayA[i][j] = Integer.parseInt(splitLine[j]);
                }
                if (i > size) {
                    arrayB[i % (size + 1)][j] = Integer.parseInt(splitLine[j]);

                }
            }
        }
        matrix_list.add(arrayA); //store matrix A
        matrix_list.add(arrayB); //store matrix B

        return matrix_list;
    }

    /**
     * Function provides a human readable representation of a square matrix
     *
     * @param matrix being printed out to standard output
     */
    public void printArray(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * Functions to partition out a matrix of size n into four matrices of size
     * n/2 components. This is accomplished by strategic placement of the pos1
     * and pos2 variables within the matrix, setting its working parameters.
     *
     * @param original unpartitioned matrix
     * @param reduced partitioned matrix of size n/2
     * @param pos1 position within the matrix to determine left bounds of
     * partition
     * @param pos2 position within the matrix to determine right bound of
     * partition
     */
    public void reduce(int[][] original, int[][] reduced, int pos1, int pos2) {

        for (int i1 = 0, i2 = pos1; i1 < reduced.length; i1++, i2++) {
            for (int j1 = 0, j2 = pos2; j1 < reduced.length; j1++, j2++) {
                reduced[i1][j1] = original[i2][j2];
            }
        }
    }

    /**
     * Combine works to summate segments of partitions between matrices to
     * assist in providing resursively smaller partitions.
     *
     * @param arrayA partition to be conjoined
     * @param arrayB partition to be conjoined
     * @return a matrix containing the sum of both matrix partitions
     */
    public int[][] combine(int[][] arrayA, int[][] arrayB) {
        int size = arrayA.length;
        int[][] combined = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                combined[i][j] = arrayA[i][j] + arrayB[i][j];
            }
        }
        return combined;
    }

    /**
     * Functions to subtract out segments of two matrix partitions not needed to
     * perform parts of matrix reduction. Returns a matrix partition maintaining
     * only the relevent components for the needed calculation.
     *
     * @param arrayA Matrix partition containing unneeded segments
     * @param arrayB Matrix partition containing unneeded segments
     * @return Matrix difference between the two partitions
     */
    public int[][] diff(int[][] arrayA, int[][] arrayB) {
        int size = arrayA.length;
        int[][] combined = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                combined[i][j] = arrayA[i][j] - arrayB[i][j];
            }
        }
        return combined;
    }

    /**
     * This method functions as the reverse method of reduce. Transfer takes
     * partitioned components and recombines them into a final product matrix
     * which is comprised of the recursively partitioned and multiplied out
     * segments of the original two matrices.
     *
     * @param partition A matrix partition
     * @param transfered The multiplied matrix product
     * @param pos1 Segment within the final product to recombine partition
     * @param pos2 Segment within the final product to recombine partition
     */
    public void transfer(int[][] partition, int[][] transfered, int pos1,
            int pos2) {
        for (int p1 = 0, p2 = pos1; p1 < partition.length; p1++, p2++) {
            for (int j1 = 0, j2 = pos2; j1 < partition.length; j1++, j2++) {
                transfered[p2][j2] = partition[p1][j1];
            }
        }
    }

    /**
     * This method provides a recursive and base case for implementing a divide
     * and conquer strategy for Strassen's matrix multiplication algorithm. The
     * algorithm functions by progressively dividing, combining, summating, and
     * recombining components of two matrices to effectively produce a
     * multiplicative product.
     *
     *
     * @param arrayA Matrix A to be multiplied
     * @param arrayB Matrix B to be multiplied
     * @return Product of the two matrices
     */
    public int[][] divideNconquer(int[][] arrayA, int[][] arrayB) {
        int size = arrayA.length;
        int[][] product = new int[size][size]; //final product of two matrices

        if (size == 1) {
            product[0][0] = arrayA[0][0] * arrayB[0][0]; //base case to resolve recursion
        } else {
            //initilize matrix partitions
            int[][] arrayA1, arrayA2, arrayA3, arrayA4, arrayB1, arrayB2,
                    arrayB3, arrayB4;
            arrayA1 = new int[size / 2][size / 2];
            arrayA2 = new int[size / 2][size / 2];
            arrayA3 = new int[size / 2][size / 2];
            arrayA4 = new int[size / 2][size / 2];
            arrayB1 = new int[size / 2][size / 2];
            arrayB2 = new int[size / 2][size / 2];
            arrayB3 = new int[size / 2][size / 2];
            arrayB4 = new int[size / 2][size / 2];

            //reduce matrices A and B to respective partitioned components
            reduce(arrayA, arrayA1, 0, 0);
            reduce(arrayA, arrayA2, 0, size / 2);
            reduce(arrayA, arrayA3, size / 2, 0);
            reduce(arrayA, arrayA4, size / 2, size / 2);
            reduce(arrayB, arrayB1, 0, 0);
            reduce(arrayB, arrayB2, 0, size / 2);
            reduce(arrayB, arrayB3, size / 2, 0);
            reduce(arrayB, arrayB4, size / 2, size / 2);

            //combine respective partitioned components from matrix A and B
            //and recursively call for them to be further reduced
            int[][] part1, part2, part3, part4, part5, part6, part7;

            part1 = divideNconquer(combine(arrayA1, arrayA4), combine(
                    arrayB1, arrayB4));

            part2 = divideNconquer(combine(arrayA3, arrayA4), arrayB1);
            part3 = divideNconquer(arrayA1, diff(arrayB2, arrayB4));
            part4 = divideNconquer(arrayA4, diff(arrayB3, arrayB1));
            part5 = divideNconquer(combine(arrayA1, arrayA2), arrayB4);
            part6 = divideNconquer(diff(arrayA3, arrayA1), combine(arrayB1,
                    arrayB2));
            part7 = divideNconquer(diff(arrayA2, arrayA4), combine(arrayB3,
                    arrayB4));

            //after recursive calls are complete recombine partitions
            int[][] comb1, comb2, comb3, comb4;
            comb1 = combine(diff(combine(part1, part4), part5), part7);
            comb2 = combine(part3, part5);
            comb3 = combine(part2, part4);
            comb4 = combine(diff(combine(part1, part3), part2), part6);

            //stitch respective matrix partitions back together into final matrix product
            transfer(comb1, product, 0, 0);
            transfer(comb2, product, 0, size / 2);
            transfer(comb3, product, size / 2, 0);
            transfer(comb4, product, size / 2, size / 2);

        }
        return product; //final matrix product of A and B
    }

}
