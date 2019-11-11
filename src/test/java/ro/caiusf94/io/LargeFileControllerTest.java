package ro.caiusf94.io;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;


public class LargeFileControllerTest {

    final String SMALL_FILE_PATH = "src/test/resources/file_with_numbers_1to20.txt";
    final String LARGE_FILE_PATH = "src/test/resources/file_with_numbers_1to100k.txt";
    final String FAULTY_FILE_PATH = "src/test/resources/file_with_faulty_lines.txt";
    final String EMPTY_FILE_PATH = "src/test/resources/empty_file.txt";

    final String ENCONDING = "UTF-8";

    private static LargeFileController largeFileController;

    @BeforeClass
    public static void setup() {
        largeFileController = new LargeFileController();
    }

    @Test
    public void testWithSmallFile() {
        largeFileController.readNumbersFromFile(SMALL_FILE_PATH, ENCONDING);
        Assert.assertThat("Number of lines in file is different than expected!", largeFileController.getLinesCount(), is((long) 20));
    }


    @Test
    public void testWithLargeFile() {
        largeFileController.readNumbersFromFile(LARGE_FILE_PATH, ENCONDING);
        Assert.assertThat("Number of lines in file is different than expected!", largeFileController.getLinesCount(), is((long) 100_000));
    }

    @Test
    public void testWithFaultyFile() {
        largeFileController.readNumbersFromFile(FAULTY_FILE_PATH, ENCONDING);
        Assert.assertThat("Number of lines in file is different than expected!", largeFileController.getLinesCount(), is((long) 0));
    }

    @Test
    public void testWithEmptyFile() {
        largeFileController.readNumbersFromFile(EMPTY_FILE_PATH, ENCONDING);
        Assert.assertThat("Number of lines in file is different than expected!", largeFileController.getLinesCount(), is((long) 0));
    }


}