package com.cripto.criptoMpnedas.repository;

import com.cripto.criptoMpnedas.domain.CriptoCurrency;
import com.cripto.criptoMpnedas.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriptoCurrencyRepository extends JpaRepository<CriptoCurrency,Long> {
    public List<CriptoCurrency> findByName(String name);
    public List<CriptoCurrency> findBySymbol(String symbol);

}