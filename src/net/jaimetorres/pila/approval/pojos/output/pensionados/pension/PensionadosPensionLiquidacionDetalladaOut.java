package net.jaimetorres.pila.approval.pojos.output.pensionados.pension;

import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosLiquidacionDetalladaOutputFile;

public class PensionadosPensionLiquidacionDetalladaOut 
extends CommonPensionadosLiquidacionDetalladaOutputFile{
	
	private String colombianoResidenteExterior;
	private String novedadTrasladoAfpDesde;
	private String novedadTrasladoAfpA;
	
	private String diasCotizadosPension;
	private String tarifaPension;
	private String cotizacionPension;
	
	private String cotizacionVoluntariaPensionAfiliado;
	private String totalCotizacion;
	private String aporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	private String aporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	private String valorNoRetenidoAportesVoluntarios;

	public String getColombianoResidenteExterior() {
		return colombianoResidenteExterior;
	}

	public void setColombianoResidenteExterior(String colombianoResidenteExterior) {
		this.colombianoResidenteExterior = colombianoResidenteExterior;
	}

	public String getNovedadTrasladoAfpDesde() {
		return novedadTrasladoAfpDesde;
	}

	public void setNovedadTrasladoAfpDesde(String novedadTrasladoAfpDesde) {
		this.novedadTrasladoAfpDesde = novedadTrasladoAfpDesde;
	}

	public String getNovedadTrasladoAfpA() {
		return novedadTrasladoAfpA;
	}

	public void setNovedadTrasladoAfpA(String novedadTrasladoAfpA) {
		this.novedadTrasladoAfpA = novedadTrasladoAfpA;
	}

	public String getDiasCotizadosPension() {
		return diasCotizadosPension;
	}

	public void setDiasCotizadosPension(String diasCotizadosPension) {
		this.diasCotizadosPension = diasCotizadosPension;
	}

	public String getTarifaPension() {
		return tarifaPension;
	}

	public void setTarifaPension(String tarifaPension) {
		this.tarifaPension = tarifaPension;
	}

	public String getCotizacionPension() {
		return cotizacionPension;
	}

	public void setCotizacionPension(String cotizacionPension) {
		this.cotizacionPension = cotizacionPension;
	}

	public String getCotizacionVoluntariaPensionAfiliado() {
		return cotizacionVoluntariaPensionAfiliado;
	}

	public void setCotizacionVoluntariaPensionAfiliado(String cotizacionVoluntariaPensionAfiliado) {
		this.cotizacionVoluntariaPensionAfiliado = cotizacionVoluntariaPensionAfiliado;
	}

	public String getTotalCotizacion() {
		return totalCotizacion;
	}

	public void setTotalCotizacion(String totalCotizacion) {
		this.totalCotizacion = totalCotizacion;
	}

	public String getAporteFondoSolidaridadPensionalSubcuentaSolidaridad() {
		return aporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	}

	public void setAporteFondoSolidaridadPensionalSubcuentaSolidaridad(
			String aporteFondoSolidaridadPensionalSubcuentaSolidaridad) {
		this.aporteFondoSolidaridadPensionalSubcuentaSolidaridad = aporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	}

	public String getAporteFondoSolidaridadPensionalSubcuentaSubsistencia() {
		return aporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	}

	public void setAporteFondoSolidaridadPensionalSubcuentaSubsistencia(
			String aporteFondoSolidaridadPensionalSubcuentaSubsistencia) {
		this.aporteFondoSolidaridadPensionalSubcuentaSubsistencia = aporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	}

	public String getValorNoRetenidoAportesVoluntarios() {
		return valorNoRetenidoAportesVoluntarios;
	}

	public void setValorNoRetenidoAportesVoluntarios(String valorNoRetenidoAportesVoluntarios) {
		this.valorNoRetenidoAportesVoluntarios = valorNoRetenidoAportesVoluntarios;
	}
	
}
