package net.jaimetorres.pila.approval.pojos.output.activos.cajas;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosLiquidacionDetalladaOutputFile;

public class ActivosCajasLiquidacionDetalladaOut extends CommonActivosLiquidacionDetalladaOutputFile {

	private String diasNovedadIncapacidadRiesgosProfesionales;
	private String diasCotizadosCajas;
	private String ibcCajas;
	private String tarifaCajas;
	private String cotizacionCajas;
	
	private String fechaInicioVCT;
	private String fechaFinVCT;
	private String fechaInicioIRL;
	private String fechaFinIRL;
	private String nroHorasLaboradas;
	
	public String getDiasNovedadIncapacidadRiesgosProfesionales() {
		return diasNovedadIncapacidadRiesgosProfesionales;
	}
	public void setDiasNovedadIncapacidadRiesgosProfesionales(String diasNovedadIncapacidadRiesgosProfesionales) {
		this.diasNovedadIncapacidadRiesgosProfesionales = diasNovedadIncapacidadRiesgosProfesionales;
	}
	public String getDiasCotizadosCajas() {
		return diasCotizadosCajas;
	}
	public void setDiasCotizadosCajas(String diasCotizadosCajas) {
		this.diasCotizadosCajas = diasCotizadosCajas;
	}
	public String getIbcCajas() {
		return ibcCajas;
	}
	public void setIbcCajas(String ibcCajas) {
		this.ibcCajas = ibcCajas;
	}
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
	public String getFechaInicioIRL() {
		return fechaInicioIRL;
	}
	public void setFechaInicioIRL(String fechaInicioIRL) {
		this.fechaInicioIRL = fechaInicioIRL;
	}
	public String getFechaFinIRL() {
		return fechaFinIRL;
	}
	public void setFechaFinIRL(String fechaFinIRL) {
		this.fechaFinIRL = fechaFinIRL;
	}
	public String getNroHorasLaboradas() {
		return nroHorasLaboradas;
	}
	public void setNroHorasLaboradas(String nroHorasLaboradas) {
		this.nroHorasLaboradas = nroHorasLaboradas;
	}
	public String getFechaInicioVCT() {
		return fechaInicioVCT;
	}
	public void setFechaInicioVCT(String fechaInicioVCT) {
		this.fechaInicioVCT = fechaInicioVCT;
	}
	public String getFechaFinVCT() {
		return fechaFinVCT;
	}
	public void setFechaFinVCT(String fechaFinVCT) {
		this.fechaFinVCT = fechaFinVCT;
	}
}
