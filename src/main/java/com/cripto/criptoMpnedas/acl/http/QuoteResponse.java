package com.cripto.criptoMpnedas.acl.http;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class QuoteResponse {
    private Long id;
    private String name;
    private String symbol;
    private Double price;
    private Date lastUpdate;
}
