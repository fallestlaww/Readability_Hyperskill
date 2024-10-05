package readability;

import readability.indexes.methods.Menu;

import java.util.Scanner;

import static java.lang.System.in;

public class Main {
    public static final Scanner scanner = new Scanner(in);
    public static void main(String[] args) {
        Menu.showResult(args);
    }
}