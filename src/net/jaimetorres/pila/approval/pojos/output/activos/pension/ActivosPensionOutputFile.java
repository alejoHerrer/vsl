package net.jaimetorres.pila.approval.pojos.output.activos.pension;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class ActivosPensionOutputFile
extends CommonOutputFile{
	
	private ActivosPensionEncabezadoOut encabezado;
	private List<ActivosPensionLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private ActivosPensionTotalesRenglon31Out renglon31;
	private ActivosPensionTotalesRenglon36Out renglon36;
	private ActivosPensionTotalesRenglon39Out renglon39;

	public ActivosPensionOutputFile(File file) {
		super(file);
	}
	
	public ActivosPensionEncabezadoOut getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(ActivosPensionEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}
	public List<ActivosPensionLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}
	public void setLiquidacionDetalladaList(List<ActivosPensionLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}
	public ActivosPensionTotalesRenglon31Out getRenglon31() {
		return renglon31;
	}
	public void setRenglon31(ActivosPensionTotalesRenglon31Out renglon31) {
		this.renglon31 = renglon31;
	}
	public ActivosPensionTotalesRenglon36Out getRenglon36() {
		return renglon36;
	}
	public void setRenglon36(ActivosPensionTotalesRenglon36Out renglon36) {
		this.renglon36 = renglon36;
	}
	public ActivosPensionTotalesRenglon39Out getRenglon39() {
		return renglon39;
	}
	public void setRenglon39(ActivosPensionTotalesRenglon39Out renglon39) {
		this.renglon39 = renglon39;
	}
}
