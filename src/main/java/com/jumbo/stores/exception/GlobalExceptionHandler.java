package com.jumbo.stores.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller Advice to handle Application Exceptions.
 * 
 * @author Waqas
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Method to handle {@link JumboException}
     * 
     * @param ex {@link JumboException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(JumboException.class)
    protected ResponseEntity<Object> handleEntityNotFound(JumboException ex) {
        return buildResponseEntity(new ApiError(ex.getHttpStatus(), ex));
    }

    /**
     * Method to build the {@link ResponseEntity}
     * 
     * @param apiError
     * @return
     */
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
