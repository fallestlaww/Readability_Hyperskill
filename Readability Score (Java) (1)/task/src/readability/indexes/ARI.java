package readability.indexes;

import readability.indexes.methods.CalculateWSCS;
import readability.interfaces.Readability;

/**
ARI - abbreviation of Automated Readability Index.
This class performs the role of calculating Automated Readability Index on the data we take from other methods
 */
public class ARI implements Readability {
    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return calculated Automated Readability Index (ARI) by given formula
     */
    public static int calcARI(String fileName) {
        int calculatedCharacters = CalculateWSCS.countCharacters(fileName);
        int calculatedWords = CalculateWSCS.countWords(fileName);
        int calculatedSentences = CalculateWSCS.countSentences(fileName);
        return (int) Math.round(4.71 * ((double) calculatedCharacters / calculatedWords) +
                0.5 * ((double) calculatedWords / calculatedSentences) - 21.43);
    }


    @Override
    public int calculateReadabilityAge(String fileName) {
        return calcARI(fileName) + 5;
    }
}
