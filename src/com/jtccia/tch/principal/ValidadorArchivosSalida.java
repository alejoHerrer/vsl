package com.jtccia.tch.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
import java.util.Vector;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jtccia.tch.utiles.AuxiliarErroresValidador;
import com.jtccia.tch.utiles.AuxiliarLog;
import com.jtccia.tch.utiles.Correo;
import com.jtccia.tch.utiles.Utilidades;

import net.jaimetorres.pila.approval.conf.ApprovalConfig;
import net.jaimetorres.pila.approval.core.ApprovalFileValidationBean;
import net.jaimetorres.pila.approval.core.misc.Constants;
import net.jaimetorres.pila.approval.core.misc.ErrorsWriter;
import net.jaimetorres.pila.approval.pojos.ApprovalValidationError;
import net.jaimetorres.pila.approval.pojos.ExecutionParameters;

public class ValidadorArchivosSalida {

	private ApplicationContext approvalCtx;

	public TreeSet<String> archConError = new TreeSet<String>();

	public TreeSet<String> planillasConErrorDivipola = new TreeSet<String>();

	public TreeSet<String> archConWarning = new TreeSet<String>();

	public static String INICIO_REG_TIPO1 = "000001";

	public static String VERSION = "ValidadorArchivosSalida Version 4.8.2";

	private String url, rutaBase, fechaPago, rutaErrados, destinatario1, destinatario2, destinatario3, rutaLog;

	private static Character SALTOLINEA1 = new Character('\r');

	/** Cadena para el final de los archivos IPR */

	private static final String CADENA_ARCHIVOS_AP = "AP";

	private static final String CADENA_ARCHIVOS_APR = "APR";

	private static Character SALTOLINEA2 = new Character('\n');

	public static Logger validadorLogger;

	private Properties properties;

	private boolean mueveArchivo;

	/* 891886 */
	private List<String> clasesRiesgos;
	/* 891886 */

	private Correo objCorreoTch;

	/** Cadena underscore */
	private static final String CADENA_UNDERSCORE = "_";

	private static final String CADENA_SLASH = "\\/";

	/** Cadena para el final de los archivos que se van a validar */
	private static final String CADENA_TXT = ".TXT";

	/** Cadena constante que para indicar rutaErrados **/
	private static final String RUTA_ERRADOS = "/usr/errados/f_interssi/86/";

	private static final String RUTA_BASE = "/usr/f_interssi/86/";

	/** Cadena para el final de los archivos I */
	private static final String CADENA_ARCHIVOS_I = "I";

	/** Cadena para el final de los archivos IR */
	private static final String CADENA_ARCHIVOS_IR = "IR";

	/** Cadena para el final de los archivos IP */
	private static final String CADENA_ARCHIVOS_IP = "IP";

	/** Cadena para el final de los archivos IPR */
	private static final String CADENA_ARCHIVOS_IPR = "IPR";

	private static String SEPARADOR = File.separator;
	/** Cadena vacía */
	private static final String CADENA_VACIA = "";

	private Connection conexion;

	/** Cadena para resolucion 634 */
	private static final String SEISTREINTAYCUATRO = "634";

	/** Cadena para resolucion 1747 */
	private static final String DIEZYSIETECUARENTAYSIETE = "1747";

	/** Cadena para resolucion 3336 */
	private static final String TREINTAYTRES_TREINTAYSEIS = "3336";

	/** Contiene la resolucion contra la que se valida programa */
	private String resolucion;

	/** Contiene el nombre de la tabla de estructura segun la resolucion */
	private String tablaEstruct;

	private char tipoAportante;

	/**
	 * Contiene las entidades presentes en el sistema (llave) y el tipo de la misma (valor)
	 */

	/**
	 * Opcion para saber si: Cuando queda con valor 1-> valida OtrosOperadores Cuando queda con valor 2-> valida
	 * Operador Propio Cuando queda con valor 3-> valida OtrosOperadores y Operador Propio
	 */
	private int opcionQueOperadoresVal;

	private String usuarioDB;

	private String contrasenaDB;

	private Date fechaInicio3336;

	/** Lista de archivos que se validarán */
	private List<File> listaActivosAValidar = null;

	/** Lista de archivos que se validarán */
	private List<File> listaAportantesAValidar = null;

	/** Lista de archivos que se validarán */
	private List<File> listaPensionadosAValidar = null;

	/** Tipo de archivo del negocio que se va a validar */
	private String tipoArchivo = "";
	
	/** Lista que contiene todos los errores de los archivos validados */
	private List<ApprovalValidationError> allAccumulatedErrorsList = new ArrayList<>();
	
	/** Codigo admin valido para consulta por carpeta */
	private String codigoAdminEspecifica;

	public ValidadorArchivosSalida(String _url, String _rutaBase, String _fechaPago, String _rutaErrados,
			String _destinatario1, String _destinatario2, String _destinatario3, String _rutaLog, String _mueveArchivo,
			String _operador, String usuario, String contrasena) {

		this.setUrl(_url);
		this.setRutaBase(_rutaBase);
		this.setFechaPago(_fechaPago);
		this.setRutaErrados(_rutaErrados);
		this.setDestinatario1(_destinatario1);
		this.setDestinatario2(_destinatario2);
		this.setDestinatario3(_destinatario3);
		this.setRutaLog(_rutaLog);
		this.setMueveArchivo(Boolean.valueOf(_mueveArchivo));
		this.setOpcionQueOperadoresVal(Integer.parseInt(_operador));
		this.setUsuarioDB(usuario);
		this.setContrasenaDB(contrasena);

		this.initApprovalContext();
	}
		
	public ValidadorArchivosSalida(String args[]) {
		
		ExecutionParameters.getInstance(args);
		this.setUrl(args[0]);
		this.setRutaBase(args[1]);
		this.setFechaPago(args[2]);
		this.setRutaErrados(args[3]);
		this.setDestinatario1(args[4]);
		this.setDestinatario2(args[5]);
		this.setDestinatario3(args[6]);
		this.setRutaLog(args[7]);
		this.setMueveArchivo(Boolean.valueOf(args[8]));
		this.setOpcionQueOperadoresVal(Integer.parseInt(args[9]));
		this.setUsuarioDB(args[10]);
		this.setContrasenaDB(args[11]);
		this.codigoAdminEspecifica = args[12];
		this.initApprovalContext();
		iniciarProceso();
	}

	/** Constructor que no tiene en cuenta la fecha de pago para empezar a validar, valida los archivos de un directorio */
	public ValidadorArchivosSalida(String _url, String _rutaBase, String _rutaErrados, String _destinatario1,
			String _destinatario2, String _destinatario3, String _rutaLog, String _mueveArchivo, String _operador,
			String usuario, String contrasena) {

		this.setUrl(_url);
		this.setRutaBase(_rutaBase);
		this.setFechaPago("");
		this.setRutaErrados(_rutaErrados);
		this.setDestinatario1(_destinatario1);
		this.setDestinatario2(_destinatario2);
		this.setDestinatario3(_destinatario3);
		this.setRutaLog(_rutaLog);
		this.setMueveArchivo(Boolean.valueOf(_mueveArchivo));
		this.setOpcionQueOperadoresVal(Integer.parseInt(_operador));
		this.setUsuarioDB(usuario);
		this.setContrasenaDB(contrasena);

		this.initApprovalContext();

	}

	private void initApprovalContext(){
		this.approvalCtx = new AnnotationConfigApplicationContext(ApprovalConfig.class); 
	}

	public static void main(String args[]) {

		ExecutionParameters.getInstance(args);
		long inicio = System.currentTimeMillis();

		int longPar = args.length;

		if (longPar == 12) {

			new ValidadorArchivosSalida(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7],
					args[8], args[9], args[10], args[11]).iniciarProceso();
		} else if (longPar == 11) {
			new ValidadorArchivosSalida(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7],
					args[8], args[9], args[10]).iniciarProceso2();
		}

		Enumeration e = validadorLogger.getAllAppenders();
		while (e.hasMoreElements()) {
			Appender app = (Appender) e.nextElement();
			if (app instanceof FileAppender) {
				System.out.println("LOG de errores -> " + ((FileAppender) app).getFile());
			}
		}

		System.out.println("Tiempo de ejecución -> " + (System.currentTimeMillis() - inicio) / 1000 );
		System.exit(0);
	}
	
	/** Proceso cuando se trata de validar los archivos de las administradoras de una fecha de pago parametrizada */
	public void iniciarProceso() {

		String fechaSistema = obtenerFecha('-', 4, "dd/mm/aaaa");
		String horaSistema = obtenerHora(false, '_');
		String nombreLog_Erores = "logValidador" + fechaSistema + "_" + horaSistema + ".log";

		//XXX PARA QUE NO INCLUYA LA FECHA Y HORA DEL SISTEMA
		//+ ".log";

		String archErroresDivipola = "archErrDivipola_" + fechaSistema + "_" + horaSistema + ".log";

		String nombreLogArchConErr = "logValidador_archConErr" + fechaSistema + "_" + horaSistema + ".log";
		configuraPropiedades(nombreLog_Erores);
		validadorLogger = Logger.getLogger(ValidadorArchivosSalida.class);
		objCorreoTch = new Correo(validadorLogger, this.getDestinatario1(), this.getDestinatario2(), this.getDestinatario3());
		conexion = null;
		try {

			cargarConfiguraciones();

			String emailSalida = "";
			String smtpHost = "";
			Hashtable<String, String> datosMail = null;

			enviarCorreo(nombreLog_Erores, emailSalida, smtpHost);
			
			Vector<String> administradoras = null;
			
			if(this.codigoAdminEspecifica != null){
				administradoras = new Vector<String>(Arrays.asList(this.codigoAdminEspecifica));
			}
			else{
				administradoras = obtenerAdministradoras();
			}
			 
			// Listando directorios de Administradoras

			File dirBase = new File(this.getRutaBase());

			String[] dirAdministradoras = dirBase.list();

			// Se recorre cada una de los directorios de las administradoras
			AuxiliarErroresValidador.archivosConError = new HashSet<String>();
			AuxiliarErroresValidador.archivosConWarning = new HashSet<String>();

			for (String adm : dirAdministradoras) {
				if (administradoras.contains(adm)) {

					File dirAdministradora = new File(this.getRutaBase() + adm);

					if (dirAdministradora.isDirectory()) {

						File directorioAExaminar = new File(this.getRutaBase() + adm + SEPARADOR + this.getFechaPago() + SEPARADOR);
						
						if(adm.equals(Constants.FOSYGA_CODIGO)){
							directorioAExaminar =  new File(this.getRutaBase() + adm);
						}
						
						Hashtable<Character, String> caracteresNoPermitidos = new Hashtable<Character, String>();

						if (directorioAExaminar.exists()) {
							System.out.println(VERSION);
							System.out.println("=============Validando Activos==========");
							procesarArchivosDirectorioActivos(directorioAExaminar, caracteresNoPermitidos);
							Utilidades.resetMap();
						} else {
							continue;
						}
					} else {
						// Se continua cuando el archivo encontrado no es un
						// directorio
						continue;
					}
				} else {
					// Se continua cuando el archivo encontrado no es un
					// directorio de una de las administradoras de operador 86
					continue;
				}
			}

			ErrorsWriter.write(validadorLogger, archConError, allAccumulatedErrorsList);
			archivosErrados(fechaSistema, horaSistema);
			
						// Corta los archivos de ruta Errados y los devuelve a
			// /usr/f_interssi/86/
			
			cutFile(administradoras, dirAdministradoras);
			
		} catch (Exception e) {
			e.printStackTrace();
			validadorLogger.error(AuxiliarLog.obtieneExcepcion(e));
			// TODO: handle exception
		} finally {
			try {

				if (generarNuevoLog(nombreLogArchConErr, archConError)) {
					objCorreoTch.adjuntarArchivo(getRutaLog()
							+ nombreLogArchConErr);
				}

				if ( planillasConErrorDivipola.size() >0 ){
					if (generarNuevoLog(archErroresDivipola, planillasConErrorDivipola)) {
						objCorreoTch.adjuntarArchivo(getRutaLog()
								+ archErroresDivipola);
					}			
				}

				File archivoAdjuntar = new File(this.getRutaLog()
						+ nombreLog_Erores);

				if (archivoAdjuntar.length() == 0) {
					archivoAdjuntar.delete();
					objCorreoTch.fijarTitulo("NO SE ENCONTRARON ERRORES D03");
				} else {
					objCorreoTch.adjuntarArchivo(this.getRutaLog() + nombreLog_Erores);
				}
				if (conexion != null)
					conexion.close();

			} catch (Exception e) {
				System.err.println("Errores por favor verifique el log");
				validadorLogger.error(AuxiliarLog.obtieneExcepcion(e));
				// e.printStackTrace();
			}
		}
	}

	private void cargarConfiguraciones() throws Exception {
		conexion = obtenerConexion();
		obtenerFechaInicio3336();
	}

	private void enviarCorreo(String nombreLog_Erores, String emailSalida, String smtpHost) throws Exception {
		Hashtable<String, String> datosMail;
		datosMail = obtenerDatosMailOperador("86");
		if (datosMail.get("origen") != null)
			emailSalida = datosMail.get("origen").toString();
		if (datosMail.get("smtp") != null)
			smtpHost = datosMail.get("smtp").toString();

		objCorreoTch.fijarTitulo(nombreLog_Erores);
		// objCorreo.adicionarDestinatario( );
		objCorreoTch.setOrigen(emailSalida);
		objCorreoTch.setSmtpHost(smtpHost);
	}

	private void archivosErrados(String fechaSistema, String horaSistema) {
		Iterator<String> nombresArchivo = AuxiliarErroresValidador.archivosConError.iterator();
		String nombreyRutaArchivo = "";
		String nombreYRutaArchTipoA = "";
		while (nombresArchivo.hasNext()) {

			nombreyRutaArchivo = nombresArchivo.next();
			nombreYRutaArchTipoA = nombreyRutaArchivo.replace("86_I", "86_A");
			File f_archivoErrado = new File(nombreyRutaArchivo);
			String partesNombre[] = nombreyRutaArchivo.split(CADENA_SLASH);

			String rutaDestino = "";
			try {
				rutaDestino = getRutaErrados()
						+ partesNombre[4] + SEPARADOR + partesNombre[5] + SEPARADOR;
			} catch (ArrayIndexOutOfBoundsException e) {
				String nvaRutaDestino = getRutaErrados()
						+ fechaSistema + "_" + horaSistema + SEPARADOR;
				File dirNuevaRutaDes = new File(nvaRutaDestino);
				if (!dirNuevaRutaDes.exists()) {
					dirNuevaRutaDes.mkdirs();
				}
				rutaDestino = nvaRutaDestino;
			}

			File dirErrados = new File(rutaDestino);

			if (!dirErrados.exists()) {
				dirErrados.mkdirs();
			}

			if (mueveArchivo && copiarOmoverArchivo(f_archivoErrado, rutaDestino, new Character('m'))) {
				//
				File f_archivoErradoTipoA = new File(nombreYRutaArchTipoA);
				if (f_archivoErradoTipoA.exists()) {
					copiarOmoverArchivo(f_archivoErradoTipoA, rutaDestino, new Character('m'));
				}
				int esActualizado = actualizaIndicadorD03(f_archivoErrado.getName(), 1);// Se actualia a 1 indicando
				// que
				// archivo tiene error de
				// estructura
				if (esActualizado != 1) {
					validadorLogger.error("==El archivo no se encontraba en relacion_planos:" + f_archivoErrado.getName());
				}
			}

		}
	}

	private void cutFile(Vector<String> administradoras, String[] dirAdministradoras) {
		if (getRutaBase().equals(RUTA_ERRADOS)) {

			// Se recorre cada una de los directorios de las administradoras
			for (String nombreDirAdmon : dirAdministradoras) {
				if (administradoras.contains(nombreDirAdmon)) {

					File dirAdministradora = new File(this.getRutaBase()
							+ nombreDirAdmon);
					if (dirAdministradora.isDirectory()) {
						String dirFechaPago = this.getRutaBase()
								+ nombreDirAdmon + SEPARADOR + this.getFechaPago() + SEPARADOR;
						File directorioAExaminar = new File(dirFechaPago);
						String[] archivos = directorioAExaminar.list();
						for (String nombreArch : archivos) {
							File f_archivo = new File(dirFechaPago
									+ nombreArch);
							if (mueveArchivo
									&& f_archivo.exists()
									&& !AuxiliarErroresValidador.archivosConError.contains(f_archivo.getName())) {

								copiarOmoverArchivo(f_archivo, RUTA_BASE
										+ nombreDirAdmon + SEPARADOR + getFechaPago() + SEPARADOR, 'm');
								/* Se actualia a 2 indicando que archivo tenia errores y ha sido corregido */
								int esActualizado = actualizaIndicadorD03(f_archivo.getName(), 2);
								if (esActualizado != 1) {
									validadorLogger.error("==El archivo no se encontraba en relacion_planos:"
											+ f_archivo.getName());
								}
							}
						}
					}
				}
			}
		}
	}

	private File generarArchErrDivipola() {
		// TODO Auto-generated method stub
		return null;
	}

	/** Proceso cuando se trata de validar los archivos de un directorio determinado */

	public void iniciarProceso2() {

		String fechaSistema = obtenerFecha('-', 4, "dd/mm/aaaa");
		String horaSistema = obtenerHora(false, '_');
		String nombreLog_Erores = "logValidador"
				+ fechaSistema + "_" + horaSistema + ".log";
		String nombreLogArchConErr = "logValidador_archConErr"
				+ fechaSistema + "_" + horaSistema + ".log";

		String archErroresDivipola = "archErrDivipola_"
				+ fechaSistema + "_" + horaSistema + ".log";

		configuraPropiedades(nombreLog_Erores);
		validadorLogger = Logger.getLogger(ValidadorArchivosSalida.class);
		objCorreoTch = new Correo(validadorLogger, this.getDestinatario1(), this.getDestinatario2(),
				this.getDestinatario3());
		conexion = null;
		try {

			cargarConfiguraciones();

			String emailSalida = "";
			String smtpHost = "";
			Hashtable<String, String> datosMail = null;

			enviarCorreo(nombreLog_Erores, emailSalida, smtpHost);

			// Se recorre cada una de los directorios de las administradoras
			AuxiliarErroresValidador.archivosConError = new HashSet<String>();
			AuxiliarErroresValidador.archivosConWarning = new HashSet<String>();

			File directorioAExaminar = new File(this.getRutaBase());

			Hashtable<Character, String> caracteresNoPermitidos = new Hashtable<Character, String>();

			if (directorioAExaminar.exists()) {
				procesarArchivosDirectorioActivos(directorioAExaminar, caracteresNoPermitidos);
			}

			Iterator<String> nombresArchivo = AuxiliarErroresValidador.archivosConError.iterator();
			String nombreyRutaArchivo = "";
			String nombreYRutaArchTipoA = "";

			while (nombresArchivo.hasNext()) {

				nombreyRutaArchivo = nombresArchivo.next();
				nombreYRutaArchTipoA = nombreyRutaArchivo.replace("86_I", "86_A");
				File f_archivoErrado = new File(nombreyRutaArchivo);
				// String partesNombre[] = nombreyRutaArchivo.split(CADENA_SLASH);

				String rutaDestino = getRutaErrados();
				File dirErrados = new File(rutaDestino);

				if (!dirErrados.exists()) {
					dirErrados.mkdirs();
				}

				if (mueveArchivo
						&& copiarOmoverArchivo(f_archivoErrado, rutaDestino, new Character('m'))) {
					//
					File f_archivoErradoTipoA = new File(nombreYRutaArchTipoA);
					if (f_archivoErradoTipoA.exists()) {
						copiarOmoverArchivo(f_archivoErradoTipoA, rutaDestino, new Character('m'));
					}

					int esActualizado = actualizaIndicadorD03(f_archivoErrado.getName(), 1);// Se actualia a 1 indicando
					// que archivo tiene error
					// de estructura
					if (esActualizado != 1) {
						validadorLogger.error("==El archivo no se encontraba en relacion_planos:"
								+ f_archivoErrado.getName());
					}

				}

			}

		} catch (Exception e) {
			// e.printStackTrace();
			validadorLogger.error(e.getMessage(), e);
			// TODO: handle exception
		} finally {
			try {

				if (generarNuevoLog(nombreLogArchConErr, archConError)) {
					objCorreoTch.adjuntarArchivo(getRutaLog()
							+ nombreLogArchConErr);
				}

				if ( planillasConErrorDivipola.size() >0 ){
					if (generarNuevoLog(archErroresDivipola, planillasConErrorDivipola)) {
						objCorreoTch.adjuntarArchivo(getRutaLog()
								+ archErroresDivipola);
					}			
				}

				File archivoAdjuntar = new File(this.getRutaLog()
						+ nombreLog_Erores);

				if (archivoAdjuntar.length() == 0) {
					archivoAdjuntar.delete();
					objCorreoTch.fijarTitulo("NO SE ENCONTRARON ERRORES D03");
				} else {
					// System.out.println("TAMANO : " +
					// archivoAdjuntar.length());
					objCorreoTch.adjuntarArchivo(this.getRutaLog()
							+ nombreLog_Erores);
				}

				if (conexion != null)
					conexion.close();

			} catch (Exception e) {
				validadorLogger.error(AuxiliarLog.obtieneExcepcion(e));
				e.printStackTrace();
			}
		}
	}
	
	// T379958
	private void obtenerFechaInicio3336() {
		// TODO Auto-generated method stub
		Statement stEnunciado = null;
		ResultSet rsParametro = null;
		String s_fecha = "";
		try {
			stEnunciado = conexion.createStatement();
			rsParametro = stEnunciado
					.executeQuery("SELECT par_valor FROM parametros where par_nombre = 'fechaInicio3336' AND par_estado = 'A'");
			while (rsParametro.next()) {
				fechaInicio3336 = Date.valueOf(rsParametro.getString(1));
			}
		} catch (Exception e) {
			validadorLogger.error("Err2-->"
					+ e.getMessage(), e);
		} finally {
			try {
				if (rsParametro != null) {
					rsParametro.close();
				}
				if (stEnunciado != null) {
					stEnunciado.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				validadorLogger.error(e.getMessage(), e);
			}
		}
	}

	private boolean generarNuevoLog(String _nombreLogArchConErr, TreeSet<String> _archConError) {
		File f_archConErr = new File(getRutaLog()
				+ _nombreLogArchConErr);
		RandomAccessFile raf_archConErr = null;

		try {
			raf_archConErr = new RandomAccessFile(f_archConErr, "rw");

			for (String nomArchivo : _archConError) {
				try {
					raf_archConErr.writeBytes(nomArchivo
							+ SALTOLINEA1 + SALTOLINEA2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			System.err.println("Por favor verifique parametro log: "
					+ getRutaLog() + _nombreLogArchConErr);

			// e.printStackTrace();
		} finally {
			if (raf_archConErr != null) {
				try {
					raf_archConErr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		if (f_archConErr.length() > 0) {
			return true;
		} else {
			return false;
		}
		// TODO Auto-generated method stub

	}

	private int actualizaIndicadorD03(String _nombreArchivo, int _indicador) {
		// TODO Auto-generated method stub
		Statement stInterssi = null;
		int retorno = 0;
		try {
			stInterssi = conexion.createStatement();
			retorno = stInterssi.executeUpdate("UPDATE relacion_planos SET rp_indica_errorD03 = "
					+ _indicador + " WHERE rp_nombre_arch = '" + _nombreArchivo + "'");
		} catch (Exception e) {
			validadorLogger.error(AuxiliarLog.obtieneExcepcion(e));
		}
		return retorno;
	}

	/**
	 * @param _operacion
	 *            Cuando el valor de este entero es 1, indicara que se debe mover el archivo, cuando es 0 indicara que
	 *            se debe copiar
	 * */
	private boolean copiarOmoverArchivo(File _archivoSalida, String _rutaDestino, Character _operacion) {

		// System.out.println("_archivoSalida:" +
		// _archivoSalida.getAbsolutePath() + " rutaDestino:" + _rutaDestino);

		RandomAccessFile archivoDestino = null;
		FileReader fr_Reader = null;
		BufferedReader br_Archivo = null;

		File f_destino = null;

		try {
			File rutaDestino = new File(_rutaDestino);
			if (!rutaDestino.exists()) {
				rutaDestino.mkdirs();
			}
			f_destino = new File(_rutaDestino
					+ _archivoSalida.getName());
			archivoDestino = new RandomAccessFile(f_destino, "rw");
			fr_Reader = new FileReader(_archivoSalida);
			br_Archivo = new BufferedReader(fr_Reader);
			String linea = "";
			while ((linea = br_Archivo.readLine()) != null) {
				archivoDestino.writeBytes(linea
						+ SALTOLINEA1 + SALTOLINEA2);
			}
		} catch (FileNotFoundException fne) {
			// System.out.println( "_rutaDestino->" + _rutaDestino );
			// System.out.println("_getName->" + _archivoSalida.getName( ) );
			if (_archivoSalida.getName().equals("descargados")) {
				f_destino.delete();
			}
			// System.out.println("Ruta _archivoSalida" + _archivoSalida.getAbsolutePath( ) );

			validadorLogger.error(AuxiliarLog.obtieneExcepcion(fne));
			// fne.printStackTrace();
			return false;
		} catch (IOException ioe) {
			validadorLogger.error(AuxiliarLog.obtieneExcepcion(ioe));
			// ioe.printStackTrace();
			return false;
		} catch (Exception e) {
			validadorLogger.error(AuxiliarLog.obtieneExcepcion(e));
			// e.printStackTrace();
			return false;
		} finally {
			try {
				if (_operacion.equals(new Character('m'))) {// Si la operacion
					// es mover el
					// archivo
					// System.out.println("ELIMINANDO:" +
					// _archivoSalida.getAbsolutePath());
					_archivoSalida.delete();// Se elimina el archivo original
					// despues de haberlo copiado
				}
				if (archivoDestino != null)
					archivoDestino.close();
				if (br_Archivo != null)
					br_Archivo.close();
				if (fr_Reader != null)
					fr_Reader.close();
			} catch (Exception e) {
				validadorLogger.error(AuxiliarLog.obtieneExcepcion(e));
				// e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private boolean moverArchivo(File origen, String destino) {
		try {
			System.out.println("moverArchivo(File origen, String destino) - Inicio");
			File rDestino = new File(destino);
			File fDestino = null;
			boolean hecho = false;
			hecho = rDestino.isDirectory();
			if (!hecho) {
				hecho = rDestino.mkdir();
				if (!hecho) {
					return false;
				}
			}
			System.out.println("ya existe - continua");
			fDestino = new File(rDestino
					+ origen.getName());
			fDestino.setWritable(true);
			fDestino.setReadable(true);
			FileChannel in = (new FileInputStream(origen)).getChannel();
			FileChannel out = (new FileOutputStream(fDestino)).getChannel();
			in.transferTo(0, origen.length(), out);
			in.close();
			out.close();
			if (fDestino.exists()
					&& fDestino.isFile()) {
				origen.delete();
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	private Hashtable<String, String> obtenerDatosMailOperador(String codigoOperador) throws Exception {
		Hashtable<String, String> datosMail = new Hashtable<String, String>();
		Statement enunciado = null;
		Connection conexion = null;
		ResultSet resultado = null;
		try {
			conexion = obtenerConexion();
			enunciado = conexion.createStatement();
			resultado = enunciado
					.executeQuery("SELECT opr_mail_origen, opr_smtp FROM operadores_inf WHERE opr_codigo = "
							+ codigoOperador);
			if (resultado.next()) {
				datosMail.put("origen", resultado.getString(1));
				datosMail.put("smtp", resultado.getString(2));
			}
		} catch (SQLException sqle) {
			validadorLogger.error("ERR"
					+ AuxiliarLog.obtieneExcepcion(sqle));
			// sqle.printStackTrace( );
			throw sqle;
		} finally {
			try {
				if (resultado != null)
					resultado.close();
				if (enunciado != null)
					enunciado.close();
				if (conexion != null)
					conexion.close();
			} catch (Exception e) {
				validadorLogger.error("ERR" + AuxiliarLog.obtieneExcepcion(e));
				throw e;
			}
		}
		return datosMail;
	}

	private void configuraPropiedades(String _nombreLog) {

		if (new File(this.getRutaLog()).exists()) {
			properties = new Properties();
			properties.put("log4j.logger.com.jtccia.tch.principal.ValidadorArchivosSalida", "ALL, appenderD03");
			
			properties.put("log4j.appender.appenderD03", "org.apache.log4j.FileAppender");
			properties.put("log4j.appender.appenderD03.file", this.getRutaLog() + _nombreLog);
			properties.put("log4j.appender.appenderD03.layout", "org.apache.log4j.SimpleLayout");
			properties.put("log4j.appender.appenderD03.append", "false");

			PropertyConfigurator.configure(properties);

		} else {
			System.err.println("ERROR al configurar el log, verifique que la ruta del log parametrizada exista");
		}

	}

	/**
	 * Procesa y valida los archivos de activos presentes en un directorio
	 * 
	 * @param directorio
	 *            Directorio inicial de búsqueda
	 * @throws Exception
	 *             En caso de error
	 */

	private void procesarArchivosDirectorioActivos(File directorio, Hashtable<Character, String> caracteresNoPermitidos)
					throws Exception {
 
		ApprovalFileValidationBean approvalFileValidationBean = approvalCtx.getBean(ApprovalFileValidationBean.class);

		System.out.println("Ruta de busqueda -> " + directorio.getAbsolutePath());

		try {

			// TODO validar (despues) que el vector no esté vacío
			File[] archivos = directorio.listFiles();
			listaActivosAValidar = new ArrayList<File>();
			/*
			 * Se genera un lista de todos los archivos que se deben validar al validador correspondiente (listaAValidar)
			 */
			for (File file : archivos) {
				archivosAValidar(file, "activos");
			}		
			
			Utilidades.cargarMapaCotizantes(listaActivosAValidar);
			
			for (File archivo : listaActivosAValidar) {
				System.out.println("Validando archivo -> " + archivo.getName());
				
				String nombreArch = archivo.getName();

				if ( nombreArch.endsWith("txt") ){
					validadorLogger.error("Archivo con extensión en minúscula: " + nombreArch);
					archConError.add(archivo.getAbsolutePath());
				}
				allAccumulatedErrorsList.addAll(approvalFileValidationBean.validateOutputFile(archivo,validadorLogger,archConError));
			}
			Utilidades.validationDual();
			if (Utilidades.getErrorsList().size()>0) {
				allAccumulatedErrorsList.addAll(Utilidades.getErrorsList());
			}
		} catch (Exception e) {
			throw e;
		} finally{

		}

	}

	/** Define si se debe o no validar el objeto {@link File} según reglas del negocio */
	private boolean seDebeValidar(File fileValidar) {
		String nombreArchivo = fileValidar.getName();
		if (fileValidar.isFile()) {

			String elementosNombre[] = fileValidar.getName().split(CADENA_UNDERSCORE);
			int cantPartes = elementosNombre.length;

			/* Fecha de pago obtenida del nombre del archivo */
			java.sql.Date fechaPago_nombreArchivo = null;
			try {
				fechaPago_nombreArchivo = java.sql.Date.valueOf(elementosNombre[0]);
			} catch (Exception e) {
				System.err.println("El nombre del archivo no es correcto");
				validadorLogger
				.error("El nombre del archivo no es correcto - Revisar la fecha de pago que viene en el nombre del archivo");
				System.exit(0);
			}/* Fecha de pago obtenida del nombre del archivo */

			int posOperador = 6; // Por defecto resoluciòn 634

			int cantPartesArchivo = 9;
			int indiceTipoArchivo = 7;

			if (cantPartes == 7) {
				setResolucion(SEISTREINTAYCUATRO);
				posOperador = 5;
			} else if (cantPartes == 9
					|| cantPartes == 8) {
				setResolucion(DIEZYSIETECUARENTAYSIETE);
			} else {
				System.out.println("continua por no encontrar Resolucion "
						+ nombreArchivo);
				return false;
			}

			if (fechaPago_nombreArchivo.compareTo(fechaInicio3336) >= 0) {
				setResolucion(TREINTAYTRES_TREINTAYSEIS);
			} 
			if (getResolucion().equals(SEISTREINTAYCUATRO)) {
				cantPartesArchivo = 7;
				indiceTipoArchivo = 6;
			}
			if (nombreArchivo.indexOf(CADENA_ARCHIVOS_IP) != -1
					|| nombreArchivo.indexOf(CADENA_ARCHIVOS_AP) != -1
					|| nombreArchivo.indexOf(CADENA_ARCHIVOS_APR) != -1) {
				// System.out.println("Se omite en activos->" + archivo.getAbsolutePath());
				return false;
			}

			if (elementosNombre.length < indiceTipoArchivo) {
				System.out.println("No se debe validar:"
						+ fileValidar.getAbsolutePath());
				return false;
			}
			try {
				tipoArchivo = elementosNombre[indiceTipoArchivo];
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			if (!nombreArchivo.endsWith(CADENA_TXT)
					&& !nombreArchivo.endsWith("txt")) {
				// System.out.println("Continue2->" + archivo.getName());
				return false;
			}
			// System.out.println(archivo.getName() + " cantPartes:" +
			// elementosNombre.length);

			if ((resolucion.equals(SEISTREINTAYCUATRO) && cantPartes != 7)
					|| (resolucion.equals(DIEZYSIETECUARENTAYSIETE)
							&& cantPartes != 8 && cantPartes != 9)) {
				return false;
			}

			int operador = 0;
			try {
				operador = Integer.parseInt(elementosNombre[posOperador]);

				// Valida solo Otros Operadores
				if (opcionQueOperadoresVal == 1
						&& operador == 86) {
					// System.out.println("Archivo Operador Propio: " + nombreArchivo);
					return false;
				}
				// Valida Operador Propio
				if (opcionQueOperadoresVal == 2
						&& operador != 86) {
					// System.out.println("Archivo de otro operador: " + nombreArchivo);
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				validadorLogger
				.error("com.jtccia.tch.principal.ValidadorArchivosSalida.seDebeValidar(File) Excep se esperaba encontrar valor numérico al obtener el código del operador del nombre del archivo: "
						+ nombreArchivo, e);
				return false;
			}
		} else if (fileValidar.getName() != null
				&& fileValidar.isDirectory() && fileValidar.getName().equalsIgnoreCase("descargados")) {
			return true;
		}
		return true;
	}

	/** Define si se debe o no validar el objeto {@link File} según reglas del negocio */
	private boolean seDebeValidarAportante(File fileValidar) {
		String nombreArchivo = fileValidar.getName();
		if (fileValidar.isFile()) {
			String[] elementosNombre = nombreArchivo.split("_");

			/* Fecha de pago obtenida del nombre del archivo */
			java.sql.Date fechaPago_nombreArchivo = null;
			try {
				fechaPago_nombreArchivo = java.sql.Date.valueOf(elementosNombre[0]);
			} catch (Exception e) {
				System.err.println("El nombre del archivo no es correcto");
				validadorLogger
				.error("El nombre del archivo no es correcto - Revisar la fecha de pago que viene en el nombre del archivo");
				System.exit(0);
			}/* Fecha de pago obtenida del nombre del archivo */

			// 2011-09-06_2_8603589311_NI_900406744_CCF40_86_A_2011-08.TXT
			int cantPartes = elementosNombre.length;

			int posOperador = 6; // Por defecto resoluciòn 1747
			if (cantPartes == 7) {
				setResolucion(SEISTREINTAYCUATRO);
				posOperador = 5;
			} else if (cantPartes == 9
					|| cantPartes == 8) {
				setResolucion(DIEZYSIETECUARENTAYSIETE);

			}else {
				return false;
			}

			if (fechaPago_nombreArchivo.compareTo(fechaInicio3336) >= 0) {
				setResolucion(TREINTAYTRES_TREINTAYSEIS);
			} 

			int operador = 0;
			try {
				operador = Integer.parseInt(elementosNombre[posOperador]);

				// Valida solo Otros Operadores
				if (opcionQueOperadoresVal == 1
						&& operador == 86) {
					// System.out.println("Archivo Operador Propio: " + nombreArchivo);
					return false;
				}
				// Valida Operador Propio
				if (opcionQueOperadoresVal == 2
						&& operador != 86) {
					// System.out.println("Archivo de otro operador: " + nombreArchivo);
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				validadorLogger
				.error("com.jtccia.tch.principal.ValidadorArchivosSalida.seDebeValidarAportante(File) Excep se esperaba encontrar valor numérico al obtener el código del operador del nombre del archivo: "
						+ nombreArchivo, e);
				// logValidador.warn("Archivo de otro operador: " + operador
				// + "cantPartes:" + cantPartes + " reso:" +
				// getResolucion());
				return false;
			}

		} else if (fileValidar.getName() != null
				&& fileValidar.isDirectory() && fileValidar.getName().equalsIgnoreCase("descargados")) {
			return true;
		}
		return true;
	}

	/** Define si se debe o no validar el objeto {@link File} según reglas del negocio */
	private boolean seDebeValidarPensionado(File fileValidar) {
		String nombreArchivo = fileValidar.getName();
		if (fileValidar.isFile()) {
			String[] elementosNombre = nombreArchivo.split("_");

			// 2011-09-06_2_8603589311_NI_900406744_CCF40_86_A_2011-08.TXT
			// int cantPartes = elementosNombre.length;

			/* Fecha de pago obtenida del nombre del archivo */
			java.sql.Date fechaPago_nombreArchivo = null;
			try {
				fechaPago_nombreArchivo = java.sql.Date.valueOf(elementosNombre[0]);
			} catch (Exception e) {
				System.err.println("El nombre del archivo no es correcto");
				validadorLogger
				.error("El nombre del archivo no es correcto - Revisar la fecha de pago que viene en el nombre del archivo");
				System.exit(0);
			}/* Fecha de pago obtenida del nombre del archivo */

			int cantPartes = elementosNombre.length;

			int posOperador = 6; // Por defecto resoluciòn 1747
			if (cantPartes == 7) {
				setResolucion(SEISTREINTAYCUATRO);
				posOperador = 5;
			} else if (cantPartes == 9
					|| cantPartes == 8) {
				setResolucion(DIEZYSIETECUARENTAYSIETE);

			} else {
				return false;
			}

			if (fechaPago_nombreArchivo.compareTo(fechaInicio3336) >= 0) {
				setResolucion(TREINTAYTRES_TREINTAYSEIS);
			}

			int operador = 0;
			try {
				operador = Integer.parseInt(elementosNombre[posOperador]);

				// Valida solo Otros Operadores
				if (opcionQueOperadoresVal == 1
						&& operador == 86) {
					// System.out.println("Archivo Operador Propio: " + nombreArchivo);
					return false;
				}
				// Valida Operador Propio
				if (opcionQueOperadoresVal == 2
						&& operador != 86) {
					// System.out.println("Archivo de otro operador: " + nombreArchivo);
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				validadorLogger
				.error("ValidadorArchivosSalida.seDebeValidarPensionado(File) Excep se esperaba encontrar valor numérico al obtener el código del operador del nombre del archivo: "
						+ nombreArchivo, e);
				// logValidador.warn("Archivo de otro operador: " + operador
				// + "cantPartes:" + cantPartes + " reso:" +
				// getResolucion());
				return false;
			}

		} else if (fileValidar.getName() != null
				&& fileValidar.isDirectory() && fileValidar.getName().equalsIgnoreCase("descargados")) {
			return true;
		}
		return true;
	}

	/** Retorna la lista de objetos {@link File} que se validarán */
	private void archivosAValidar(File fileValidar, String tipoDirectorio) {
		String nomArch =  fileValidar.getName(); 
		String partesArch[] = nomArch.split("_");


		boolean isNombreArchivoValidoAdministradoras = partesArch.length > 8;
		boolean isNombreArchivoValidoMinps = partesArch[0].startsWith("PIL");
		boolean isNombreArchivoValidoFosyga = fileValidar.toString().contains(Constants.FOSYGA_CODIGO);
		boolean isExtensionArchivoValida = nomArch.endsWith("TXT") ||  nomArch.endsWith("txt");
		tipoDirectorio = isNombreArchivoValidoMinps ? Constants.MINPS_CODIGO : isNombreArchivoValidoFosyga ? Constants.FOSYGA_CODIGO: tipoDirectorio;
		
		if ((!isNombreArchivoValidoAdministradoras  || !isNombreArchivoValidoMinps) && !isExtensionArchivoValida){return;}

		if (tipoDirectorio.equalsIgnoreCase("aportante")) {

			//ARCHIVOS TIPO A (ACTIVOS)
			String[] partes = nomArch.split("_");
			if (fileValidar.isFile()) {
				if (partes.length >= 6 && (partes[7].equals("A") || partes[7].equals("AR")) ) {
					if (seDebeValidar(fileValidar)) {
						listaAportantesAValidar.add(fileValidar);
					}
				}
			} else if (nomArch.equalsIgnoreCase("descargados")) {

				File[] lista = fileValidar.listFiles();
				for (File file : lista) {
					String[] partes2 = file.getName().split("_");
					if (partes2[7].equals("A") || partes2[7].equals("AR")) {
						if (seDebeValidar(file)) {
							listaAportantesAValidar.add(file);
						}
					}
				}
			}
		} else if (tipoDirectorio.equalsIgnoreCase("activos")) {


			String[] partes = nomArch.split("_");
			if (fileValidar.isFile()) {
				
				//TODOS LOS ARCHIVOS SE DEBEN VALIDAR
				listaActivosAValidar.add(fileValidar);
				
			} else if (nomArch.equalsIgnoreCase("descargados")) {
				System.out.println("ACT            -> "
						+ fileValidar.getAbsolutePath());
				File[] lista = fileValidar.listFiles();
				for (File file : lista) {
					String[] partes2 = file.getName().split("_");
					if (	//Registro Tipo A
							partes[7].equals("A") || partes[7].equals("AR") ||
							//Registro Tipo I
							partes2[7].equals("I") || partes2[7].equals("IR")) {
						if (seDebeValidarAportante(file)) {
							listaActivosAValidar.add(file);
						}
					}
				}
			}
		} else if (tipoDirectorio.equalsIgnoreCase("pensionados")) {
			String[] partes = fileValidar.getName().split("_");
			if (fileValidar.isFile()) {
				if (partes[7].equals("AP")
						|| partes[7].equals("APR") || partes[7].equals("IP")) {
					if (seDebeValidarPensionado(fileValidar)) {
						listaPensionadosAValidar.add(fileValidar);
					}
				}
			} else if (nomArch.equalsIgnoreCase("descargados")) {
				System.out.println("ACT            -> "
						+ fileValidar.getAbsolutePath());
				File[] lista = fileValidar.listFiles();
				for (File file : lista) {
					String[] partes2 = file.getName().split("_");
					if (partes2[7].equals("AP")
							|| partes2[7].equals("APR") || partes2[7].equals("IP")) {
						if (seDebeValidarPensionado(file)) {
							listaPensionadosAValidar.add(file);
						}
					}
				}
			}
		}
		else if(tipoDirectorio.equals(Constants.MINPS_CODIGO) || tipoDirectorio.equals(Constants.FOSYGA_CODIGO)){
			listaActivosAValidar.add(fileValidar);
		}
	}

	// T379958 Resolución 1300 que en un inicio se llamo CREE, modifia en Activos las estucturas de archivos I de
	// Parafiscales y la estructura de Datos del Aportante
	// private void obtenerCamposArchivoSalidaCREE() throws Exception {
	// }
	
	private Vector<String> obtenerAdministradoras() {
		Vector<String> administradoras = new Vector<String>();

		Statement stInterssi = null;
		ResultSet rsInterssi = null;
		try {
			conexion = obtenerConexion();
			stInterssi = conexion.createStatement();

			rsInterssi = stInterssi.executeQuery("SELECT DISTINCT pse_codigo FROM operadores_entidades");
			while (rsInterssi.next()) {
				administradoras.add(rsInterssi.getString(1));
			}

			administradoras.add("25-10");
			administradoras.add(Constants.MINPS_CODIGO);
			administradoras.add(Constants.FOSYGA_CODIGO);

		} catch (Exception e) {
			validadorLogger.error(e.getMessage(), e);
			// e.printStackTrace();
		} finally {
			try {
				if (rsInterssi != null)
					rsInterssi.close();
				if (stInterssi != null)
					stInterssi.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// System.out.println("En metdodo");
		return administradoras;
	}

	/**
	 * Metodo que obtiene la conexion a través de la URL
	 */
	private Connection obtenerConexion() throws Exception {
		Connection cn = null;
		try {
			Driver driver = null;
			driver = (Driver) Class.forName("org.postgresql.Driver").newInstance();
			Properties propiedades = new Properties();
			propiedades.put("user", getUsuarioDB());
			propiedades.put("password", getContrasenaDB());
			cn = driver.connect(this.getUrl(), propiedades);

		} catch (Exception exc) {
			System.err.println("ERROR al configurar la URL de BD. Por favor verifique, puede ser permisos: "
					+ this.getUrl());
			throw exc;
		}
		return cn;
	}

	/**
	 * Obtiene la fecha actual del sistema con el formato especificado por par&aacute;metro
	 * 
	 * @param separador
	 *            Separador de la fecha
	 * @param cantDigYear
	 *            Cantidad de d&iacute;gitos del a&ntilde;o
	 * @param formato
	 *            Formato de la fecha (aaaa/mm/dd &oacute; dd/mm/aaaa)
	 * @return La fecha actual del sistema.
	 */
	private String obtenerFecha(char separador, int cantDigYear, String formato) {
		Calendar cal = Calendar.getInstance();
		String fecha = "";
		// Se obtiene el ano.
		String strYear = String.valueOf(cal.get(Calendar.YEAR));
		if (cantDigYear == 2) {
			if (strYear.length() > 2) {
				strYear = strYear.substring(strYear.length() - 2, strYear.length());
			}
		}
		// Se obtiene el mes.
		String strMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
		if (strMonth.length() < 2) {
			strMonth = "0"
					+ strMonth;
		}
		// Se obtiene el dia.
		String strDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		if (strDay.length() < 2) {
			strDay = '0' + strDay;
		}
		// Se fija el formato deseado.
		if (formato.equals("aaaa/mm/dd")) {
			fecha = strYear
					+ separador + strMonth + separador + strDay;
		} else if (formato.equals("dd/mm/aaaa")) {
			fecha = strDay
					+ separador + strMonth + separador + strYear;
		}
		return fecha;
	}

	/**
	 * Obtiene la hora del sistema.
	 * 
	 * @param milisegundos
	 *            Indica si se deben retornar tambi&eacute;n los milisegundos (true)
	 * @return La hora del sistema
	 */
	private String obtenerHora(boolean milisegundos, char separador) {
		StringBuilder sB_hora = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		// Obtiene la hora actual.
		String strAux = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		if (strAux.length() < 2) {
			sB_hora.append('0');
			sB_hora.append(strAux);
		} else {
			sB_hora.append(strAux);
		}
		sB_hora.append(separador);
		// Obtiene el minuto actual.
		strAux = String.valueOf(cal.get(Calendar.MINUTE));
		if (strAux.length() < 2) {
			sB_hora.append('0');
			sB_hora.append(strAux);
		} else {
			sB_hora.append(strAux);
		}
		sB_hora.append(separador);
		// Obtiene el segundo actual.
		strAux = String.valueOf(cal.get(Calendar.SECOND));
		if (strAux.length() < 2) {
			sB_hora.append('0');
			sB_hora.append(strAux);
		} else {
			sB_hora.append(strAux);
		}
		if (milisegundos) {
			// Se obtiene los milisegundos actuales.
			strAux = String.valueOf(cal.get(Calendar.MILLISECOND));
			if (strAux.length() < 2) {
				sB_hora.append(strAux);
			} else {
				sB_hora.append(strAux);
			}
		}
		return sB_hora.toString();
	}

	public String getRutaBase() {
		return rutaBase;
	}

	public void setRutaBase(String rutaBase) {
		this.rutaBase = rutaBase;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getRutaErrados() {
		return rutaErrados;
	}

	public void setRutaErrados(String rutaErrados) {
		this.rutaErrados = rutaErrados;
	}

	public String getDestinatario1() {
		return destinatario1;
	}

	public void setDestinatario1(String destinatario1) {
		this.destinatario1 = destinatario1;
	}

	public String getDestinatario2() {
		return destinatario2;
	}

	public void setDestinatario2(String destinatario2) {
		this.destinatario2 = destinatario2;
	}

	public String getDestinatario3() {
		return destinatario3;
	}

	public void setDestinatario3(String destinatario3) {
		this.destinatario3 = destinatario3;
	}

	public String getRutaLog() {
		return rutaLog;
	}

	public void setRutaLog(String rutaLog) {
		this.rutaLog = rutaLog;
	}

	public String getTablaEstruct() {
		return tablaEstruct;
	}

	public void setTablaEstruct(String tablaEstruct) {
		this.tablaEstruct = tablaEstruct;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public boolean isMueveArchivo() {
		return mueveArchivo;
	}

	public void setMueveArchivo(boolean mueveArchivo) {
		this.mueveArchivo = mueveArchivo;
	}

	public int getOpcionQueOperadoresVal() {
		return opcionQueOperadoresVal;
	}

	public void setOpcionQueOperadoresVal(int opcionQueOperadoresVal) {
		this.opcionQueOperadoresVal = opcionQueOperadoresVal;
	}

	public String getUsuarioDB() {
		return this.usuarioDB;
	}

	public void setUsuarioDB(String usuarioDB) {
		this.usuarioDB = usuarioDB;
	}

	public String getContrasenaDB() {
		return this.contrasenaDB;
	}

	public void setContrasenaDB(String contrasenaDB) {
		this.contrasenaDB = contrasenaDB;
	}

	public char getTipoAportante() {
		return tipoAportante;
	}

	public void setTipoAportante(char tipoAportante) {
		this.tipoAportante = tipoAportante;
	}

	public List<ApprovalValidationError> getAllAccumulatedErrorsList() {
		return allAccumulatedErrorsList;
	}

	public void setAllAccumulatedErrorsList(List<ApprovalValidationError> allAccumulatedErrorsList) {
		this.allAccumulatedErrorsList = allAccumulatedErrorsList;
	}	
	
}