package com.ciandt.summit.bootcamp2022.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponseMassage> handleAllException(Exception e,
                                                                          WebRequest request){
        ExceptionResponseMassage exceptionResponseMassage = new ExceptionResponseMassage(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponseMassage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedRequestException.class)
    public final ResponseEntity<ExceptionResponseMassage> handleUnauthorizedRequestException(UnauthorizedRequestException e,
                                                                                       WebRequest request){
       ExceptionResponseMassage exceptionResponseMassage = new ExceptionResponseMassage(
              new Date(),
              e.getMessage(),
              request.getDescription(false)
       );
        return new ResponseEntity<>(exceptionResponseMassage, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(InvalidFilterException.class)
    public final ResponseEntity<ExceptionResponseMassage> handleInvalidFilterException(InvalidFilterException e,
                                                                                       WebRequest request){
        ExceptionResponseMassage exceptionResponseMassage = new ExceptionResponseMassage(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponseMassage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidIdException.class)
    public final ResponseEntity<ExceptionResponseMassage> handleInvalidIdException(InvalidIdException e,
                                                                                 WebRequest request){
        ExceptionResponseMassage exceptionResponseMassage = new ExceptionResponseMassage(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponseMassage, HttpStatus.BAD_REQUEST);
    }
}
