package com.jumbo.stores.validator;

import org.junit.Assert;
import org.junit.Test;

import com.jumbo.stores.exception.JumboException;

public class StoreLocationValidatorTest {

	StoreLocationValidator validator = new StoreLocationValidator();

	@Test
	public void testValidateRectangleInputs() {
		boolean valid = validator.validateRectangleInputs(Double.valueOf("51.778461"), Double.valueOf("4.615551"),
				Double.valueOf("52.264417"), Double.valueOf("4.762433"));
		Assert.assertTrue(valid);
	}

	@Test(expected = JumboException.class)
	public void testValicateRectableInputsWithInvalidLatitude() {
		validator.validateRectangleInputs(Double.valueOf("190.778461"), Double.valueOf("4.615551"),
				Double.valueOf("52.264417"), Double.valueOf("4.762433"));
	}

	@Test(expected = JumboException.class)
	public void testValicateRectableInputsWithInvalidLongitude() {
		validator.validateRectangleInputs(Double.valueOf("51.778461"), Double.valueOf("4776.615551"),
				Double.valueOf("52.264417"), Double.valueOf("4.762433"));
	}

	@Test
	public void testValidateLocation() {
		boolean valid = validator.validateLocation(Double.valueOf("52.264417"), Double.valueOf("4.615551"));
		Assert.assertTrue(valid);
	}

	@Test(expected = JumboException.class)
	public void testValidateLocationWithInvalidLatitude() {
		validator.validateLocation(null, Double.valueOf("51.778461"));

	}

	@Test(expected = JumboException.class)
	public void testValidateLocationWithInvalidLongitude() {
		validator.validateLocation(Double.valueOf("52.264417"), null);

	}

}
