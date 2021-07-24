package com.nit.hk.blogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

import com.nit.hk.exception.InsufficientFundsException;
import com.nit.hk.exception.InvalidAmountException;
import com.nit.hk.spec.BankAccount;

public class SBIBankAccount implements BankAccount {

	private static AtomicLong accNumberGenerator;
	
	private long accNum;
	private String accHName;
	private double balance;
	
	static {
		try(
				BufferedReader br =
							new BufferedReader(new FileReader("SBIaccNumSeq.txt"));
				){
				long currentAccountNumber = Long.parseLong(br.readLine());
				accNumberGenerator = new AtomicLong(currentAccountNumber);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}	
	}

	@Override
	public void init() {
		Scanner scn = new Scanner(System.in);
		
		accNum = accNumberGenerator.incrementAndGet();
		
		System.out.print("Enter accHName :");
		accHName = scn.nextLine();
		
		System.out.print("Enter Balance :");
		balance = scn.nextDouble();
		
		try(
				PrintWriter pw = new PrintWriter("SBINumSeq.txt");
				){
				pw.print(accNumberGenerator.get());
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			scn.close();
		}
	}

	@Override
	public void deposit(double amt) throws InvalidAmountException {
		if(amt <= 0)
			throw new InvalidAmountException("amount can not be -ve or zero");
		
		this.balance = this.balance + amt;
		}
		

	@Override
	public void withdraw(double amt) throws InvalidAmountException , InsufficientFundsException {
		if(amt <= 0)
			throw new InvalidAmountException("amount can not be -ve or zero");
		if(amt>balance)
			throw new InsufficientFundsException("Insufficient Funds");
		
		this.balance = this.balance - amt;
		}
		

	@Override
	public void currentBalance() {
		System.out.println(balance);
	

	}

	@Override
	public void transferMoney(BankAccount destAcc, double amt)
			throws InvalidAmountException, InsufficientFundsException {
		
		this.withdraw(amt);
		destAcc.deposit(amt);
	

	}

	public void setAccNum(long accNum) {
		this.accNum = accNum;
	}

	public void setAccHName(String accHName) {
		this.accHName = accHName;
	}

	public long getAccNum() {
		return accNum;
	}
	@Override
	public String toString() {
		return "SBIBankAccount (" + accNum + ", " + accHName + ", " + balance + ")";
	}

}