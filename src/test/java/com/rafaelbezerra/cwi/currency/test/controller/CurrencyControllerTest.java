package com.rafaelbezerra.cwi.currency.test.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.rafaelbezerra.cwi.currency.config.Config;
import com.rafaelbezerra.cwi.currency.controller.CurrencyController;
import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;
import com.rafaelbezerra.cwi.currency.exception.CurrencyValueLessThanZeroException;
import com.rafaelbezerra.cwi.currency.service.comparator.BigDecimalComparator;
import com.rafaelbezerra.cwi.currency.service.comparator.BigDecimalValues;
import com.rafaelbezerra.cwi.currency.test.helper.CurrencyTestHelper;
import com.rafaelbezerra.cwi.currency.test.template.CurrencyDTOTemplateLoader;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * Currency Controller Test for {@link CurrencyController}
 * 
 * @author rafaelbezerra
 */
public class CurrencyControllerTest {

	private CurrencyController currencyController;
	private CurrencyTestHelper currencyTestHelper;

	@Before
	public void init() {
		FixtureFactoryLoader.loadTemplates("com.rafaelbezerra.cwi.currency.test.template");
		currencyController = new CurrencyController();
		currencyTestHelper = new CurrencyTestHelper();
	}

	/**
	 * Tests the {@link CurrencyValueLessThanZeroException} when we pass
	 * negative values by parameter
	 */
	@Test(expected = CurrencyValueLessThanZeroException.class)
	public void testValueLessThanZeroException() {
		currencyController.currencyCalculation(null, null, BigDecimalValues.NEGATIVE_ONE);
	}

	/**
	 * Tests the specific number of decimal places
	 */
	@Test
	public void testResultTruncate() {
		CurrencyDTO currencyDTOFrom = Fixture.from(CurrencyDTO.class).gimme(CurrencyDTOTemplateLoader.BASIC);
		CurrencyDTO currencyDTOTo = Fixture.from(CurrencyDTO.class).gimme(CurrencyDTOTemplateLoader.BASIC);

		BigDecimal resultActual = currencyController.currencyCalculation(currencyDTOFrom, currencyDTOTo,
				BigDecimalValues.HUNDRED);

		assertEquals(2, currencyTestHelper.getNumberOfDecimalPlacesByString(resultActual));
	}

	/**
	 * Tests the
	 * {@link CurrencyController#currencyCalculation(CurrencyDTO, CurrencyDTO, Number)}
	 * calculation assertion
	 */
	@Test
	public void testResultAssert() {
		CurrencyDTO currencyDTOFrom = Fixture.from(CurrencyDTO.class).gimme(CurrencyDTOTemplateLoader.BASIC);
		CurrencyDTO currencyDTOTo = Fixture.from(CurrencyDTO.class).gimme(CurrencyDTOTemplateLoader.BASIC);

		BigDecimal resultActual = currencyController.currencyCalculation(currencyDTOFrom, currencyDTOTo,
				BigDecimalValues.HUNDRED);

		BigDecimal resultExpected = BigDecimalComparator.truncate(BigDecimalValues.HUNDRED
				.multiply(currencyDTOFrom.getPurchaseRate().divide(currencyDTOTo.getPurchaseRate(),
						Config.getCalculationScale(), Config.getCalculationRoundingMode())),
				2);

		assertEquals(resultExpected, resultActual);
	}

}
