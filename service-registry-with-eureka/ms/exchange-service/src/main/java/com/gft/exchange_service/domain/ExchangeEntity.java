package com.gft.exchange_service.domain;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity(name = "exchange")
public class ExchangeEntity implements Serializable {

    private static final long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal conversionFactor;

    public ExchangeEntity(String fromCurrency, String toCurrency, BigDecimal conversionFactor, BigDecimal convertedValue, String environment) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.conversionFactor = conversionFactor;
        this.convertedValue = convertedValue;
        this.environment = environment;
    }

    @Transient
    private BigDecimal convertedValue;

    @Transient
    private String environment;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeEntity that = (ExchangeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fromCurrency, that.fromCurrency) && Objects.equals(toCurrency, that.toCurrency) && Objects.equals(conversionFactor, that.conversionFactor) && Objects.equals(convertedValue, that.convertedValue) && Objects.equals(environment, that.environment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromCurrency, toCurrency, conversionFactor, convertedValue, environment);
    }
}
