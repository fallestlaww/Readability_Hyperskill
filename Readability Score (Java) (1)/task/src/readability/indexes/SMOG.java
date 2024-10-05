package readability.indexes;

import readability.indexes.methods.CalculateWSCS;
import readability.interfaces.Readability;
/**
 SMOG- abbreviation of Simple Measure of Gobbledygook.
 This class performs the role of calculating Simple Measure of Gobbledygook index on the data we take from other methods
 */
public class SMOG implements Readability {
    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return calculated Simple Measure of Gobbledygook (SMOG) index by given formula
     */
    public static int calculateSMOG(String fileName) {
        int calculatedSentences = CalculateWSCS.countSentences(fileName);
        int calculatedPolysyllables = CalculateWSCS.countPolysyllablesInFile(fileName);
        return (int) Math.round(1.034 * Math.sqrt(calculatedPolysyllables * (double)(30 / calculatedSentences)) + 3.1291);
    }

    @Override
    public int calculateReadabilityAge(String fileName) {
        return calculateSMOG(fileName) + 5;
    }
}
