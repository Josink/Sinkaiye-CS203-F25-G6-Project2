package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MergeSort {
    File inputFile;
    int[] array;
    static String inputFileName;

    private static long comparisonCount = 0;
    private static long moveCount = 0;
    private long executionTime = 0;

    public MergeSort(String inputFileName) throws FileNotFoundException {
        this.inputFileName = inputFileName;
        inputFile = new File(inputFileName);

        Scanner scanner = new Scanner(inputFile);
        ArrayList<Integer> list = new ArrayList <>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        scanner.close();

        array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        long startTime = System.currentTimeMillis();
        mergeSort(array, 0, array.length - 1);
        long endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;

        writeOutput();
    }

    static void mergeSort(int[] array, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(array, l, m);
            mergeSort(array, m + 1, r);

            merge(array, l, m, r);
        }
    }

    static void merge (int[] array, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, l, L, 0, n1);
        System.arraycopy(array, m +1, R, 0, n2);

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            comparisonCount++;

            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else{
                array[k] = R[j];
                j++;
            }

            moveCount++;
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
            moveCount++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
            moveCount++;
        }
    }

    private void writeOutput(){
        String outputFileName = inputFileName + "_MergeSort";
        File outputFile = new File("sortedFiles",outputFileName);

        try (FileWriter writer = new FileWriter(outputFile)) {
            for (int j : array) {
                writer.write(j + "\n");
            }
            writer.write("\nComparisons: " + comparisonCount);
            writer.write("\nMoves: " + moveCount);
            writer.write("\nExecution time (ns): " + executionTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Insertion sort comparisons: " + comparisonCount);
        System.out.println("Swaps comparisons: " + moveCount);
        System.out.println("Time elapsed (MS): " + executionTime + " milliseconds");
    }
}
