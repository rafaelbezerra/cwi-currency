package com.rafaelbezerra.cwi.currency.test.helper;

import java.math.BigDecimal;

/**
 * Currency Test Helper class
 * 
 * @author rafaelbezerra
 */
public class CurrencyTestHelper {

	/**
	 * Gets the number of decimal places of a {@link BigDecimal}
	 * 
	 * @param bigDecimal
	 *            to get the number of decimal places
	 * @return int of number of decimal places
	 */
	public int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
		if (bigDecimal != null) {
			String string = bigDecimal.stripTrailingZeros().toPlainString();
			int index = string.indexOf(".");
			return index < 0 ? 0 : string.length() - index - 1;
		}
		throw new IllegalArgumentException("It isn't allowed null parameter.");
	}
	
	/**
	 * Gets the number of decimal places by string of a {@link BigDecimal}
	 * 
	 * @param bigDecimal
	 *            to get the number of decimal places
	 * @return int of number of decimal places
	 */
	public int getNumberOfDecimalPlacesByString(BigDecimal bigDecimal) {
		if (bigDecimal != null) {
			String string = bigDecimal.toString();
			int index = string.indexOf(".");
			return index < 0 ? 0 : string.length() - index - 1;
		}
		throw new IllegalArgumentException("It isn't allowed null parameter.");
	}
}
