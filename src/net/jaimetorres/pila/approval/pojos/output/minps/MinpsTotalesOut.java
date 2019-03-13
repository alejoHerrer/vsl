package net.jaimetorres.pila.approval.pojos.output.minps;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public abstract class MinpsTotalesOut extends CommonOutputRecord{

	private String tipoRegistro;
	private String nroRegistro;
	private String codigoOperador;
	private String nroPlanilla;
	
	private String codigoAdministradora;
	private String nroIdentificacionAdministradora;
	private String dvAdministradora;

	private String cotizacion;
	private String diasMora;
	private String moraCotizacion;
	private String totalPago;
	
	private String nroAfiliados;

	public final String getTipoRegistro() {
		return tipoRegistro;
	}

	public final void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public final String getNroRegistro() {
		return nroRegistro;
	}

	public final void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	public final String getCodigoOperador() {
		return codigoOperador;
	}

	public final void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}

	public final String getNroPlanilla() {
		return nroPlanilla;
	}

	public final void setNroPlanilla(String nroPlanilla) {
		this.nroPlanilla = nroPlanilla;
	}

	public final String getCodigoAdministradora() {
		return codigoAdministradora;
	}

	public final void setCodigoAdministradora(String codigoAdministradora) {
		this.codigoAdministradora = codigoAdministradora;
	}

	public final String getNroIdentificacionAdministradora() {
		return nroIdentificacionAdministradora;
	}

	public final void setNroIdentificacionAdministradora(String nroIdentificacionAdministradora) {
		this.nroIdentificacionAdministradora = nroIdentificacionAdministradora;
	}

	public final String getDvAdministradora() {
		return dvAdministradora;
	}

	public final void setDvAdministradora(String dvAdministradora) {
		this.dvAdministradora = dvAdministradora;
	}

	public final String getCotizacion() {
		return cotizacion;
	}

	public final void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	public final String getDiasMora() {
		return diasMora;
	}

	public final void setDiasMora(String diasMora) {
		this.diasMora = diasMora;
	}

	public final String getMoraCotizacion() {
		return moraCotizacion;
	}

	public final void setMoraCotizacion(String moraCotizacion) {
		this.moraCotizacion = moraCotizacion;
	}

	public final String getTotalPago() {
		return totalPago;
	}

	public final void setTotalPago(String totalPago) {
		this.totalPago = totalPago;
	}

	public final String getNroAfiliados() {
		return nroAfiliados;
	}

	public final void setNroAfiliados(String nroAfiliados) {
		this.nroAfiliados = nroAfiliados;
	}

}
