package net.jaimetorres.pila.approval.core;

import java.util.Locale;
import java.time.format.DateTimeFormatter;
public enum Core {
	PARSING_ERROR

	//enum sin valores :-(
	;

	public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
	
	public static final String PERCENT_FORMAT = "#.####%";
	
	public static final String STRING_DATE_FORMAT = "yyyy-MM-dd";
		
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(STRING_DATE_FORMAT);
	
	public static final Locale ES_CO_LOCALE =  new Locale.Builder().setLanguage("es").setRegion("CO").build();
	
	public static final Integer TIPO_COTIZANTE_DEPENDIENTE = 1;
	
	public static final String TIPO_PLANILLA_N = "N";
	
	public static final String X_MARK = "X";
	
	public static final String DATE_1900 = "1900-01-01";
	
	public static final String TIME_00 = "00:00:00";
	
	public static final String PROCESO_GENERACION_PLANILLA_N_MANUAL = "Generacion Planilla N Manual"; 
	
	public static final Integer OPERADOR_ASOPAGOS = 86;
	
	public static final String CODIGO_ORIGINADOR = "ASC001";
	
	public static final String ORIGEN_PLANILLA = "0";
	
	public static final String PASENA = "PASENA";
	
	public static final String PAICBF = "PAICBF";
	
	public static final String PAESAP = "PAESAP";
	
	public static final String PAMIED = "PAMIED";
	
	public static final String CORRECCION_CON_BENEFICIO_LEY_1819 = "CORRECCION_CON_BENEFICIO_LEY_1819";
	
	public static final String FECHA_LIMITE_BENEFICIOS_1819_IND246 = "fechaLimiteBeneficios1819Ind246";
	
	public static final String LIMITE_LIQUIDACION_FORZOZA_1819 = "limiteLiquidacionForzoza1819";
	
	public static final Integer COINCIDENCIA = 1;
	
	public static final String PLANILLA_N = "C";
	
	public static final String LEGACY_SINARP = "SINARP";
	
	public static final String FSP001= "FSP001";
	
	public static final String SINAFP= "SINAFP";
	
	//XXX CAMBIAR
	public static final String[] tipoVinculacion = {
			"'3'","'15'","'16'","'33'","'34'","'35'","'36'","'40'","'41'",
			"'42'","'43'","'52'","'53'","'42'","'03'","'04'","'56'","'57'","'58'","'59'","'60'"};
	
	public static final Integer LIMITE_INFERIOR_SMMLV_FSP = 4;
	
	public static final float TARIFA_FONDO_SUBSISTENCIA = 0.005f;
	
	//Dias es usado para obtener la fechaVencimientoNextDay
	public static final Integer DIAS = 1;
	
}
