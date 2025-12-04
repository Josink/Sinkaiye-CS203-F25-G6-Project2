package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    File inputFile;
    int[] array;
    static String inputFileName;

    private static long comparisonCount = 0;
    private static long swapCount = 0;
    private long executionTime = 0;

    public QuickSort(String inputFileName) throws FileNotFoundException {
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
        qSort(array, 0, array.length - 1);
        long endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;

        writeOutput();
    }

    // partition function
    static int partition(int[] arr, int low, int high) {

        // choose the pivot
        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {

            comparisonCount++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }


        swap(arr, i + 1, high);
        return i + 1;
    }

    // swap function
    static void swap(int[] arr, int i, int j) {
        swapCount++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // the QuickSort function implementation
    static void qSort(int[] arr, int low, int high) {
        if (low < high) {
            comparisonCount++;

            int pi = partition(arr, low, high);

            qSort(arr, low, pi - 1);
            qSort(arr, pi + 1, high);
        }
    }

    private void writeOutput(){
        String outputFileName = inputFileName + "_QuickSort";
        File outputFile = new File("sortedFiles",outputFileName);

        try (FileWriter writer = new FileWriter(outputFile)) {
            for (int j : array) {
                writer.write(j + "\n");
            }

            writer.write("\nComparisons: " + comparisonCount);
            writer.write("\nSwaps: " + swapCount);
            writer.write("\nExecution time (ns): " + executionTime);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Insertion sort comparisons: " + comparisonCount);
        System.out.println("Swaps comparisons: " + swapCount);
        System.out.println("Time elapsed (MS): " + executionTime + " milliseconds");
    }
}
