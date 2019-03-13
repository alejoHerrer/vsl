package net.jaimetorres.pila.approval.pojos.output.activos.registroA;

import java.io.File;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class ActivosRegistroAOutputFile 
extends CommonOutputFile{
	
	private ActivosRegistroAEncabezadoOut encabezado;

	public ActivosRegistroAOutputFile(File file) {
		super(file);
	}

	public ActivosRegistroAEncabezadoOut getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(ActivosRegistroAEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}

}
