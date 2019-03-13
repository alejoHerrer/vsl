package net.jaimetorres.pila.approval.pojos.output.pensionados.commons;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public class CommonPensionadosTotalesAporte 
extends CommonOutputRecord{
	
	private String registroTotalesRenglon31;
	private String tipoRegistro;
	private String totalIbc;
	private String totalCotizacion;
	
	public String getRegistroTotalesRenglon31() {
		return registroTotalesRenglon31;
	}
	public void setRegistroTotalesRenglon31(String registroTotalesRenglon31) {
		this.registroTotalesRenglon31 = registroTotalesRenglon31;
	}
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getTotalIbc() {
		return totalIbc;
	}
	public void setTotalIbc(String totalIbc) {
		this.totalIbc = totalIbc;
	}
	public String getTotalCotizacion() {
		return totalCotizacion;
	}
	public void setTotalCotizacion(String totalCotizacion) {
		this.totalCotizacion = totalCotizacion;
	}
}
