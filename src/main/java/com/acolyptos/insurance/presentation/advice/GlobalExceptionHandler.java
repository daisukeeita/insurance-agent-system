package com.acolyptos.insurance.presentation.advice;

import com.acolyptos.insurance.domain.exceptions.EntityAlreadyExistsException;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import com.acolyptos.insurance.domain.exceptions.InvalidRequestBodyException;
import com.acolyptos.insurance.domain.response.ErrorResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleArgumentNotValidException(MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap<>();
    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, errors);

    return errorResponse;
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse handleEntityAlreadyExistsException(EntityAlreadyExistsException exception) {
    Map<String, String> errors = new HashMap<>();
    errors.put("message", exception.getMessage());

    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, errors);

    return errorResponse;
  }

  @ExceptionHandler(EntityDoesNotExistException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleEntityDoesNotExistException(EntityDoesNotExistException exception) {
    Map<String, String> errors = new HashMap<>();
    errors.put("message", exception.getMessage());

    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, errors);

    return errorResponse;
  }

  @ExceptionHandler(InvalidRequestBodyException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleInvalidRequestBodyException(InvalidRequestBodyException exception) {
    Map<String, String> errors = new HashMap<>();
    errors.put("message", exception.getMessage());

    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, errors);

    return errorResponse;
  }
}
