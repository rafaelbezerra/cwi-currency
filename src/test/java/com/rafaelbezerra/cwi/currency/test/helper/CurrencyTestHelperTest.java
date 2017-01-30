package com.rafaelbezerra.cwi.currency.test.helper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/**
 * Currency Test Helper Test of {@link CurrencyTestHelper}
 * 
 * @author rafaelbezerra
 */
public class CurrencyTestHelperTest {

	private CurrencyTestHelper currencyTestHelper;

	@Before
	public void init() {
		currencyTestHelper = new CurrencyTestHelper();
	}

	/**
	 * Tests the assertion of
	 * {@link CurrencyTestHelper#getNumberOfDecimalPlaces(BigDecimal)}
	 */
	@Test
	public void testAssertionGetNumberOfDecimalPlaces() {
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("0.001")), equalTo(3));
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("0.01")), equalTo(2));
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("0.1")), equalTo(1));
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("1.000")), equalTo(0));
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("1.00")), equalTo(0));
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("1.0")), equalTo(0));
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("1")), equalTo(0));
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("10")), equalTo(0));
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("10.1")), equalTo(1));
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("10.01")), equalTo(2));
		assertThat(currencyTestHelper.getNumberOfDecimalPlaces(new BigDecimal("10.001")), equalTo(3));
	}

	/**
	 * Tests passing null parameter for
	 * {@link CurrencyTestHelper#getNumberOfDecimalPlaces(BigDecimal)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullGetNumberOfDecimalPlaces() {
		currencyTestHelper.getNumberOfDecimalPlaces(null);
	}

	/**
	 * Tests the assertion of
	 * {@link CurrencyTestHelper#getNumberOfDecimalPlaces(BigDecimal)}
	 */
	@Test
	public void testAssertionGetNumberOfDecimalPlacesByString() {
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("0.001")), equalTo(3));
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("0.01")), equalTo(2));
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("0.1")), equalTo(1));
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("1.000")), equalTo(3));
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("1.00")), equalTo(2));
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("1.0")), equalTo(1));
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("1")), equalTo(0));
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("10")), equalTo(0));
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("10.1")), equalTo(1));
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("10.01")), equalTo(2));
		assertThat(currencyTestHelper.getNumberOfDecimalPlacesByString(new BigDecimal("10.001")), equalTo(3));
	}

	/**
	 * Tests passing null parameter for
	 * {@link CurrencyTestHelper#getNumberOfDecimalPlaces(BigDecimal)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullGetNumberOfDecimalPlacesByString() {
		currencyTestHelper.getNumberOfDecimalPlacesByString(null);
	}

}
