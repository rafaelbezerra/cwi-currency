package com.rafaelbezerra.cwi.currency.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.rafaelbezerra.cwi.currency.enums.CurrencyIdentifierEnum;

/**
 * Currency DTO to be used for the DTO from mirror of CSV row
 * 
 * @author rafaelbezerra
 */
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyDTO other = (CurrencyDTO) obj;
		if (currency != other.currency)
			return false;
		if (id != other.id)
			return false;
		if (purchaseParity == null) {
			if (other.purchaseParity != null)
				return false;
		} else if (!purchaseParity.equals(other.purchaseParity))
			return false;
		if (purchaseRate == null) {
			if (other.purchaseRate != null)
				return false;
		} else if (!purchaseRate.equals(other.purchaseRate))
			return false;
		if (quotationDate == null) {
			if (other.quotationDate != null)
				return false;
		} else if (!quotationDate.equals(other.quotationDate))
			return false;
		if (saleParity == null) {
			if (other.saleParity != null)
				return false;
		} else if (!saleParity.equals(other.saleParity))
			return false;
		if (saleRate == null) {
			if (other.saleRate != null)
				return false;
		} else if (!saleRate.equals(other.saleRate))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
