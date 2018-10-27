package com.jumbo.stores.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.stores.dto.StoreDTO;
import com.jumbo.stores.dto.StoreLocationDTO;
import com.jumbo.stores.service.IJumboStoreService;
import com.jumbo.stores.utils.TestUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(value = JumboStoreControllerImpl.class)

public class JumboStoreControllerImplTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IJumboStoreService jumboStoreService;
    ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("unchecked")
    @Test
    public void testGetNearestStores() throws Exception {

        Mockito.when(jumboStoreService.getNearestStoresByLocation(Mockito.anyDouble(), Mockito.anyDouble(),
                Mockito.anyInt())).thenReturn(getStoresList());

        MvcResult result = mvc.perform(get("/api/list/nearest/stores?latitude=52.264417&longitude=4.762433"))
                .andExpect(status().isOk()).andReturn();
        Assert.assertEquals(TestUtils.APPLICATION_JSON_UTF8, result.getResponse().getContentType());
        List<StoreLocationDTO> list = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(5, list.size());

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetAllStores() throws Exception {
        Mockito.when(jumboStoreService.getAllStoresLocation()).thenReturn(getStoresList());

        MvcResult result = mvc.perform(get("/api/list/stores")).andExpect(status().isOk()).andReturn();
        Assert.assertEquals(TestUtils.APPLICATION_JSON_UTF8, result.getResponse().getContentType());
        List<StoreLocationDTO> list = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void testGetStoreDetails() throws Exception {

        Mockito.when(jumboStoreService.getStoreDetails(Mockito.anyString())).thenReturn(getStoreDTO());
        MvcResult result = mvc.perform(get("/api/store/7ewKYx4Xqp0AAAFIHigYwKrH")).andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals(TestUtils.APPLICATION_JSON_UTF8, result.getResponse().getContentType());

        StoreDTO store = mapper.readValue(result.getResponse().getContentAsString(), StoreDTO.class);
        Assert.assertNotNull(store);
        Assert.assertEquals("7ewKYx4Xqp0AAAFIHigYwKrH", store.getUuid());

    }

    /**
     * For mock storelist
     * 
     * @return {@link List} of {@link StoreLocationDTO}
     */
    public List<StoreLocationDTO> getStoresList() {

        List<StoreLocationDTO> stores = new ArrayList<>();
        stores.add(new StoreLocationDTO("abcd", Double.valueOf("51.264417"), Double.valueOf("4.962433"), "address1"));
        stores.add(new StoreLocationDTO("efgh", Double.valueOf("50.364417"), Double.valueOf("4.362433"), "address2"));
        stores.add(new StoreLocationDTO("ijkl", Double.valueOf("53.364417"), Double.valueOf("4.662433"), "address3"));
        stores.add(new StoreLocationDTO("mnop", Double.valueOf("52.764417"), Double.valueOf("5.662433"), "address4"));
        stores.add(new StoreLocationDTO("qrst", Double.valueOf("54.364417"), Double.valueOf("5.262433"), "address5"));

        return stores;
    }

    /**
     * For mock store DTO
     * 
     * @return
     */
    public StoreDTO getStoreDTO() {

        return new StoreDTO("'s-Heerenberg", "7041 JE", "Stadsplein", "", "", "Jumbo 's-Heerenberg Stadsplein",
                "7ewKYx4Xqp0AAAFIHigYwKrH", Double.valueOf("4.962433"), Double.valueOf("51.264417"), 4423, true, null,
                "Supermarkt", true, 1232, null);
    }

}
