package com.bcp.app.exchangerate.business;

import com.bcp.app.exchangerate.model.ExchangeRateRequest;
import com.bcp.app.exchangerate.model.ExchangeRateResponse;
import reactor.core.publisher.Mono;

public interface ExchangeRateService {
    Mono<ExchangeRateResponse> exchangeRate(ExchangeRateRequest exchangeRateRequest);
}
