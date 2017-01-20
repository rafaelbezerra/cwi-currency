package com.rafaelbezerra.cwi.currency.controller;

import java.math.BigDecimal;

import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;
import com.rafaelbezerra.cwi.currency.util.CurrencyUtil;

public class CurrencyController {

	public BigDecimal currencyCalculation(CurrencyDTO currencyFrom, CurrencyDTO currencyTo, Number valueFrom) {

		BigDecimal valueFromBigDecimal = new BigDecimal(valueFrom.toString());
		BigDecimal purchaseRateCurrencyFrom = currencyFrom.getPurchaseRate();
		BigDecimal purchaseRateCurrencyTo = currencyTo.getPurchaseRate();

		return valueFromBigDecimal.multiply(purchaseRateCurrencyFrom.divide(purchaseRateCurrencyTo,
				CurrencyUtil.CALCULATION_SCALE, CurrencyUtil.CALCULATION_ROUNDING_MODE));
	}

}
