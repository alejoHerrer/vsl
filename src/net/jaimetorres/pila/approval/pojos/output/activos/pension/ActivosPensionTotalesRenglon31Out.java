package net.jaimetorres.pila.approval.pojos.output.activos.pension;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosTotalesAporte;

public class ActivosPensionTotalesRenglon31Out 
extends CommonActivosTotalesAporte{
	
	private String totalCotizacionVoluntariaPensionAfiliado;
	private String totalCotizacionVoluntariaPensionAportante;
	private String totalCotizacionPensionMasVoluntarias;
	private String totalAporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	private String totalAporteFondoSolidaridadPensionalSubcuentaSubsistencia;

	public String getTotalCotizacionVoluntariaPensionAfiliado() {
		return totalCotizacionVoluntariaPensionAfiliado;
	}
	public void setTotalCotizacionVoluntariaPensionAfiliado(String totalCotizacionVoluntariaPensionAfiliado) {
		this.totalCotizacionVoluntariaPensionAfiliado = totalCotizacionVoluntariaPensionAfiliado;
	}
	public String getTotalCotizacionVoluntariaPensionAportante() {
		return totalCotizacionVoluntariaPensionAportante;
	}
	public void setTotalCotizacionVoluntariaPensionAportante(String totalCotizacionVoluntariaPensionAportante) {
		this.totalCotizacionVoluntariaPensionAportante = totalCotizacionVoluntariaPensionAportante;
	}
	public String getTotalCotizacionPensionMasVoluntarias() {
		return totalCotizacionPensionMasVoluntarias;
	}
	public void setTotalCotizacionPensionMasVoluntarias(String totalCotizacionPensionMasVoluntarias) {
		this.totalCotizacionPensionMasVoluntarias = totalCotizacionPensionMasVoluntarias;
	}
	public String getTotalAporteFondoSolidaridadPensionalSubcuentaSolidaridad() {
		return totalAporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	}
	public void setTotalAporteFondoSolidaridadPensionalSubcuentaSolidaridad(
			String totalAporteFondoSolidaridadPensionalSubcuentaSolidaridad) {
		this.totalAporteFondoSolidaridadPensionalSubcuentaSolidaridad = totalAporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	}
	public String getTotalAporteFondoSolidaridadPensionalSubcuentaSubsistencia() {
		return totalAporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	}
	public void setTotalAporteFondoSolidaridadPensionalSubcuentaSubsistencia(
			String totalAporteFondoSolidaridadPensionalSubcuentaSubsistencia) {
		this.totalAporteFondoSolidaridadPensionalSubcuentaSubsistencia = totalAporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	}
}
