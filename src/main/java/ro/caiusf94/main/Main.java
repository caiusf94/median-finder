package ro.caiusf94.main;

import ro.caiusf94.io.LargeFileController;

import java.time.Duration;
import java.time.Instant;

/**
 * Main class for Median Finder
 */
public class Main {

    final static String ENCODING = "UTF-8";


    public static void main(String[] args) {

        final LargeFileController largeFileController = new LargeFileController();

        /**
         * Make sure script is run correctly
         */
        if (args.length != 1) {
            System.out.println("\nWrong params. Usage: ./run.sh " + " [file]");
        } else {
            final String fileName = args[0];
            System.out.println("\nStarting to read and find median from file: " + fileName);
            Instant start = Instant.now();
            largeFileController.readNumbersFromFile(fileName, ENCODING);
            System.out.println("\n" + largeFileController.getNumberHeap().displayMedian());
            System.out.println("Elapsed time: " + Duration.between(start, Instant.now()).toSeconds() + "s");
        }

    }
}
