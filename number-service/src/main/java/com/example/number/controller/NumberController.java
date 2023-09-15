package com.example.number.controller;

import com.example.number.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/number")
public class NumberController {
    private final NumberService numberService;

    @RequestMapping("/random")
    public String random() {
        return numberService.random();
    }
    @RequestMapping("/next")
    public String next() {
        return numberService.next();
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String handleNumberFormatException(NumberFormatException e) {
        return e.getMessage();
    }
}
