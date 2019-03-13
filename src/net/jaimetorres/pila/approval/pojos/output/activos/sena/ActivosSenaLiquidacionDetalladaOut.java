package net.jaimetorres.pila.approval.pojos.output.activos.sena;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosLiquidacionDetalladaOutputFile;

public class ActivosSenaLiquidacionDetalladaOut
extends CommonActivosLiquidacionDetalladaOutputFile{

	private String diasNovedadIncapacidadRiesgosProfesionales;
	private String diasCotizados;
	private String ibc;
	private String tarifa;
	private String cotizacion;
	private String cotizanteExoneradoLey1607;
	private String fechaInicioVCT;
	private String fechaFinVCT;
	private String fechaInicioIRL;
	private String fechaFinIRL;
	
	public String getDiasNovedadIncapacidadRiesgosProfesionales() {
		return diasNovedadIncapacidadRiesgosProfesionales;
	}
	public void setDiasNovedadIncapacidadRiesgosProfesionales(String diasNovedadIncapacidadRiesgosProfesionales) {
		this.diasNovedadIncapacidadRiesgosProfesionales = diasNovedadIncapacidadRiesgosProfesionales;
	}
	public String getDiasCotizados() {
		return diasCotizados;
	}
	public void setDiasCotizados(String diasCotizados) {
		this.diasCotizados = diasCotizados;
	}
	public String getIbc() {
		return ibc;
	}
	public void setIbc(String ibc) {
		this.ibc = ibc;
	}
	public String getTarifa() {
		return tarifa;
	}
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}
	public String getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}
	public String getCotizanteExoneradoLey1607() {
		return cotizanteExoneradoLey1607;
	}
	public void setCotizanteExoneradoLey1607(String cotizanteExoneradoLey1607) {
		this.cotizanteExoneradoLey1607 = cotizanteExoneradoLey1607;
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
}
