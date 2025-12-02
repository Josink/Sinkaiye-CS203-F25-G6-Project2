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

    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    Menu(){
        //asking for file name and creating file
        System.out.print("Enter a name for the file with your inputs: ");
        String inputFileName = sc.next();
        File inputFile = new File(inputFileName);

        //asking for array size/ generating numbers and adding it to the file
        System.out.print("Enter a integer from 0 - 10000 for how many values you want in your array: ");
        int arraySize = sc.nextInt();
        try {
            FileWriter inputFileWriter = new FileWriter(inputFile);
            for(int i = 0; i < arraySize; i++){
                int randomInt = random.nextInt(arraySize);
                inputFileWriter.write(randomInt + "\n");
            }
            inputFileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //asking sort or print
        System.out.println("Do you want to (1) Sort or (2) Search? ");
        int choice = sc.nextInt();

        if(choice == 1){
            System.out.println("Choose your sorting algorithm: \n" +
                    "(1) Selection Sort \n" +
                    "(2) Bubble Sort \n" +
                    "(3) Insertion Sort \n" +
                    "(4) Merge Sort \n" +
                    "(5) Quick Sort \n" +
                    "(6) Heap Sort)");
            int sortChoice = sc.nextInt();
            if(sortChoice == 1){
                try {
                    SelectionSort selectionSort = new SelectionSort(inputFileName);
                    selectionSort.sort();
                    selectionSort.writeOutput();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (sortChoice == 2){
                try{
                    BubbleSort bubbleSort = new BubbleSort(inputFileName);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (sortChoice == 3){
                try {
                    InsertionSort insertionSort = new InsertionSort(inputFileName);
                    insertionSort.sort();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if (sortChoice == 4){
                try {
                    MergeSort mergeSort = new MergeSort(inputFileName);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (sortChoice == 5){
                try {
                    QuickSort quickSort = new QuickSort(inputFileName);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (sortChoice == 6){
                try {
                    HeapSort heapSort = new HeapSort(inputFileName);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else{
                System.out.println("Invalid choice");
            }
        } else if(choice == 2){
            System.out.println("Choose your searching algorithm: \n" +
                    "(1) Sequential Search \n" +
                    "(2) Binary Search");
            int searchChoice = sc.nextInt();
            if (searchChoice == 1){
                System.out.println("What number would you like to search? ");
                int sequentialSearchChoice = sc.nextInt();
                try {
                    SequentialSearch sequentialSearch = new SequentialSearch(inputFileName, sequentialSearchChoice);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (searchChoice == 2){
                System.out.println("What number would you like to search? ");
                int binarySearchChoice = sc.nextInt();
                try {
                    BinarySearch binarySearch = new BinarySearch(inputFileName, binarySearchChoice);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } else{
            System.out.println("Invalid choice");
        }

    }
}
