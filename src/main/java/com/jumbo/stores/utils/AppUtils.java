package com.jumbo.stores.utils;

import java.util.Optional;

import com.google.common.geometry.S1Angle;
import com.google.common.geometry.S2LatLng;
import com.google.common.geometry.S2LatLngRect;
import com.jumbo.stores.model.Location;

/**
 * Utility class for the Application having utility methods.
 * 
 * @author Waqas
 *
 */
public class AppUtils {

    /**
     * Returns the Optional {@link Optional} object of given Type.
     * 
     * @param value of any Type.
     * @return {@link Optional}
     */
    public static <T> Optional<T> getOptional(T value) {
        return Optional.ofNullable(value);
    }

    /**
     * Return the minimum distance (measured along the surface of the sphere)from a given point to the rectangle (both
     * its boundary and its interior).The latLng must be valid.
     * 
     * @param location {@link Location}
     * @param rect {@link S2LatLngRect}
     * @return {@link Double}
     */
    public static Double getDistanceFromRectangleToPoint(Location location, S2LatLngRect rect) {
        S1Angle angle = rect.getDistance(S2LatLng.fromDegrees(location.getLatitude(), location.getLongtitude()));
        return angle.radians();
    }

    /**
     * Returns the Distance between two points.
     * 
     * @param lat1 {@link Double}
     * @param lon1 {@link Double}
     * @param lat2 {@link Double}
     * @param lon2 {@link Double}
     * @return {@link Double}
     */
    public static Double getDistanceBetweenTwoPoints(Double lat1, Double lon1, Double lat2, Double lon2) {
        return S2LatLng.fromDegrees(lat1, lon1).getDistance(S2LatLng.fromDegrees(lat2, lon2)).radians();
    }

}
