package com.jumbo.stores.model;

import com.jumbo.stores.exception.JumboException;
import com.jumbo.stores.utils.AppUtils;

import lombok.Data;
import lombok.Getter;

/**
 * This class is used to calculate the store distance to the given location, It contains the store and Its Distance from
 * the given location.
 * 
 * @author Waqas
 *
 */
@Data
@Getter
public class StoreDistance {

    private final Double distanFromlocation;
    private final Store store;

    /**
     * Instantiate the {@link StoreDistance} by providing the Store and the location from which its distance will be
     * calculated.
     * 
     * @param store {@link Store}
     * @param location {@link Location}
     * @throws JumboException @{@link JumboException}
     */
    public StoreDistance(Store store, Location location) throws JumboException {

        this.store = store;

        Double latitude = store.getLatitude();
        Double longitude = store.getLongitude();

        distanFromlocation = AppUtils.getDistanceBetweenTwoPoints(latitude, longitude, location.getLatitude(),
                location.getLongtitude());

    }

}
