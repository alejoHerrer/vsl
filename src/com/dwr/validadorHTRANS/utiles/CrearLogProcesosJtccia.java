package com.dwr.validadorHTRANS.utiles;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Registra informacion de errores y procesos en un archivo
 */

public class CrearLogProcesosJtccia {

	private static final long serialVersionUID = 1L;

	/** Maneja el archivo a escribir */
	private RandomAccessFile archivoLogProcesos;

	/** Objeto para obtener datos de hora y fecha */
	private Calendar cal;

	/** Nombre del archivo */
	private String nombreArchivo;

	/** Posicion a escribir en el archivo */
	private long ultimaPosicion;

	private Locale language;

	private SimpleDateFormat sdf;

	private static String SALTO_LINEA = "\n";

	private static String CADENA_VACIA = "";

	private static String CADENA_RW = "rw";

	private static String CADENA_PUNTO = ".";

	private static String CADENA_DOS_PUNTOS = ":";

	/**
	 * Constructor de la clase Obtiene la ultima posicion de escritura del archivo
	 * 
	 * @param nombreArchivo
	 *            Nombre del archivo de procesos
	 */

	public CrearLogProcesosJtccia(String nombreArchivo) {
		try {
			this.nombreArchivo = nombreArchivo;
			File archivo = new File(nombreArchivo);
			if (archivo.exists()) {
				ultimaPosicion = archivo.length();
			} else {
				ultimaPosicion = 0;
			}
			language = new Locale("es", " ");
			sdf = new SimpleDateFormat("dd 'de' MMMM yyyy: hh:mm:ss,S a", language);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor por defecto de la clase
	 */

	public CrearLogProcesosJtccia() {
		nombreArchivo = CADENA_VACIA;
		ultimaPosicion = 0;
	}

	/**
	 * Adiciona un mensaje al log de procesos
	 * 
	 * @param mensaje
	 *            Mensaje que se va a adicionar al log
	 */

	public synchronized void adicionarMensaje(String mensaje, boolean sinFechaCompleta) {
		try {
			if (!nombreArchivo.equals(CADENA_VACIA)) {

				archivoLogProcesos = new RandomAccessFile(nombreArchivo, CADENA_RW);
				ultimaPosicion = archivoLogProcesos.length();
				archivoLogProcesos.seek(ultimaPosicion);
				if (!sinFechaCompleta) {
					archivoLogProcesos.writeBytes(obtenerFechaCompleta() + SALTO_LINEA + mensaje + SALTO_LINEA);
				} else {
					archivoLogProcesos.writeBytes(mensaje + SALTO_LINEA);
				}
				archivoLogProcesos.close();
			} else {
				System.err.println(this.getClass().getName() + ".adicionarMensaje "
						+ "Falta fijar el nombre del archivo de log de procesos.\n"
						+ "Intente utilizar el constructor que recibe como parametro el nombre "
						+ "del archivo o utilice el metodo fijarNombreArchivo(String)");
			}
		} catch (Exception exception) {
			System.err.println(this.getClass().getName() + ".adicionarMensaje Error General " + exception);
		}
	}

	/**
	 * Adiciona un mensaje al log de procesos
	 * 
	 * @param mensaje
	 *            Mensaje que se va a adicionar al log
	 */

	public synchronized void adicionarMensaje(String mensaje) {
		try {
			if (!nombreArchivo.equals(CADENA_VACIA)) {
				archivoLogProcesos = new RandomAccessFile(nombreArchivo, CADENA_RW);
				ultimaPosicion = archivoLogProcesos.length();
				archivoLogProcesos.seek(ultimaPosicion);
				archivoLogProcesos.writeBytes(obtenerFechaCompleta() + SALTO_LINEA + mensaje + SALTO_LINEA);
				archivoLogProcesos.close();
			} else {
				System.err.println(this.getClass().getName() + ".adicionarMensaje "
						+ "Falta fijar el nombre del archivo de log de procesos.\n"
						+ "Intente utilizar el constructor que recibe como parametro el nombre "
						+ "del archivo o utilice el metodo fijarNombreArchivo(String)");
			}
		} catch (Exception exception) {
			System.err.println(this.getClass().getName() + ".adicionarMensaje Error General " + exception);
		}
	}

	/**
	 * Adiciona la excepcion al log de procesos
	 * 
	 * @param excepcion
	 *            Excepcion a adicionar
	 */

	public synchronized void adicionarExcepcionALog(Exception excepcion) {
		try {
			if (!nombreArchivo.equals(CADENA_VACIA)) {
				archivoLogProcesos = new RandomAccessFile(nombreArchivo, CADENA_RW);
				ultimaPosicion = archivoLogProcesos.length();
				archivoLogProcesos.seek(ultimaPosicion);

				StringBuffer stack = new StringBuffer();
				StackTraceElement[] elementosStack = excepcion.getStackTrace();
				StackTraceElement elementoStack = null;

				int tam = elementosStack.length;
				for (int i = 0; i < tam; i++) {
					elementoStack = elementosStack[i];
					if (i == 0) {
						stack.append(obtenerFechaCompleta() + SALTO_LINEA + elementoStack.getClassName() + ": "
								+ excepcion.getMessage() + SALTO_LINEA);
					}
					stack.append("\t at " + elementoStack.getClassName() + CADENA_DOS_PUNTOS
							+ elementoStack.getMethodName() + "(" + elementoStack.getFileName() + CADENA_DOS_PUNTOS
							+ elementoStack.getLineNumber() + ")\n");
				}
				stack.append(SALTO_LINEA + SALTO_LINEA);
				archivoLogProcesos.writeBytes(stack.toString());
				archivoLogProcesos.close();
			} else {
				System.err.println(this.getClass().getName() + ".adicionarExcepcionALog "
						+ "Falta fijar el nombre del archivo del log de procesos.\n"
						+ "Intente utilizar el constructor que recibe como parametro el nombre "
						+ "del archivo o utilice el metodo fijarNombreArchivo(String)");
			}
		} catch (Exception exception) {
			System.err.println(this.getClass().getName() + ".adicionarExcepcionALog Error General " + exception);
		}
	}

	/**
	 * Fija el nombre de archivo del log de procesos
	 * 
	 * @param nombreArchivo
	 *            Nombre del archivo del log de procesos
	 */

	public void fijarNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
		File archivo = new File(nombreArchivo);
		if (archivo.exists()) {
			ultimaPosicion = archivo.length();
		} else {
			ultimaPosicion = 0;
		}
	}

	/**
	 * Retorna el nombre del archivo de log
	 * 
	 * @return Nombre del archivo de log
	 */

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * Obtiene la hora del sistema
	 * 
	 * @return Hora actual del sistema
	 */

	private String obtenerFechaCompleta() {
		cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}

	/**
	 * 
	 * @param mensaje
	 * @param metodo
	 * @param e
	 * @param nombreClase
	 */

	public void registrarError(String mensaje, String metodo, Exception e, String nombreClase) {
		adicionarMensaje(mensaje + SALTO_LINEA + nombreClase + CADENA_PUNTO + metodo);
		adicionarExcepcionALog(e);
	}
}