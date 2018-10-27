package com.jumbo.stores.service;

import java.util.List;

import com.jumbo.stores.dto.StoreDTO;
import com.jumbo.stores.dto.StoreLocationDTO;

/**
 * Jumbo Store service which interact with the data and provides the required information related to the Jumbo Stores.
 * 
 * @author Waqas
 *
 */
public interface IJumboStoreService {

    /**
     * Returns the List of nearest Jumbo Store {@link StoreLocationDTO} from given latitude and longitude.
     * 
     * @param latitude {@link Double}
     * @param longitude {@link Double}
     * @return {@link List}
     */
    public List<StoreLocationDTO> getNearestStoresByLocation(Double latitude, Double longitude, Integer numNeighbour);

    /**
     * Returns the list of all Jumbo Stores {@link StoreLocationDTO}
     * 
     * @return {@link List}
     */
    public List<StoreLocationDTO> getAllStoresLocation();

    /**
     * returns the details of Store Details for given Store UUID.
     * 
     * @param uuid
     * @return {@link StoreDTO}
     */
    public StoreDTO getStoreDetails(String uuid);

}
