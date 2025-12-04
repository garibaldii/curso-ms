package com.gft.exchange_service.repository;

import com.gft.exchange_service.domain.ExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeEntity, Long> {

    Optional<ExchangeEntity> findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}
