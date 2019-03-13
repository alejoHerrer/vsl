package net.jaimetorres.pila.approval.pojos.output.pensionados.cajas;

import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosEncabezadoOutputFile;

public class PensionadosCajasEncabezadoOut
extends CommonPensionadosEncabezadoOutputFile{
	
	private String secuencia;
	private String codigoAdm;
	private String totalMesadas;
	
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public String getCodigoAdm() {
		return codigoAdm;
	}
	public void setCodigoAdm(String codigoAdm) {
		this.codigoAdm = codigoAdm;
	}
	public String getTotalMesadas() {
		return totalMesadas;
	}
	public void setTotalMesadas(String totalMesadas) {
		this.totalMesadas = totalMesadas;
	}
}
