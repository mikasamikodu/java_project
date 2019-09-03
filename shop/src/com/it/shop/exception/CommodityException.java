package com.it.shop.exception;

public class CommodityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommodityException() {
		super();
	}

	public CommodityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CommodityException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommodityException(String message) {
		super(message);
	}

	public CommodityException(Throwable cause) {
		super(cause);
	}

}
