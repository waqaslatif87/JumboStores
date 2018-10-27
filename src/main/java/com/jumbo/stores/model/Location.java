package com.jumbo.stores.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents the Location (Latitude and Longitude).
 * 
 * @author Waqas
 *
 */
@Data
@AllArgsConstructor
public class Location {
    private Double latitude;
    private Double longtitude;
}
