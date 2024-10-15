package com.example.currencyconverter.service;

import com.example.currencyconverter.model.Currency;
import com.example.currencyconverter.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Value("${tcmb.api.url}")
    private String tcmbApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        fetchAndUpdateCurrencyData();
    }

    public void fetchAndUpdateCurrencyData() {
        String response = restTemplate.getForObject(tcmbApiUrl, String.class);
        // Parse the response and update the database
        // Example: parse response and save to database
        // You need to implement parsing logic according to TCMB API response format
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getCurrency(String code) {
        return currencyRepository.findById(code).orElse(null);
    }

    public double convertCurrency(String from, String to, double amount) {
        Currency fromCurrency = getCurrency(from);
        Currency toCurrency = getCurrency(to);

        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Invalid currency code");
        }

        return amount * (toCurrency.getRate() / fromCurrency.getRate());
    }
}
