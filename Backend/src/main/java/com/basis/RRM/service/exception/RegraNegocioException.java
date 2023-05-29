package com.basis.RRM.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(){
        super();
    }
    public RegraNegocioException(String message){
        super(message);
    }
    public RegraNegocioException(Throwable cause){
        super(cause);
    }
    public RegraNegocioException(String message, Throwable cause){
        super(message, cause);
    }
}
