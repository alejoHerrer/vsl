package net.jaimetorres.pila.approval.pojos.output.activos.pension;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosTotalesMora;

public class ActivosPensionTotalesRenglon36Out 
extends CommonActivosTotalesMora{

	private String moraCotizacionVoluntaria;
	private String moraTotalCotizacion;	
	private String moraAporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	private String moraAporteFondoSolidaridadPensionalSubcuentaSubsistencia;

	public String getMoraCotizacionVoluntaria() {
		return moraCotizacionVoluntaria;
	}
	public void setMoraCotizacionVoluntaria(String moraCotizacionVoluntaria) {
		this.moraCotizacionVoluntaria = moraCotizacionVoluntaria;
	}
	public String getMoraTotalCotizacion() {
		return moraTotalCotizacion;
	}
	public void setMoraTotalCotizacion(String moraTotalCotizacion) {
		this.moraTotalCotizacion = moraTotalCotizacion;
	}
	public String getMoraAporteFondoSolidaridadPensionalSubcuentaSolidaridad() {
		return moraAporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	}
	public void setMoraAporteFondoSolidaridadPensionalSubcuentaSolidaridad(
			String moraAporteFondoSolidaridadPensionalSubcuentaSolidaridad) {
		this.moraAporteFondoSolidaridadPensionalSubcuentaSolidaridad = moraAporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	}
	public String getMoraAporteFondoSolidaridadPensionalSubcuentaSubsistencia() {
		return moraAporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	}
	public void setMoraAporteFondoSolidaridadPensionalSubcuentaSubsistencia(
			String moraAporteFondoSolidaridadPensionalSubcuentaSubsistencia) {
		this.moraAporteFondoSolidaridadPensionalSubcuentaSubsistencia = moraAporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	}
}
