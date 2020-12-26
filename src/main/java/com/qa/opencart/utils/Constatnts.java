package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constatnts {
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String TEXT_MSG="I am a returning customer";
	public static final String LOGIN_PAGE_HEADER="Returning Customer";
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final String ACCOUNT_PAGE_HEADER="Your Store";
	public static final int ACCOUNTS_SECTIONS=4;
	public static final String PRODUCT_NAME_HEADER="MacBook Pro";
	public static final String CART_PAGE_HEADER="Shopping Cart";
	public static final String CART_PAGE_NEXT_ACTION_HEADER="What would you like to do next?";
	public static final String TEST_DATA_SHEET_NAME="registration";
	public static final String ACCOUNT_CREATION_MESSAGE="Your Account Has Been Created";
	
	public static List<String> getAccountSectionList() {
		List<String> accountlist=new ArrayList<String>();
		accountlist.add("My Account");
		accountlist.add("My Orders");
		accountlist.add("My Affiliate Account");
		accountlist.add("Newsletter");
		
		return accountlist;
		
	}
	//{Brand=Apple, Availability=Out Of Stock, price=$2,000.00, Ex Tax=$2,000.00, Product Code=Product 18, Reward Points=800}
	
	public static Map<String,String> getProudctInfoMapforMacbookPro(){
		Map<String,String> productMap=new HashMap<>();
		productMap.put("Brand", "Apple");
		productMap.put("Availability", "Out Of Stock");
		productMap.put("price", "$2,000.00");
		productMap.put("Ex Tax", "$2,000.00");
		productMap.put("Product Code", "Product 18");
		productMap.put("Reward Points", "800");
		return productMap;
		
		
	}
	
	public static List<String> macBookProInfoCartPage() {
		List<String> productInfo=new ArrayList<>();
		productInfo.add("Product 18");
		productInfo.add("$2,000.00");
		productInfo.add("$4,000.00");
		return productInfo;
	}
	
	public static List<String> NextActionHeader(){
		List<String> actionList=new ArrayList<>();
		actionList.add("Use Coupon Code");
		actionList.add("Estimate Shipping & Taxes");
		actionList.add("Use Gift Certificate");
		return actionList;
	}
}
	
