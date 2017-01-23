package com.rafaelbezerra.cwi.currency.service.impl;

import java.math.BigDecimal;

import com.rafaelbezerra.cwi.currency.controller.CurrencyController;
import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;
import com.rafaelbezerra.cwi.currency.manager.CurrencyManager;
import com.rafaelbezerra.cwi.currency.manager.impl.CurrencyManagerCSVImpl;
import com.rafaelbezerra.cwi.currency.service.CurrencyService;

/**
 * Currency Service Implementation of {@link CurrencyService}
 * 
 * @author rafaelbezerra
 */
public class CurrencyServiceImpl implements CurrencyService {

	private CurrencyManager currencyManager = new CurrencyManagerCSVImpl();
	private CurrencyController currencyController = new CurrencyController();

	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation) {
		CurrencyDTO currencyFrom = currencyManager.findByCurrencyQuotation(from, quotation);
		CurrencyDTO currencyTo = currencyManager.findByCurrencyQuotation(to, quotation);

		return currencyController.currencyCalculation(currencyFrom, currencyTo, value);
	}

}
