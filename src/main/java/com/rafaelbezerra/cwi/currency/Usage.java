package com.rafaelbezerra.cwi.currency;

import com.rafaelbezerra.cwi.currency.service.CurrencyService;
import com.rafaelbezerra.cwi.currency.service.impl.CurrencyServiceImpl;

public class Usage {

	public static void main(String[] args) {

		CurrencyService currencyService = new CurrencyServiceImpl();

		System.out.println(currencyService.currencyQuotation("USD", "EUR", 100.00, "22/01/2017"));

	}
}
