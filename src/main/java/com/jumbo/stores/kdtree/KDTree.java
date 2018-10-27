package com.jumbo.stores.kdtree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.stereotype.Component;

import com.google.common.geometry.S2LatLng;
import com.google.common.geometry.S2LatLngRect;
import com.jumbo.stores.exception.JumboException;
import com.jumbo.stores.model.Location;
import com.jumbo.stores.model.Store;
import com.jumbo.stores.model.StoreDistance;
import com.jumbo.stores.utils.AppUtils;

/**
 * This Class provides methods to build the KDTree and its traversing and searching nodes.
 * 
 * @author Waqas
 *
 */
@Component
public class KDTree {

    private static int STORES_DIVISION_LIMIT = 3;

    /**
     * Build the KDTree and Returns the Root Node({@link KDNode}) of the KDTree
     * 
     * @param stores {@link List} of {@link Store}
     * @param dividingStep
     * @return {@link KDNode}
     * @throws JumboException @{@link JumboException}
     */
    public KDNode buildKDTree(List<Store> stores, int dividingStep) throws JumboException {

        Double minLatitude = Double.MAX_VALUE;
        Double maxLatitude = Double.MIN_VALUE;

        Double minLongitude = Double.MAX_VALUE;
        Double maxLongitude = Double.MIN_VALUE;

        MutablePair<Double, Double> pair = new MutablePair<>();
        for (Store store : stores) {
            Double latitude = store.getLatitude();
            if (AppUtils.getOptional(latitude).isPresent()) {
                setMinMaxLatLng(pair, latitude, minLatitude, maxLatitude);
                minLatitude = pair.left;
                maxLatitude = pair.right;
            }
            Double longitude = store.getLongitude();
            if (AppUtils.getOptional(longitude).isPresent()) {
                setMinMaxLatLng(pair, longitude, minLongitude, maxLongitude);
                minLongitude = pair.left;
                maxLongitude = pair.right;
            }
        }

        if (stores.size() > STORES_DIVISION_LIMIT) {

            List<Store> leftNode = new ArrayList<>();
            List<Store> rightNode = new ArrayList<>();

            Double middle;

            if (dividingStep % 2 == 1) {
                middle = (minLatitude + maxLatitude) / 2;
                stores.forEach((Store store) -> {
                    Double latitude = store.getLatitude();
                    AppUtils.getOptional(latitude).ifPresent(lat -> {
                        if (Double.compare(lat, middle) >= 0) {
                            rightNode.add(store);
                        } else {
                            leftNode.add(store);
                        }
                    });
                });
            } else {
                middle = (minLongitude + maxLongitude) / 2;
                stores.forEach(storeItem -> {
                    Double longitude = storeItem.getLongitude();
                    AppUtils.getOptional(longitude).ifPresent(lo -> {
                        if (Double.compare(lo, middle) >= 0) {
                            rightNode.add(storeItem);
                        } else {
                            leftNode.add(storeItem);
                        }
                    });
                });
            }
            return new KDNode(buildKDTree(leftNode, dividingStep + 1), buildKDTree(rightNode, dividingStep + 1),
                    new S2LatLngRect(S2LatLng.fromDegrees(minLatitude, minLongitude),
                            S2LatLng.fromDegrees(maxLatitude, maxLongitude)),
                    dividingStep, null);
        } else {
            return new KDNode(null, null, getS2LatLngRect(minLatitude, minLongitude, maxLatitude, maxLongitude),
                    dividingStep, stores);
        }
    }

    /**
     * Create instance of {@link S2LatLngRect} by providing two points and return it.
     * 
     * @param minLatitude {@link Double}
     * @param minLongitude {@link Double}
     * @param maxLatitude {@link Double}
     * @param maxLongitude {@link Double}
     * @return {@link S2LatLngRect}
     */
    private static S2LatLngRect getS2LatLngRect(Double minLatitude, Double minLongitude, Double maxLatitude,
            Double maxLongitude) {
        return new S2LatLngRect(S2LatLng.fromDegrees(minLatitude, minLongitude),
                S2LatLng.fromDegrees(maxLatitude, maxLongitude));
    }

    /**
     * Traverse KDTree and Build the {@link TreeSet} with the n number of nearest {@link Store}.
     * 
     * @param root {@link KDNode}
     * @param location {@link Location}
     * @param numberOfNeighbours int
     * @param storesTreeSet {@link TreeSet}
     * @throws JumboException @{@link Link JumboException}
     */
    public void getKNearestNeighbors(KDNode root, Location location, int numberOfNeighbours,
            TreeSet<StoreDistance> storesTreeSet) throws JumboException {
        KDNode child = root.getChildFor(location);
        if (!AppUtils.getOptional(child).isPresent()) {
            List<Store> storeItems = root.getStores();
            storesTreeSet.addAll(
                    storeItems.stream().filter((Store store) -> AppUtils.getOptional(store.getLongitude()).isPresent()
                            && AppUtils.getOptional(store.getLatitude()).isPresent()).map((Store store) -> {
                                try {
                                    return new StoreDistance(store, location);
                                } catch (JumboException e) {
                                    // logger.error(e.getMessage(), e);
                                }
                                return null;
                            }).collect(Collectors.toList()));
            while (storesTreeSet.size() > numberOfNeighbours) {
                storesTreeSet.pollLast();
            }
        } else {
            getKNearestNeighbors(child, location, numberOfNeighbours, storesTreeSet);
            KDNode nextNode;
            if (AppUtils.getOptional(root.getRight()).isPresent() && AppUtils.getOptional(root.getLeft()).isPresent()) {
                if (root.getRight() == child) {
                    nextNode = root.getLeft();
                } else {
                    nextNode = root.getRight();
                }
                if (storesTreeSet.size() < numberOfNeighbours) {
                    getKNearestNeighbors(nextNode, location, numberOfNeighbours, storesTreeSet);
                } else {
                    if (storesTreeSet.last().getDistanFromlocation() > AppUtils
                            .getDistanceFromRectangleToPoint(location, nextNode.getRectangle())) {
                        getKNearestNeighbors(nextNode, location, numberOfNeighbours, storesTreeSet);
                    }
                }
            }
        }

    }

    private void setMinMaxLatLng(MutablePair<Double, Double> pair, Double value, Double min, Double max) {
        if (Double.compare(value, min) < 0) {
            pair.setLeft(value);
        } else {
            pair.setLeft(min);
        }
        if (Double.compare(value, max) > 0) {
            pair.setRight(value);
        } else {
            pair.setRight(max);
        }
    }
}
