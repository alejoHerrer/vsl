package net.jaimetorres.pila.approval.pojos.output.activos.commons;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public abstract class CommonActivosLey1819OutputRecord 
extends CommonOutputRecord{

	// Beneficiario ley 1819
	private String tipoRegistro;
	private String indicadorUgpp;
	private String nroActoAdministrativo;
	private String fechaAperturaLiquidacion;
	private String nombreEntidad;
	private String valorPagadoSancion;

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getIndicadorUgpp() {
		return indicadorUgpp;
	}

	public void setIndicadorUgpp(String indicadorUgpp) {
		this.indicadorUgpp = indicadorUgpp;
	}

	public String getNroActoAdministrativo() {
		return nroActoAdministrativo;
	}

	public void setNroActoAdministrativo(String nroActoAdministrativo) {
		this.nroActoAdministrativo = nroActoAdministrativo;
	}

	public String getFechaAperturaLiquidacion() {
		return fechaAperturaLiquidacion;
	}

	public void setFechaAperturaLiquidacion(String fechaAperturaLiquidacion) {
		this.fechaAperturaLiquidacion = fechaAperturaLiquidacion;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public String getValorPagadoSancion() {
		return valorPagadoSancion;
	}

	public void setValorPagadoSancion(String valorPagadoSancion) {
		this.valorPagadoSancion = valorPagadoSancion;
	}
}
