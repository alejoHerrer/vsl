package net.jaimetorres.pila.approval.utils;

public class NumberUtils {

	public static synchronized boolean isNumber(final String str) {
		if (str == null) {
			return false;
		}
		String regex = "[0-9]+";
		return str.matches(regex);
	}
	
	public static synchronized String padNumberWithZeros (final Integer number, final Integer zeroPaddingLength) {
		
		if(number == null){
			return null;
		}
		
		if(zeroPaddingLength == null || zeroPaddingLength <= 0){
			return String.valueOf(number);
		}
		
		String padding = "%0" + zeroPaddingLength +"d";
		return String.format(padding, number);
	}
}
