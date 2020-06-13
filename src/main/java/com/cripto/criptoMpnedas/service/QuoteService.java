package com.cripto.criptoMpnedas.service;

import com.cripto.criptoMpnedas.domain.CriptoCurrency;
import com.cripto.criptoMpnedas.domain.Quote;

import java.util.List;

public interface QuoteService {

    public Quote createQuote(Quote quote, Long id);
    public List<Quote> findByName(String name);
    public List<Quote> findBySymbol(String symbol);
    public  List<Quote> findByNameAndCriptocurrency(String name, CriptoCurrency curency);
    public  List<Quote> findBySymbolAndCriptocurrency(String symbol, CriptoCurrency currency);


}
