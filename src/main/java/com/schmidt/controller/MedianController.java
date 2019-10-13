package com.schmidt.controller;

import com.schmidt.service.MedianService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "median")
public class MedianController {

    private final MedianService medianService;

    @Autowired
    public MedianController(final MedianService medianService) {
        this.medianService = medianService;
    }

    @PostMapping("/incremental")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "calculate the median of the numbers in a incremental way")
    public List<BigDecimal> calculateIncrementalMedian(@RequestBody List<Integer> numbers) {
        return medianService.calculateIncrementalMedian(numbers);
    }

}
