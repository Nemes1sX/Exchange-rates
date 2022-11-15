package com.example.demo.repositories;

import com.example.demo.models.entities.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

//@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyExchange, Long> {
    List<CurrencyExchange> findTop7ByCurrencyCodeOrderByExchangeDateDesc(String currencyCode);
    CurrencyExchange findByCurrencyCodeAndExchangeDate(String CurrencyCode, Date ExchangeDate);
}
