package net.jaimetorres.pila.approval.pojos.output.activos.cajas;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosEncabezadoOutputFile;

public class ActivosCajasEncabezadoOut 
extends CommonActivosEncabezadoOutputFile{

	private String nroRegistrosTipo2;
	private String fechaMatriculaMercantil;
	private String codigoDepartamentoMatriculaMercantil;
	private String aportanteAcogeBeneficiosLey1429;
	
	public String getNroRegistrosTipo2() {
		return nroRegistrosTipo2;
	}
	public void setNroRegistrosTipo2(String nroRegistrosTipo2) {
		this.nroRegistrosTipo2 = nroRegistrosTipo2;
	}
	public String getFechaMatriculaMercantil() {
		return fechaMatriculaMercantil;
	}
	public void setFechaMatriculaMercantil(String fechaMatriculaMercantil) {
		this.fechaMatriculaMercantil = fechaMatriculaMercantil;
	}
	public String getCodigoDepartamentoMatriculaMercantil() {
		return codigoDepartamentoMatriculaMercantil;
	}
	public void setCodigoDepartamentoMatriculaMercantil(String codigoDepartamentoMatriculaMercantil) {
		this.codigoDepartamentoMatriculaMercantil = codigoDepartamentoMatriculaMercantil;
	}
	public String getAportanteAcogeBeneficiosLey1429() {
		return aportanteAcogeBeneficiosLey1429;
	}
	public void setAportanteAcogeBeneficiosLey1429(String aportanteAcogeBeneficiosLey1429) {
		this.aportanteAcogeBeneficiosLey1429 = aportanteAcogeBeneficiosLey1429;
	}
}
