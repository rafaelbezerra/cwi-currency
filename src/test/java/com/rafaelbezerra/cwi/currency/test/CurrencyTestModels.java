package com.rafaelbezerra.cwi.currency.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;
import com.rafaelbezerra.cwi.currency.enums.CurrencyIdentifierEnum;

public class CurrencyTestModels {

	private CurrencyDTO currencyDTOFromBasicForController;
	private CurrencyDTO currencyDTOToBasicForController;

	private SimpleDateFormat sdf = new SimpleDateFormat();

	public CurrencyTestModels() {
		this.initModels();
	}

	private void initModels() {
		currencyDTOFromBasicForController.setQuotationDate(this.getDateFromString("18/01/2017"));
		currencyDTOFromBasicForController.setId(220);
		currencyDTOFromBasicForController.setType('A');
		currencyDTOFromBasicForController.setCurrency(CurrencyIdentifierEnum.USD);
		currencyDTOFromBasicForController.setPurchaseRate(new BigDecimal("3.2205"));
		currencyDTOFromBasicForController.setSaleRate(new BigDecimal("3.2211"));
		
		
	}

	public Date getDateFromString(String dateStr) {
		sdf.applyPattern("dd/MM/yyyy");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
