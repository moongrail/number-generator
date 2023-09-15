package com.example.number.service;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

import static java.util.Objects.isNull;

@Service
public class NumberServiceImpl implements NumberService {
    private static final char[] LETTERS = {'А', 'Е', 'Т', 'О', 'Р', 'Н', 'У', 'К', 'Х', 'С', 'В', 'М'};
    private String lastNumber = "";

    @Override
    public String random() {
        String generatedNumber = generateRandomNumber();
        lastNumber = generatedNumber;

        try {
            Short.parseShort(generatedNumber.substring(1, 4));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Не получилось преобразовать строку в число");
        }
        return generatedNumber;
    }


    @Override
    public String next() {
        if (lastNumber.isBlank() ) {
            return parseToStringResult("000");
        }

        String substringNumber = lastNumber.substring(1, 4);

        char[] numberChars = substringNumber.toCharArray();


        for (int i = numberChars.length-1; i >= 0; i--) {
            if (numberChars[i] == '9') {
                numberChars[i] = '0';
                if (i !=0){
                    numberChars[i-1] += 1;
                }
            } else {
                numberChars[i] += 1;
                break;
            }
        }
        return parseToStringResult(new String(numberChars));
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
}
