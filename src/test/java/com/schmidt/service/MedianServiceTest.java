package com.schmidt.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class MedianServiceTest {

    private MedianService medianService;

    @Before
    public void setup() {
        medianService = new MedianService();
    }

    @Test
    public void checkRunningMedian() {
        int[] numbers = new int[] { 23, 12, 45, 6, 78, 9, 0, 13 };

        List<BigDecimal> results = medianService.runningMedian(numbers);

        List<BigDecimal> expected = Arrays.asList(new BigDecimal(23).setScale(1), new BigDecimal(17.5).setScale(1),
                new BigDecimal(23).setScale(1), new BigDecimal(17.5).setScale(1), new BigDecimal(23).setScale(1),
                new BigDecimal(17.5).setScale(1), new BigDecimal(12).setScale(1), new BigDecimal(12.5).setScale(1));

        Assert.assertThat(results, is(expected));
    }
}
