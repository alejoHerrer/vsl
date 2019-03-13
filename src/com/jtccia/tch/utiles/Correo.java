package com.jtccia.tch.utiles;

/**
 * Librerias importadas para el funcionamiento de la clase EnviarEmail.
 */

import java.io.File;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

/**
 * <p>
 * <code>EnviarMail.java</code> Esta clase es la encargada de enviar un e-mail a n destinatarios con un contenido, un
 * titulo y con n archivos adjuntos fijando el e-mail de origen y el smtp host por donde desea salir.
 * 
 * @author L&aacute;zaro Miguel Coronado Torres.
 * @version 1.0, Apr 22, 2005
 */

public class Correo {
	private Logger logProcesosJtccia;
	private Vector<String> destinatarios;
	private String origen;
	private String contenido;
	private String titulo;
	private String smtpHost;
	private Vector<String> rutaArchivosAdjuntos;

	/**
	 * M&eacute;todo que crea la clase que env&iacute;a un mail con texto de contenido y/o archivos adjuntos.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que crea la clase que env&iacute;a un mail con texto de contenido y/o
	 * archivos adjuntos.
	 * <p>
	 * 
	 * @param crearLogProcesosJtccia
	 *            Clase que maneja el log de procesos del sistema.
	 */

	public Correo(String _destinatarioSoporte) {// Correo para Soporte
		destinatarios = new Vector<String>();
		this.adicionarDestinatario(_destinatarioSoporte);
		// System.out.println("constructor1");
		origen = "";
		contenido = "";
		titulo = "";
		smtpHost = "";
		rutaArchivosAdjuntos = new Vector<String>();

	}

	public Correo(String _destinatario1Asc, String _destinatario2Asc) { // Correo Informativo para Asocajas
		destinatarios = new Vector<String>();
		this.adicionarDestinatario(_destinatario1Asc);
		this.adicionarDestinatario(_destinatario2Asc);

		// System.out.println("constructor2");

		origen = "";
		contenido = "";
		titulo = "";
		smtpHost = "";
		rutaArchivosAdjuntos = new Vector<String>();
	}

	public Correo(Logger _logProcesosJtccia, String _destinatarioDirTch, String _destinatarioDevelop,
			String _tercerDestinatario) { // Correo con Excepciones para Tecnologia

		destinatarios = new Vector<String>();

		if (!_destinatarioDirTch.trim().equals("")) {
			// System.out.println("Adicionando _destinatarioDirTch de correo: " + _destinatarioDirTch);
			this.adicionarDestinatario(_destinatarioDirTch);
		}

		if (!_destinatarioDevelop.trim().equals("")) {
			// System.out.println("Adicionando _destinatarioDevelop de correo: " + _destinatarioDevelop);
			this.adicionarDestinatario(_destinatarioDevelop);
		}

		if (!_tercerDestinatario.trim().equals("")) {
			// System.out.println("Adicionando _tercerDestinatario de correo: " + _tercerDestinatario);
			this.adicionarDestinatario(_tercerDestinatario);
		}

		this.logProcesosJtccia = _logProcesosJtccia;
		this.adicionarDestinatario(_destinatarioDirTch);
		this.adicionarDestinatario(_destinatarioDevelop);
		origen = "";
		contenido = "";
		titulo = "";
		smtpHost = "";
		rutaArchivosAdjuntos = new Vector<String>();
	}

	/**
	 * M&eacute;todo que obtiene los destinatarios a los cuales se les va a enviar el e-mail.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que obtiene los destinatarios a los cuales se les va a enviar el
	 * e-mail.
	 * <p>
	 * 
	 * @return Vector con los nombres de los destinatarios.
	 */

	/**
	 * M&eacute;todo que adiciona un destinatario para enviar el e-mail.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que adiciona un destinatario para enviar el e-mail.
	 * <p>
	 * 
	 * @param destinatario
	 *            Direcci&oacute;n electr&oacute;nica al cual se le va a enviar el e-mail.
	 */
	private void adicionarDestinatario(String destinatario) {
		if (isEmail(destinatario)) {
			// System.out.println("destinatario==>"+destinatario);
			destinatarios.add(destinatario);
		}
	}

	private boolean isEmail(String destinatario) {
		// TODO Auto-generated method stub

		int rtaArroba = destinatario.indexOf("@");
		if (rtaArroba == -1) {
			return false;
		} else {
			String nombreServidor = destinatario.substring(rtaArroba);
			if (nombreServidor.length() == 0) {
				return false;
			} else {
				int rtaPunto = nombreServidor.indexOf(".");
				if (rtaPunto == -1) {
					return false;
				}
			}
		}
		return true;
	}

	public void limpiarDestinatarios() {
		destinatarios = new Vector<String>();
	}

	/**
	 * M&eacute;todo que retorna la direcci&oacute;n electr&oacute;nica de origen del env&iacute;a del e-mail.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que retorna la direcci&oacute;n electr&oacute;nica de origen del
	 * env&iacute;a del e-mail.
	 * <p>
	 * 
	 * @return Direcci&oacute;n electr&oacute;nica al cual env&iacute;a el e-mail.
	 */

	public String getOrigen() {
		return origen;
	}

	/**
	 * M&eacute;todo que fija la direcci&oacute;n electr&oacute;nica de origen del env&iacute;a del e-mail.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que fija la direcci&oacute;n electr&oacute;nica de origen del
	 * env&iacute;a del e-mail.
	 * <p>
	 * 
	 * @param origen
	 *            Direcci&oacute;n electr&oacute;nica al cual env&iacute;a el e-mail.
	 */

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * M&eacute;todo que retorna el contenido a enviar.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que retorna el contenido a enviar.
	 * <p>
	 * 
	 * @return Contenido que se va a enviar.
	 */

	public String obtenerContenido() {
		return contenido;
	}

	/**
	 * M&eacute;todo que fija el contenido a enviar.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que fija el contenido a enviar.
	 * <p>
	 * 
	 * @param contenido
	 *            Contenido que se va a enviar.
	 */

	public void fijarContenido(String contenido) {
		this.contenido = contenido;
	}

	/**
	 * M&eacute;todo que retorna el titulo del e-mail.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que retorna el titulo del e-mail.
	 * <p>
	 * 
	 * @return Titulo que van a ver los o el destinatario(s).
	 */
	public String obtenerTitulo() {
		return titulo;
	}

	/**
	 * M&eacute;todo que fija el titulo del e-mail.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que fija el titulo del e-mail.
	 * <p>
	 * 
	 * @param titulo
	 *            Titulo que van a ver los o el destinatario(s).
	 */

	public void fijarTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * M&eacute;todo que retorna el host SMTP por el cual el e-mail va a salir.
	 * 
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que retorna el host SMTP por el cual el e-mail va a salir.
	 * <p>
	 * 
	 * @return Host SMTP por el cual el e-mail va a salir.
	 */

	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * M&eacute;todo que fija el host SMTP por el cual el e-mail va a salir.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que fija el host SMTP por el cual el e-mail va a salir.
	 * <p>
	 * 
	 * @param smtpHost
	 *            Host SMTP por el cual el e-mail va a salir.
	 */

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * M&eacute;todo que limpia el vector de la ruta de archivos adjuntos que se van en el e-mail.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que limpia el vector de la ruta de archivos adjuntos que se van en el
	 * e-mail.
	 * <p>
	 */

	public void limpiarRutaArchivosAdjuntos() {
		rutaArchivosAdjuntos = new Vector<String>();
	}

	/**
	 * M&eacute;todo que retorna el vector con los archivos que se van a adjuntar al e-mail.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que retorna el vector con los archivos que se van a adjuntar al e-mail.
	 * <p>
	 * 
	 * @return Vector con las rutas de los archivos a adjuntar al e-mail.
	 */

	/**
	 * M&eacute;todo que crea el mensaje con que se va a enviar a los destinatarios con el contenido correspondiente y
	 * los archivos adjuntos si los hay y los env&iacute;a.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que crea el mensaje con que se va a enviar a los destinatarios con el
	 * contenido correspondiente y los archivos adjuntos si los hay y los env&iacute;a.
	 * <p>
	 * 
	 * @return true si el e-mail se env&iacute;o, false de lo contrario.
	 */

	public boolean enviar() {
		boolean debug = false;
		// Fija el host
		Properties propiedades = new Properties();
		propiedades.put("mail.smtp.host", smtpHost);
		// Crea algunas propiedades y obtiene la sesion por defecto.
		Session sesion = Session.getDefaultInstance(propiedades, null);
		sesion.setDebug(debug);
		try {
			// Crea un mensaje
			Message mensaje = new MimeMessage(sesion);
			InternetAddress direccionOrigen = new InternetAddress(origen);
			// Se fija el correo de origen.
			mensaje.setFrom(direccionOrigen);

			// System.out.println("**" + destinatarios.size( ));
			InternetAddress[] direcciones = new InternetAddress[destinatarios.size()];
			// Se recorre el vector de destinatarios.
			for (int contadorDestinatarios = 0; contadorDestinatarios < destinatarios.size(); contadorDestinatarios++) {
				// Se fija el destinatario.
				direcciones[contadorDestinatarios] = new InternetAddress(
						(String) destinatarios.elementAt(contadorDestinatarios));
			}
			// Se fijan los recipientes.
			mensaje.setRecipients(Message.RecipientType.TO, direcciones);
			// Se fija el titulo
			mensaje.setSubject(titulo);
			mensaje.setHeader("X-Mailer", "msgsend");
			// Se fija la fecha de envio.
			mensaje.setSentDate(new Date());
			// Se crea la clase para crear multiples partes para el mensaje
			Multipart multiPart = new MimeMultipart();
			// Se crea la parte del texto plano.
			BodyPart bodyPartTexto = new MimeBodyPart();
			// Se fija el contenido de la parte del texto plano.
			bodyPartTexto.setContent(contenido, "text/plain");
			// Se adiciona al multiPart
			multiPart.addBodyPart(bodyPartTexto, 0);
			// Se verifica que se desea adjuntar archivos.
			if (rutaArchivosAdjuntos.size() > 0) {
				// Se recorre el vector con las rutas de los archivos adjuntos.
				for (int contadorArchivos = 0; contadorArchivos < rutaArchivosAdjuntos.size(); contadorArchivos++) {
					// Se obtiene la ruta del archivo a adjuntar
					String rutaArchivo = (String) rutaArchivosAdjuntos.elementAt(contadorArchivos);
					// Se obtiene el nombre del archivo.
					String nombreArchivo = rutaArchivo.substring(
							(rutaArchivo.lastIndexOf(System.getProperty("file.separator")) + 1), rutaArchivo.length());
					// Se crea la parte para adjuntar el archivo.
					BodyPart bodyPartAttach = new MimeBodyPart();
					// Se crea el datasource para el archivo a adjuntar
					DataSource archivoAdjunto = new FileDataSource(rutaArchivo);
					// Se fija el manejador de datos.
					bodyPartAttach.setDataHandler(new DataHandler(archivoAdjunto));
					// Se fija el nombre del archivo.
					bodyPartAttach.setFileName(nombreArchivo);
					// Se adiciona la parte del archivo adjunto al multipart.
					multiPart.addBodyPart(bodyPartAttach, (contadorArchivos + 1));
				}
			}
			// Se fija el contenido.
			mensaje.setContent(multiPart);
			// Se salvan los cambios en el mensaje
			mensaje.saveChanges();
			// Se envia el mensaje a los destinatarios.
			Transport.send(mensaje);

		} catch (MessagingException mex) {
			// mex.printStackTrace();
			// System.err.println
			// ("Err al enviar correo, Verifique el log y/o verifique direcciones válidas en parámetros destinatarios"
			// );
			logProcesosJtccia.error(AuxiliarLog.obtieneExcepcion(mex));
			return false;
		} catch (Exception ex) {
			// ex.printStackTrace();
			// System.err.println
			// ("Err al enviar correo, Verifique el log y/o verifique direcciones válidas en parámetros destinatarios"
			// );
			logProcesosJtccia.error(AuxiliarLog.obtieneExcepcion(ex));
			return false;
		}
		return true;
	}

	/**
	 * M&eacute;todo que adjunta un archivo verificando que la ruta del archivo existe.
	 * <p>
	 * <b> Descripci&oacute;n </b> M&eacute;todo que adjunta un archivo verificando que la ruta del archivo existe.
	 * <p>
	 * 
	 * @param rutaArchivo
	 *            Ruta completa del archivo a adjuntar.
	 * @return true si se pudo adjuntar la ruta del archivo, false de lo contrario.
	 */
	public boolean adjuntarArchivo(String rutaArchivo) {
		boolean adjuntoArchivo = false;
		try {
			File archivoAdjunto = new File(rutaArchivo);
			if (archivoAdjunto.exists()) {
				rutaArchivosAdjuntos.add(rutaArchivo);
				adjuntoArchivo = true;
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			logProcesosJtccia.error(AuxiliarLog.obtieneExcepcion(excepcion));
			adjuntoArchivo = false;
		}
		return adjuntoArchivo;
	}

}