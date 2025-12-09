package searchingAlgorithms;

import sortingAlgorithms.SelectionSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SequentialSearch {

    File inputFile;
    int[] array;
    String inputFileName;
    SelectionSort selectionSort;
    int searchValue;

    long timeTaken;
    int comparisonCount;

    public SequentialSearch(String inputFileName, int searchValue) throws FileNotFoundException {

        this.inputFileName = inputFileName;
        inputFile = new File(inputFileName);
        this.searchValue = searchValue;

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

        long start = System.currentTimeMillis();
        int result = sequentialSearch(array, searchValue);
        long end = System.currentTimeMillis();

        timeTaken = end - start;

        // Print results
        if (result == -1) {
            System.out.println("The number you are looking for does not exist");
        } else {
            System.out.println("The number you are looking for is at position " + (result + 1));
        }

        System.out.println("Sequential Search Time: " + timeTaken + " ms");
        System.out.println("Comparisons Made: " + comparisonCount);
    }

    int sequentialSearch(int[] arr, int x) {
        comparisonCount = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisonCount++;

            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }
}
