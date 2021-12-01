package com.congestion.calculator.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.congestion.calculator.Vehicle;
import com.congestion.calculator.messaging.TaxRequest;
import com.congestion.calculator.messaging.TaxResponse;

@Service
public class CongestionTaxCalculatorServiceImpl implements CongestionTaxCalculatorService {

	private static Map<String, Integer> tollFreeVehicles = new HashMap<>();

	static {
		tollFreeVehicles.put("Motorcycle", 0);
		tollFreeVehicles.put("Tractor", 1);
		tollFreeVehicles.put("Emergency", 2);
		tollFreeVehicles.put("Diplomat", 3);
		tollFreeVehicles.put("Foreign", 4);
		tollFreeVehicles.put("Military", 5);

	}

	@Override
	public TaxResponse getTax(TaxRequest taxRequest) {
		TaxResponse taxResponse = new TaxResponse();
		Date[] allDates = taxRequest.getDates();
		Date intervalStart = allDates[0];
		int totalFee = 0; 
		for (int i = 0; i < allDates.length; i++) {
			Date date = allDates[i];
			int nextFee = GetTollFee(date, taxRequest.getVehicleType());
			int tempFee = GetTollFee(intervalStart, taxRequest.getVehicleType());

			long diffInMillies = date.getTime() - intervalStart.getTime();
			long minutes = diffInMillies / 1000 / 60;

			if (minutes <= 60) {
				if (totalFee > 0)
					totalFee -= tempFee;
				if (nextFee >= tempFee)
					tempFee = nextFee;
				totalFee += tempFee;
			} else {
				totalFee += nextFee;
			}
		}

		if (totalFee > 60)
			totalFee = 60;
		taxResponse.setTax(totalFee);
		return taxResponse;
	}

	private boolean IsTollFreeVehicle(String vehicle) {
		if (vehicle == null)
			return false;
		String vehicleType = vehicle;
		return tollFreeVehicles.containsKey(vehicleType);
	}

	public int GetTollFee(Date date, String vehicle) {
		if (IsTollFreeDate(date) || IsTollFreeVehicle(vehicle))
			return 0;

		int hour = date.getHours();
		int minute = date.getMinutes();

		if (hour == 6 && minute >= 0 && minute <= 29)
			return 8;
		else if (hour == 6 && minute >= 30 && minute <= 59)
			return 13;
		else if (hour == 7 && minute >= 0 && minute <= 59)
			return 18;
		else if (hour == 8 && minute >= 0 && minute <= 29)
			return 13;
		else if (hour >= 8 && minute >= 30 || hour <= 14 && minute <= 59)
			return 8;
//		else if (hour >= 8 && hour <= 14 && minute >= 30 && minute <= 59)
//			return 8;
		else if (hour == 15 && minute >= 0 && minute <= 29)
			return 13;
		else if (hour == 15 && minute >= 0 || hour == 16 && minute <= 59)
			return 18;
		else if (hour == 17 && minute >= 0 && minute <= 59)
			return 13;
		else if (hour == 18 && minute >= 0 && minute <= 29)
			return 8;
		else
			return 0;
	}

	private Boolean IsTollFreeDate(Date date) {
		int year = date.getYear();
		int month = date.getMonth() + 1;
		int day = date.getDay() + 1;
		int dayOfMonth = date.getDate();

		if (day == Calendar.SATURDAY || day == Calendar.SUNDAY)
			return true;

		if (year == 113) {
			if ((month == 1 && dayOfMonth == 1) || (month == 3 && (dayOfMonth == 28 || dayOfMonth == 29))
					|| (month == 4 && (dayOfMonth == 1 || dayOfMonth == 30))
					|| (month == 5 && (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 9))
					|| (month == 6 && (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 21)) || (month == 7)
					|| (month == 11 && dayOfMonth == 1) || (month == 12
							&& (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 31))) {
				return true;
			}
		}
		return false;
	}

}
