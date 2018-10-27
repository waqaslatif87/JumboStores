package com.jumbo.stores.model;

import java.util.Comparator;

/**
 * Comparator used to sort the list of {@link StoreDistance}
 * 
 * @author Waqas
 *
 */
public class StoreDistanceComparator implements Comparator<StoreDistance> {

    @Override
    public int compare(StoreDistance o1, StoreDistance o2) {
        return o1.getDistanFromlocation().compareTo(o2.getDistanFromlocation());
    }

}
