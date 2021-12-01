package com.congestion.calculator.messaging;

import java.util.Date;

import com.congestion.calculator.Vehicle;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

public class TaxRequest {

	private String vehicleType;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Amsterdam")
	private Date[] dates;

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
	}

}
