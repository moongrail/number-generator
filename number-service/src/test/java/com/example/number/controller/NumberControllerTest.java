package com.example.number.controller;

import com.example.number.service.NumberService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = NumberController.class)
class NumberControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    NumberService numberService;

    @Test
    @SneakyThrows
    void getRandom_whenOK_thenStatusOk() {
        when(numberService.random()).thenReturn("Hello");

        mvc.perform(get("/number/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andDo(print())
                .andReturn();
    }

    @Test
    @SneakyThrows
    void getNext_whenOK_thenStatusOk() {
        when(numberService.next()).thenReturn("Hello");

        mvc.perform(get("/number/next"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andDo(print())
                .andReturn();
    }
}