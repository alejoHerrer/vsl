package com.dwr.validadorHTRANS;

import com.jtccia.tch.utiles.AuxiliarErroresValidador;
import com.jtccia.tch.utiles.TiposMensajeValidador;

public class MensajesValidacion {

	private String descripcion;

	private int posicionInicial;

	private int posicionFinal;

	TiposMensajeValidador tipoMensaje;

	private String valorEsperado;

	private String valorEncontrado;

	private int numeroLinea;

	private String nombreArchivo;

	private static final String CADENA_VACIA = "";

	private static final String CADENA_POSINI = "|PosIni|";

	private static final String CADENA_POSFIN = "|PosFin|";

	private static final String CADENA_LINEA = "|Linea|";

	private static final String CADENA_VALOR_ENCONTRADO = "|ValorEncontrado|";

	private static final String CADENA_VALOR_ESPERADO = "|ValorEsperado|";

	private static final String CADENA_NOMBRE_ARCHIVO = "|NombreArchivo|";

	public MensajesValidacion() {
		descripcion = CADENA_VACIA;
		posicionInicial = 0;
		posicionFinal = 0;
		valorEsperado = CADENA_VACIA;
		valorEncontrado = CADENA_VACIA;
		numeroLinea = 0;
		nombreArchivo = CADENA_VACIA;
	}

	public MensajesValidacion(String descripcion, int posicionInicial, int posicionFinal, String valorEsperado,
			String valorEncontrado, int numeroLinea, String nombreArchivo) {

		this.descripcion = descripcion;
		this.setPosicionInicial(posicionInicial);
		this.setPosicionFinal(posicionFinal);
		this.valorEsperado = valorEsperado;
		this.valorEncontrado = valorEncontrado;
		this.numeroLinea = numeroLinea;
		this.nombreArchivo = nombreArchivo;
		setTipoMensaje(TiposMensajeValidador.ERROR);
	}
	
	//Constructor con las posiciones indicadas correctamente
	public MensajesValidacion(int numeroLinea, int posicionInicial, int posicionFinal,
			String valorEncontrado, String valorEsperado,
			String descripcionError,String nombreArchivo){
		
		this.numeroLinea = numeroLinea;
		this.posicionInicial = posicionInicial;
		this.posicionFinal = posicionFinal;
		this.valorEncontrado = valorEncontrado;
		this.valorEsperado = valorEsperado;
		this.descripcion = descripcionError;
		this.nombreArchivo = nombreArchivo;
		this.setTipoMensaje(TiposMensajeValidador.ERROR);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(int numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public int getPosicionFinal() {
		return posicionFinal;
	}

	public void setPosicionFinal(int posicionFinal) {
		this.posicionFinal = posicionFinal + 1;
	}

	public int getPosicionInicial() {
		return posicionInicial;
	}

	public void setPosicionInicial(int posicionInicial) {
		this.posicionInicial = posicionInicial + 1;
	}

	public String getValorEncontrado() {
		return valorEncontrado;
	}

	public void setValorEncontrado(String valorEncontrado) {
		this.valorEncontrado = valorEncontrado;
	}

	public String getValorEsperado() {
		return valorEsperado;
	}

	public void setValorEsperado(String valorEsperado) {
		this.valorEsperado = valorEsperado;
	}

	public String toString() {
		return descripcion + CADENA_POSINI + posicionInicial + CADENA_POSFIN + posicionFinal + CADENA_VALOR_ENCONTRADO
				+ valorEncontrado + CADENA_VALOR_ESPERADO + valorEsperado + CADENA_LINEA + numeroLinea
				+ CADENA_NOMBRE_ARCHIVO + nombreArchivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public TiposMensajeValidador getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(TiposMensajeValidador tipoMensaje) {
		this.tipoMensaje = tipoMensaje;

		if (getTipoMensaje().equals(TiposMensajeValidador.WARNING)) {
			AuxiliarErroresValidador.archivosConError.remove(nombreArchivo);
		} else {
			AuxiliarErroresValidador.archivosConError.add(nombreArchivo);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((nombreArchivo == null) ? 0 : nombreArchivo.hashCode());
		result = prime * result + numeroLinea;
		result = prime * result + posicionFinal;
		result = prime * result + posicionInicial;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MensajesValidacion other = (MensajesValidacion) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (nombreArchivo == null) {
			if (other.nombreArchivo != null)
				return false;
		} else if (!nombreArchivo.equals(other.nombreArchivo))
			return false;
		if (posicionFinal != other.posicionFinal)
			return false;
		if (posicionInicial != other.posicionInicial)
			return false;
		return true;
	}
	
	

}