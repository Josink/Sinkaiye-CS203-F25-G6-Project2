package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuickSort {

    File inputFile;         // File containing numbers to sort
    int[] array;            // Array to hold numbers
    static String inputFileName;

    private static long comparisonCount = 0;  // Number of comparisons made
    private static long swapCount = 0;        // Number of swaps made
    private long executionTime = 0;           // Time taken to sort in milliseconds

    public QuickSort(String inputFileName) throws FileNotFoundException {

        this.inputFileName = inputFileName;
        inputFile = new File(inputFileName);

        // Read numbers from file into a list
        Scanner scanner = new Scanner(inputFile);
        ArrayList<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        scanner.close();

        // Convert list to array
        array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        // Measure execution time
        long startTime = System.currentTimeMillis();
        qSort(array, 0, array.length - 1);
        long endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;

        // Write sorted array and statistics to file
        writeOutput();
    }

    // QuickSort implementation
    static void qSort(int[] arr, int low, int high) {
        if (low < high) {
            comparisonCount++;

            int pi = partition(arr, low, high);  // Partition array around pivot

            qSort(arr, low, pi - 1);  // Recursively sort left subarray
            qSort(arr, pi + 1, high); // Recursively sort right subarray
        }
    }

    // Partition the array using last element as pivot
    static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            comparisonCount++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);  // Place pivot at correct position
        return i + 1;
    }

    // Swap two elements in the array
    static void swap(int[] arr, int i, int j) {
        swapCount++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Getter for array (optional)
    public int[] getArray() {
        return array;
    }

    // Write sorted array and statistics to output file
    private void writeOutput() {
        String outputFileName = inputFileName + "_QuickSort";
        File outputFile = new File("sortedFiles", outputFileName);

        try (FileWriter writer = new FileWriter(outputFile)) {
            for (int j : array) {
                writer.write(j + "\n");
            }

            writer.write("\nComparisons: " + comparisonCount);
            writer.write("\nSwaps: " + swapCount);
            writer.write("\nExecution time (ms): " + executionTime);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Print statistics to console
        System.out.println("Quick sort comparisons: " + comparisonCount);
        System.out.println("Swaps: " + swapCount);
        System.out.println("Time elapsed (MS): " + executionTime + " milliseconds");
    }
}
