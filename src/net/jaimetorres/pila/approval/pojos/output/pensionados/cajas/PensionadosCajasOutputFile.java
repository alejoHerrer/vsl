package net.jaimetorres.pila.approval.pojos.output.pensionados.cajas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class PensionadosCajasOutputFile 
extends CommonOutputFile{

	private PensionadosCajasEncabezadoOut encabezado;
	private List<PensionadosCajasLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private PensionadosCajasTotalesOut totales;
	
	public PensionadosCajasOutputFile(File file) {
		super(file);
	}

	public PensionadosCajasEncabezadoOut getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(PensionadosCajasEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}

	public List<PensionadosCajasLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}

	public void setLiquidacionDetalladaList(List<PensionadosCajasLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}

	public PensionadosCajasTotalesOut getTotales() {
		return totales;
	}

	public void setTotales(PensionadosCajasTotalesOut totales) {
		this.totales = totales;
	}

}
