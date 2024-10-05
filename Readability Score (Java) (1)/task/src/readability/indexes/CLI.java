package readability.indexes;

import readability.interfaces.Readability;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 CLI - abbreviation of Coleman–Liau index.
 This class performs the role of calculating Coleman–Liau index on the data we take from other methods
 */
public class CLI implements Readability {
    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return calculated Coleman–Liau index (CLI) by given formula
     */
    public static int calculateCLI(String fileName) {
        double L = calculateL(fileName);
        double S = calculateS(fileName);
        return (int) Math.round((0.0588 * L) - (0.296 * S) - 15.8);
    }

    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return average number of characters per 100 words
     */
    private static double calculateL(String fileName) {
        int totalCharacters = 0; // total counter of characters in text
        int totalWords = 0; // total counter of words in text
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line; // represents a line of a text from given line in every part of the cycle
            while ((line = reader.readLine()) != null) {
                // splitting every word in a line by using a regex, which represents an empty character
                String[] words = line.split("\\s+");
                totalWords += words.length;
                for (String word : words) {
                    totalCharacters += word.length();
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        /*
        this statement returns 0 if total count of words in text equals 0
        or returns an average number of characters per 100 words if not
         */
        return (totalWords == 0) ? 0 : ((double) totalCharacters / totalWords) * 100;
    }
    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return average number of sentences per 100 words
     */
    private static double calculateS(String fileName) {
        int totalSentences = 0; // total counter of  sentences in text
        int totalWords = 0; // total counter of words in text
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line; // represents a line of a text from given line in every part of the cycle
            while ((line = reader.readLine()) != null) {
                // splitting every sentence in a line by using a regex, which represents a character that can end a sentence
                String[] sentences = line.split("[.!?]");
                totalSentences += sentences.length;
                // splitting every word in a line by using a regex, which represents an empty character
                String[] words = line.split("\\s+");
                totalWords += words.length;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        /*
        this statement returns 0 if total count of words in text equals 0
        or returns an average number of sentences per 100 words if not
         */
        return (totalWords == 0) ? 0 : ((double) totalSentences / totalWords) * 100;
    }

    @Override
    public int calculateReadabilityAge(String fileName) {
        return calculateCLI(fileName) + 5;
    }
}
