package com.rafaelbezerra.cwi.currency.exception;

/**
 * Quotation Download Exception
 * 
 * @author rafaelbezerra
 */
public class QuotationDownloadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QuotationDownloadException(String message, Throwable exception) {
		super(message, exception);
	}
}
