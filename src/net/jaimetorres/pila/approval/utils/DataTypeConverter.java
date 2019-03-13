package net.jaimetorres.pila.approval.utils;

import org.apache.commons.beanutils.ConvertUtils;

public class DataTypeConverter {
	
	public static<T> T convert(Object value, Class<T> targetType){

		//https://stackoverflow.com/questions/2800739/how-to-remove-leading-zeros-from-alphanumeric-text
		if(Number.class.isAssignableFrom(targetType) 
				&& value instanceof String){
			value = ((String) value).replaceFirst("^0+(?!$)", "");
		}
		
		return targetType.cast(ConvertUtils.convert(value, targetType));
	}

}
