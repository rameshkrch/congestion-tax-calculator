package com.congestion.calculator.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.congestion.calculator.Vehicle;
import com.congestion.calculator.messaging.TaxRequest;
import com.congestion.calculator.messaging.TaxResponse;
import com.congestion.calculator.service.CongestionTaxCalculatorService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/tax-service")
@Api(value = "Tax", tags = { "Tax" })
public class CongestionTaxCalculatorController {

	@Autowired
	CongestionTaxCalculatorService congestionTaxCalculatorService;

	@GetMapping("/tax")
	public TaxResponse getTax(@RequestBody TaxRequest taxRequest) {
		return congestionTaxCalculatorService.getTax(taxRequest);
	}

}
