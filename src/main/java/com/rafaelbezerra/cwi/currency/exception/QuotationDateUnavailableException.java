package com.rafaelbezerra.cwi.currency.exception;

/**
 * Quotation Date Unavailable Exception
 * 
 * @author rafaelbezerra
 */
public class QuotationDateUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QuotationDateUnavailableException(String message) {
		super(message);
	}
}
