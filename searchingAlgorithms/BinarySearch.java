package searchingAlgorithms;

import sortingAlgorithms.QuickSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearch {

    File inputFile;
    int[] array;
    String inputFileName;
    QuickSort quickSort;
    int binarySearchChoice;

    long timeTaken;
    long comparisonCount = 0;

    public BinarySearch(String inputFileName, int binarySearchChoice) throws FileNotFoundException {

        this.inputFileName = inputFileName;
        inputFile = new File(inputFileName);
        this.binarySearchChoice = binarySearchChoice;

        // Sort the data first
        quickSort = new QuickSort(inputFileName);
        array = quickSort.getArray();

        // Load numbers from file
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

        // Measure search time
        long startTime = System.currentTimeMillis();
        int result = binarySearch(array, binarySearchChoice);
        long endTime = System.currentTimeMillis();

        timeTaken = endTime - startTime;

        // Print results
        if (result == -1) {
            System.out.println("The number you are looking for does not exist");
        } else {
            System.out.println("The number you are looking for is at position " + (result + 1));
        }

        System.out.println("Binary Search Time: " + timeTaken + " ms");
        System.out.println("Comparisons: " + comparisonCount);
    }

    int binarySearch(int[] arr, int x) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            comparisonCount++;
            if (arr[mid] == x)
                return mid;

            comparisonCount++;
            if (arr[mid] < x)
                low = mid + 1;
            else {
                comparisonCount++;
                high = mid - 1;
            }
        }

        return -1;
    }
}
