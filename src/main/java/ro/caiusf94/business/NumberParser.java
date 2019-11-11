package ro.caiusf94.business;

import ro.caiusf94.business.exception.LineNotParsedException;

/**
 * Class responsible for parsing lines read from the file to {@link Number} objects
 */
public class NumberParser {

    /**
     * Tries to parse a {@link String} value to a {@link Number} object
     *
     * @param line value as String
     * @return {@link Number} parsed from line
     */
    public Number parseLineToNumber(final String line) throws LineNotParsedException {
        try {

            final long longValue = Long.parseLong(line);

            if (isIntValue(longValue)) {
                return (int) longValue; // return it as Integer to save up memory space
            } else {
                return longValue;
            }


        } catch (final NumberFormatException e) { // line might be a decimal number
            try {
                final double doubleValue = Double.parseDouble(line.replace(',', '.')); // decimals could be separated by "," or by "."

                return doubleValue;

            } catch (final NumberFormatException nfe) { // line is not a valid number
                throw new LineNotParsedException(nfe.getMessage());
            }
        }
    }


    /**
     * Check if long value is within the {@link Integer} range of values
     *
     * @param longValue
     * @return true if value belongs to Integer values range, false otherwise
     */
    private boolean isIntValue(final long longValue) {
        return Integer.MIN_VALUE < longValue && longValue < Integer.MAX_VALUE;
    }
}
