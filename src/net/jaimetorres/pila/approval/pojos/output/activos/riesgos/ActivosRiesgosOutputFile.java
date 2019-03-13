package net.jaimetorres.pila.approval.pojos.output.activos.riesgos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class ActivosRiesgosOutputFile
extends CommonOutputFile{
	
	private ActivosRiesgosEncabezadoOut encabezado;
	private List<ActivosRiesgosLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private ActivosRiesgosTotalesRenglon31Out renglon31;
	private ActivosRiesgosTotalesRenglon32Out renglon32;
	private ActivosRiesgosTotalesRenglon36Out renglon36;
	private ActivosRiesgosTotalesRenglon39Out renglon39;
	private ActivosRiesgosLey1819Out ley1819;
	
	public ActivosRiesgosOutputFile(File file) {
		super(file);
	}
	
	public ActivosRiesgosEncabezadoOut getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(ActivosRiesgosEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}
	public List<ActivosRiesgosLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}
	public void setLiquidacionDetalladaList(List<ActivosRiesgosLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}
	public ActivosRiesgosTotalesRenglon31Out getRenglon31() {
		return renglon31;
	}
	public void setRenglon31(ActivosRiesgosTotalesRenglon31Out renglon31) {
		this.renglon31 = renglon31;
	}
	public ActivosRiesgosTotalesRenglon32Out getRenglon32() {
		return renglon32;
	}
	public void setRenglon32(ActivosRiesgosTotalesRenglon32Out renglon32) {
		this.renglon32 = renglon32;
	}
	public ActivosRiesgosTotalesRenglon36Out getRenglon36() {
		return renglon36;
	}
	public void setRenglon36(ActivosRiesgosTotalesRenglon36Out renglon36) {
		this.renglon36 = renglon36;
	}
	public ActivosRiesgosTotalesRenglon39Out getRenglon39() {
		return renglon39;
	}
	public void setRenglon39(ActivosRiesgosTotalesRenglon39Out renglon39) {
		this.renglon39 = renglon39;
	}
	public ActivosRiesgosLey1819Out getLey1819() {
		return ley1819;
	}
	public void setLey1819(ActivosRiesgosLey1819Out ley1819) {
		this.ley1819 = ley1819;
	}
}
