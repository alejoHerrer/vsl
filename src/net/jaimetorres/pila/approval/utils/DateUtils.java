package net.jaimetorres.pila.approval.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.jaimetorres.pila.approval.core.Core;


public class DateUtils {

	public static final TimeZone APPLICATION_TIME_ZONE = TimeZone.getTimeZone("America/Bogota");
	public static final ZoneId APPLICATION_ZONE_ID = APPLICATION_TIME_ZONE.toZoneId();
	private static final String CADENA_FORMATO_FECHA = "yyyy-MM-dd";
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(CADENA_FORMATO_FECHA);
//	private static final String CADENA_FORMATO_HORA = "hh:mm:ss";
//	private static final SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat(CADENA_FORMATO_HORA);
	private static final Integer MES_2 = 2;
	
	public static synchronized LocalDate convertDate2LocalDate(java.util.Date date){
		
		if(date == null){return null;}
		
		if(date instanceof java.sql.Date){
			java.sql.Date sqlDate = (java.sql.Date)date;
			return sqlDate.toLocalDate();
		}
		
		Instant dateInstant = date.toInstant();
		ZonedDateTime zoneDateTime = dateInstant.atZone(APPLICATION_ZONE_ID);
		return zoneDateTime.toLocalDate();
	}
	
	public static synchronized YearMonth convertLocalDate2YearMonth(LocalDate source){
		return YearMonth.from(source);
	}
	
	public static synchronized YearMonth convertString2YearMonth(String source, String dateTimeFormatterPattern){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatterPattern);
		return YearMonth.parse(source, dateTimeFormatter);
	}

	public static synchronized LocalDate convertString2LocalDate(String source, String dateTimeFormatterPattern){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatterPattern);
		return LocalDate.parse(source, dateTimeFormatter);
	}
	
	public static synchronized String convertSqlDate2String(java.sql.Date date){
		return date!=null?Core.DATE_TIME_FORMATTER.format(date.toLocalDate()):null;
	}
	
	public static synchronized String convertLocalDate2String(LocalDate localDate){
		return Core.DATE_TIME_FORMATTER.format(localDate);
	}
	
	public static synchronized String convertLocalDate2String(LocalDate localDate,String format){
		return DateTimeFormatter.ofPattern(format).format(localDate);
	}
	
	public static synchronized LocalDate convertString2LocalDate(String date){
		return LocalDate.parse(date, Core.DATE_TIME_FORMATTER);
	}
	
	//XXX IMPLEMENTAR EL USO DE CACHE para este metodo
	public static synchronized java.sql.Date convertLocalDate2SqlDate(LocalDate date){
		if(date == null){return null;}
		return java.sql.Date.valueOf(date);
	}
	
	public static synchronized boolean isDateformatValid(String date){
		try{
			
			Core.DATE_TIME_FORMATTER.parse(date);
			return Boolean.TRUE;
		}catch(DateTimeParseException e){
			return Boolean.FALSE;
		}
	}
	
	//XXX GENERALIZAR
	public static synchronized long daysBetweenLocalDates(LocalDate localDateInicial, LocalDate localDateFinal){
		return Duration.between(localDateInicial.atTime(0,0),localDateFinal.atTime(0,0)).toDays();
	} 
	
	public static synchronized String calculatePeriodBetweenLocalDateTime(
			LocalDateTime fromDateTime, LocalDateTime toDateTime){
		
		LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );
		
		long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS);
		tempDateTime = tempDateTime.plusYears( years );
		
		long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS);
		tempDateTime = tempDateTime.plusMonths( months );

		long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS);
		tempDateTime = tempDateTime.plusDays( days );

		long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS);
		tempDateTime = tempDateTime.plusHours( hours );

		long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES);
		tempDateTime = tempDateTime.plusMinutes( minutes );

		long seconds = tempDateTime.until( toDateTime, ChronoUnit.SECONDS);
		tempDateTime = tempDateTime.plusMinutes( seconds );
		
		long milliSeconds = tempDateTime.until( toDateTime, ChronoUnit.MILLIS);
		
		String out = years + " years " + 
		        months + " months " + 
		        days + " days " +
		        hours + " hours " +
		        minutes + " minutes " +
		        seconds + " seconds " +
		        milliSeconds + "millis.";
		
		return out;
	}
	
	public boolean isLocalDateInBetween(LocalDate dateInEvaluation, LocalDate firstDate, LocalDate lastDate){
		
		//by-pass
		if(dateInEvaluation == null || firstDate == null || lastDate == null){
			return false;
		}
		
		
		if( (firstDate.isBefore(dateInEvaluation) || firstDate.equals(dateInEvaluation) )
				&&
			(lastDate.equals(dateInEvaluation) || lastDate.isAfter(dateInEvaluation)) 	){
			return true;
		}else{
			return false;
		}
	}
	public static synchronized java.sql.Date convertStringToSqlDate (String fechaString){
		if(fechaString == null || fechaString.trim().isEmpty()){return null;}
		
		Date date = null;
		try{
			date = convertStringToDate(fechaString);
		}catch(Exception e){
			//Se maneja como ausencia del campo desde el Commons
			return null;
		}
 		
 		return new java.sql.Date(date.getTime());
 	}
	public static synchronized Date convertStringToDate (String fechaString){
		if(fechaString == null || fechaString.trim().isEmpty()){return null;}
		
		Date date;
		try{
			date = SIMPLE_DATE_FORMAT.parse(fechaString);
		}catch(ParseException pe){
			throw new RuntimeException("Error al intentar parsear la fecha: [" + fechaString +"]");
		}
		
		return date;		
	}

	public static synchronized java.sql.Time convertStringToSqlTime(String horaString) {
		if(horaString == null){	return null;}
		//XXX SE DEBE VALIDAR EL FORMATO DEL ARGUMENTO
		LocalTime localTime = LocalTime.parse( horaString ) ;
		return java.sql.Time.valueOf(localTime);
	}
	
	public static synchronized java.sql.Time convertLocalTimeToSqlTime(LocalTime localTime) {
		if(localTime == null){	return null;}
		return java.sql.Time.valueOf(localTime);
	}
	
	public static synchronized List<LocalDate> retrieveLocalDateListInBetween(LocalDate begin, LocalDate end){
		
		if(begin == null || end == null){return null;}
		if(begin.isAfter(end)){ throw new RuntimeException("La fecha de inicio no puede ser posterior a la fecha fin");}
		
		return Stream.iterate(begin,d->d.plusDays(1))
				.limit(ChronoUnit.DAYS.between(begin, end) + 1)
				.sorted()
				.collect(Collectors.toList());
	}
	
	public static long calcularDiasNovedad(LocalDate fechaInicio, LocalDate fechaFin) {

		long days = ChronoUnit.DAYS.between(fechaInicio, fechaFin)
				//se le suma uno porque este calculo maneja exclusivamente (y no inclusivo) la fecha final
				+ 1;
		
		int totalDiasMes = calcularNumDiasMes(YearMonth.from(fechaFin));
		
		if(days >= totalDiasMes){
			days = 30;
		}
		
		return days;
	}

	public static int calcularNumDiasMes(YearMonth yearMonth) {
		
		int totalDiasMes = 30;
		if(Integer.valueOf(yearMonth.getMonthValue()).equals(DateUtils.MES_2)) { // Febrero
			totalDiasMes = yearMonth.lengthOfMonth();
		}
		return totalDiasMes;
	}
	
	

	

	
}
