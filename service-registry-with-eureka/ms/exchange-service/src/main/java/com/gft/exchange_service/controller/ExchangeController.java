package com.gft.exchange_service.controller;

import com.gft.exchange_service.domain.ExchangeEntity;
import com.gft.exchange_service.environment.InstanceInformationService;
import com.gft.exchange_service.repository.ExchangeRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/exchange-service")
public class ExchangeController {

    private final ExchangeRepository exchangeRepository;
    private final InstanceInformationService instanceInformationService;

    public ExchangeController(ExchangeRepository exchangeRepository, InstanceInformationService instanceInformationService) {
        this.exchangeRepository = exchangeRepository;
        this.instanceInformationService = instanceInformationService;
    }

    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeEntity getExchange(@PathVariable BigDecimal amount, @PathVariable String from, @PathVariable String to) {

        var optExchange = exchangeRepository.findByFromCurrencyAndToCurrency(from, to);

        if(optExchange.isEmpty()){
            throw new RuntimeException("Currency Unsupported!");
        }

        var exchange = optExchange.get();

        BigDecimal conversionFactor = exchange.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);

        exchange.setConvertedValue(convertedValue);
        exchange.setEnvironment("PORT " + instanceInformationService.retrieveServerPort());

        return exchange;

    }

}
