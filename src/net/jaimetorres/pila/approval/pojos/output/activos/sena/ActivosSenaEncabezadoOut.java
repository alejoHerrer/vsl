package net.jaimetorres.pila.approval.pojos.output.activos.sena;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosEncabezadoOutputFile;

public class ActivosSenaEncabezadoOut 
extends CommonActivosEncabezadoOutputFile{

	private String nroRegistrosTipo2;
	private String fechaMatriculaMercantil;
	private String codigoDepartamentoMatriculaMercantil;
	private String aportanteExoneradoLey1607;
	
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
	public String getAportanteExoneradoLey1607() {
		return aportanteExoneradoLey1607;
	}
	public void setAportanteExoneradoLey1607(String aportanteExoneradoLey1607) {
		this.aportanteExoneradoLey1607 = aportanteExoneradoLey1607;
	}
}
