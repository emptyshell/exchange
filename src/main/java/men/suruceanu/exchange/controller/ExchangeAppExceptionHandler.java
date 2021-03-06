package men.suruceanu.exchange.controller;

import men.suruceanu.exchange.dto.ApiError;
import men.suruceanu.exchange.exception.NotFoundException;
import men.suruceanu.exchange.exception.RegisterEmployeeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExchangeAppExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeAppExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleApiException(NoSuchElementException ex, WebRequest request) {
        logger.error("request {} failed execution with the following exception", request, ex);
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleApiException(IllegalArgumentException ex, WebRequest request) {
        logger.error("request {} failed execution with the following exception", request, ex);
        return handleExceptionInternal(ex, new ApiError(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleApiException(Exception ex, WebRequest request) {
        logger.error("request {} failed execution with the following exception", request, ex);
        return handleExceptionInternal(ex, new ApiError("Something went wrong on server side"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(RegisterEmployeeException.class)
    protected ResponseEntity<Object> handleApiException(RegisterEmployeeException validationException, WebRequest request) {
        logger.error("Validation failed with next message: {}, request: {}, exception: {}", validationException.getMessage(), request, validationException);
        return ResponseEntity.badRequest().body(new ApiError(validationException.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleApiException(AccessDeniedException validationException, WebRequest request) {
        logger.error("Validation failed with next message: {}, request: {}, exception: {}", validationException.getMessage(), request, validationException);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiError(validationException.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleApiException(NotFoundException notFoundException, WebRequest request) {
        logger.error("Validation failed with next message: {}, request: {}, exception: {}", notFoundException.getMessage(), request, notFoundException);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiError(notFoundException.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder errorBuilder = new StringBuilder();
        errorBuilder.append("Request validation failed:");

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorBuilder.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(", ");
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errorBuilder.append(error.getObjectName()).append(": ").append(error.getDefaultMessage()).append(", ");
        }
        errorBuilder.deleteCharAt(errorBuilder.length() - 1);

        ApiError apiError = new ApiError(errorBuilder.toString());
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder errorBuilder = new StringBuilder();
        if (ex.getRootCause() != null) {
            errorBuilder.append(ex.getRootCause().getMessage());
        }
        else {
            errorBuilder.append(ex.getMessage());
        }

        ApiError apiError = new ApiError(errorBuilder.toString());

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }
}
