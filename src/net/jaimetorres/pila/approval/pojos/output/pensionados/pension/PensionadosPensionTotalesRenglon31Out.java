package net.jaimetorres.pila.approval.pojos.output.pensionados.pension;

import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosTotalesAporte;

public class PensionadosPensionTotalesRenglon31Out
extends CommonPensionadosTotalesAporte{
	
	private String totalCotizacionObligatoria;
	private String totalCotizacionVoluntariaAfiliado;
	
	private String totalAporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	private String totalAporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	
	public String getTotalCotizacionVoluntariaAfiliado() {
		return totalCotizacionVoluntariaAfiliado;
	}
	public void setTotalCotizacionVoluntariaAfiliado(String totalCotizacionVoluntariaAfiliado) {
		this.totalCotizacionVoluntariaAfiliado = totalCotizacionVoluntariaAfiliado;
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
	public String getTotalCotizacionObligatoria() {
		return totalCotizacionObligatoria;
	}
	public void setTotalCotizacionObligatoria(String totalCotizacionObligatoria) {
		this.totalCotizacionObligatoria = totalCotizacionObligatoria;
	}
}
