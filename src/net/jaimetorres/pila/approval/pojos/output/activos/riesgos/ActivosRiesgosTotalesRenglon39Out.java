package net.jaimetorres.pila.approval.pojos.output.activos.riesgos;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosTotalesRenglon39;

public class ActivosRiesgosTotalesRenglon39Out 
extends CommonActivosTotalesRenglon39{

	private String totalDescontar;
	private String totalNetoAportes;
	
	public String getTotalDescontar() {
		return totalDescontar;
	}
	public void setTotalDescontar(String totalDescontar) {
		this.totalDescontar = totalDescontar;
	}
	public String getTotalNetoAportes() {
		return totalNetoAportes;
	}
	public void setTotalNetoAportes(String totalNetoAportes) {
		this.totalNetoAportes = totalNetoAportes;
	}
}
