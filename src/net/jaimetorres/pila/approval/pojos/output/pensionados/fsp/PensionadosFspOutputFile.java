package net.jaimetorres.pila.approval.pojos.output.pensionados.fsp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class PensionadosFspOutputFile 
extends CommonOutputFile{

	private PensionadosFspEncabezadoOut encabezado;
	private List<PensionadosFspLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private PensionadosFspTotalesOut totales;
	
	public PensionadosFspOutputFile(File file) {
		super(file);
	}

	public PensionadosFspEncabezadoOut getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(PensionadosFspEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}

	public List<PensionadosFspLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}

	public void setLiquidacionDetalladaList(List<PensionadosFspLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}

	public PensionadosFspTotalesOut getTotales() {
		return totales;
	}

	public void setTotales(PensionadosFspTotalesOut totales) {
		this.totales = totales;
	}

}
