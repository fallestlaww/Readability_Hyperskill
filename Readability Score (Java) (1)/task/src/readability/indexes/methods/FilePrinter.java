package readability.indexes.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilePrinter {
    /**
     * @param fileName designation of the file from which we read the text given to us
     */
    public static void printFileContent(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line; // represents a line of a text from given line in every part of the cycle
            // if line is empty, loop ends
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}