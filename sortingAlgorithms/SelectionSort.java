package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectionSort {

    File inputFile;         // File containing numbers to sort
    int[] array;            // Array to hold numbers
    String inputFileName;   // Name of the input file

    private long comparisonCount = 0;  // Number of comparisons made
    private long swapCount = 0;        // Number of swaps made
    private long executionTime = 0;    // Time taken to sort in milliseconds

    public SelectionSort(String inputFileName) throws FileNotFoundException {

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
    }

    // Selection sort algorithm
    public void sort() {
        int n = array.length;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n - 1; i++) {
            int smallest = i;

            // Find the smallest element in remaining unsorted array
            for (int j = i + 1; j < n; j++) {
                comparisonCount++;
                if (array[j] < array[smallest]) {
                    smallest = j;
                }
            }

            // Swap the found smallest element with the first element
            if (smallest != i) {
                swap(array, i, smallest);
                swapCount++;
            }
        }

        long endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
    }

    // Getter for array (optional)
    public int[] getArray() {
        return array;
    }

    // Swap two elements in the array
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Write sorted array and statistics to output file
    public void writeOutput() {
        String outputFileName = inputFileName + "_SelectionSort";
        File outputFile = new File("sortedFiles", outputFileName);

        try (FileWriter writer = new FileWriter(outputFile)) {
            for (int j : array) {
                writer.write(j + "\n");
            }
            writer.write("Comparisons: " + comparisonCount + "\n" +
                    "Swaps: " + swapCount + "\n" +
                    "Execution time (ms): " + executionTime + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Print statistics to console
        System.out.println("Selection sort comparisons: " + comparisonCount);
        System.out.println("Swaps: " + swapCount);
        System.out.println("Time elapsed (MS): " + executionTime + " milliseconds");
    }
}
