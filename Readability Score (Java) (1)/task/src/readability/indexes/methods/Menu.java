package readability.indexes.methods;

import readability.Main;
import readability.interfaces.Readability;
import readability.indexes.ARI;
import readability.indexes.CLI;
import readability.indexes.FKRT;
import readability.indexes.SMOG;

public class Menu {
    // objects of classes of code index calculation methods
    private static final Readability readabilityARI = new ARI();
    private static final Readability readabilityFKRT = new FKRT();
    private static final Readability readabilitySMOG = new SMOG();
    private static final Readability readabilityCLI = new CLI();

    public static void showResult(String[] args) {
        if (args.length > 0) {
            String fileName = args[0];
            FilePrinter.printFileContent(fileName);
            displayTextStatistics(fileName);
            showMenu(fileName);
        } else {
            System.out.println("Please provide a file name as a command-line argument.");
        }
    }

    private static void displayTextStatistics(String fileName) {
        int calculatedCharacters = CalculateWSCS.countCharacters(fileName);
        int calculatedWords = CalculateWSCS.countWords(fileName);
        int calculatedSentences = CalculateWSCS.countSentences(fileName);
        int calculatedSyllables = CalculateWSCS.countSyllablesInFile(fileName);
        int calculatedPolysyllables = CalculateWSCS.countPolysyllablesInFile(fileName);

        System.out.println("Words: " + calculatedWords
                + "\nSentences: " + calculatedSentences
                + "\nCharacters: " + calculatedCharacters
                + "\nSyllables: " + calculatedSyllables
                + "\nPolysyllables: " + calculatedPolysyllables);
    }

    private static void showMenu(String fileName) {
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        String choice = Main.scanner.nextLine();

        switch (choice) {
            case "ARI":
                displayScoreAndAge("Automated Readability Index", ARI.calcARI(fileName), readabilityARI.calculateReadabilityAge(fileName));
                break;
            case "FK":
                displayScoreAndAge("Flesch-Kincaid readability tests", FKRT.calculateFKRT(fileName), readabilityFKRT.calculateReadabilityAge(fileName));
                break;
            case "SMOG":
                displayScoreAndAge("Simple Measure of Gobbledygook", SMOG.calculateSMOG(fileName), readabilitySMOG.calculateReadabilityAge(fileName));
                break;
            case "CL":
                displayScoreAndAge("Coleman–Liau index", CLI.calculateCLI(fileName), readabilityCLI.calculateReadabilityAge(fileName));
                break;
            case "all":
                displayAllScores(fileName);
                break;
            default:
                System.out.println("Invalid choice. Please select a valid readability index.");
        }
    }

    private static void displayScoreAndAge(String name, int score, int age) {
        System.out.printf("%s: %d (about %d-year-olds)%n", name, score, age);
    }

    private static void displayAllScores(String fileName) {
        int calculatedARI = ARI.calcARI(fileName);
        int ARIage = readabilityARI.calculateReadabilityAge(fileName);
        displayScoreAndAge("Automated Readability Index", calculatedARI, ARIage);

        int calculatedFKRT = FKRT.calculateFKRT(fileName);
        int FKRTage = readabilityFKRT.calculateReadabilityAge(fileName);
        displayScoreAndAge("Flesch-Kincaid readability tests", calculatedFKRT, FKRTage);

        int calculatedSMOG = SMOG.calculateSMOG(fileName);
        int SMOGage = readabilitySMOG.calculateReadabilityAge(fileName);
        displayScoreAndAge("Simple Measure of Gobbledygook", calculatedSMOG, SMOGage);

        int calculatedCLI = CLI.calculateCLI(fileName);
        int CLIage = readabilityCLI.calculateReadabilityAge(fileName);
        displayScoreAndAge("Coleman–Liau index", calculatedCLI, CLIage);

        double average = (double) (calculatedARI + calculatedFKRT + calculatedSMOG + calculatedCLI) / 4;
        System.out.printf("This text should be understood in average by %.2f-year-olds.%n", average);
    }
}
