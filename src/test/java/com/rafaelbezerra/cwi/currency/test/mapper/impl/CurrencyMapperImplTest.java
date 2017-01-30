package com.rafaelbezerra.cwi.currency.test.mapper.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rafaelbezerra.cwi.currency.config.Config;
import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;
import com.rafaelbezerra.cwi.currency.helper.CurrencyHelper;
import com.rafaelbezerra.cwi.currency.mapper.impl.CurrencyMapperImpl;
import com.rafaelbezerra.cwi.currency.test.template.CurrencyDTOTemplateLoader;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * Currency Mapper Implementation Test for {@link CurrencyMapperImpl}
 * 
 * @author rafaelbezerra
 */
public class CurrencyMapperImplTest {

	private CurrencyMapperImpl currencyMapperImpl, currencyMapperImplMock;

	@Before
	public void init() {
		FixtureFactoryLoader.loadTemplates("com.rafaelbezerra.cwi.currency.test.template");
		currencyMapperImpl = new CurrencyMapperImpl();
		currencyMapperImplMock = mock(CurrencyMapperImpl.class);
	}

	/**
	 * Tests the map assertion
	 */
	@Test
	public void testMap() {
		CurrencyDTO currencyDTOActual = Fixture.from(CurrencyDTO.class).gimme(CurrencyDTOTemplateLoader.BASIC);

		String[] toBeExpected = new String[8];
		toBeExpected[0] = CurrencyHelper.getStringFromDate(currencyDTOActual.getQuotationDate(),
				Config.getQuotationDateInsertDateFormat());
		toBeExpected[1] = currencyDTOActual.getId() + "";
		toBeExpected[2] = String.valueOf(currencyDTOActual.getType());
		toBeExpected[3] = currencyDTOActual.getCurrency().getIdentifier();
		toBeExpected[4] = currencyDTOActual.getPurchaseRate().toString();
		toBeExpected[5] = currencyDTOActual.getSaleRate().toString();
		toBeExpected[6] = currencyDTOActual.getPurchaseParity().toString();
		toBeExpected[7] = currencyDTOActual.getSaleParity().toString();

		assertEquals(currencyDTOActual, currencyMapperImpl.map(toBeExpected));
	}

	/**
	 * Tests the unmap assertion
	 */
	@Test
	public void testUnmap() {
		CurrencyDTO toBeExpected = Fixture.from(CurrencyDTO.class).gimme(CurrencyDTOTemplateLoader.BASIC);

		String[] currencyStringArrayActual = new String[8];
		currencyStringArrayActual[0] = CurrencyHelper.getStringFromDate(toBeExpected.getQuotationDate(),
				Config.getQuotationDateInsertDateFormat());
		currencyStringArrayActual[1] = toBeExpected.getId() + "";
		currencyStringArrayActual[2] = String.valueOf(toBeExpected.getType());
		currencyStringArrayActual[3] = toBeExpected.getCurrency().getIdentifier();
		currencyStringArrayActual[4] = toBeExpected.getPurchaseRate().toString();
		currencyStringArrayActual[5] = toBeExpected.getSaleRate().toString();
		currencyStringArrayActual[6] = toBeExpected.getPurchaseParity().toString();
		currencyStringArrayActual[7] = toBeExpected.getSaleParity().toString();

		assertArrayEquals(currencyStringArrayActual, currencyMapperImpl.unmap(toBeExpected));
	}

	/**
	 * Tests the map null
	 */
	@Test
	public void testMapNull() {
		assertNull(currencyMapperImpl.map(null));
	}

	/**
	 * Tests the unmap null
	 */
	@Test
	public void testUnmapNull() {
		assertNull(currencyMapperImpl.unmap(null));
	}

	/**
	 * Tests the map list size assertion
	 */
	@Test
	public void testMapList() {
		String[] anyCurrencyStringArray = new String[8];
		List<String[]> currencyStringArrayList = new ArrayList<String[]>();
		currencyStringArrayList.add(anyCurrencyStringArray);
		currencyStringArrayList.add(anyCurrencyStringArray);
		currencyStringArrayList.add(anyCurrencyStringArray);

		when(currencyMapperImplMock.mapList(anyList())).thenCallRealMethod();
		when(currencyMapperImplMock.map(anyCurrencyStringArray)).thenReturn(new CurrencyDTO());

		assertEquals(3, currencyMapperImplMock.mapList(currencyStringArrayList).size());
	}

	/**
	 * Tests the unmap list size assertion
	 */
	@Test
	public void testUnmapList() {
		CurrencyDTO anyCurrencyDTO = Fixture.from(CurrencyDTO.class).gimme(CurrencyDTOTemplateLoader.BASIC);
		List<CurrencyDTO> currencyDTOList = new ArrayList<CurrencyDTO>();
		currencyDTOList.add(anyCurrencyDTO);
		currencyDTOList.add(anyCurrencyDTO);
		currencyDTOList.add(anyCurrencyDTO);
		currencyDTOList.add(anyCurrencyDTO);
		currencyDTOList.add(anyCurrencyDTO);

		when(currencyMapperImplMock.unmapList(anyList())).thenCallRealMethod();
		when(currencyMapperImplMock.unmap(anyCurrencyDTO)).thenReturn(new String[8]);

		assertEquals(5, currencyMapperImplMock.unmapList(currencyDTOList).size());
	}

	/**
	 * Tests the map list null
	 */
	@Test
	public void testMapListNull() {
		assertNull(currencyMapperImpl.mapList(null));
	}

	/**
	 * Tests the unmap list null
	 */
	@Test
	public void testUnmapListNull() {
		assertNull(currencyMapperImpl.unmapList(null));
	}

	/**
	 * Tests the map list empty
	 */
	@Test
	public void testMapListEmpty() {
		assertTrue(currencyMapperImpl.mapList(new ArrayList<String[]>()).isEmpty());
	}

	/**
	 * Tests the unmap list empty
	 */
	@Test
	public void testUnmapListEmpty() {
		assertTrue(currencyMapperImpl.unmapList(new ArrayList<CurrencyDTO>()).isEmpty());
	}

}
