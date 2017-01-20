package com.rafaelbezerra.cwi.currency.service;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author rafaelbezerra
 */
public interface CurrencyService {

	/**
	 * 
	 * @param from
	 * @param to
	 * @param value
	 * @param quotation
	 * @return
	 */
	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation);
	
}
