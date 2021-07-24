package com.nit.hk.spec;

import com.nit.hk.exception.InsufficientFundsException;
import com.nit.hk.exception.InvalidAmountException;

public interface BankAccount {
	
	public void init();
	
	public void deposit (double amt)
					throws InvalidAmountException;
	
	public void withdraw(double amt)
					throws InvalidAmountException, InsufficientFundsException;
	
	public void currentBalance();
	
	public void transferMoney(BankAccount destAcc,double amt)
					throws InvalidAmountException, InsufficientFundsException;
}

