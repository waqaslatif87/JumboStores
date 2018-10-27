package com.jumbo.stores.utils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jumbo.stores.dto.StoreDTO;
import com.jumbo.stores.dto.StoreLocationDTO;
import com.jumbo.stores.model.Store;

public class ModelMapperTest {
	DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");

	ModelMapper mapper = new ModelMapper();

	private Store store;

	@Before
	public void init() {
		store = new Store("s Gravendeel", "3295 BD", "Kerkstraat", "37", "", "Jumbo 's Gravendeel Gravendeel Centrum",
				"EOgKYx4XFiQAAAFJa_YYZ4At", Double.valueOf(4.615551D), Double.valueOf(51.778461D),
				Integer.valueOf(33249), Boolean.valueOf(true), formatter.parseDateTime("08:00"), "SupermarktPuP",
				Boolean.valueOf(true), Integer.valueOf(3605), formatter.parseDateTime("20:00"));
	}

	@Test
	public void testMapStore2DTO() {

		StoreDTO storeDto = mapper.mapStore2DTO(store);

		Assert.assertEquals(store.getCity(), storeDto.getCity());
		Assert.assertEquals(store.getAddressName(), storeDto.getAddressName());
		Assert.assertEquals(store.getLocationType(), storeDto.getLocationType());
		Assert.assertEquals(store.getPostalCode(), storeDto.getPostalCode());
		Assert.assertEquals(store.getStreet(), storeDto.getStreet());
		Assert.assertEquals(store.getStreet2(), storeDto.getStreet2());
		Assert.assertEquals(store.getStreet3(), storeDto.getStreet3());
		Assert.assertEquals(store.getUuid(), storeDto.getUuid());
		Assert.assertEquals(store.getCollectionPoint(), storeDto.getCollectionPoint());
		Assert.assertEquals(store.getComplexNumber(), storeDto.getComplexNumber());
		Assert.assertEquals(store.getLatitude(), storeDto.getLatitude());
		Assert.assertEquals(store.getLongitude(), storeDto.getLongitude());
		Assert.assertEquals(store.getSapStoreID(), storeDto.getSapStoreID());
		Assert.assertEquals(store.getShowWarningMessage(), storeDto.getShowWarningMessage());
		Assert.assertEquals(store.getTodayClose(), storeDto.getTodayClose());
		Assert.assertEquals(store.getTodayOpen(), storeDto.getTodayOpen());

	}

	@Test
	public void testMapStoreToStoreLocationDTO() {

		StoreLocationDTO locationDTO = mapper.mapStoreToStoreLocationDTO(store);

		Assert.assertEquals(store.getAddressName(), locationDTO.getAddressName());
		Assert.assertEquals(store.getUuid(), locationDTO.getUuid());
		Assert.assertEquals(store.getLatitude(), locationDTO.getLatitude());
		Assert.assertEquals(store.getLongitude(), locationDTO.getLongitude());

	}

}
