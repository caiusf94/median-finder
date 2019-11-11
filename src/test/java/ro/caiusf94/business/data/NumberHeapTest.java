package ro.caiusf94.business.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ro.caiusf94.io.LargeFileController;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class NumberHeapTest {

    private final String LARGE_FILE_PATH = "src/test/resources/file_with_numbers_1to100k.txt";
    private final String RANDOM_NUMBERS_FILE_PATH = "src/test/resources/file_with_random_numbers.txt";

    private static NumberHeap numberHeap;
    private static LargeFileController controller;

    @Before
    public void setup() {
        controller = new LargeFileController();
        numberHeap = controller.getNumberHeap();
    }

    @Test
    public void testNumberHeapNotNull(){
        Assert.assertNotNull("Number heap should not be null!", numberHeap);
    }

    @Test
    public void testCalculateMedianForLargeFile() {
        controller.readNumbersFromFile(LARGE_FILE_PATH, "UTF-8");
        Assert.assertThat("Median is different than expected!", numberHeap.calculateMedian(), is(50001.5));
    }

    @Test
    public void testCalculateMedianForFileWithRandomNumbers() {
        controller.readNumbersFromFile(RANDOM_NUMBERS_FILE_PATH, "UTF-8");
        Assert.assertThat("Median is different than expected!", numberHeap.calculateMedian(), is(55.0));
    }
}