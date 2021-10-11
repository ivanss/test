package com.bcp.app.exchangerate.repository;

import com.bcp.app.exchangerate.model.entity.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Integer> {

    ExchangeRate findByCountryAndCurrencyFromAndCurrencyToAndDate(String country, String currencyFrom, String CurrencyTo, LocalDate date);

}
