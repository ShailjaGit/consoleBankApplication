package com.nit.hk.user;


import java.util.Scanner;

import com.nit.hk.exception.InsufficientFundsException;
import com.nit.hk.exception.InvalidAmountException;
import com.nit.hk.spec.BankAccount;


public class Bank {
	public static void main(String[] args) throws IllegalAccessException {
		Scanner scn = new Scanner(System.in);
		BankAccount acc = null;
	loop:while(true) {
		System.out.println("Choose one option");
		System.out.println(" 1. OpenAccount");
		System.out.println(" 2. Deposit");
		System.out.println(" 3. Withdraw");
		System.out.println(" 4. Curent Balance");
		System.out.println(" 5. Transfer Money");
		System.out.println(" 6. Details");
		System.out.println(" 7. Exit");
		
		System.out.print("Enter number : ");
		int option = scn.nextInt(); scn.nextLine();
		
		switch(option) {
		case 1:{//open account
			try {
			//read input in the form of com.nit.hk.blogic.BankName
			System.out.print("Enterbank name: ");
			Class cls = Class.forName(scn.next());
			Object obj = cls.newInstance();
			
			if(obj instanceof BankAccount) {
				acc = (BankAccount)obj;
				acc.init();
				System.out.println("Account object created and inialized");
				System.out.println(acc);
			}else {
				System.out.println("Error: Invalid Bank");
			}
				/*BankAccount acc1 = (BankAccount)obj;
				acc1.init();
				acc1.deposit(5000);
				acc1.currentBalance();
				acc1.withdraw(3000);
				acc1.currentBalance();*/
			}
			
		catch(ClassNotFoundException e) {
			System.out.println("Given class is not found");
		}catch(InstantiationException e) {
			System.out.println("Given class does not have param constructor");
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}
		
		break;
	}//case #1:open account close	
		
		case 2:{
			if(acc == null) {
				System.out.println("Fisrt open account");
				continue;
			}
			System.out.print("Enter Amount :");
			try {
				double amt = scn.nextDouble(); scn.nextLine();
				acc.deposit(amt);
				System.out.println(amt +" credited to your account");
				System.out.println("Current balance is ");
				acc.currentBalance();
				
			}catch(InvalidAmountException e) {
				System.out.println(e.getMessage());
			}
			
			break;
		}// case 2: deposit operation closed
		
		case 3:{//withdraw
			if(acc == null) {
				System.out.println("Fisrt open account");
				continue;
			}
			
			System.out.print("Enter Amount :");
			try {
				double amt = scn.nextDouble(); scn.nextLine();
				acc.withdraw(amt);
				System.out.println(amt + "Debited from your account");
				System.out.println("Current balance is :");
				acc.currentBalance();
			}catch(InvalidAmountException e) {
				System.out.println(e.getMessage());
			}catch(InsufficientFundsException e) {
				System.out.println(e.getMessage());
			}
			
			break;
		}//case 3: withdraw operation completed
		
		case 4:{//current balance
			if(acc == null) {
				System.out.println("Fisrt open account");
				continue;
			}
			
			System.out.println("Current Balance is :");
			acc.currentBalance();
			
			break;
		
		}//case 4: Current balance operation completed
		
		case 5:{//transfer amount
			if(acc == null) {
				System.out.println("Fisrt open account");
				continue;
			}
			
			try {
				System.out.print("Enter dest bank name:");
				Class destBankClass = Class.forName(scn.next());
				Object destBankObj = destBankClass.newInstance();
				if(destBankObj instanceof BankAccount ) {
					BankAccount destAcc = (BankAccount)destBankObj;
						
					System.out.print("Enter amount to transfer :");
					acc.transferMoney(destAcc,scn.nextDouble()); scn.nextLine();
					System.out.println("amount treansfered successfully");
					}
				}//try
			catch(ClassNotFoundException e) {
				System.out.println("Given class is not found");
			}catch(InstantiationException e) {
				System.out.println("Given class dose not have param constructor ");
			}catch (IllegalArgumentException e) {
				System.out.println("Given class no param constructor is not visible");
			}catch(InvalidAmountException e) {
				System.out.println(e.getMessage());
			}catch(InsufficientFundsException e) {
				System.out.println(e.getMessage());
			}
			
			break;
		
		}//case 5: transfer amount operation completed
		
		case 6:{//Account Details
			if(acc == null) {
				System.out.println("Fisrt open account");
				continue;
			}
			System.out.println(acc);
			break;
		}
		case 7:{//exit case
			System.out.println("Thank You Visit Again");
			break loop;
		}
		
		default :{
			System.out.println("Invalid Option");
			break;
		 }
		
       }//switch
		
      }//loop
	
    }//main
	
}//class
