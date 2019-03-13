package net.jaimetorres.pila.approval.pojos.output.pensionados.commons;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public class CommonPensionadosTotalesOnly 
extends CommonOutputRecord{
	
	private String tipoRegistro;
	private String totalCotizacion;
	private String diasMora;
	private String moraTotalCotizacion;
	private String totalPagar;
	
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getTotalCotizacion() {
		return totalCotizacion;
	}
	public void setTotalCotizacion(String totalCotizacion) {
		this.totalCotizacion = totalCotizacion;
	}
	public String getDiasMora() {
		return diasMora;
	}
	public void setDiasMora(String diasMora) {
		this.diasMora = diasMora;
	}
	public String getMoraTotalCotizacion() {
		return moraTotalCotizacion;
	}
	public void setMoraTotalCotizacion(String moraTotalCotizacion) {
		this.moraTotalCotizacion = moraTotalCotizacion;
	}
	public String getTotalPagar() {
		return totalPagar;
	}
	public void setTotalPagar(String totalPagar) {
		this.totalPagar = totalPagar;
	}
}
