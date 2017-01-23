package com.rafaelbezerra.cwi.currency.exception;

/**
 * Currency Invalid Exception
 * 
 * @author rafaelbezerra
 */
public class CurrencyInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CurrencyInvalidException(String message) {
		super(message);
	}

}
