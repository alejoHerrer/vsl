package net.jaimetorres.pila.approval.pojos.output.pensionados.salud;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public class PensionadosSaludTotalesRenglon37Out
extends CommonOutputRecord{
	
	private String registroTotalesRenglon37;
	private String tipoRegistro;
	private String valorIngresoBaseCotizacion;
	private String totalCotizacion;
	private String totalUpcAdicional;
	
	public String getRegistroTotalesRenglon37() {
		return registroTotalesRenglon37;
	}
	public void setRegistroTotalesRenglon37(String registroTotalesRenglon37) {
		this.registroTotalesRenglon37 = registroTotalesRenglon37;
	}
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getValorIngresoBaseCotizacion() {
		return valorIngresoBaseCotizacion;
	}
	public void setValorIngresoBaseCotizacion(String valorIngresoBaseCotizacion) {
		this.valorIngresoBaseCotizacion = valorIngresoBaseCotizacion;
	}
	public String getTotalCotizacion() {
		return totalCotizacion;
	}
	public void setTotalCotizacion(String totalCotizacion) {
		this.totalCotizacion = totalCotizacion;
	}
	public String getTotalUpcAdicional() {
		return totalUpcAdicional;
	}
	public void setTotalUpcAdicional(String totalUpcAdicional) {
		this.totalUpcAdicional = totalUpcAdicional;
	}
	
	
}
