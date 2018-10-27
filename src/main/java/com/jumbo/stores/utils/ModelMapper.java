package com.jumbo.stores.utils;

import org.springframework.stereotype.Component;

import com.jumbo.stores.dto.StoreDTO;
import com.jumbo.stores.dto.StoreLocationDTO;
import com.jumbo.stores.model.Store;

/**
 * This class is Responsible of mapping entitis to DTO's.
 * 
 * @author Waqas
 *
 */
@Component
public class ModelMapper {

    /**
     * Returns {@link StoreDTO} after Mapping {@link Store} to {@link StoreDTO}
     * 
     * @param store {@link Store}
     * @return {@link StoreDTO}
     */
    public StoreDTO mapStore2DTO(Store store) {

        return new StoreDTO(store.getCity(), store.getPostalCode(), store.getStreet(), store.getStreet2(),
                store.getStreet3(), store.getAddressName(), store.getUuid(), store.getLongitude(), store.getLatitude(),
                store.getComplexNumber(), store.getShowWarningMessage(), store.getTodayOpen(), store.getLocationType(),
                store.getCollectionPoint(), store.getSapStoreID(), store.getTodayClose());
    }

    /**
     * Returns {@link StoreLocationDTO} after Mapping {@link Store} to {@link StoreLocationDTO}
     * 
     * @param store
     * @return
     */
    public StoreLocationDTO mapStoreToStoreLocationDTO(Store store) {

        return new StoreLocationDTO(store.getUuid(), store.getLatitude(), store.getLongitude(), store.getAddressName());
    }

}
