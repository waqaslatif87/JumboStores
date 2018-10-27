package com.jumbo.stores.model.deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.stores.model.Store;

import lombok.SneakyThrows;

public class StoreDataDeseializerTest {

	String json;

	private ObjectMapper mapper;
	private StoreDataDeseializer deserializer;

	DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm");

	@Before
	@SneakyThrows({ IOException.class })
	public void init() {

		mapper = new ObjectMapper();
		deserializer = new StoreDataDeseializer();

		ClassLoader classLoader = getClass().getClassLoader();
		json = IOUtils.toString(classLoader.getResourceAsStream("json/store_deserializer_test.json"));

	}

	@Test
	@SneakyThrows({ JsonParseException.class, IOException.class })
	public void testDeserializeJsonParserDeserializationContext() {
		InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
		JsonParser parser = mapper.getFactory().createParser(stream);
		DeserializationContext ctxt = mapper.getDeserializationContext();
		Store store = deserializer.deserialize(parser, ctxt);
		Assert.assertEquals("EOgKYx4XFiQAAAFJa_YYZ4At", store.getUuid());
		Assert.assertEquals("'s Gravendeel", store.getCity());
		Assert.assertEquals("Jumbo 's Gravendeel Gravendeel Centrum", store.getAddressName());
		Assert.assertEquals("4.615551", store.getLongitude().toString());
		Assert.assertEquals("51.778461", store.getLatitude().toString());
		Assert.assertEquals("SupermarktPuP", store.getLocationType());
		Assert.assertEquals(Integer.valueOf(3605), store.getSapStoreID());
		Assert.assertEquals("3295 BD", store.getPostalCode());
		Assert.assertEquals("Kerkstraat", store.getStreet());
		Assert.assertEquals("37", store.getStreet2());
		Assert.assertEquals("", store.getStreet3());
		Assert.assertEquals(true, store.getShowWarningMessage());
		Assert.assertEquals(Integer.valueOf(33249), store.getComplexNumber());
		Assert.assertEquals("20:00", store.getTodayClose().toString("HH:mm"));
		Assert.assertEquals("08:00", store.getTodayOpen().toString("HH:mm"));
		Assert.assertEquals(true, store.getCollectionPoint());

	}

}
