package net.jaimetorres.pila.approval.pojos.output.activos.minedu;

import java.io.File;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class ActivosMineduOutputFile 
extends CommonOutputFile{
	
	private ActivosMineduEncabezadoOut encabezado;
	private ActivosMineduTotalesRenglon31Out renglon31;
	private ActivosMineduTotalesRenglon36Out renglon36;
	private ActivosMineduTotalesRenglon39Out renglon39;
	
	public ActivosMineduOutputFile(File file) {
		super(file);
	}
	
	public ActivosMineduEncabezadoOut getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(ActivosMineduEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}
	public ActivosMineduTotalesRenglon31Out getRenglon31() {
		return renglon31;
	}
	public void setRenglon31(ActivosMineduTotalesRenglon31Out renglon31) {
		this.renglon31 = renglon31;
	}
	public ActivosMineduTotalesRenglon36Out getRenglon36() {
		return renglon36;
	}
	public void setRenglon36(ActivosMineduTotalesRenglon36Out renglon36) {
		this.renglon36 = renglon36;
	}
	public ActivosMineduTotalesRenglon39Out getRenglon39() {
		return renglon39;
	}
	public void setRenglon39(ActivosMineduTotalesRenglon39Out renglon39) {
		this.renglon39 = renglon39;
	}
}
