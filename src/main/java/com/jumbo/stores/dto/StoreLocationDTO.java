package com.jumbo.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object which contains the Store UUID address and its Location Latitude and Longitude.
 * 
 * @author Waqas
 *
 */
@Data
@AllArgsConstructor
public class StoreLocationDTO {
    private String uuid;
    private Double latitude;
    private Double Longitude;
    private String addressName;

}
