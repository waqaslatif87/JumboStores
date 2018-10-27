package com.jumbo.stores.dto;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreDTO {

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
