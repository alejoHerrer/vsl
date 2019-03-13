package net.jaimetorres.pila.approval.pojos.output.pensionados.registroA;

import java.io.File;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class PensionadosRegistroAOutputFile 
extends CommonOutputFile{

	private PensionadosRegistroAEncabezadoOut encabezado;
	
	public PensionadosRegistroAOutputFile(File file) {
		super(file);
	}

	public PensionadosRegistroAEncabezadoOut getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(PensionadosRegistroAEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}

}
