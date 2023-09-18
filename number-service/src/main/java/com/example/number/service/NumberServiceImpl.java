package com.example.number.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class NumberServiceImpl implements NumberService {
    private static final char[] LETTERS = {'А', 'Е', 'Т', 'О', 'Р', 'Н', 'У', 'К', 'Х', 'С', 'В', 'М'};
    private String lastNumber = "";

    @Override
    public String random() {
        String generatedNumber = generateRandomNumber();
        log.info("Generated number: {}", generatedNumber);
        lastNumber = generatedNumber;

        return generatedNumber;
    }


    @Override
    public String next() {
        if (lastNumber.isBlank()) {
            String nextNumber = parseToStringResult("000");
            log.info("Generated number: {}", nextNumber);
            lastNumber = nextNumber;
            return lastNumber;
        }

        String substringNumber = lastNumber.substring(1, 4);

        if (substringNumber.equals("999")) {
            lastNumber = lastNumber.replace(substringNumber, "000");
            log.info("Generated number: {}", lastNumber);
            return lastNumber;
        }

        char[] numberChars = substringNumber.toCharArray();

        for (int i = numberChars.length - 1; i >= 0; i--) {
            if (numberChars[i] == '9') {
                numberChars[i] = '0';
            } else {
                numberChars[i] += 1;
                break;
            }
        }

        lastNumber = lastNumber.charAt(0) + String.valueOf(numberChars) + lastNumber.substring(4);
        log.info("Generated number: {}", lastNumber);

        return lastNumber;
    }

    private String generateRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(1000);

        return parseToStringResult(String.format("%03d", number));
    }

    private static String parseToStringResult(String number) {
        return geRandomLetter() +
                number +
                geRandomLetter() +
                geRandomLetter() +
                " 116 RUS";
    }

    private static char geRandomLetter() {
        Random random = new Random();
        return LETTERS[random.nextInt(LETTERS.length)];
    }

    public void setLastNumber(String lastNumber) {
        this.lastNumber = lastNumber;
    }
}
