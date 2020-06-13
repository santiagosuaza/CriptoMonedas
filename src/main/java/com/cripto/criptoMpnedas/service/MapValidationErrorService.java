package com.cripto.criptoMpnedas.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MapValidationErrorService {
    public ResponseEntity<?> MapValidationService(BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errorMap = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);

        }
        return null;
    }

}
