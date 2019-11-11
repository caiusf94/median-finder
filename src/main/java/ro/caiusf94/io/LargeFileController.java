package ro.caiusf94.io;

import jdk.jfr.Unsigned;
import ro.caiusf94.business.NumberParser;
import ro.caiusf94.business.data.NumberHeap;
import ro.caiusf94.business.exception.LineNotParsedException;

import java.io.*;

/**
 * Controller dealing with reading lines from the file
 */
public class LargeFileController {

    /**
     * Line counter. Marked as unsigned because there's no need for negative values
     */
    @Unsigned
    private long linesCount;

    /**
     * Heap for storing read numbers
     */
    private NumberHeap numberHeap = new NumberHeap();

    /**
     * Number parser
     */
    private NumberParser parser = new NumberParser();

    /**
     * Reads lines from the file that can be parsed as numbers
     *
     * @param fileName name of file which contains the dataset
     * @param encoding encoding type
     */
    public void readNumbersFromFile(final String fileName, final String encoding) {

        initializeCount(); // start counting from zero

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding))) {

            Number number;

            for (String line; (line = reader.readLine()) != null; ) {
                try {
                    number = parser.parseLineToNumber(line);
                    linesCount++;
                    numberHeap.addNumberToHeap(number);
                } catch (final LineNotParsedException e) {
                    System.out.println(e.getClass().getName() + " caught in " + this.getClass().getName() + ". Please check the message below: ");
                    System.out.println("Couldn't parse line to a number. Reason: " + e.getMessage());
                    System.out.println("Line discarded. Continuing to read from file...\n");
                }
                if (linesCount % 100_000_00 == 0) { // show progress on screen
                    System.out.println("Reading line " + linesCount + " from file " + fileName);
                }
            }

        } catch (final IOException e) {
            System.out.println(e.getClass().getName() + " caught in " + this.getClass().getName() + ". Please check the message below: ");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get number heap
     *
     * @return numberHeapa
     */
    public NumberHeap getNumberHeap() {
        return numberHeap;
    }

    /**
     * Get line counter
     *
     * @return linesCount
     */
    public long getLinesCount() {
        return linesCount;
    }

    /**
     * Set counter to zero
     */
    private void initializeCount() {
        linesCount = 0;
    }
}
