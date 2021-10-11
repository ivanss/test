package com.bcp.app.exchangerate.expose.web;

import com.bcp.app.exchangerate.api.ExchangeRateApiDelegate;
import com.bcp.app.exchangerate.business.ExchangeRateService;
import com.bcp.app.exchangerate.model.ExchangeRateRequest;
import com.bcp.app.exchangerate.model.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Service class that contains the necessary methods to process the data
 * and business logic that will consume the REST ExchangeRateApiController class.
 *
 * @author Vito Llave
 * @version 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeRateApiImpl implements ExchangeRateApiDelegate {
    private final ExchangeRateService exchangeRateServicess;

    @Override
    public Mono<ResponseEntity<ExchangeRateResponse>> exchangeRate(Mono<ExchangeRateRequest> exchangeRateRequest,
                                                                   ServerWebExchange exchange) {
        log.info("ExchangeRateApiImpl - exchangeRate");
        return exchangeRateRequest.flatMap(exchangeRateServicess::exchangeRate).map(ResponseEntity::ok);
    }
}
