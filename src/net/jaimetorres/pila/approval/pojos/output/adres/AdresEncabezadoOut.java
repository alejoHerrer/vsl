package net.jaimetorres.pila.approval.pojos.output.adres;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public class AdresEncabezadoOut extends CommonOutputRecord{

	private String nroRegistro;
	private String tipoRegistro;
	private String codigoOperador;
	private String fechaPago;
	private String nroTotalPlanillasReportadas;
	
	public String getNroRegistro() {
		return nroRegistro;
	}
	public void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getNroTotalPlanillasReportadas() {
		return nroTotalPlanillasReportadas;
	}
	public void setNroTotalPlanillasReportadas(String nroTotalPlanillasReportadas) {
		this.nroTotalPlanillasReportadas = nroTotalPlanillasReportadas;
	}
	public String getCodigoOperador() {
		return codigoOperador;
	}
	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}
	
}
