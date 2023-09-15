package com.example.number.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class NumberServiceImplTest {
    public static final String PATTERN_STRING = "[А-Я]\\d{3}[А-Я]{2} 116 RUS";
    private NumberServiceImpl numberService = new NumberServiceImpl();

    @Test
    void testRandom_whenInvoke_thenReturnRightString() {
        String randomNumber = numberService.random();

        assertEquals(14, randomNumber.length());
    }

    @Test
    void testRandom_whenInvoke_thenReturnRightPatternString() {
        String randomNumber = numberService.random();

        assertTrue(randomNumber.matches(PATTERN_STRING));
    }

    @Test
    void testNext_whenInvoke_thenReturnRightString() {
        String nextNumber = numberService.next();

        assertEquals(14, nextNumber.length());
    }

    @Test
    void testNext_whenInvokeBlankLastNumber_thenReturn000Number() {
        String nextNumber = numberService.next();
        assertEquals("000", nextNumber.substring(1, 4));
    }

    @Test
    void testNext_whenInvokeAfterRandom_thenReturnNextNumber() {
        String randomNumber = numberService.random();
        String nextNumber = numberService.next();

        assertNotEquals(randomNumber, nextNumber);
    }

    @Test
    void testNext_whenInvoke_thenReturnRightPatternString() {
        String nextNumber = numberService.next();

        assertTrue(nextNumber.matches(PATTERN_STRING));
    }

    @ParameterizedTest
    @MethodSource("provideLastNumbers")
    void testNext_whenInvoke_thenReturnNextNumber(String lastNumber, String expectedNumber) {
        numberService.setLastNumber(lastNumber);
        String nextNumber = numberService.next();

        assertEquals(expectedNumber, nextNumber);
    }

    private static Stream<Arguments> provideLastNumbers() {
        return Stream.of(
                Arguments.of("В999ВВ 116 RUS", "В000ВВ 116 RUS"),
                Arguments.of("В000ВВ 116 RUS", "В001ВВ 116 RUS"),
                Arguments.of("В099ВВ 116 RUS", "В100ВВ 116 RUS"),
                Arguments.of("В009ВВ 116 RUS", "В010ВВ 116 RUS"),
                Arguments.of("В031ВВ 116 RUS", "В032ВВ 116 RUS"),
                Arguments.of("В029ВВ 116 RUS", "В030ВВ 116 RUS")
        );
    }
}