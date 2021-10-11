package com.bcp.app.exchangerate.business.impl;

import com.bcp.app.exchangerate.model.ExchangeRateRequest;
import com.bcp.app.exchangerate.model.ExchangeRateResponse;
import com.bcp.app.exchangerate.model.entity.ExchangeRate;
import com.bcp.app.exchangerate.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ExchangeRateServiceImplTest {

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;
    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Returns the exchange rate from soles (PEN) to dollars (USD)")
    void returnsTheExchangeRateFromSolesPenToDollarsUsd() {
        given(exchangeRateRepository.findByCountryAndCurrencyFromAndCurrencyToAndDate(eq("PE"), eq("PEN"), eq("USD"), any(LocalDate.class)))
                .willReturn(ExchangeRate.builder().id(1)
                        .country("PE")
                        .currencyTo("PEN")
                        .currencyFrom("USD")
                        .exchangeRate(new BigDecimal("4.098"))
                        .date(LocalDate.now()).build());

        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest().amount(new BigDecimal(1000L)).currencyFrom("PEN").currencyTo("USD");
        Mono<ExchangeRateResponse> exchangeRateResponseMono = exchangeRateService.exchangeRate(exchangeRateRequest);

        ExchangeRateResponse exchangeRateResponseExpected = new ExchangeRateResponse()
                .amount(new BigDecimal(1000L))
                .amountWithExchangeRate(new BigDecimal("4098.000"))
                .currencyFrom("PEN")
                .currencyTo("USD")
                .exchangeRate(new BigDecimal("4.098"));

        StepVerifier.create(exchangeRateResponseMono.log())
                .consumeNextWith(response -> Assertions.assertEquals(exchangeRateResponseExpected.toString(), response.toString()))
                .verifyComplete();

        verify(exchangeRateRepository, times(1)).findByCountryAndCurrencyFromAndCurrencyToAndDate(eq("PE"), eq("PEN"), eq("USD"), any(LocalDate.class));
    }
}