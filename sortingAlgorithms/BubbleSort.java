package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BubbleSort {
    File inputFile;
    int[] array;
    String inputFileName;

    private long comparisonCount = 0;
    private long swapCount = 0;
    private long executionTime = 0;

    public BubbleSort(String inputFileName) throws FileNotFoundException {
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

        optimizedSort();
        writeOutput();
    }

    public void unoptimizedSort() {
        int n = array.length;

        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                comparisonCount++;
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    swapCount++;
                }
            }
        }

        long endTime = System.nanoTime();
        executionTime += (endTime - startTime);
    }

    // breaks if no elements are swapped in the inner loop
    public void optimizedSort() {
        int n = array.length;

        long startTime = System.nanoTime();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1; j++) {
                comparisonCount++;
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    swapCount++;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        long endTime = System.nanoTime();
        executionTime += (endTime - startTime);
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void writeOutput(){
        String outputFileName = inputFileName + "_OptimizedBubbleSort";
        File outputFile = new File("sortedFiles",outputFileName);

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
