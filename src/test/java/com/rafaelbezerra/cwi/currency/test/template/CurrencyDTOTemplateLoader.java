package com.rafaelbezerra.cwi.currency.test.template;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.rafaelbezerra.cwi.currency.dto.CurrencyDTO;
import com.rafaelbezerra.cwi.currency.enums.CurrencyIdentifierEnum;
import com.rafaelbezerra.cwi.currency.service.comparator.BigDecimalValues;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * CurrencyDTO Template Loader for {@link CurrencyDTO}
 * 
 * @author rafaelbezerra
 */
public class CurrencyDTOTemplateLoader implements TemplateLoader {

	public static final String BASIC = "basic";
	public static final String QUOTATION_DATE_IN_FUTURE = "quotationDateInFuture";

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void load() {
		try {
			Fixture.of(CurrencyDTO.class).addTemplate(BASIC, new Rule() {
				{
					add("quotationDate", random(sdf.parse("10/01/2017"), sdf.parse("13/01/2017"),
							sdf.parse("16/01/2017"), sdf.parse("20/01/2017")));
					add("id", random(Integer.class, range(1L, 200L)));
					add("type", random('A', 'B'));
					add("currency", random(CurrencyIdentifierEnum.values()));
					add("purchaseRate", random(BigDecimal.class, range(BigDecimalValues.THREE, BigDecimalValues.FOUR)));
					add("saleRate", random(BigDecimal.class, range(BigDecimalValues.THREE, BigDecimalValues.FOUR)));
					add("purchaseParity",
							random(BigDecimal.class, range(BigDecimalValues.HALF_ONE, BigDecimalValues.FOUR)));
					add("saleParity",
							random(BigDecimal.class, range(BigDecimalValues.HALF_ONE, BigDecimalValues.FOUR)));
				}
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Fixture.of(CurrencyDTO.class).addTemplate(QUOTATION_DATE_IN_FUTURE).inherits(BASIC, new Rule() {
			{
				add("quotationDate", instant("2 days after"));
			}
		});
	}

}
