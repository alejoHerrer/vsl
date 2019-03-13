package net.jaimetorres.pila.approval.pojos.output.pensionados.fsp;

import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosLiquidacionDetalladaOutputFile;

public class PensionadosFspLiquidacionDetalladaOut
extends CommonPensionadosLiquidacionDetalladaOutputFile{
	
	private String diasCotizadosFsp;
	private String tarifaFsp;
	private String cotizacionFsp;
	
	public String getDiasCotizadosFsp() {
		return diasCotizadosFsp;
	}
	public void setDiasCotizadosFsp(String diasCotizadosFsp) {
		this.diasCotizadosFsp = diasCotizadosFsp;
	}
	public String getTarifaFsp() {
		return tarifaFsp;
	}
	public void setTarifaFsp(String tarifaFsp) {
		this.tarifaFsp = tarifaFsp;
	}
	public String getCotizacionFsp() {
		return cotizacionFsp;
	}
	public void setCotizacionFsp(String cotizacionFsp) {
		this.cotizacionFsp = cotizacionFsp;
	}
}
