package net.jaimetorres.pila.approval.pojos.output.adres;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public class AdresPlanillaTotalesOut 
extends CommonOutputRecord{

	private String nroRegistro;
	private String tipoRegistro;
	private String codigoOperador;
	private String nroPlanilla;
	private String codigoAdmSalud;
	private String periodoPagoSalud;
	private String fechaPago;
	private String totalCotizacionSalud;
	private String diasMora;
	private String totalMoraCotizacion;
	private String totalUpcAdicional;
	private String toalMoraUpcAdicional;
	
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
	public String getNroPlanilla() {
		return nroPlanilla;
	}
	public void setNroPlanilla(String nroPlanilla) {
		this.nroPlanilla = nroPlanilla;
	}
	public String getCodigoAdmSalud() {
		return codigoAdmSalud;
	}
	public void setCodigoAdmSalud(String codigoAdmSalud) {
		this.codigoAdmSalud = codigoAdmSalud;
	}
	public String getPeriodoPagoSalud() {
		return periodoPagoSalud;
	}
	public void setPeriodoPagoSalud(String periodoPagoSalud) {
		this.periodoPagoSalud = periodoPagoSalud;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getTotalCotizacionSalud() {
		return totalCotizacionSalud;
	}
	public void setTotalCotizacionSalud(String totalCotizacionSalud) {
		this.totalCotizacionSalud = totalCotizacionSalud;
	}
	public String getDiasMora() {
		return diasMora;
	}
	public void setDiasMora(String diasMora) {
		this.diasMora = diasMora;
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
	public String getToalMoraUpcAdicional() {
		return toalMoraUpcAdicional;
	}
	public void setToalMoraUpcAdicional(String toalMoraUpcAdicional) {
		this.toalMoraUpcAdicional = toalMoraUpcAdicional;
	}
}
