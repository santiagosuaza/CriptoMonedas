package com.cripto.criptoMpnedas.controller;

import com.cripto.criptoMpnedas.acl.NotFoundException;
import com.cripto.criptoMpnedas.domain.CriptoCurrency;
import com.cripto.criptoMpnedas.domain.Quote;
import com.cripto.criptoMpnedas.service.CriptoCurrencyService;
import com.cripto.criptoMpnedas.service.MapValidationErrorService;
import com.cripto.criptoMpnedas.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/quote")
public class QuoteController {

    @Autowired
    private  QuoteService quoteService;

    @Autowired
    private CriptoCurrencyService criptoCurrencyService;

    @Autowired
    private MapValidationErrorService validationErrorService;

    @Autowired
    private NotFoundException notFoundException;


    @PostMapping( "/{id}")
    public ResponseEntity<?> crearQuote(@Valid @PathVariable Long id, @RequestBody Quote quote, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;
        CriptoCurrency currency = criptoCurrencyService.getCyroCryptocurrency(id);

        if (currency == null) {
            ResponseEntity<?> exception = notFoundException.Exception("Currency: " + id + " doesn't exist");
            return exception;
        }
        if (quote.getName() == null || quote.getSymbol() == null) {
            ResponseEntity<?> exception = notFoundException.Exception("name y symbol no puede ser vacio");
            return exception;
        }
        if (quote.getPrices() < 1) {
            ResponseEntity<?> exception = notFoundException.Exception("El precio no puede ser negativo");
            return exception;
        }

        if(!quote.getSymbol().equals("USD") && !quote.getSymbol().equals("EUR") && !quote.getSymbol().equals("GBP")) {
            ResponseEntity<?> exception = notFoundException.Exception("Solo es posible adicionar estas tres divisas: USD-EUR-GBP");
            return exception;
        }
        List<Quote> nameQuote = quoteService.findByNameAndCriptocurrency(quote.getName(), currency);
        List<Quote> symbolQuote = quoteService.findBySymbolAndCriptocurrency(quote.getSymbol(), currency);
        if(!nameQuote.isEmpty() && !symbolQuote.isEmpty()){
            ResponseEntity<?> exception = notFoundException.Exception("name y Symbol ya existe");
            return exception;
        }

        if(!nameQuote.isEmpty()){
            ResponseEntity<?> exception = notFoundException.Exception("name ya existe");
            return exception;
        }
        if(!symbolQuote.isEmpty()){
            ResponseEntity<?> exception = notFoundException.Exception("Symbol ya existe");
            return exception;
        }
        quote.setCriptocurrency(currency);
        Quote quoteCreated = quoteService.createQuote(quote, id);
        if (quoteCreated==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(quoteCreated);
    }
}
