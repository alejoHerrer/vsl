package net.jaimetorres.pila.approval.pojos.output.adres;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class AdresOutputFile 
extends CommonOutputFile{
	
	private AdresEncabezadoOut encabezado;
	private List<AdresPlanillaOut> planillas = new ArrayList<>();
	private AdresTotalesOut totales;

	public AdresOutputFile(File file) {
		super(file);
	}

	public AdresEncabezadoOut getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(AdresEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}

	public List<AdresPlanillaOut> getPlanillas() {
		return planillas;
	}

	public void setPlanillas(List<AdresPlanillaOut> planillas) {
		this.planillas = planillas;
	}

	public AdresTotalesOut getTotales() {
		return totales;
	}

	public void setTotales(AdresTotalesOut totales) {
		this.totales = totales;
	}

}
