package com.example.currencyconverter.controller;

import com.example.currencyconverter.model.Currency;
import com.example.currencyconverter.service.CurrencyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/{code}")
    public Currency getCurrency(@PathVariable String code) {
        return currencyService.getCurrency(code);
    }

    @GetMapping("/convert")
    public double convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        return currencyService.convertCurrency(from, to, amount);
    }
}
