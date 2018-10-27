package com.jumbo.stores.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.stores.dto.StoreDTO;
import com.jumbo.stores.dto.StoreLocationDTO;
import com.jumbo.stores.exception.JumboException;
import com.jumbo.stores.kdtree.KDNode;
import com.jumbo.stores.kdtree.KDTree;
import com.jumbo.stores.model.Store;
import com.jumbo.stores.model.StoreDistance;
import com.jumbo.stores.utils.ModelMapper;
import com.jumbo.stores.validator.StoreLocationValidator;

@SpringBootTest(classes = { JumboStoreServiceImpl.class, StoreLocationValidator.class, ObjectMapper.class,
        ModelMapper.class, KDTree.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = { "stores.json.file.path=/json/stores_test.json" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JumboStoreServiceImplTest {

    @Autowired
    IJumboStoreService service;

    @Mock
    TreeSet<StoreDistance> treeSet;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }

    /**
     * Test the Service is initialised with the required store data.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void test1InitMethod() {

        List<Store> stores = (List<Store>) ReflectionTestUtils.getField(service, "jumboStores");
        KDNode rootNode = (KDNode) ReflectionTestUtils.getField(service, "rootNode");
        Map<String, Store> storesMap = (Map<String, Store>) ReflectionTestUtils.getField(service, "storesMap");
        Assert.assertNotNull(stores);
        Assert.assertNotNull(rootNode);
        Assert.assertNotNull(storesMap);
        Assert.assertEquals(41, stores.size());

    }

    @Test
    /**
     * Test service method to get 5 Nearest Stores by location from given longitude and latitude. Latitude =
     * 52.792848536279344 and Longitude = 4.7966978775024245
     */
    public void test2GetNearestStoresByLocation() {
        List<StoreLocationDTO> nearestStores = service.getNearestStoresByLocation(Double.valueOf("52.79367895541187"),
                Double.valueOf("4.7637388931274245"), 5);

        Assert.assertNotNull(nearestStores);
        Assert.assertEquals(5, nearestStores.size());

        List<String> nearestStoresUuid = Arrays.asList("2AMKYx4X30YAAAFIxdAYwKxK", "gssKYx4XJwoAAAFbn.BMqPTb",
                "iGEKYx4XCWEAAAFIUL0YwKxK", "mfoKYx4X7kYAAAFIKCYYwKxK", "ji4KYx4X49UAAAFIVNsYwKxJ");

        Assert.assertEquals(5, nearestStores.size());

        nearestStores.forEach(store -> {
            Assert.assertTrue("Store with UUID : " + store.getUuid() + " and Address Name : " + store.getAddressName()
                    + " is not a nearest Neighbour", nearestStoresUuid.contains(store.getUuid()));
        });

    }

    /**
     * Test service get All stores
     */
    @Test
    public void test3GetAllStoresLocation() {
        List<StoreLocationDTO> stores = service.getAllStoresLocation();
        Assert.assertNotNull(stores);
        Assert.assertEquals(41, stores.size());
    }

    /**
     * Test service method to get details of store by UUID.
     * 
     * { "city": "Wommels", "postalCode": "8731 AX", "street": "Terp", "street2": "11", "street3": "", "addressName":
     * "Jumbo Wommels Terp", "uuid": "iGEKYx4XCWEAAAFIUL0YwKxK", "longitude": "5.588159", "latitude": "53.108561",
     * "complexNumber": "32126", "showWarningMessage": true, "todayOpen": "08:00", "locationType": "SupermarktPuP",
     * "collectionPoint": true, "sapStoreID": "6507", "todayClose": "20:00" }
     */
    @Test
    public void test4GetStoreDetails() {
        StoreDTO storeDto = service.getStoreDetails("iGEKYx4XCWEAAAFIUL0YwKxK");

        Assert.assertNotNull(storeDto);
        Assert.assertEquals("Jumbo Wommels Terp", storeDto.getAddressName());
        Assert.assertEquals(Double.valueOf("5.588159"), storeDto.getLongitude());
        Assert.assertEquals(Double.valueOf("53.108561"), storeDto.getLatitude());
        Assert.assertEquals("SupermarktPuP", storeDto.getLocationType());
        Assert.assertEquals("iGEKYx4XCWEAAAFIUL0YwKxK", storeDto.getUuid());

    }

    /**
     * Test service method to get details of store for unknown store uuid.
     */
    @Test(expected = JumboException.class)
    public void test5ExceptionGetStoreDetails() {
        service.getStoreDetails("abcd");

    }

    /**
     * Test service method to get All stores when the Array is null.
     */
    @Test(expected = JumboException.class)
    public void test6ExceptionGetAllStoresLocation() {

        ReflectionTestUtils.setField(service, "jumboStores", null);
        service.getAllStoresLocation();
    }

    /**
     * Test service method to get details of store if the Stores Map is null.
     */
    @Test(expected = JumboException.class)
    public void test7ExceptionGetStoreDetails() {
        ReflectionTestUtils.setField(service, "storesMap", null);
        service.getStoreDetails("iGEKYx4XCWEAAAFIUL0YwKxK");

    }

}
