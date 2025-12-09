import searchingAlgorithms.BinarySearch;
import searchingAlgorithms.SequentialSearch;
import sortingAlgorithms.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);  // Scanner for user input
    Random random = new Random();         // Random generator for creating file numbers
    String inputFileName;                  // Name of the input file
    File inputFile;                        // File object

    Menu() {

        // Ask user if they want to create a new file or use an existing one
        System.out.println("Welcome to the Menu. Would you like to create your own file (1) or use an existing file (2)?");
        int fileSelection = sc.nextInt();

        if (fileSelection == 1) {
            // Ask for file name
            System.out.print("Enter a name for the file with your inputs: ");
            inputFileName = sc.next();
            inputFile = new File(inputFileName);

            // Ask for array size and generate random numbers in the file
            System.out.print("Enter an integer from 0 - 5,000,000 for how many values you want in your array: ");
            int arraySize = sc.nextInt();
            try (FileWriter inputFileWriter = new FileWriter(inputFile)) {
                for (int i = 0; i < arraySize; i++) {
                    int randomInt = random.nextInt(arraySize);
                    inputFileWriter.write(randomInt + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (fileSelection == 2) {
            // Ask for existing file name
            System.out.print("Enter filename: ");
            inputFileName = sc.next();
        } else {
            System.out.println("Invalid input");
            return;
        }

        // Ask user if they want to sort or search
        System.out.println("Do you want to (1) Sort or (2) Search? ");
        int choice = sc.nextInt();

        if (choice == 1) {
            // Sorting menu
            System.out.println("Choose your sorting algorithm: \n" +
                    "(1) Selection Sort \n" +
                    "(2) Bubble Sort \n" +
                    "(3) Insertion Sort \n" +
                    "(4) Merge Sort \n" +
                    "(5) Quick Sort \n" +
                    "(6) Heap Sort");
            int sortChoice = sc.nextInt();

            try {
                switch (sortChoice) {
                    case 1 -> {
                        SelectionSort selectionSort = new SelectionSort(inputFileName);
                        selectionSort.sort();
                        selectionSort.writeOutput();
                    }
                    case 2 -> new BubbleSort(inputFileName);        // BubbleSort constructor handles sorting and output
                    case 3 -> {
                        InsertionSort insertionSort = new InsertionSort(inputFileName);
                        insertionSort.sort();
                    }
                    case 4 -> new MergeSort(inputFileName);         // MergeSort constructor handles sorting and output
                    case 5 -> new QuickSort(inputFileName);         // QuickSort constructor handles sorting and output
                    case 6 -> new HeapSort(inputFileName);          // HeapSort constructor handles sorting and output
                    default -> System.out.println("Invalid choice");
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else if (choice == 2) {
            // Searching menu
            System.out.println("Choose your searching algorithm: \n" +
                    "(1) Sequential Search \n" +
                    "(2) Binary Search");
            int searchChoice = sc.nextInt();

            if (searchChoice == 1) {
                System.out.println("What number would you like to search? ");
                int sequentialSearchChoice = sc.nextInt();
                try {
                    new SequentialSearch(inputFileName, sequentialSearchChoice);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (searchChoice == 2) {
                System.out.println("What number would you like to search? ");
                int binarySearchChoice = sc.nextInt();
                try {
                    new BinarySearch(inputFileName, binarySearchChoice);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Invalid choice");
            }

        } else {
            System.out.println("Invalid choice");
        }
    }
}
