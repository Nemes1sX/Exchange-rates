package com.example.demo.services;

import com.example.demo.models.entities.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyExchange, Long> {
}
