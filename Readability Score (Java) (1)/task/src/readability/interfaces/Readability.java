package readability.interfaces;
public interface Readability {
    /**
     * @param fileName designation of the file from which we read the text given to us
     * @return calculated Automated Readability Index by given formula
     * */
    // we should add +5 to our score because of dependence of age on the result of calculations, which can be seen in the table
    int calculateReadabilityAge(String fileName);
    }
