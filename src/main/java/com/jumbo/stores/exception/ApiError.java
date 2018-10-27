package com.jumbo.stores.exception;

import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Class that represents Error Object,use to return the Error details.
 * 
 * @author Waqas
 *
 */
@Data
@Getter
@Setter
class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    /**
     * Default constructor
     */
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    /**
     * Constructor to instantiate {@link ApiError}
     * 
     * @param status {@link HttpStatus}
     */
    ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    /**
     * Constructor to instantiate {@link ApiError}
     * 
     * @param status {@link HttpStatus}
     * @param ex {@link Throwable}
     */
    ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = ex.getMessage();
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * Constructor to instantiate {@link ApiError}
     * 
     * @param status {@link HttpStatus}
     * @param message {@link String}
     * @param ex {@link Throwable}
     */
    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}