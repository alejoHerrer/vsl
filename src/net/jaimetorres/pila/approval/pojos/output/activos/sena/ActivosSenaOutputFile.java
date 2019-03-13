package net.jaimetorres.pila.approval.pojos.output.activos.sena;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class ActivosSenaOutputFile
extends CommonOutputFile{

	private ActivosSenaEncabezadoOut encabezado;
	private List<ActivosSenaLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private ActivosSenaTotalesRenglon31Out renglon31;
	private ActivosSenaTotalesRenglon36Out renglon36;
	private ActivosSenaTotalesRenglon39Out renglon39;
	private ActivosSenaLey1819Out ley1819;
	
	public ActivosSenaOutputFile(File file) {
		super(file);
	}
	
	public ActivosSenaEncabezadoOut getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(ActivosSenaEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}
	public List<ActivosSenaLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}
	public void setLiquidacionDetalladaList(List<ActivosSenaLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}
	public ActivosSenaTotalesRenglon31Out getRenglon31() {
		return renglon31;
	}
	public void setRenglon31(ActivosSenaTotalesRenglon31Out renglon31) {
		this.renglon31 = renglon31;
	}
	public ActivosSenaTotalesRenglon36Out getRenglon36() {
		return renglon36;
	}
	public void setRenglon36(ActivosSenaTotalesRenglon36Out renglon36) {
		this.renglon36 = renglon36;
	}
	public ActivosSenaTotalesRenglon39Out getRenglon39() {
		return renglon39;
	}
	public void setRenglon39(ActivosSenaTotalesRenglon39Out renglon39) {
		this.renglon39 = renglon39;
	}
	public ActivosSenaLey1819Out getLey1819() {
		return ley1819;
	}
	public void setLey1819(ActivosSenaLey1819Out ley1819) {
		this.ley1819 = ley1819;
	}
}
