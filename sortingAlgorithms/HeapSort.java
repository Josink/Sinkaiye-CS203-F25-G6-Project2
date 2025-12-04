package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HeapSort {

    File inputFile;
    int[] array;
    String inputFileName;

    private long comparisonCount = 0;
    private long swapCount = 0;
    private long executionTime = 0;

    public HeapSort(String inputFileName) throws FileNotFoundException {
        this.inputFileName = inputFileName;
        inputFile = new File(inputFileName);

        Scanner scanner = new Scanner(inputFile);
        ArrayList<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        scanner.close();

        array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        long start = System.currentTimeMillis();
        heapSort(array);
        long end = System.currentTimeMillis();

        executionTime = end - start;

        writeOutput();
    }

    private void heapify(int[] arr, int heapSize, int rootIndex) {
        int largest = rootIndex;

        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        if (left < heapSize) {
            comparisonCount++;
            if (arr[left] > arr[largest]) {
                largest = left;
            }
        }

        if (right < heapSize) {
            comparisonCount++;
            if (arr[right] > arr[largest]) {
                largest = right;
            }
        }

        if (largest != rootIndex) {
            swap(arr, rootIndex, largest);
            heapify(arr, heapSize, largest);
        }
    }

    private void swap(int[] arr, int i, int j) {
        swapCount++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private void writeOutput() {
        String outputFileName = inputFileName + "_HeapSort";
        File outputFile = new File("sortedFiles", outputFileName);

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
