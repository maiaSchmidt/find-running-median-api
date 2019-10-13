package com.schmidt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schmidt.service.MedianService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MedianControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private MedianService medianService;

    @InjectMocks
    private MedianController controller;

    @Captor
    private ArgumentCaptor<List<Integer>> numbersCaptor;

    @Before
    public void setUp() {
        StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(controller);

        objectMapper = new ObjectMapper();

        mockMvc = builder.build();
    }

    @Test
    public void checkCalculateIncrementalMedian() throws Exception {
        List<Integer> numbers = Arrays.asList(34, 2, 21, 7, 3, 97, 8, 12);

        when(medianService.calculateIncrementalMedian(numbersCaptor.capture())).thenReturn(new ArrayList<>());
        String jsonNumbers = objectMapper.writeValueAsString(numbers);

        mockMvc.perform(
                post("/median/incremental")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNumbers))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertThat(numbersCaptor.getValue(), is(numbers));
    }

    @Test
    public void checkCalculateIncrementalMedianRequestInvalid() throws Exception {
        List<Character> numbers = Arrays.asList('4', '2', '1', '7', '3', '9', '8', 'd');

        String jsonNumbers = objectMapper.writeValueAsString(numbers);

        mockMvc.perform(
                post("/median/incremental")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNumbers))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(medianService, times(0)).calculateIncrementalMedian(anyList());
    }
}
