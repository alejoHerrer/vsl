package net.jaimetorres.pila.approval.utils;

import java.time.LocalDate;
import java.time.YearMonth;

import net.jaimetorres.pila.approval.pojos.OutputFilenameFields;

public class PilaUtils {

	private String outputFilenameRegexPattern = 
			//[0] FECHA_PAGO
			"(\\d{4}-\\d{2}-\\d{2})_"
			//[1] MODALIDAD
			+ "(\\d{1})_"
			//[2] NRO_PLANILLA
			+ "(\\d+)_"
			//[3] TIPO_IDENTIFICACION
			+ "(\\w{2})_"
			//[4] NRO_IDENTIFICACION
			+ "(\\w+)_"
			//[5] CODIGO_ADMINISTRADORA
			+ "(.+)_"
			//[6] CODIGO_OPERADOR
			+ "(\\d+)_"
			//[7] TIPO_ARCHIVO
			+ "(\\w+)_"
			//[8] PERIODO_PAGO
			+ "(\\d{4}-\\d{2})"
			//[n/a] FILE_EXTENSION
			+ ".TXT";
	public boolean adminFileNamePatternMatch(String filename){
		return filename.matches(outputFilenameRegexPattern);
	}
	
	
	private String outputMinpsFilenameRegexPattern = 
			//[0] MODULO DE INFORMACION (constante)
			"PIL"
			//[1] TIPO DE FUENTE (constante)
			+ "019"
			//[2] TEMA DE INFORMACION (constante)
			+ "PILA"
			//[3] FECHA DE PAGO
			+ "(\\d{4}\\d{2}\\d{2})"
			//[4] TIPO DE IDENTIFICACION DE LA ENTIDAD REPORTADORA (constante)
			+ "OP"
			//[5] CODIGO DEL OPERADOR DE INFORMACION
			+ "\\d{12}"
			//[6] NUMERO DE PLANILLA
			+ "\\d{10}"
			//[7] TIPO DE INFORMACION
			+ "\\d{1}"
			//[n/a] FILE_EXTENSION
			+ ".TXT";
	public boolean minpsFileNamePatternMatch(String filename){
		return filename.matches(outputMinpsFilenameRegexPattern);
	}
	
	private String outputAdresFilenameRegexPattern = 
			//[0] FECHA DE PAGO
			"(\\d{4}-\\d{2}-\\d{2})_"
			//[1] CODIGO DE OPERADOR 
			+ "\\d{2}_"
			//[2] Nro de PLANILLAS REPORTADAS
			+ "\\d{7}"
			//[n/a] FILE_EXTENSION
			+ ".TXT";
	public boolean adresFileNamePatternMatch(String filename){
		return filename.matches(outputAdresFilenameRegexPattern);
	}
	
	//---------------
	//FILENAME FIELDS
	//---------------
	public OutputFilenameFields retrieveOutputFilenameFields(String filename){
			
		String[] fileNameSplited = filename.split(".TXT")[0].split("_");
		
		OutputFilenameFields outputFilenameFields = new OutputFilenameFields();
		
		//FECHA_PAGO
		String[] fechaPagoSplited = fileNameSplited[0].split("-");
		outputFilenameFields.setFechaPago(LocalDate.of(Integer.valueOf(fechaPagoSplited[0]), 
				Integer.valueOf(fechaPagoSplited[1]), Integer.valueOf(fechaPagoSplited[2])));
		//MODALIDAD
		outputFilenameFields.setModalidadPlanilla(Integer.valueOf(fileNameSplited[1]));
		//NRO_PLANILLA
		outputFilenameFields.setNroPlanilla(Long.valueOf(fileNameSplited[2]));
		//TIPO_IDENTIFICACION
		outputFilenameFields.setTipoIdentificacion(fileNameSplited[3]);
		//NRO_IDENTIFICACION
		outputFilenameFields.setNroIdentificacion(fileNameSplited[4]);
		//CODIGO_ADMIN
		outputFilenameFields.setCodigoAdministradora(fileNameSplited[5]);
		//CODIGO_OPERADOR
		outputFilenameFields.setCodigoOperador(Integer.valueOf(fileNameSplited[6]));
		//TIPO_ARCHIVO
		outputFilenameFields.setTipoArchivo(fileNameSplited[7]);
		//PERIODO_PAGO
		String[] periodoPagoSplited = fileNameSplited[8].split("-");
		outputFilenameFields.setPeriodoPago(YearMonth.of(Integer.valueOf(periodoPagoSplited[0]), Integer.valueOf(periodoPagoSplited[1])));
		
		return outputFilenameFields;
		
	}
	
	public static void main(String[] args) {
		
		String filename = "PIL019PILA20171006OP00000000008600121377211.TXT";
		
		boolean match = new PilaUtils().minpsFileNamePatternMatch(filename);
		System.out.println(match);
		
		
	}
	
}
