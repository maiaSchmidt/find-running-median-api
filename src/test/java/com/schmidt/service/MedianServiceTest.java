package com.schmidt.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MedianServiceTest {

    private MedianService medianService;

    @Before
    public void setup() {
        medianService = new MedianService();
    }

    @Test
    public void checkRunningMedian() {
        int[] numbers = new int[] { 23, 12, 45, 6, 78, 9, 0, 13 };

        double[] results = medianService.runningMedian(numbers);

        double[] expected = new double[] { 23, 17.5, 23, 17.5, 23, 17.5, 12, 12.5 };

        Assert.assertEquals(expected.length, results.length);

        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], results[i], 0.01d);
        }
    }
}
