package com.congestion.calculator.rest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.congestion.calculator.service.CongestionTaxCalculatorService;

@AutoConfigureMockMvc
@ActiveProfiles("junit")
public class CongestionTaxCalculatorControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	CongestionTaxCalculatorService congestionTaxCalculatorService;

	@Test
	public void testGetTax_valid() {
		//Mockito.when(congestionTaxCalculatorService.getTax(Mockito.any())).thenReturn(10);

	}
}
