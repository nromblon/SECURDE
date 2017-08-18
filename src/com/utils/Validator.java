package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	private static Validator validator = new Validator();
	
	private Validator(){}
	
	public static Validator getInstance(){
		return validator;
	}
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
	    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validateEmail(String input) {
        int length = 45;
        if (input.length() > length)
        	return false;
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(input);
        return matcher.find();
	}
	
	public boolean isAlphaNumeric(String input, int length){
	    String pattern= "^[a-zA-Z0-9]*$";
	    if (input.length() > length)
	    	return false;
	    return input.matches(pattern);
	}
	
	public boolean isAlphaNumericHasSpace(String input, int length){
	    String pattern= "^[a-zA-Z0-9 ]*$";
	    if (input.length() > length)
	    	return false;
	    return input.matches(pattern);
	}
	
	public boolean isDate(String input){
		String pattern = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
		return input.matches(pattern);
	}
	
	public boolean isValidPassword(String input){
		String pattern = "(?=.*[0-9])|(?=.*[a-z])|(?=.*[A-Z])|(?=.*[@#$%^&+=])|(?=\\S+$).{8,}";
		return input.matches(pattern);
	}
	
	public static boolean isNumeric(String input){
		try {
			Integer.parseInt(input);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
