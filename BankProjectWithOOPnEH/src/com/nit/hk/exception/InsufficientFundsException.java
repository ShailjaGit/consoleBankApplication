//InsufficientFundsException.java
package com.nit.hk.exception;

public class InsufficientFundsException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public InsufficientFundsException() {
		super();
	}
	
	public InsufficientFundsException(String errMsg) {
		super(errMsg);
	}

}
