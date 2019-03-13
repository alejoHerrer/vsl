package net.jaimetorres.pila.approval.pojos.output.activos.salud;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosTotalesRenglon39;

public class ActivosSaludTotalesRenglon39Out 
extends CommonActivosTotalesRenglon39{

	private String totalNetoAportes;
	private String totalUpcAdicional;
	
	public String getTotalNetoAportes() {
		return totalNetoAportes;
	}
	public void setTotalNetoAportes(String totalNetoAportes) {
		this.totalNetoAportes = totalNetoAportes;
	}
	public String getTotalUpcAdicional() {
		return totalUpcAdicional;
	}
	public void setTotalUpcAdicional(String totalUpcAdicional) {
		this.totalUpcAdicional = totalUpcAdicional;
	}
}
