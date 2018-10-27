package com.jumbo.stores.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom Exception class for handling the exception in application.
 * 
 * @author Waqas
 *
 */
public class JumboException extends RuntimeException {

    private static final long serialVersionUID = -2501472101778549925L;
    private final HttpStatus httpStatus;

    /**
     * Returns the Exception {@link HttpStatus}
     * 
     * @return
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Instantiate the {@link JumboException} by using Error Message {@link String}
     * 
     * @param message {@link String}
     */
    public JumboException(String message) {
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Instantiate the {@link JumboException} by using {@link HttpStatus} and Error Message {@link String}
     * 
     * @param httpStatus
     * @param message
     */
    public JumboException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
