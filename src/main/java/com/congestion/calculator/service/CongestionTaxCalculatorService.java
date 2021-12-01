package com.congestion.calculator.service;

import java.util.Date;

import com.congestion.calculator.Vehicle;
import com.congestion.calculator.messaging.TaxRequest;
import com.congestion.calculator.messaging.TaxResponse;

public interface CongestionTaxCalculatorService {

	TaxResponse getTax(TaxRequest taxRequest);
}
