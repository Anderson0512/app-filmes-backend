package com.example.aplication.controller;

import com.example.aplication.exception.BusinessException;
import com.example.aplication.exception.ErrorResponsePersonal;
import com.example.aplication.exception.NoContentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public abstract class BaseRestController {

    @ExceptionHandler(NoContentException.class)
    private ResponseEntity<Void> handlerNoContentException(NoContentException e){
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<ErrorResponsePersonal> handlerBusinessException(BusinessException e){
        return ResponseEntity.unprocessableEntity().body(new ErrorResponsePersonal(e.getMessage()));
    }

    @ExceptionHandler(Throwable.class)
    private ResponseEntity<ErrorResponsePersonal> handlerUnexpectedException(Throwable e){

        e.printStackTrace();

        return ResponseEntity.internalServerError().body(
                new ErrorResponsePersonal("Error unexpected, unknow error!!!"));
    }
}
