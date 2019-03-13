package net.jaimetorres.pila.approval.pojos.output.minps;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public class MinpsLey1819Out 
extends CommonOutputRecord{

	private String codigoOperador;
	private String nroPlanilla;	
	private String tipoRegistro;
	private String nroRegistro;
	
	private String indicadorUgpp;
	private String  nroActoAdministrativo;
	private String fechaAperturaLiquidacion;
	private String  nombreEntidad;
	private String valorPagadoSancion;

	public String getIndicadorUgpp() {
		return indicadorUgpp;
	}
	public void setIndicadorUgpp(String l) {
		this.indicadorUgpp = l;
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
	public void setValorPagadoSancion(String l) {
		this.valorPagadoSancion = l;
	}
	public String getCodigoOperador() {
		return codigoOperador;
	}
	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}
	public String getNroPlanilla() {
		return nroPlanilla;
	}
	public void setNroPlanilla(String nroPlanilla) {
		this.nroPlanilla = nroPlanilla;
	}
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getNroRegistro() {
		return nroRegistro;
	}
	public void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
			
	
	
}
