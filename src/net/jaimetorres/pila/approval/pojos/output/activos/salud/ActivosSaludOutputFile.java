package net.jaimetorres.pila.approval.pojos.output.activos.salud;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class ActivosSaludOutputFile
extends CommonOutputFile{
	
	private ActivosSaludEncabezadoOut encabezado;
	private List<ActivosSaludLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private ActivosSaludTotalesRenglon31Out renglon31;
	private ActivosSaludTotalesRenglon36Out renglon36;
	private ActivosSaludTotalesRenglon39Out renglon39;
	private ActivosSaludLey1819Out ley1819;

	public ActivosSaludOutputFile(File file) {
		super(file);
	}
	
	public ActivosSaludEncabezadoOut getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(ActivosSaludEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}


	public List<ActivosSaludLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}


	public void setLiquidacionDetalladaList(List<ActivosSaludLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}


	public ActivosSaludTotalesRenglon31Out getRenglon31() {
		return renglon31;
	}


	public void setRenglon31(ActivosSaludTotalesRenglon31Out renglon31) {
		this.renglon31 = renglon31;
	}


	public ActivosSaludTotalesRenglon36Out getRenglon36() {
		return renglon36;
	}


	public void setRenglon36(ActivosSaludTotalesRenglon36Out renglon36) {
		this.renglon36 = renglon36;
	}


	public ActivosSaludTotalesRenglon39Out getRenglon39() {
		return renglon39;
	}


	public void setRenglon39(ActivosSaludTotalesRenglon39Out renglon39) {
		this.renglon39 = renglon39;
	}


	public ActivosSaludLey1819Out getLey1819() {
		return ley1819;
	}


	public void setLey1819(ActivosSaludLey1819Out ley1819) {
		this.ley1819 = ley1819;
	}
}
