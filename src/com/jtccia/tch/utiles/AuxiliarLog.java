package com.jtccia.tch.utiles;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AuxiliarLog {

	public static synchronized String obtieneExcepcion(Exception excepcion) {
		StringBuilder sb_excepciones = new StringBuilder();
		try {
			StackTraceElement[] elementosStack = excepcion.getStackTrace();
			StackTraceElement elementoStack = null;
			for (int i = 0; i < elementosStack.length; i++) {
				elementoStack = elementosStack[i];
				if (i == 0) {
					sb_excepciones.append(obtenerFechaCompleta() + "\n" + elementoStack.getClassName() + ": "
							+ excepcion.getMessage() + "\n");
				} else {
					sb_excepciones.append("\t at " + elementoStack.getClassName() + ":" + elementoStack.getMethodName()
							+ "(" + elementoStack.getFileName() + ":" + elementoStack.getLineNumber() + ")\n");
				}
			}
			return sb_excepciones.toString();
		} catch (Exception exception) {
			excepcion.printStackTrace();
			// System.err.println( this.getClass( ).getName() + ".obtieneExcepcion " + "Error General " + exception );
			return "";
		}
	}

	/**
	 * Obtiene la hora del sistema
	 * 
	 * @return Hora actual del sistema
	 */
	private static String obtenerFechaCompleta() {
		Calendar cal;
		cal = Calendar.getInstance();
		Locale language = new Locale("es", " ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM yyyy: hh:mm:ss,S a", language);
		return sdf.format(cal.getTime());
	}
}
