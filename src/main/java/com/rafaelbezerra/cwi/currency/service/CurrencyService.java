package com.rafaelbezerra.cwi.currency.service;

import java.math.BigDecimal;

/**
 * Currency Service Interface
 * 
 * @author rafaelbezerra
 */
public interface CurrencyService {

	/**
	 * Converts a specific currency to other in a specific quotation date
	 * 
	 * @param from
	 *            the current currency
	 * @param to
	 *            the currency to be convertatted
	 * @param value
	 *            the current currency value
	 * @param quotation
	 *            the quotation date
	 * @return the currency value converted
	 */
	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation);

}
