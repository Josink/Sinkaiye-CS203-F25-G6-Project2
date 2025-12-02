package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectionSort {

    File inputFile;
    int[] array;
    String inputFileName;

    private long comparisonCount = 0;
    private long swapCount = 0;
    private long executionTime = 0;

    public SelectionSort(String inputFileName) throws FileNotFoundException {
        this.inputFileName = inputFileName;
        inputFile = new File(inputFileName);

        Scanner scanner = new Scanner(inputFile);
        ArrayList <Integer> list = new ArrayList <>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        scanner.close();

        array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

    }

    public void sort() {
        int n = array.length;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n - 1; i++) {
            int smallest = i;

            for (int j = i + 1; j < n; j++) {
                comparisonCount++;

                if (array[j] < array[smallest]) {
                    smallest = j;
                }
            }

            if (smallest != i) {
                swap(array, i, smallest);
                swapCount++;
            }
        }

        long endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
    }

    public int[] getArray(){
        return array;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void writeOutput(){
        String outputFileName = inputFileName + "_SelectionSort";
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

        System.out.println("Insertion sort comparisons: " + comparisonCount);
        System.out.println("Swaps comparisons: " + swapCount);
        System.out.println("Time elapsed (MS): " + executionTime + " milliseconds");

    }
}
