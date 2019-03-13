package net.jaimetorres.pila.approval.pojos.output.pensionados.cajas;

import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosLiquidacionDetalladaOutputFile;

public class PensionadosCajasLiquidacionDetalladaOut
extends CommonPensionadosLiquidacionDetalladaOutputFile{
	
	private String tarifaCajas;
	private String cotizacionCajas;
	private String diasCotizadosCajas;
	
	public String getTarifaCajas() {
		return tarifaCajas;
	}
	public void setTarifaCajas(String tarifaCajas) {
		this.tarifaCajas = tarifaCajas;
	}
	public String getCotizacionCajas() {
		return cotizacionCajas;
	}
	public void setCotizacionCajas(String cotizacionCajas) {
		this.cotizacionCajas = cotizacionCajas;
	}
	public String getDiasCotizadosCajas() {
		return diasCotizadosCajas;
	}
	public void setDiasCotizadosCajas(String diasCotizadosCajas) {
		this.diasCotizadosCajas = diasCotizadosCajas;
	}
}
