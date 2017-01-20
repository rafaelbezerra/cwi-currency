package com.rafaelbezerra.cwi.currency.manager;

import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;

public interface CurrencyManager {

	CurrencyDTO findByCurrencyQuotation(String currency, String quotation);

}
