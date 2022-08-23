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
    public final ResponseEntity<ExceptionResponseMessage> handleAllException(Exception e,
                                                                             WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedRequestException.class)
    public final ResponseEntity<ExceptionResponseMessage> handleUnauthorizedRequestException(UnauthorizedRequestException e,
                                                                                             WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidFilterException.class)
    public final ResponseEntity<ExceptionResponseMessage> handleInvalidFilterException(InvalidFilterException e,
                                                                                       WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidIdException.class)
    public final ResponseEntity<ExceptionResponseMessage> handleInvalidIdException(InvalidIdException e,
                                                                                   WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyListException.class)
    public final ResponseEntity<ExceptionResponseMessage> handleEmptyListException(EmptyListException e,
                                                                                   WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.NO_CONTENT.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MusicNotFoundException.class)
    public final ResponseEntity<ExceptionResponseMessage> handleMusicNotFoundException(MusicNotFoundException e,
                                                                                   WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.BAD_REQUEST);
    }
}
