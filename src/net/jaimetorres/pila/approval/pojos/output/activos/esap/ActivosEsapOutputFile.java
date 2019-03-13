package net.jaimetorres.pila.approval.pojos.output.activos.esap;

import java.io.File;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class ActivosEsapOutputFile 
extends CommonOutputFile{
	
	private ActivosEsapEncabezadoOut encabezado;
	private ActivosEsapTotalesRenglon31Out renglon31;
	private ActivosEsapTotalesRenglon36Out renglon36;
	private ActivosEsapTotalesRenglon39Out renglon39;
	
	public ActivosEsapOutputFile(File file) {
		super(file);
	}
	
	public ActivosEsapEncabezadoOut getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(ActivosEsapEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}
	public ActivosEsapTotalesRenglon31Out getRenglon31() {
		return renglon31;
	}
	public void setRenglon31(ActivosEsapTotalesRenglon31Out renglon31) {
		this.renglon31 = renglon31;
	}
	public ActivosEsapTotalesRenglon36Out getRenglon36() {
		return renglon36;
	}
	public void setRenglon36(ActivosEsapTotalesRenglon36Out renglon36) {
		this.renglon36 = renglon36;
	}
	public ActivosEsapTotalesRenglon39Out getRenglon39() {
		return renglon39;
	}
	public void setRenglon39(ActivosEsapTotalesRenglon39Out renglon39) {
		this.renglon39 = renglon39;
	}
}
