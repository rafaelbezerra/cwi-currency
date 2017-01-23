package com.rafaelbezerra.cwi.currency.controller;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rafaelbezerra.cwi.currency.config.Config;
import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;
import com.rafaelbezerra.cwi.currency.exception.CurrencyValueLessThanZeroException;
import com.rafaelbezerra.cwi.currency.helper.CurrencyHelper;
import com.rafaelbezerra.cwi.currency.service.comparator.BigDecimalComparator;

/**
 * Currency Controller
 * 
 * @author rafaelbezerra
 */
public class CurrencyController {

	private static final Logger LOGGER = Logger.getLogger(CurrencyHelper.getClassName(CurrencyController.class));

	/**
	 * Calculates the currency conversion
	 * 
	 * @param currencyFrom
	 *            the current currency data
	 * @param currencyTo
	 *            the currency data to be converted
	 * @param valueFrom
	 *            the value to be used in conversion
	 * @return the value converted
	 * 
	 * @exception CurrencyValueLessThanZeroExceptionif
	 *                the value to be converted will less than zero, the
	 *                exception will be thrown
	 */
	public BigDecimal currencyCalculation(CurrencyDTO currencyFrom, CurrencyDTO currencyTo, Number valueFrom) {

		BigDecimal valueFromBigDecimal = new BigDecimal(CurrencyHelper.replaceCommasForDots(valueFrom.toString()));

		if (BigDecimalComparator.isLessThanZero(valueFromBigDecimal)) {
			CurrencyValueLessThanZeroException ex = new CurrencyValueLessThanZeroException(
					"The value to be formatted cannot be less than zero");
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			throw ex;
		}

		BigDecimal purchaseRateCurrencyFrom = currencyFrom.getPurchaseRate();
		BigDecimal purchaseRateCurrencyTo = currencyTo.getPurchaseRate();

		BigDecimal valueConverted = valueFromBigDecimal.multiply(purchaseRateCurrencyFrom.divide(purchaseRateCurrencyTo,
				Config.getCalculationScale(), Config.getCalculationRoundingMode()));

		return BigDecimalComparator.truncate(valueConverted, 2);
	}

}
