package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BubbleSort {

    File inputFile;         // File containing numbers to sort
    int[] array;            // Array to hold numbers
    String inputFileName;   // Name of the input file

    private long comparisonCount = 0;  // Number of comparisons made
    private long swapCount = 0;        // Number of swaps made
    private long executionTime = 0;    // Time taken to sort in milliseconds

    public BubbleSort(String inputFileName) throws FileNotFoundException {

        this.inputFileName = inputFileName;
        inputFile = new File(inputFileName);

        // Read numbers from file into a list
        Scanner scanner = new Scanner(inputFile);
        ArrayList<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        scanner.close();

        // Convert ArrayList to array
        array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        // Perform optimized bubble sort
        optimizedSort();

        // Write sorted array and statistics to file
        writeOutput();
    }

    // Standard (unoptimized) bubble sort
    public void unoptimizedSort() {
        int n = array.length;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                comparisonCount++;
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    swapCount++;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        executionTime += (endTime - startTime);
    }

    // Optimized bubble sort stops early if the array is already sorted
    public void optimizedSort() {
        int n = array.length;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                comparisonCount++;
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    swapCount++;
                    swapped = true;
                }
            }

            // If no swaps occurred, the array is sorted
            if (!swapped) {
                break;
            }
        }

        long endTime = System.currentTimeMillis();
        executionTime += (endTime - startTime);
    }

    // Swap two elements in the array
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Write sorted array and statistics to output file
    private void writeOutput() {
        String outputFileName = inputFileName + "_OptimizedBubbleSort";
        File outputFile = new File("sortedFiles", outputFileName);

        try (FileWriter writer = new FileWriter(outputFile)) {
            for (int j : array) {
                writer.write(j + "\n");
            }
            writer.write("comparisons: " + comparisonCount + "\n" +
                    "swaps: " + swapCount + "\n" +
                    "execution time: " + executionTime + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Print statistics to console
        System.out.println("Bubble sort comparisons: " + comparisonCount);
        System.out.println("Swaps comparisons: " + swapCount);
        System.out.println("Time elapsed (MS): " + executionTime + " milliseconds");
    }
}
