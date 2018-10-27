package com.jumbo.stores.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jumbo.stores.model.deserializer.StoreDataDeseializer;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represent a Jumbo Store, It provides the information about the store specific to its Address,
 * Location(Latitude and Longitude) and its opening and closing time.
 * 
 * @author Waqas
 *
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = StoreDataDeseializer.class)

public class Store {
    private String city;
    private String postalCode;
    private String street;
    private String street2;
    private String street3;
    private String addressName;
    private String uuid;
    private Double longitude;
    private Double latitude;

    private Integer complexNumber;
    private Boolean showWarningMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private DateTime todayOpen;
    private String locationType;
    private Boolean collectionPoint;
    private Integer sapStoreID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private DateTime todayClose;

}
