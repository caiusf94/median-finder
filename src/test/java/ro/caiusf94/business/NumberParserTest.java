package ro.caiusf94.business;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import ro.caiusf94.business.exception.LineNotParsedException;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;

@RunWith(value = Parameterized.class)
public class NumberParserTest {

    @Parameter
    public String lineFromFile;

    @Parameter(value = 1)
    public Number expectedNumber;

    private Number actualNumber;

    private static NumberParser numberParser;

    @BeforeClass
    public static void setup() {
        numberParser = new NumberParser();
    }


    @Parameters(name = "{index}: Test that {0} is parsed to {1}.")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"42", 42},
                {"-987654321", -987654321},
                {"7.5", 7.5},
                {"123,456", 123.456},
                {"44444,0", 44444.0},
                {"250.0000000001", 250.0000000001},
                {"3147483647", 3147483647L},
                {"0,01234567890123", 0.01234567890123},
                {"-999999999.99999999", -999999999.99999999},
                {String.valueOf(Integer.MAX_VALUE * 10000L), 21474836470000L},
                {"0", 0},
                {"0.0", 0.0},
                {"1.", 1.0},
                {"1,", 1.0},
        });
    }

    @Test
    public void testParseLineToNumber() throws LineNotParsedException {
        actualNumber = numberParser.parseLineToNumber(lineFromFile);
        Assert.assertThat("Actual value: " + actualNumber + ". Expected number is different than parsed number!", actualNumber, is(expectedNumber));
    }

}