package com.jumbo.stores.model.builder;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.stores.model.Store;

import lombok.SneakyThrows;

public class StoreBuilderTest {

	StoreBuilder builder;
	String json;
	ObjectMapper mapper;
	JsonNode jsonNode;

	@Before
	@SneakyThrows({ IOException.class })
	public void init() {
		builder = new StoreBuilder();
		ClassLoader classLoader = getClass().getClassLoader();
		json = IOUtils.toString(classLoader.getResourceAsStream("json/store_deserializer_test.json"));
		mapper = new ObjectMapper();
		jsonNode = mapper.readTree(json);
	}

	@Test
	public void testWithAllFields() {
		Store store = builder.setCity(jsonNode.get("city")).setPostalCode(jsonNode.get("postalCode"))
				.setStreet(jsonNode.get("street")).setStreet2(jsonNode.get("street2"))
				.setStreet3(jsonNode.get("street3")).setAddressName(jsonNode.get("addressName"))
				.setUuid(jsonNode.get("uuid")).setLongitude(jsonNode.get("longitude"))
				.setLatitude(jsonNode.get("latitude")).setComplexNumber(jsonNode.get("complexNumber"))
				.setShowWarningMessage(jsonNode.get("showWarningMessage")).setTodayOpen(jsonNode.get("todayOpen"))
				.setTodayClose(jsonNode.get("todayClose")).setLocationType(jsonNode.get("locationType"))
				.setCollectionPoint(jsonNode.get("collectionPoint")).setSapStoreID(jsonNode.get("sapStoreID")).build();
		Assert.assertEquals("'s Gravendeel", store.getCity());
		Assert.assertEquals("3295 BD", store.getPostalCode());
		Assert.assertEquals("Kerkstraat", store.getStreet());
		Assert.assertEquals("37", store.getStreet2());
		Assert.assertEquals("", store.getStreet3());
		Assert.assertEquals("Jumbo 's Gravendeel Gravendeel Centrum", store.getAddressName());
		Assert.assertEquals("EOgKYx4XFiQAAAFJa_YYZ4At", store.getUuid());
		Assert.assertEquals("4.615551", store.getLongitude().toString());
		Assert.assertEquals("51.778461", store.getLatitude().toString());
		Assert.assertEquals(true, store.getShowWarningMessage());
		Assert.assertEquals(8, store.getTodayOpen().toDateTime().getHourOfDay());
		Assert.assertEquals(20, store.getTodayClose().toDateTime().getHourOfDay());
		Assert.assertEquals(true, store.getCollectionPoint());
		Assert.assertEquals(Integer.valueOf(3605), store.getSapStoreID());
	}

	@Test
	public void testWithNullString() {
		Store store = builder.setCity(null).build();
		Assert.assertNull(store.getCity());

	}

}
