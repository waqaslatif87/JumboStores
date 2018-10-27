package com.jumbo.stores.kdtree;

import java.util.Collections;
import java.util.List;

import com.google.common.geometry.S2LatLngRect;
import com.jumbo.stores.model.Location;
import com.jumbo.stores.model.Store;

import lombok.Data;
import lombok.Getter;

/**
 * This class represents a Node of KDTree which contains a Left Node, a Right Node, {@link S2LatLngRect}, List of stores
 * based on its diving step. step.
 * 
 * @author Waqas
 *
 */
@Data
@Getter
public class KDNode {

    private final KDNode left;
    private final KDNode right;
    private final S2LatLngRect rectangle;

    private final int diivingStep;
    private final List<Store> stores;

    public KDNode(KDNode left, KDNode right, S2LatLngRect rectangle, int divingStep, List<Store> stores) {
        this.left = left;
        this.right = right;
        this.rectangle = rectangle;
        this.diivingStep = divingStep;
        if (stores != null) {
            this.stores = Collections.unmodifiableList(stores);
        } else {
            this.stores = null;
        }
    }

    /**
     * Returns the child node based on the node dividing step
     * 
     * @param location {@link Location}
     * @return {@link KDNode}
     */
    public KDNode getChildFor(Location location) {
        if (left != null && right != null) {
            Double pointGeo;
            Double middle;
            if ((diivingStep % 2) == 0) {
                middle = Double.valueOf(rectangle.getCenter().lngDegrees());
                pointGeo = location.getLongtitude();
            } else {
                middle = rectangle.getCenter().latDegrees();
                pointGeo = location.getLatitude();
            }
            if (pointGeo < middle) {
                return left;
            } else {
                return right;
            }
        } else {
            return null;
        }
    }

}
