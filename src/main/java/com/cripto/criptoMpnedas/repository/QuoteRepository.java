package com.cripto.criptoMpnedas.repository;


import com.cripto.criptoMpnedas.domain.CriptoCurrency;
import com.cripto.criptoMpnedas.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface QuoteRepository extends JpaRepository<Quote,Long> {
    public List<Quote> findByName(String name);
    public List<Quote> findBySymbol(String symbol);
    public  List<Quote> findByNameAndCriptocurrency(String name, CriptoCurrency curency);
    public  List<Quote> findBySymbolAndCriptocurrency(String symbol, CriptoCurrency currency);



}
