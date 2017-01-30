package com.rafaelbezerra.cwi.currency.test.manager.impl;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.rafaelbezerra.cwi.currency.exception.QuotationDateUnavailableException;
import com.rafaelbezerra.cwi.currency.exception.QuotationFileDoesnExistsException;
import com.rafaelbezerra.cwi.currency.helper.CurrencyHelper;
import com.rafaelbezerra.cwi.currency.manager.impl.CurrencyManagerCSVImpl;
import com.rafaelbezerra.cwi.currency.mapper.impl.CurrencyMapperImpl;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * Currency Manager CSV Implementation Test for {@link CurrencyMapperImpl}
 * 
 * @author rafaelbezerra
 */
// @RunWith(PowerMockRunner.class)
// @PrepareForTest(CurrencyHelper.class)
public class CurrencyManagerCSVImplTest {

	private CurrencyManagerCSVImpl currencyManagerCSVImpl, currencyManagerCSVImplMock;
	private SimpleDateFormat sdf;

	@Before
	public void init() {
		FixtureFactoryLoader.loadTemplates("com.rafaelbezerra.cwi.currency.test.template");
		currencyManagerCSVImpl = new CurrencyManagerCSVImpl();
		currencyManagerCSVImplMock = mock(CurrencyManagerCSVImpl.class);
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	@Test(expected = QuotationDateUnavailableException.class)
	public void testQuotationDateInFuture() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 2);

		currencyManagerCSVImpl.findByCurrencyQuotation("USD", sdf.format(calendar.getTime()));
	}

	@Ignore
	@Test(expected = QuotationFileDoesnExistsException.class)
	public void bla() {
		String quotationDateString = sdf.format(new Date());
		String currencyString = "USD";

		// PowerMockito.mockStatic(CurrencyHelper.class);

		when(CurrencyHelper.filePathExists(anyString())).thenReturn(false);

		when(currencyManagerCSVImplMock.findByCurrencyQuotation(currencyString, quotationDateString))
				.thenCallRealMethod();

		currencyManagerCSVImplMock.findByCurrencyQuotation(currencyString, quotationDateString);
	}

}
