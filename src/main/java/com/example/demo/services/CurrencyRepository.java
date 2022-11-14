package com.example.demo.services;

import com.example.demo.models.entities.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

//@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyExchange, Long> {
    @Query(value = "SELECT * FROM currency_exchange c  WHERE convert_currency LIKE %?1% ORDER BY DESC exchange_date LIMIT 7", nativeQuery = true)
    List<CurrencyExchange> findByCurrencyCode(String currencyCode);
    CurrencyExchange findByCurrencyCodeAndExchangeDate(String CurrencyCode, Date ExchangeDate);
}
