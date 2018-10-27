package com.jumbo.stores.exception;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandlerTest {

	@Autowired
	GlobalExceptionHandler instance = new GlobalExceptionHandler();

	@Test
	public void testHandleEntityNotFound() {

		JumboException je = new JumboException(HttpStatus.BAD_REQUEST, "invalid request.");

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "invalid request.", je);

		ResponseEntity<Object> error = instance.handleEntityNotFound(je);

		Assert.assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());

		Assert.assertEquals(apiError.getDebugMessage(), ((ApiError) error.getBody()).getDebugMessage());
		Assert.assertEquals(apiError.getMessage(), ((ApiError) error.getBody()).getMessage());
		Assert.assertEquals(apiError.getStatus(), ((ApiError) error.getBody()).getStatus());

	}

}
