package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MedianService {

    static double[] runningMedian(int[] numbers) {
        List<Integer> currentNumbers = new ArrayList<>();
        List<BigDecimal> medianResults = new ArrayList<>();
        for (int number : numbers) {
            addSorted(currentNumbers, number);
            medianResults.add(calculateMedian(currentNumbers));
        }

        double[] results = new double[medianResults.size()];

        for(int i = 0; i < results.length; i++) {
            results[i] = medianResults.get(i).doubleValue();
        }

        return results;
    }

    private static void addSorted(List<Integer> currentNumbers, int number) {
        if (currentNumbers.isEmpty()) {
            currentNumbers.add(number);
        } else {
            int startIndex = 0;
            int endIndex = currentNumbers.size() - 1;
            Integer insertIndex = null;
            int[] indexes;
            while (insertIndex == null) {
                if (startIndex == endIndex) {
                    insertIndex = number < currentNumbers.get(startIndex) ? startIndex : startIndex + 1;
                } else {
                    indexes = getMedianIndexes(startIndex, endIndex);
                    int index = indexes[0];
                    int medianOperand = currentNumbers.get(index);
                    if (number < medianOperand) {
                        endIndex = index;
                    } else if (number > medianOperand) {
                        if (indexes.length < 2) {
                            startIndex = index;
                        } else {
                            int index2 = indexes[1];
                            int medianOperand2 = currentNumbers.get(index2);
                            if (number <= medianOperand2) {
                                insertIndex = index2;
                            } else if (number > medianOperand2) {
                                startIndex = index2;
                            }
                        }
                    } else {
                        insertIndex = index;
                    }
                }
            }
            currentNumbers.add(insertIndex, number);
        }
    }

    private static int[] getMedianIndexes(List<Integer> currentNumbers) {
        return getMedianIndexes(0, currentNumbers.size() - 1);
    }

    private static int[] getMedianIndexes(int startIndex, int endIndex) {
        int quantity = endIndex - startIndex + 1;
        int[] indexes;
        if (quantity % 2 == 0) {
            indexes = new int[2];
            indexes[0] = (quantity/2) - 1 + startIndex;
            indexes[1] = (quantity/2) + startIndex;
        } else {
            indexes = new int[1];
            indexes[0] = ((quantity - 1)/2) + startIndex;
        }

        return indexes;
    }

    private static BigDecimal calculateMedian(List<Integer> currentNumbers) {
        int[] indexes = getMedianIndexes(currentNumbers);

        if (indexes.length < 2) {
            return new BigDecimal(currentNumbers.get(indexes[0])).setScale(1,BigDecimal.ROUND_HALF_EVEN);
        } else {
            return new BigDecimal(currentNumbers.get(indexes[0])).add(new BigDecimal(currentNumbers.get(indexes[1]))).divide(new BigDecimal(2), 1, BigDecimal.ROUND_HALF_EVEN);
        }
    }
}
