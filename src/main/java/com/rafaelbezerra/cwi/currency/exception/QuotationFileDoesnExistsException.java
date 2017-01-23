package com.rafaelbezerra.cwi.currency.exception;

/**
 * Quotation File Doesn Exists Exception
 * 
 * @author rafaelbezerra
 */
public class QuotationFileDoesnExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QuotationFileDoesnExistsException(String message) {
		super(message);
	}

}
