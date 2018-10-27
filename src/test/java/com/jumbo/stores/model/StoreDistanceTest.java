package com.jumbo.stores.model;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;

import com.jumbo.stores.utils.AppUtils;

public class StoreDistanceTest {

	DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");

	@Test
	public void testStoreDistance() {

		Store store = new Store("s Gravendeel", "3295 BD", "Kerkstraat", "37", "",
				"Jumbo 's Gravendeel Gravendeel Centrum", "EOgKYx4XFiQAAAFJa_YYZ4At", Double.valueOf(4.615551D),
				Double.valueOf(51.778461D), Integer.valueOf(33249), Boolean.valueOf(true),
				formatter.parseDateTime("08:00"), "SupermarktPuP", Boolean.valueOf(true), Integer.valueOf(3605),
				formatter.parseDateTime("20:00"));

		Location location = new Location(Double.valueOf(54.778461D), Double.valueOf(6.615551D));

		StoreDistance sd = new StoreDistance(store, location);

		Assert.assertEquals(store, sd.getStore());

		Double distance = AppUtils.getDistanceBetweenTwoPoints(store.getLatitude(), store.getLongitude(),
				location.getLatitude(), location.getLongtitude());

		Assert.assertEquals(distance, sd.getDistanFromlocation());

	}

}
