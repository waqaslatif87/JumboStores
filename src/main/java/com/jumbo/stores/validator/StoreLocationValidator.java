package com.jumbo.stores.validator;

import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.jumbo.stores.exception.JumboException;
import com.jumbo.stores.utils.AppUtils;

/**
 * Validator Class to validates the input specific to the Store location.
 * 
 * @author Waqas
 *
 */
@Component
public class StoreLocationValidator {

    private static final Logger logger = LogManager.getLogger(StoreLocationValidator.class);

    public static Double MIN_LATITUDE_VALUE = -85D;
    public static Double MAX_LATITUDE_VALUE = 85D;

    public static Double MIN_LONGITUDE_VALUE = -180D;
    public static Double MAX_LONGITUDE_VALUE = 180D;

    private static Predicate<Double> latitudeValidationPredicate = (d) -> d == null || d < MIN_LATITUDE_VALUE
            || d > MAX_LATITUDE_VALUE;

    private static Predicate<Double> longitudeValidationPredicate = (d) -> d == null || d < MIN_LONGITUDE_VALUE
            || d > MAX_LONGITUDE_VALUE;

    /**
     * validate both rectangle minimum and maximum latitude and longitude.
     * 
     * @param minLatitude
     * @param maxLatitude
     * @param minLongitude
     * @param maxLongitude
     * @return true if points are valid otherwise throw {@link JumboException}
     */
    public boolean validateRectangleInputs(Double minLatitude, Double maxLatitude, Double minLongitude,
            Double maxLongitude) {

        if (latitudeValidationPredicate.test(minLatitude) || latitudeValidationPredicate.test(maxLatitude)) {
            logger.error("Invalid latitude values. min latitude = {} , max latitude = {}", minLatitude, maxLatitude);
            throw new JumboException(HttpStatus.BAD_REQUEST, "Invalid latitude value.");
        }

        if (longitudeValidationPredicate.test(minLongitude) || longitudeValidationPredicate.test(maxLongitude)) {
            logger.error("Invalid latitude values. min longitude = {} , max longitude = {}", minLongitude,
                    maxLongitude);
            throw new JumboException(HttpStatus.BAD_REQUEST, "Invalid longitude value.");
        }

        return true;
    }

    /**
     * Validate Location latitude and longitude.
     * 
     * @param latitude
     * @param longitude
     * @return true if the latitude and longitude of location is valid otherwise return {@link JumboException}
     */
    public boolean validateLocation(Double latitude, Double longitude) {
        if (!(AppUtils.getOptional(latitude).isPresent() && AppUtils.getOptional(longitude).isPresent())) {

            logger.error("Invalid location with latitude = {} and longitude = {}", latitude, longitude);

            throw new JumboException(HttpStatus.BAD_REQUEST,
                    "Invalid location with latitude : " + latitude + " and longitude" + longitude);
        }
        return true;
    }

}
