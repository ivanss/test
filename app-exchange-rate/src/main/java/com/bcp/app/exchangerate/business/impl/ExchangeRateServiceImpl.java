package com.bcp.app.exchangerate.business.impl;

import com.bcp.app.exchangerate.business.ExchangeRateService;
import com.bcp.app.exchangerate.repository.ExchangeRateRepository;
import com.bcp.app.exchangerate.model.ExchangeRateRequest;
import com.bcp.app.exchangerate.model.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * Service class that contains the necessary methods to process the data and business logic.
 *
 * @author Vito Llave
 * @version 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public Mono<ExchangeRateResponse> exchangeRate(ExchangeRateRequest request) {
        log.info("ExchangeRateServiceImpl - exchangeRate");
        return Mono.fromCallable(() -> exchangeRateRepository.findByCountryAndCurrencyFromAndCurrencyToAndDate(
                        "PE", request.getCurrencyFrom(), request.getCurrencyTo(), LocalDate.now()))
                .map(exchangeRate -> new ExchangeRateResponse()
                        .amount(request.getAmount())
                        .amountWithExchangeRate(exchangeRate.getExchangeRate().multiply(request.getAmount()))
                        .currencyFrom(request.getCurrencyFrom())
                        .currencyTo(request.getCurrencyTo())
                        .exchangeRate(exchangeRate.getExchangeRate()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new Exception("Exchange rate not found"))));
    }
}
