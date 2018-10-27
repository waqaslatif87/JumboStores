package com.jumbo.stores.utils;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.geometry.S2LatLng;
import com.google.common.geometry.S2LatLngRect;
import com.jumbo.stores.model.Location;

public class AppUtilsTest {

	@Test
	public void testGetOptional() {

		String s = "test";

		Optional<String> op = AppUtils.getOptional(s);
		Assert.assertTrue(op.isPresent());
		Assert.assertEquals(s, op.get());

	}

	@Test
	public void testGetDistanceFromRectangleToPoint() {

		S2LatLngRect rect = new S2LatLngRect(
				S2LatLng.fromDegrees(Double.valueOf("51.778461"), Double.valueOf("4.615551")),
				S2LatLng.fromDegrees(Double.valueOf("52.264417"), Double.valueOf("4.762433")));

		Location loc = new Location(Double.valueOf("51.478461"), Double.valueOf("4.655551"));

		Double val = AppUtils.getDistanceFromRectangleToPoint(loc, rect);

		Assert.assertEquals(Double.valueOf("0.005235987755982885"), val);
	}

	@Test
	public void testGetDistanceBetweenTwoPoints() {
		Double returnedValue = AppUtils.getDistanceBetweenTwoPoints(Double.valueOf("51.778461"),
				Double.valueOf("4.615551"), Double.valueOf("52.264417"), Double.valueOf("4.762433"));

		Assert.assertEquals(Double.valueOf("0.008626988230939025"), returnedValue);
	}

}
