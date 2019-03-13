package net.jaimetorres.pila.approval.pojos.output.adres;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public class AdresTotalesOut 
extends CommonOutputRecord{
	
	private String nroRegistro;
	private String tipoRegistro;
	private String codigoOperador;
	private String fechaPago;
	private String nroPlanillasPagadas;
	
	private String totalCotizacion;
	private String totalMoraCotizacion;
	private String totalUpcAdicional;
	private String totalMoraUpcAdicional;
	
	public String getNroRegistro() {
		return nroRegistro;
	}
	public void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getCodigoOperador() {
		return codigoOperador;
	}
	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getTotalCotizacion() {
		return totalCotizacion;
	}
	public void setTotalCotizacion(String totalCotizacion) {
		this.totalCotizacion = totalCotizacion;
	}
	public String getTotalMoraCotizacion() {
		return totalMoraCotizacion;
	}
	public void setTotalMoraCotizacion(String totalMoraCotizacion) {
		this.totalMoraCotizacion = totalMoraCotizacion;
	}
	public String getTotalUpcAdicional() {
		return totalUpcAdicional;
	}
	public void setTotalUpcAdicional(String totalUpcAdicional) {
		this.totalUpcAdicional = totalUpcAdicional;
	}
	public String getTotalMoraUpcAdicional() {
		return totalMoraUpcAdicional;
	}
	public void setTotalMoraUpcAdicional(String totalMoraUpcAdicional) {
		this.totalMoraUpcAdicional = totalMoraUpcAdicional;
	}
	public String getNroPlanillasPagadas() {
		return nroPlanillasPagadas;
	}
	public void setNroPlanillasPagadas(String nroPlanillasPagadas) {
		this.nroPlanillasPagadas = nroPlanillasPagadas;
	}
}
