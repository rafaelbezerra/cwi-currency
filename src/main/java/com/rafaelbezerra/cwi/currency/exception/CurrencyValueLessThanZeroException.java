package com.rafaelbezerra.cwi.currency.exception;

/**
 * Currency Value Less Than Zero Exception
 * 
 * @author rafaelbezerra
 */
public class CurrencyValueLessThanZeroException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CurrencyValueLessThanZeroException(String message) {
		super(message);
	}

}
