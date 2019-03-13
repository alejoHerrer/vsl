package net.jaimetorres.pila.approval.pojos.output.pensionados.salud;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class PensionadosSaludOutputFile 
extends CommonOutputFile{

	private PensionadosSaludEncabezadoOut encabezado;
	private List<PensionadosSaludLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private PensionadosSaludTotalesRenglon31Out renglon31;
	private PensionadosSaludTotalesRenglon36Out renglon36;
	private PensionadosSaludTotalesRenglon37Out renglon37;
	private PensionadosSaludTotalesRenglon39Out renglon39;
	
	public PensionadosSaludOutputFile(File file) {
		super(file);
	}

	public PensionadosSaludEncabezadoOut getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(PensionadosSaludEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}

	public List<PensionadosSaludLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}

	public void setLiquidacionDetalladaList(List<PensionadosSaludLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}

	public PensionadosSaludTotalesRenglon31Out getRenglon31() {
		return renglon31;
	}

	public void setRenglon31(PensionadosSaludTotalesRenglon31Out renglon31) {
		this.renglon31 = renglon31;
	}

	public PensionadosSaludTotalesRenglon36Out getRenglon36() {
		return renglon36;
	}

	public void setRenglon36(PensionadosSaludTotalesRenglon36Out renglon36) {
		this.renglon36 = renglon36;
	}

	public PensionadosSaludTotalesRenglon37Out getRenglon37() {
		return renglon37;
	}

	public void setRenglon37(PensionadosSaludTotalesRenglon37Out renglon37) {
		this.renglon37 = renglon37;
	}

	public PensionadosSaludTotalesRenglon39Out getRenglon39() {
		return renglon39;
	}

	public void setRenglon39(PensionadosSaludTotalesRenglon39Out renglon39) {
		this.renglon39 = renglon39;
	}
}
