package com.jumbo.stores.exception;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class ApiErrorTest {

	@Test
	public void testApiErrorHttpStatus() {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, apiError.getStatus());

	}

	@Test
	public void testApiErrorHttpStatusThrowable() {

		JumboException ex = new JumboException("Invalid data.");
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex);
		Assert.assertEquals("Invalid data.", apiError.getMessage());
		Assert.assertEquals(HttpStatus.BAD_REQUEST, apiError.getStatus());
		Assert.assertEquals("Invalid data.", apiError.getDebugMessage());
	}

	@Test
	public void testApiErrorHttpStatusStringThrowable() {
		JumboException ex = new JumboException("error occured.");
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error.", ex);

		Assert.assertEquals("internal server error.", apiError.getMessage());
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, apiError.getStatus());
		Assert.assertEquals("error occured.", apiError.getDebugMessage());

	}

}
