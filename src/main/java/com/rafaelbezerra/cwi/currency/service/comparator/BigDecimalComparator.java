package com.rafaelbezerra.cwi.currency.service.comparator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal Comparator, for genreal comparations
 * 
 * @author rafaelbezerra
 */
public class BigDecimalComparator {

	public static BigDecimal truncate(BigDecimal value, int precision) {
		return value.setScale(precision, RoundingMode.DOWN);
	}

	public static boolean isInScale(BigDecimal value, int precision) {
		return isEqual(truncate(value, precision), value);
	}

	public static boolean isInteger(BigDecimal value) {
		return isInScale(value, 0);
	}

	public static boolean isEqualOrLessThan(BigDecimal value1, BigDecimal value2) {
		return value1.compareTo(value2) != 1;
	}

	public static boolean isEqualOrBiggerThan(BigDecimal value1, BigDecimal value2) {
		return value1.compareTo(value2) != -1;
	}

	public static boolean isDifferent(BigDecimal value1, BigDecimal value2) {
		return value1.compareTo(value2) != 0;
	}

	public static boolean isBiggerThan(BigDecimal value1, BigDecimal value2) {
		return value1.compareTo(value2) == 1;
	}

	public static boolean isEqual(BigDecimal value1, BigDecimal value2) {
		return value1.compareTo(value2) == 0;
	}

	public static boolean isLessThan(BigDecimal value1, BigDecimal value2) {
		return value1.compareTo(value2) == -1;
	}

	public static boolean isEqualOrBiggerThanZero(BigDecimal value) {
		return isEqualOrBiggerThan(value, BigDecimalValues.ZERO);
	}

	public static boolean isEqualOrLessThanZero(BigDecimal value) {
		return isEqualOrLessThan(value, BigDecimalValues.ZERO);
	}

	public static boolean isBiggerThanZero(BigDecimal value) {
		return isBiggerThan(value, BigDecimalValues.ZERO);
	}

	public static boolean isLessThanZero(BigDecimal value) {
		return isLessThan(value, BigDecimalValues.ZERO);
	}

	public static boolean isZero(BigDecimal value) {
		return isEqual(value, BigDecimalValues.ZERO);
	}

}
