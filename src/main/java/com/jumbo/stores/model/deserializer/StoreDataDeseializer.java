package com.jumbo.stores.model.deserializer;

import java.io.IOException;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jumbo.stores.model.Store;
import com.jumbo.stores.model.builder.StoreBuilder;

/**
 * Deserialize the Store JSON Object to Java Store Bean.
 * 
 * @author Waqas
 *
 */

public class StoreDataDeseializer extends JsonDeserializer<Store> {

    DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");

    private static final String FIELD_NAME_STREET2 = "street2";
    private static final String FIELD_NAME_STREET = "street";
    private static final String FIELD_NAME_POSTAL_CODE = "postalCode";
    private static final String FIELD_NAME_CITY = "city";
    private static final String FIELD_NAME_STREET3 = "street3";
    private static final String FIELD_NAME_ADRESS_NAME = "addressName";
    private static final String FIELD_NAME_UUID = "uuid";
    private static final String FIELD_NAME_LONGITUDE = "longitude";
    private static final String FIELD_NAME_LATITUDE = "latitude";
    private static final String FIELD_NAME_COMPLEX_NUMBER = "complexNumber";
    private static final String FIELD_NAME_SHOW_WARNING_MESSAGE = "showWarningMessage";
    private static final String FIELD_NAME_TODAY_OPEN = "todayOpen";
    private static final String FIELD_NAME_LOCATION_TYPE = "locationType";
    private static final String FIELD_NAME_COLLECTION_POINT = "collectionPoint";
    private static final String FIELD_NAME_SAP_STORE_ID = "sapStoreID";
    private static final String FIELD_NAME_TODAY_CLOSE = "todayClose";

    @Override
    public Store deserialize(JsonParser jp, DeserializationContext dContext)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        
        return new StoreBuilder().setUuid(node.get(FIELD_NAME_UUID)).setCity(node.get(FIELD_NAME_CITY))
                .setPostalCode(node.get(FIELD_NAME_POSTAL_CODE)).setStreet(node.get(FIELD_NAME_STREET))
                .setStreet2(node.get(FIELD_NAME_STREET2)).setStreet3(node.get(FIELD_NAME_STREET3))
                .setAddressName(node.get(FIELD_NAME_ADRESS_NAME)).setLongitude(node.get(FIELD_NAME_LONGITUDE))
                .setLatitude(node.get(FIELD_NAME_LATITUDE)).setComplexNumber(node.get(FIELD_NAME_COMPLEX_NUMBER))
                .setShowWarningMessage(node.get(FIELD_NAME_SHOW_WARNING_MESSAGE))
                .setTodayOpen(node.get(FIELD_NAME_TODAY_OPEN)).setLocationType(node.get(FIELD_NAME_LOCATION_TYPE))
                .setCollectionPoint(node.get(FIELD_NAME_COLLECTION_POINT))
                .setSapStoreID(node.get(FIELD_NAME_SAP_STORE_ID)).setTodayClose(node.get(FIELD_NAME_TODAY_CLOSE))
                .build();
    }

}