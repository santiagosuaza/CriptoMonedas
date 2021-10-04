package com.cripto.criptoMpnedas.service.impl;

import com.cripto.criptoMpnedas.domain.CriptoCurrency;
import com.cripto.criptoMpnedas.domain.Quote;
import com.cripto.criptoMpnedas.repository.CriptoCurrencyRepository;
import com.cripto.criptoMpnedas.service.CriptoCurrencyService;
import com.cripto.criptoMpnedas.service.QuoteService;
import com.sun.org.apache.xpath.internal.objects.XNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriptoCurrencyServiceImpl implements CriptoCurrencyService {
    @Autowired
    private CriptoCurrencyRepository criptoCurrencyRepository;



    @Override
    public List<CriptoCurrency> listAllCryptocurrencies() {
        return criptoCurrencyRepository.findAll();
    }

    @Override
    public CriptoCurrency getCyroCryptocurrency(Long id) {
        return criptoCurrencyRepository.findById(id).orElse(null);
    }

    @Override
    public CriptoCurrency createCryptocurrency(CriptoCurrency criptoCurrency) {
        
           criptoCurrency.setRank(0);
           return criptoCurrencyRepository.save(criptoCurrency);



   }

    @Override
    public CriptoCurrency updateRank(Long id, Long quoteId) {
        double may = 0;
        double priceUDS = 0;
        CriptoCurrency currey =getCyroCryptocurrency(id);
        if (currey != null) {
            List<Quote> quotes = currey.getQuote();
            if (!quotes.isEmpty()){
                for (Quote cuota : quotes) {
                    String divisa = cuota.getSymbol();
                    if(divisa.equals("EUR")){
                        priceUDS  = cuota.getPrices()*1.25;
                        cuota.setPrices(priceUDS);
                    }else if(divisa.equals("GBP")){
                        priceUDS = cuota.getPrices()*1.5;
                        cuota.setPrices(priceUDS);
                    }else{
                        priceUDS = cuota.getPrices();
                        cuota.setPrices(priceUDS);
                    }
                    if (may < priceUDS) {
                        may= priceUDS;
                        currey.setRank(cuota.getId());
                    }
                }
            }else{
                currey.setRank(quoteId);
            }
            return criptoCurrencyRepository.save(currey);
        } else {
            return null;
        }
    }

    @Override
    public List<CriptoCurrency> findByName(String name) {
        return criptoCurrencyRepository.findByName(name);
    }

    @Override
    public List<CriptoCurrency> findBySymbol(String symbol) {
        return criptoCurrencyRepository.findBySymbol(symbol);
    }
}



