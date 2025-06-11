package org.example.utils;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<ApiResponse<Object>>> handleResponseStatusException(ResponseStatusException ex) {
        return ApiResponse.error(ex.getReason(), HttpStatus.valueOf(ex.getStatusCode().value()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<ApiResponse<Object>>> handleValidationException(WebExchangeBindException ex){
        Map<String, String> errors = ex.getFieldErrors().stream().collect(Collectors.toMap(
                FieldError::getField,
                FieldError::getDefaultMessage,
                (existing, replacement) ->existing
        ));
        String errorMessages = "Validations failed for: " + String.join(",", errors.keySet());
        return ApiResponse.error("Validations failed", HttpStatus.BAD_REQUEST)
                .map(response->{
                    StringBuilder errorMessage = new StringBuilder();
                    response.getBody().setData(errors);
                    return response;
                });
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiResponse<Object>>> handleGeneralException(Exception ex){
        return ApiResponse.error("Unexpected error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
