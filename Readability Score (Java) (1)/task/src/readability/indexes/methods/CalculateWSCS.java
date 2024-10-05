package readability.indexes.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 WSCS - abbreviation of "words, sentences, characters and syllables".
 This class performs the role of calculating all of the above listed data types for calculating indexes by different formulas
 */
public class CalculateWSCS {
    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return number of all words in given text
     */
    public static int countWords(String fileName) {
        int wordCount = 0; // total counter of words in given text
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line; // represents a line of a text from given line in every part of the cycle
            while ((line = reader.readLine()) != null) {
                // splitting every word in a line by using a regex, which represents an empty character
                String[] words = line.trim().split("\\s+");
                wordCount += words.length;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return wordCount;
    }
    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return number of all sentences in given text
     */
    public static int countSentences(String fileName) {
        int sentenceCount = 0; // total counter of sentences in given text
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line; // represents a line of a text from given line in every part of the cycle
            while ((line = reader.readLine()) != null) {
                // splitting every sentence in a line by using a regex, which represents a character that can end a sentence
                String[] sentences = line.trim().split("[.!?]");
                sentenceCount += sentences.length;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return sentenceCount;
    }
    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return number of all characters in given text
     */
    public static int countCharacters(String fileName) {
        int characterCount = 0; // total counter of characters in given text
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int c; // represents a boolean type of data in this text
            // if this character cannot be read, loop ends
            while ((c = reader.read()) != -1) {
                // if-statement for not counting empty characters
                if (!Character.isWhitespace(c)) {
                    characterCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return characterCount;
    }

    /**
     * @param word designation of word which this function work with
     * @return number of all syllables in this word
     */
    private static int countSyllablesLogic(String word) {
        int count = 0; // total counter of syllables in given word
        boolean vowel = false; // boolean flag to mark if syllable was the previous character
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if ("aeiou".indexOf(ch) >= 0) {
                if (!vowel) {
                    count++;
                    vowel = true;
                }
            } else {
                vowel = false;
            }
        }
        // the rule according to which the character "e", which comes last, cannot be considered as syllable
        if (word.endsWith("e")) {
            count--;
        }
        /*
            The method returns the maximum value between count and 1.
            This ensures that even if a word contains none of the specified vowels or has less than one syllable,
             the method still returns at least 1 (since every word must have at least one syllable).
         */
        return Math.max(count, 1);
    }

    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return total count of syllables in text given in file
     */
    public static int countSyllablesInFile(String fileName) {
        int totalSyllables = 0; // total counter of syllables in given text
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line; // represents a line of a text from given line in every part of the cycle
            while ((line = reader.readLine()) != null) {
                // splitting every word in a line by using a regex, which represents an empty character
                String[] words = line.split("\\s+");
                for (String word : words) {
                    totalSyllables += countSyllablesLogic(word);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return totalSyllables;
    }
    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return total count of polysyllables in text given in file
     */
    public static int countPolysyllablesInFile(String fileName) {
        int polysyllablesWordCount = 0; // total counter of polysyllables in given text
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line; // represents a line of a text from given line in every part of the cycle
            while ((line = reader.readLine()) != null) {
                // splitting every word in a line by using a regex, which represents an empty character
                String[] words = line.split("\\s+");
                for (String word : words) {
                    // as a logical conclusion, if word has 2+ syllables - this word count as polysyllable
                    if (countSyllablesLogic(word) > 2) {
                        polysyllablesWordCount++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return polysyllablesWordCount;
    }
}
