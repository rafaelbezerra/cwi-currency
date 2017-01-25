package com.rafaelbezerra.cwi.currency.test.controller;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.rafaelbezerra.cwi.currency.controller.CurrencyController;
import com.rafaelbezerra.cwi.currency.exception.CurrencyValueLessThanZeroException;

public class CurrencyControllerTest {

	private CurrencyController currencyController;

	@Before
	public void init() {
		currencyController = new CurrencyController();

	}

	@Test(expected = CurrencyValueLessThanZeroException.class)
	public void testValueLessThanZeroException() {
		currencyController.currencyCalculation(null, null, new BigDecimal(-1));
	}
	
	

}
