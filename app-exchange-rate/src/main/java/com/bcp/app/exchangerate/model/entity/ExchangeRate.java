package com.bcp.app.exchangerate.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity class that maps the exchange_rate database table.
 *
 * @author Vito Llave
 * @version 1.0
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchange_rate")
@JsonPropertyOrder({"id", "country", "currencyFrom", "currencyTo", "exchangeRate", "date"})
public class ExchangeRate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Integer id;
    @Column(name = "country")
    @JsonProperty("country")
    private String country;
    @Column(name = "currency_from")
    @JsonProperty("currencyFrom")
    private String currencyFrom;
    @Column(name = "currency_to")
    @JsonProperty("currencyTo")
    private String currencyTo;
    @Column(name = "exchange_rate")
    @JsonProperty("exchangeRate")
    private BigDecimal exchangeRate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "date")
    @JsonProperty("date")
    private LocalDate date;

}
