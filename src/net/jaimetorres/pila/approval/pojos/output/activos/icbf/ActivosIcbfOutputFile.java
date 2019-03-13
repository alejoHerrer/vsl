package net.jaimetorres.pila.approval.pojos.output.activos.icbf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class ActivosIcbfOutputFile 
extends CommonOutputFile{
	
	private ActivosIcbfEncabezadoOut encabezado;
	private List<ActivosIcbfLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private ActivosIcbfTotalesRenglon31Out renglon31;
	private ActivosIcbfTotalesRenglon36Out renglon36;
	private ActivosIcbfTotalesRenglon39Out renglon39;
	private ActivosIcbfLey1819Out ley1819;
	
	public ActivosIcbfOutputFile(File file) {
		super(file);
	}
	
	public ActivosIcbfEncabezadoOut getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(ActivosIcbfEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}
	public List<ActivosIcbfLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}
	public void setLiquidacionDetalladaList(List<ActivosIcbfLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}
	public ActivosIcbfTotalesRenglon31Out getRenglon31() {
		return renglon31;
	}
	public void setRenglon31(ActivosIcbfTotalesRenglon31Out renglon31) {
		this.renglon31 = renglon31;
	}
	public ActivosIcbfTotalesRenglon36Out getRenglon36() {
		return renglon36;
	}
	public void setRenglon36(ActivosIcbfTotalesRenglon36Out renglon36) {
		this.renglon36 = renglon36;
	}
	public ActivosIcbfTotalesRenglon39Out getRenglon39() {
		return renglon39;
	}
	public void setRenglon39(ActivosIcbfTotalesRenglon39Out renglon39) {
		this.renglon39 = renglon39;
	}
	public ActivosIcbfLey1819Out getLey1819() {
		return ley1819;
	}
	public void setLey1819(ActivosIcbfLey1819Out ley1819) {
		this.ley1819 = ley1819;
	}

}
