package com.cripto.criptoMpnedas.controller;


import com.cripto.criptoMpnedas.acl.NotFoundException;
import com.cripto.criptoMpnedas.domain.CriptoCurrency;
import com.cripto.criptoMpnedas.service.CriptoCurrencyService;
import com.cripto.criptoMpnedas.service.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/currency")
public class CriptoCurrencyController {

    @Autowired
    private CriptoCurrencyService criptoCurrencyService;

    @Autowired
    private MapValidationErrorService validationErrorService;

    @Autowired
    private NotFoundException notFoundException;


    @GetMapping(value = "/{name}")
    public ResponseEntity<List<CriptoCurrency>> getCriptoCurrency(@PathVariable("name") String name) {
    List<CriptoCurrency> nameCurrency = criptoCurrencyService.findByName(name);
        if (criptoCurrencyService.findByName(name)== null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(nameCurrency);
    }

    @GetMapping
    public List<CriptoCurrency> getAllCriptoCurrency() {
        return criptoCurrencyService.listAllCryptocurrencies();
    }

    @PostMapping
    public ResponseEntity<?> createCriptoCurrency(@Valid @RequestBody CriptoCurrency criptoCurrency, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        if (criptoCurrency.getName() == null || criptoCurrency.getSymbol() == null) {
            ResponseEntity<?> exception = notFoundException.Exception("name y symbol no puede ser vacio");
            return exception;
        }
        List<CriptoCurrency> nameCurrency = criptoCurrencyService.findByName(criptoCurrency.getName());
        List<CriptoCurrency> SymbolCurrency = criptoCurrencyService.findBySymbol(criptoCurrency.getSymbol());

        if(!nameCurrency.isEmpty() && !SymbolCurrency.isEmpty()){
            ResponseEntity<?> exception = notFoundException.Exception("name y Symbol ya existe");
            return exception;
        }

        if(!nameCurrency.isEmpty()){
            ResponseEntity<?> exception = notFoundException.Exception("name ya existe");
            return exception;
        }
        if(!SymbolCurrency.isEmpty()){
            ResponseEntity<?> exception = notFoundException.Exception("Symbol ya existe");
            return exception;
        }
        CriptoCurrency criptoCurrencyCreated = criptoCurrencyService.createCryptocurrency(criptoCurrency);

        if (criptoCurrencyCreated == null) {
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(criptoCurrencyCreated);
         }
        }

}
