package com.rafaelbezerra.cwi.currency.mapper.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.rafaelbezerra.cwi.currency.config.Config;
import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;
import com.rafaelbezerra.cwi.currency.enums.CurrencyIdentifierEnum;
import com.rafaelbezerra.cwi.currency.helper.CurrencyHelper;
import com.rafaelbezerra.cwi.currency.mapper.Mapper;

/**
 * Currency Mapper Implementation of {@link Mapper}
 * 
 * @author rafaelbezerra
 */
public class CurrencyMapperImpl implements Mapper<CurrencyDTO, String[]> {

	public CurrencyDTO map(String[] from) {
		CurrencyDTO to = null;
		if (from != null) {
			to = new CurrencyDTO();
			to.setQuotationDate(CurrencyHelper.getDateFromString(from[0], Config.getQuotationDateInsertDateFormat()));
			to.setId(Integer.parseInt(from[1]));
			to.setType(from[2].charAt(0));
			to.setCurrency(CurrencyIdentifierEnum.getByIdentifier(from[3]));
			to.setPurchaseRate(new BigDecimal(CurrencyHelper.replaceCommasForDots(from[4])));
			to.setSaleRate(new BigDecimal(CurrencyHelper.replaceCommasForDots(from[5])));
			to.setPurchaseParity(new BigDecimal(CurrencyHelper.replaceCommasForDots(from[6])));
			to.setSaleParity(new BigDecimal(CurrencyHelper.replaceCommasForDots(from[7])));
		}
		return to;
	}

	public List<CurrencyDTO> mapList(List<String[]> fromList) {
		List<CurrencyDTO> toList = null;
		if (fromList != null) {
			toList = new ArrayList<CurrencyDTO>();
			for (String[] from : fromList)
				toList.add(map(from));
		}
		return toList;
	}

	public String[] unmap(CurrencyDTO from) {
		String[] to = null;
		if (from != null) {
			to = new String[8];
			to[0] = CurrencyHelper.getStringFromDate(from.getQuotationDate(),
					Config.getQuotationDateInsertDateFormat());
			to[1] = from.getId() + "";
			to[2] = String.valueOf(from.getType());
			to[3] = from.getCurrency().getIdentifier();
			to[4] = from.getPurchaseRate().toString();
			to[5] = from.getSaleRate().toString();
			to[6] = from.getPurchaseParity().toString();
			to[7] = from.getSaleParity().toString();
		}
		return to;
	}

	public List<String[]> unmapList(List<CurrencyDTO> fromList) {
		List<String[]> toList = null;
		if (fromList != null) {
			toList = new ArrayList<String[]>();
			for (CurrencyDTO from : fromList)
				toList.add(unmap(from));
		}
		return toList;
	}

}
