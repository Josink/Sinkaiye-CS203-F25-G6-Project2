package searchingAlgorithms;

import sortingAlgorithms.SelectionSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearch {
    File inputFile;
    int[] array;
    String inputFileName;
    SelectionSort selectionSort;
    int binarySearchChoice;

    long timeTaken;
    int swapCount = 0;

    public BinarySearch(String inputFileName, int binarySearchChoice) throws FileNotFoundException {
        this.inputFileName = inputFileName;
        inputFile = new File(inputFileName);
        this.binarySearchChoice = binarySearchChoice;

        selectionSort = new SelectionSort(inputFileName);

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

        selectionSort.sort();
        array = selectionSort.getArray();

        long start = System.nanoTime();

        int result = binarySearch(array, binarySearchChoice);

        long end = System.nanoTime();
        timeTaken = end - start;

        if (result == -1) {
            System.out.println("The number you are looking for does not exist");
        } else {
            System.out.println("The number you are looking for is at position " + (result + 1));
        }

        System.out.println("Binary Search Time: " + timeTaken + " ns");
        System.out.println("Swap Count: " + swapCount);  // Always zero
    }

    static int binarySearch(int[] arr, int x) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x)
                return mid;

            if (arr[mid] < x)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }
}

