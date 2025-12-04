package com.gft.book_service.domain.dto;


import java.math.BigDecimal;

public record ExchangeDTO(
        String fromCurrency, String toCurrency, double convertedValue, String environment
) {
}
