package net.jaimetorres.pila.approval.pojos.output.activos.cajas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;


public class ActivosCajasOutputFile
extends CommonOutputFile{
	
	private ActivosCajasEncabezadoOut encabezado;
	private List<ActivosCajasLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private ActivosCajasTotalesRenglon31Out renglon31;
	private ActivosCajasTotalesRenglon36Out renglon36;
	private ActivosCajasTotalesRenglon39Out renglon39;
	private ActivosCajasLey1819Out ley1819;
	
	public ActivosCajasOutputFile(File file) {
		super(file);
	}
	
	public ActivosCajasEncabezadoOut getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(ActivosCajasEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}
	public List<ActivosCajasLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}
	public void setLiquidacionDetalladaList(List<ActivosCajasLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}
	public ActivosCajasTotalesRenglon31Out getRenglon31() {
		return renglon31;
	}
	public void setRenglon31(ActivosCajasTotalesRenglon31Out renglon31) {
		this.renglon31 = renglon31;
	}
	public ActivosCajasTotalesRenglon36Out getRenglon36() {
		return renglon36;
	}
	public void setRenglon36(ActivosCajasTotalesRenglon36Out renglon36) {
		this.renglon36 = renglon36;
	}
	public ActivosCajasTotalesRenglon39Out getRenglon39() {
		return renglon39;
	}
	public void setRenglon39(ActivosCajasTotalesRenglon39Out renglon39) {
		this.renglon39 = renglon39;
	}
	public ActivosCajasLey1819Out getLey1819() {
		return ley1819;
	}
	public void setLey1819(ActivosCajasLey1819Out ley1819) {
		this.ley1819 = ley1819;
	}
	
}
