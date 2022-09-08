package com.ciandt.summit.bootcamp2022.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
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
    @ExceptionHandler(PlaylistNotFoundException.class)
    public final ResponseEntity<ExceptionResponseMessage> handlePlaylistNotFoundException(PlaylistNotFoundException e,
                                                                                       WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PayloadInvalidException.class)
    public final ResponseEntity<ExceptionResponseMessage> handlePayloadInvalidException(PayloadInvalidException e,
                                                                                           WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MusicLimitReachedException.class)
    public final ResponseEntity<ExceptionResponseMessage> handleMusicLimitReachedException(MusicLimitReachedException e,
                                                                                          WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponseMessage> handleUserNotFoundException(UserNotFoundException e,
                                                                                           WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MusicAlreadyExistInPlaylist.class)
    public final ResponseEntity<ExceptionResponseMessage> handleUserNotFoundException(MusicAlreadyExistInPlaylist e,
                                                                                      WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                e.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exceptionResponseMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                "Formato JSON inválido, consultar documentação",
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity(exceptionResponseMessage, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }
}
