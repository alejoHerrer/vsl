package net.jaimetorres.pila.approval.pojos.output.activos.pension;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosEncabezadoOutputFile;

public class ActivosPensionEncabezadoOut 
extends CommonActivosEncabezadoOutputFile{

	private String codigoArp;
	private String nroRegistrosTipo2;
	
	public String getCodigoArp() {
		return codigoArp;
	}
	public void setCodigoArp(String codigoArp) {
		this.codigoArp = codigoArp;
	}
	public String getNroRegistrosTipo2() {
		return nroRegistrosTipo2;
	}
	public void setNroRegistrosTipo2(String nroRegistrosTipo2) {
		this.nroRegistrosTipo2 = nroRegistrosTipo2;
	}
	
}
