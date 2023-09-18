package com.example.number.controller;

import com.example.number.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/number")
public class NumberController {
    private final NumberService numberService;

    @RequestMapping(value = "/random", produces = "text/plain")
    public String random() {
        return numberService.random();
    }

    @RequestMapping(value = "/next", produces = "text/plain")
    public String next() {
        return numberService.next();
    }
}
