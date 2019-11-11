package ro.caiusf94.business.exception;

/**
 * Custom exception for lines that can't be parsed to numbers
 */
public class LineNotParsedException extends Exception {

    /**
     * Constructor for custom exception
     *
     * @param message
     */
    public LineNotParsedException(final String message) {
        super(message);
    }
}
