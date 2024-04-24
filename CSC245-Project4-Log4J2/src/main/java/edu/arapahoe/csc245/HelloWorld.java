package edu.arapahoe.csc245;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program demonstrates logging using Log4j2.
 * It prompts the user to enter an index and then displays the corresponding element from an array.
 * If the entered index is out of bounds, it logs an error message.
 */
public class HelloWorld {
    // Initialize logger
    private static final Logger logger = LogManager.getLogger(HelloWorld.class);

    // Pattern to validate integers
    private static final Pattern INTEGER_PATTERN = Pattern.compile("^-?\\d+$");

    public static void main(String[] args) {
        // Create an array to store random integers
        int[] data = initializeArray();

        // Initialize scanner to read user input
        try (Scanner input = new Scanner(System.in)) {
            logger.info("Program started."); // Log program start

            // Prompt the user to enter index
            int elementIndex = readUserIndex(input);

            // Check if the index is within the array bounds
            if (elementIndex >= 0 && elementIndex < data.length) {
                logger.debug("Attempting to access element at index: {}", elementIndex); // Log debug information
                displayElement(data, elementIndex);
                logger.info("Program completed successfully."); // Log program completion
            } else {
                logger.error("Index out of bounds: {}", elementIndex);
                System.out.println("Number is out of range.");
            }
        } catch (Exception ex) {
            System.out.println("An unexpected error occurred. Please try again.");
            logger.error("An unexpected error occurred: {}", ex.getMessage(), ex); // Log exceptions
        }
    }

    /**
     * Reads user input for index.
     * @param input Scanner object for user input.
     * @return The index entered by the user.
     */
    private static int readUserIndex(Scanner input) {
        while (true) {
            System.out.print("Enter an index: ");
            String userInput = input.nextLine();

            if (isValidInteger(userInput)) {
                return Integer.parseInt(userInput);
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                logger.error("Invalid input: {}", userInput);
            }
        }
    }

    /**
     * Validates if the input is an integer.
     * @param input User input as a string.
     * @return True if input is a valid integer, otherwise false.
     */
    private static boolean isValidInteger(String input) {
        Matcher matcher = INTEGER_PATTERN.matcher(input);
        return matcher.matches();
    }

    /**
     * Initializes an array with random integers.
     * @return Array of random integers.
     */
    private static final int[] initializeArray() {
        int[] data = new int[100];
        for (int i = 0; i < 100; i++) {
            data[i] = (int) (Math.random() * 10000);
        }
        return data;
    }

    /**
     * Displays the element at the specified index.
     * @param data Array of integers.
     * @param index Index of the element to display.
     */
    private static final void displayElement(int[] data, int index) {
        System.out.println("The element is " + data[index]);
    }
}
