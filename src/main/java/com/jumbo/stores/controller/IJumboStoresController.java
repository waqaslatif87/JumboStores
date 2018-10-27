package com.jumbo.stores.controller;

import java.util.List;

import com.jumbo.stores.dto.StoreDTO;
import com.jumbo.stores.dto.StoreLocationDTO;

public interface IJumboStoresController {
    /**
     * Returnes the 5 nearest Jumbo Stores from given Latitude and Longitude.
     * 
     * @param latitide {@link Double}
     * @param longitude {@link Double}
     * @return {@link List} of {@link StoreLocationDTO}
     */
    public List<StoreLocationDTO> getNearestStores(Double latitide, Double longitude, Integer numNeighbour);

    /**
     * Returns all jumbo stores.
     * 
     * @return {@link List} of {@link StoreLocationDTO}
     */
    public List<StoreLocationDTO> getAllStores();

    /**
     * Returns the details of the Store for given store UUID.
     * 
     * @param storeUuid {@link String}
     * @return {@link StoreDTO}
     */
    public StoreDTO getStoreDetails(String storeUuid);
}
