package net.jaimetorres.pila.approval.pojos.output.pensionados.salud;

import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosTotalesRenglon39;

public class PensionadosSaludTotalesRenglon39Out
extends CommonPensionadosTotalesRenglon39{
	
	private String aporteFondoSolidaridadGarantiaFosyga;
	private String totalUpcAdicional;
	
	public String getAporteFondoSolidaridadGarantiaFosyga() {
		return aporteFondoSolidaridadGarantiaFosyga;
	}
	public void setAporteFondoSolidaridadGarantiaFosyga(String aporteFondoSolidaridadGarantiaFosyga) {
		this.aporteFondoSolidaridadGarantiaFosyga = aporteFondoSolidaridadGarantiaFosyga;
	}
	public String getTotalUpcAdicional() {
		return totalUpcAdicional;
	}
	public void setTotalUpcAdicional(String totalUpcAdicional) {
		this.totalUpcAdicional = totalUpcAdicional;
	}
	
}
