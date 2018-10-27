package com.jumbo.stores.model.builder;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.jumbo.stores.model.Store;

/**
 * Builder pattern to construct the Store Object.
 * 
 * @author Waqas
 *
 */
public class StoreBuilder {
    private static final String pattern = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
    DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");

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

    public StoreBuilder setCity(JsonNode city) {
        this.city = getStringValue(city);
        return this;
    }

    public StoreBuilder setPostalCode(JsonNode postalCode) {
        this.postalCode = getStringValue(postalCode);
        return this;
    }

    public StoreBuilder setStreet(JsonNode street) {
        this.street = getStringValue(street);
        return this;
    }

    public StoreBuilder setStreet2(JsonNode street2) {
        this.street2 = getStringValue(street2);
        return this;
    }

    public StoreBuilder setStreet3(JsonNode street3) {
        this.street3 = getStringValue(street3);
        return this;
    }

    public StoreBuilder setAddressName(JsonNode addressName) {
        this.addressName = getStringValue(addressName);
        return this;
    }

    public StoreBuilder setUuid(JsonNode uuid) {
        this.uuid = getStringValue(uuid);
        return this;
    }

    public StoreBuilder setLongitude(JsonNode longitude) {
        this.longitude = getDoubleValue(longitude);
        return this;
    }

    public StoreBuilder setLatitude(JsonNode latitude) {
        this.latitude = getDoubleValue(latitude);
        return this;
    }

    public StoreBuilder setComplexNumber(JsonNode complexNumber) {
        this.complexNumber = getIntegerValue(complexNumber);
        return this;
    }

    public StoreBuilder setShowWarningMessage(JsonNode showWarningMessage) {
        this.showWarningMessage = getBooleanValue(showWarningMessage);
        return this;
    }

    public StoreBuilder setTodayOpen(JsonNode todayOpen) {
        this.todayOpen = getValidTime(todayOpen);
        return this;
    }

    public StoreBuilder setLocationType(JsonNode locationType) {
        this.locationType = getStringValue(locationType);
        return this;
    }

    public StoreBuilder setCollectionPoint(JsonNode collectionPoint) {
        this.collectionPoint = getBooleanValue(collectionPoint);
        return this;
    }

    public StoreBuilder setSapStoreID(JsonNode sapStoreID) {
        this.sapStoreID = getIntegerValue(sapStoreID);
        return this;
    }

    public StoreBuilder setTodayClose(JsonNode todayClose) {
        this.todayClose = getValidTime(todayClose);
        return this;
    }

    public Store build() {
        return new Store(city, postalCode, street, street2, street3, addressName, uuid, longitude, latitude,
                complexNumber, showWarningMessage, todayOpen, locationType, collectionPoint, sapStoreID, todayClose);
    }

    private String getStringValue(JsonNode jsonNode) {
        if (jsonNode == null || jsonNode.isNull() || !jsonNode.isTextual()) {
            return null;
        }
        return jsonNode.textValue();

    }

    private Double getDoubleValue(JsonNode jsonNode) {

        if (jsonNode != null && !jsonNode.isNull()
                && (jsonNode.isDouble() || (jsonNode.isTextual() && NumberUtils.isCreatable(jsonNode.textValue())))) {
            return jsonNode.asDouble();
        }

        return null;
    }

    private Integer getIntegerValue(JsonNode jsonNode) {

        if (jsonNode != null && !jsonNode.isNull()
                && (jsonNode.isDouble() || (jsonNode.isTextual() && NumberUtils.isCreatable(jsonNode.textValue())))) {

            return jsonNode.asInt();

        }
        return null;

    }

    private Boolean getBooleanValue(JsonNode jsonNode) {
        if (jsonNode == null || jsonNode.isNull() || !jsonNode.isBoolean()) {
            return null;
        }
        return jsonNode.asBoolean();
    }

    private DateTime getValidTime(JsonNode jsonNode) {
        if (jsonNode == null || jsonNode.isNull() || !jsonNode.isTextual()) {
            return null;
        }
        String time = jsonNode.asText();
        if (time.matches(pattern)) {
            return formatter.parseDateTime(time);
        } else {
            return null;
        }
    }

}
