package net.jaimetorres.pila.approval.pojos.output.pensionados.salud;

import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosLiquidacionDetalladaOutputFile;

public class PensionadosSaludLiquidacionDetalladaOut
extends CommonPensionadosLiquidacionDetalladaOutputFile{
	
	private String colombianoResidenteExterior;
	private String novedadTrasladoEpsDesde;
	private String novedadTrasladoEpsA;
	private String diasCotizadosSalud;
	private String tarifaSalud;
	private String cotizacionSalud;
	private String valorUpcAdicional;
	private String fechaRadicacionExterior;
	
	public String getColombianoResidenteExterior() {
		return colombianoResidenteExterior;
	}
	public void setColombianoResidenteExterior(String colombianoResidenteExterior) {
		this.colombianoResidenteExterior = colombianoResidenteExterior;
	}
	public String getNovedadTrasladoEpsDesde() {
		return novedadTrasladoEpsDesde;
	}
	public void setNovedadTrasladoEpsDesde(String novedadTrasladoEpsDesde) {
		this.novedadTrasladoEpsDesde = novedadTrasladoEpsDesde;
	}
	public String getNovedadTrasladoEpsA() {
		return novedadTrasladoEpsA;
	}
	public void setNovedadTrasladoEpsA(String novedadTrasladoEpsA) {
		this.novedadTrasladoEpsA = novedadTrasladoEpsA;
	}
	public String getDiasCotizadosSalud() {
		return diasCotizadosSalud;
	}
	public void setDiasCotizadosSalud(String diasCotizadosSalud) {
		this.diasCotizadosSalud = diasCotizadosSalud;
	}
	public String getTarifaSalud() {
		return tarifaSalud;
	}
	public void setTarifaSalud(String tarifaSalud) {
		this.tarifaSalud = tarifaSalud;
	}
	public String getCotizacionSalud() {
		return cotizacionSalud;
	}
	public void setCotizacionSalud(String cotizacionSalud) {
		this.cotizacionSalud = cotizacionSalud;
	}
	public String getValorUpcAdicional() {
		return valorUpcAdicional;
	}
	public void setValorUpcAdicional(String valorUpcAdicional) {
		this.valorUpcAdicional = valorUpcAdicional;
	}
	public String getFechaRadicacionExterior() {
		return fechaRadicacionExterior;
	}
	public void setFechaRadicacionExterior(String fechaRadicacionExterior) {
		this.fechaRadicacionExterior = fechaRadicacionExterior;
	}
	
}
