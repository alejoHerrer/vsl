package net.jaimetorres.pila.approval.pojos.output.pensionados.pension;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class PensionadosPensionOutputFile 
extends CommonOutputFile{

	private PensionadosPensionEncabezadoOut encabezado;
	private List<PensionadosPensionLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private PensionadosPensionTotalesRenglon31Out renglon31;
	private PensionadosPensionTotalesRenglon36Out renglon36;
	private PensionadosPensionTotalesRenglon39Out renglon39;
	
	public PensionadosPensionOutputFile(File file) {
		super(file);
	}

	public PensionadosPensionEncabezadoOut getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(PensionadosPensionEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}

	public List<PensionadosPensionLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}

	public void setLiquidacionDetalladaList(List<PensionadosPensionLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}

	public PensionadosPensionTotalesRenglon31Out getRenglon31() {
		return renglon31;
	}

	public void setRenglon31(PensionadosPensionTotalesRenglon31Out renglon31) {
		this.renglon31 = renglon31;
	}

	public PensionadosPensionTotalesRenglon36Out getRenglon36() {
		return renglon36;
	}

	public void setRenglon36(PensionadosPensionTotalesRenglon36Out renglon36) {
		this.renglon36 = renglon36;
	}

	public PensionadosPensionTotalesRenglon39Out getRenglon39() {
		return renglon39;
	}

	public void setRenglon39(PensionadosPensionTotalesRenglon39Out renglon39) {
		this.renglon39 = renglon39;
	}

}
