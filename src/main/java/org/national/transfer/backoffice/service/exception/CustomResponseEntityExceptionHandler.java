package org.national.transfer.backoffice.service.exception;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@CommonsLog
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex, WebRequest request) {
        log.error(ex, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(value = {CustomTransferException.class})
    protected ResponseEntity<Object> handleTransferException(RuntimeException ex, WebRequest request) {
        log.error(ex, ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomError(HttpStatus.CONFLICT, ex.getMessage()));
    }
}