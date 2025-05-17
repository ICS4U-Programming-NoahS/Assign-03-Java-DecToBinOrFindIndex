import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

/**
* The DecToBinOrFindIndex.java is 2 programs in one.
* The user can choose the one they want to try.
* The first program takes an integer and returns its value in binary.
* The second program returns the index of
* the first occurrence of aChar in someString
* Both programs read from an input file
* and write the results to an output file.
* The program will keep looping until the user presses 'q' to quit.

*
* @author Noah Smith
* @version 1.0
* @since 2025-05-09
*/

final class DecToBinOrFindIndex {

    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
    */
    private DecToBinOrFindIndex() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Function for the decimal to binary program.
     *
     * @param intNum The integer.
     * @return the integer's value in binary.
     */
    public static String decToBin(final int intNum) {

        // Base case
        // If the number is less than 2
        if (intNum < 2) {
            // Convert the integer to a string
            // Source: https://stackoverflow.com/questions...
            // .../5071040/java-convert-integer-to-string
            return String.valueOf(intNum);
        } else {
            // Recursive case
            // Call the function recursively with the number divided by 2
            // and add the remainder to the end of the string
            // source: https://docs.vultr.com/python/examples...
            //.../convert-decimal-to-binary-using-recursion
            return decToBin(intNum / 2) + String.valueOf(intNum % 2);
        }
    }

    /**
     * Function for finding the index of a character.
     *
     * @param aChar The character.
     * @param string The string.
     * @return The sum of the two numbers.
     */
    public static int findIndex(final String string, final char aChar) {
        // Base cases
        // If the string is empty
        if (string.isEmpty()) {
            // Return -1
            return -1;

        // If the first character of the string is the character
        } else if (string.charAt(0) == aChar) {
            // Return the index
            return 0;
        } else {
            // Call the function recursively
            // Exclude the first character of the string
            int index = findIndex(string.substring(1), aChar);

            // If the character is not found
            if (index == -1) {
                return -1;
            } else {
                // Return the index + 1,
                // Since we removed the first character with substring(1),
                // we need to add 1 to the index to get the correct index
                return index + 1;
            }
        }
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */
    public static void main(final String[] args) {
        // Create a scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Initialize the user's choice
        String choice = "";

        // Do while loop to keep looping until the user presses 'q' to quit
        do {
            // List the options for the user
            System.out.println("Press 1 to convert decimal to binary");
            System.out.println("Press 2 to find the index of a character");
            System.out.println("Press q to quit");

            // Get the user's choice
            choice = scanner.nextLine();

            // If the user enters 'q'
            if (choice.equals("q")) {
                // Display a goodbye message
                System.out.println("Goodbye!");

            // If the user enters 1
            } else if (choice.equals("1")) {
                // Try to read input from the file
                try {
                    // Open the input file for reading
                    File inputFile = new File("decToBinInput.txt");

                    // Create a scanner object to read the file
                    Scanner fileScanner = new Scanner(inputFile);

                    // Create a file writer object to write to the output file
                    FileWriter fileWriter = new FileWriter(
                            "decToBinOutput.txt");

                    // Keep reading the file until there are no more lines
                    while (fileScanner.hasNextLine()) {
                        // Read the current line of the file
                        String line = fileScanner.nextLine();

                        // catch invalid input
                        try {
                            // convert the string to an integer
                            int intNum = Integer.parseInt(line);

                            // Check if the number is negative
                            if (intNum < 0) {
                                // Write an error message to the output file
                                fileWriter.write(
                                        "'" + line + "' is negative.\n");
                            } else {
                                // Get the number in binary
                                // by calling the decToBin function
                                String binaryString = decToBin(intNum);

                                // Write what the number is
                                // in binary to the output file
                                fileWriter.write(
                                        intNum + " is "
                                        + binaryString + " in binary.\n");
                            }
                        // if the string is not a valid integer
                        } catch (Exception exception) {
                            // Display an error message
                            fileWriter.write(
                                    "'" + line + "' is not a valid integer.\n"
                                );
                        }
                    }
                    // Close the file writer and scanner
                    fileWriter.close();
                    fileScanner.close();

                    // Display a success message
                    System.out.println(
                            "Successfully written to 'decToBinOutput.txt'");

                // If there is a file error
                } catch (Exception exception) {
                    // Display an error message
                    System.out.println(
                            "Unable to write to 'decToBinOutput.txt'.");
                }
            // If the user enters 2
            } else if (choice.equals("2")) {
                // Try to read input from the file
                try {
                    // Open the input file for reading
                    File inputFile = new File("findIndexInput.txt");

                    // Create a scanner object to read the file
                    Scanner fileScanner = new Scanner(inputFile);

                    // Create a file writer object to write to the output file
                    FileWriter fileWriter = new FileWriter(
                            "findIndexOutput.txt");

                    // Get the char from the user
                    System.out.print(
                            "Enter a character to find in the string: ");
                    char aChar = scanner.nextLine().charAt(0);

                    // Keep reading the file until there are no more lines
                    while (fileScanner.hasNextLine()) {
                        // Read the current line of the file
                        String line = fileScanner.nextLine();

                        // Call the findIndex function
                        //to get the index of the character
                        int index = findIndex(line, aChar);

                        // If the index is -1, the character was not found
                        if (index == -1) {
                            // Write that character is not found to output file
                            fileWriter.write(
                                "'" + aChar + "' is not found in '"
                                + line + "'.\n");
                        } else {
                            // Write the index of the character to output file
                            fileWriter.write(
                                "'" + aChar + "' is first found at index "
                                + index + " of '" + line + "'\n");
                        }
                    }

                    // Close the file writer and scanner
                    fileWriter.close();
                    fileScanner.close();

                    // Display a success message
                    System.out.println(
                            "Successfully written to 'findIndexOutput.txt'");

                // If there is a file error
                } catch (Exception exception) {
                    System.out.println(
                            "Unable to write to 'findIndexOutput.txt'.");
                }
            // If the user enters an invalid choice
            } else {
                // Display an error message
                System.out.println("Invalid choice. Please try again.");
            }
        // End the loop if the user enters 'q'
        } while (!choice.equals("q"));

        // Close the scanner
        scanner.close();
    }
}
