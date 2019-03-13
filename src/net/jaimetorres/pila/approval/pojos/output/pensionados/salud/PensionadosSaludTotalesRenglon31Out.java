package net.jaimetorres.pila.approval.pojos.output.pensionados.salud;

import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosTotalesAporte;

public class PensionadosSaludTotalesRenglon31Out
extends CommonPensionadosTotalesAporte{
	
	private String totalUpcAdicional;
	
	public String getTotalUpcAdicional() {
		return totalUpcAdicional;
	}
	public void setTotalUpcAdicional(String totalUpcAdicional) {
		this.totalUpcAdicional = totalUpcAdicional;
	}
}
