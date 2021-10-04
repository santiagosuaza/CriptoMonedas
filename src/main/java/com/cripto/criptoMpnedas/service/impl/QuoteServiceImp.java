package com.cripto.criptoMpnedas.service.impl;

import com.cripto.criptoMpnedas.domain.CriptoCurrency;
import com.cripto.criptoMpnedas.domain.Quote;
import com.cripto.criptoMpnedas.repository.CriptoCurrencyRepository;
import com.cripto.criptoMpnedas.repository.QuoteRepository;
import com.cripto.criptoMpnedas.service.CriptoCurrencyService;
import com.cripto.criptoMpnedas.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuoteServiceImp implements QuoteService {

    List<String> quotes = new ArrayList<>();
    @Autowired
    private QuoteRepository quoteRepository;




    @Autowired
    private CriptoCurrencyService criptoCurrencyService;

    @Override
    public Quote createQuote(Quote quote, Long id) {
        quotes.add("USD");
        quotes.add("EUR");
        quotes.add("GBP");
        Date fecha = new Date();
        quote.setLastUpate(fecha);
        CriptoCurrency bdcurre = criptoCurrencyService.getCyroCryptocurrency(id);
        if (bdcurre!=null) {
            for (Quote quoteBd : bdcurre.getQuote()) {
                if (quoteBd.getName() == quote.getName()
                        && quoteBd.getSymbol() == quote.getSymbol()) {
                    return null;
                }
            }
        }
            quoteRepository.save(quote);
            criptoCurrencyService.updateRank(quote.getCriptocurrency().getId(), quote.getId());
            return quote;
    }
    @Override
    public List<Quote> findByName(String name) {
        return quoteRepository.findByName(name);
    }

    @Override
    public List<Quote> findBySymbol(String symbol) {
        return quoteRepository.findBySymbol(symbol);
    }
    @Override
    public List<Quote>  findByNameAndCriptocurrency(String name, CriptoCurrency currency) {
        return quoteRepository.findByNameAndCriptocurrency(name, currency);
    }

    @Override
    public List<Quote> findBySymbolAndCriptocurrency(String symbol, CriptoCurrency currency) {
        return quoteRepository.findBySymbolAndCriptocurrency(symbol, currency);
    }


}