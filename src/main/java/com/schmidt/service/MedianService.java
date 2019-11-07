package com.schmidt.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedianService {

    /**
     * 
     * @param numbers
     * @return incremental median of the numbers
     */    
    public List<BigDecimal> calculateIncrementalMedian(List<Integer> numbers) {
        List<Integer> currentNumbers = new ArrayList<>();
        List<BigDecimal> medianResults = new ArrayList<>();

        if (!CollectionUtils.isEmpty(numbers)) {
            for (int number : numbers) {
                addSorted(currentNumbers, number);
                medianResults.add(calculateMedian(currentNumbers));
            }
        }

        return medianResults;
    }

    private void addSorted(List<Integer> currentNumbers, int number) {
        if (currentNumbers.isEmpty()) {
            currentNumbers.add(number);
        } else {
            Integer insertIndex = calculateInsertIndex(currentNumbers, number, 0, currentNumbers.size() - 1);
            currentNumbers.add(insertIndex, number);
        }
    }

    private Integer calculateInsertIndex(List<Integer> currentNumbers,
                                         int number,
                                         int startIndex,
                                         int endIndex) {

        List<Integer> indexes;
        Integer insertIndex = null;
        while (insertIndex == null) {
            if (startIndex == endIndex) {
                insertIndex = number < currentNumbers.get(startIndex) ? startIndex : startIndex + 1;
            } else {
                indexes = getMedianIndexes(startIndex, endIndex);
                int index1 = indexes.get(0);
                int medianOperand = currentNumbers.get(index1);
                if (number < medianOperand) {
                    endIndex = index1;
                } else if (number > medianOperand) {
                    if (indexes.size() < 2) {
                        startIndex = index1;
                    } else {
                        int index2 = indexes.get(1);
                        int medianOperand2 = currentNumbers.get(index2);
                        if (number <= medianOperand2) {
                            insertIndex = index2;
                        } else if (number > medianOperand2) {
                            startIndex = index2;
                        }
                    }
                } else {
                    insertIndex = index1;
                }
            }
        }
        return insertIndex;
    }

    private List<Integer> getMedianIndexes(List<Integer> currentNumbers) {
        return getMedianIndexes(0, currentNumbers.size() - 1);
    }

    private List<Integer> getMedianIndexes(int startIndex, int endIndex) {
        int quantity = endIndex - startIndex + 1;
        List<Integer> indexes = new ArrayList<>();
        if (quantity % 2 == 0) {
            indexes.add((quantity / 2) - 1 + startIndex);
            indexes.add((quantity / 2) + startIndex);
        } else {
            indexes.add(((quantity - 1) / 2) + startIndex);
        }

        return indexes;
    }

    private BigDecimal calculateMedian(List<Integer> currentNumbers) {
        List<Integer> indexes = getMedianIndexes(currentNumbers);

        if (indexes.size() < 2) {
            return new BigDecimal(currentNumbers.get(indexes.get(0))).setScale(1, BigDecimal.ROUND_HALF_EVEN);
        } else {
            return new BigDecimal(currentNumbers.get(indexes.get(0))).add(
                    new BigDecimal(currentNumbers.get(indexes.get(1))))
                    .divide(new BigDecimal(2), 1, BigDecimal.ROUND_HALF_EVEN);
        }
    }
}
