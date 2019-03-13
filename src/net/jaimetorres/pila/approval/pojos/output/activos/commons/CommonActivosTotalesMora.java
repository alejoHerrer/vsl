package net.jaimetorres.pila.approval.pojos.output.activos.commons;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public abstract class CommonActivosTotalesMora 
extends CommonOutputRecord{

	private String registroTotalesRenglon36;
	private String tipoRegistro;
	private String diasMora;
	private String moraCotizacion;
	
	public String getDiasMora() {
		return diasMora;
	}
	public void setDiasMora(String diasMora) {
		this.diasMora = diasMora;
	}
	public String getMoraCotizacion() {
		return moraCotizacion;
	}
	public void setMoraCotizacion(String moraCotizacion) {
		this.moraCotizacion = moraCotizacion;
	}
	public String getRegistroTotalesRenglon36() {
		return registroTotalesRenglon36;
	}
	public void setRegistroTotalesRenglon36(String registroTotalesRenglon36) {
		this.registroTotalesRenglon36 = registroTotalesRenglon36;
	}
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
}
