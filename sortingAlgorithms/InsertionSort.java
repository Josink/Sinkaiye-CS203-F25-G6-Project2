package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InsertionSort {
    File inputFile;
    int[] array;
    String inputFileName;

    public static long comparisons = 0;
    public static long swaps = 0;
    public static long time = 0;

    public InsertionSort(String inputFileName) throws FileNotFoundException {
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

        comparisons = 0;
        swaps = 0;
        time = 0;

        long time1 = System.currentTimeMillis();

        sort();

        long time2 = System.currentTimeMillis();

        time = time2 - time1;
        writeOutput();

        System.out.println("Insertion sort comparisons: " + comparisons);
        System.out.println("Swaps comparisons: " + swaps);
        System.out.println("Time elapsed (MS): " + time + " milliseconds");
    }

    public void sort(){
        int n = array.length;

        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            comparisons++;

            while (j >= 0 && array[j] > key) {
                comparisons ++;
                array[j + 1] = array[j];
                swaps++;
                j = j - 1;
            }
            array[j + 1 ] = key;
            swaps++;
        }
    }

    private void writeOutput(){
        String outputFileName = inputFileName + "_InsertionSort";
        File outputFile = new File("sortedFiles",outputFileName);

        try (FileWriter writer = new FileWriter(outputFile)) {
            for (int j : array) {
                writer.write(j + "\n");
            }
            writer.write("Insertion sort comparisons: " + comparisons + "\n" +
                    "Swaps comparisons: " + swaps + "\n" +
                    "Time elapsed (MS): " + time + " milliseconds\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Insertion sort comparisons: " + comparisons);
        System.out.println("Swaps comparisons: " + swaps);
        System.out.println("Time elapsed (MS): " + time + " milliseconds");
    }

}
