package com.rafaelbezerra.cwi.currency.manager;

import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;

/**
 * The Currency Manager interface
 * 
 * @author rafaelbezerra
 */
public interface CurrencyManager {

	/**
	 * Finds the currency data based on the currency identifier and the
	 * quotation date
	 * 
	 * @param currency
	 *            the currency identifier
	 * @param quotation
	 *            the quotation date
	 * @return
	 */
	CurrencyDTO findByCurrencyQuotation(String currency, String quotation);

}
