package readability.indexes;

import readability.indexes.methods.CalculateWSCS;
import readability.interfaces.Readability;

/**
 FKRT - abbreviation of Flesch–Kincaid readability tests.
 This class performs the role of calculating FKRT on the data we take from other methods
 */
public class FKRT implements Readability {
    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return calculated Flesch–Kincaid readability tests (FKRT) index by given formula
     */
    public static int calculateFKRT(String fileName) {
        int calculatedWords = CalculateWSCS.countWords(fileName);
        int calculatedSentences = CalculateWSCS.countSentences(fileName);
        int calculatedSyllables = CalculateWSCS.countSyllablesInFile(fileName);
        return (int) Math.round(0.39 * ((double) calculatedWords / calculatedSentences) +
                11.8 * ((double) calculatedSyllables / calculatedWords) - 15.59);
    }

    @Override
    public int calculateReadabilityAge(String fileName) {
        return calculateFKRT(fileName) + 5;
    }
}
