package com.rafaelbezerra.cwi.currency.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.rafaelbezerra.cwi.currency.enums.CurrencyIdentifierEnum;

public class CurrencyDTO {

	private Date quotationDate;
	private int id;
	private char type;
	private CurrencyIdentifierEnum currency;
	private BigDecimal purchaseRate;
	private BigDecimal saleRate;
	private BigDecimal purchaseParity;
	private BigDecimal saleParity;

	public Date getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public CurrencyIdentifierEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyIdentifierEnum currency) {
		this.currency = currency;
	}

	public BigDecimal getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(BigDecimal purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public BigDecimal getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(BigDecimal saleRate) {
		this.saleRate = saleRate;
	}

	public BigDecimal getPurchaseParity() {
		return purchaseParity;
	}

	public void setPurchaseParity(BigDecimal purchaseParity) {
		this.purchaseParity = purchaseParity;
	}

	public BigDecimal getSaleParity() {
		return saleParity;
	}

	public void setSaleParity(BigDecimal saleParity) {
		this.saleParity = saleParity;
	}

}
