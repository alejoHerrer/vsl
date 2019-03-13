package net.jaimetorres.pila.approval.pojos.output.adres;

import java.util.ArrayList;
import java.util.List;

public class AdresPlanillaOut {

	private AdresPlanillaEncabezadoOut planillaEncabezado;
	private List<AdresPlanillaLiquidacionDetalladaOut> planillaLiquidacionDetalladaList = new ArrayList<>();
	private List<AdresPlanillaTotalesOut> planillaTotalesList = new ArrayList<>();
	
	public AdresPlanillaEncabezadoOut getPlanillaEncabezado() {
		return planillaEncabezado;
	}
	public void setPlanillaEncabezado(AdresPlanillaEncabezadoOut planillaEncabezado) {
		this.planillaEncabezado = planillaEncabezado;
	}
	public List<AdresPlanillaLiquidacionDetalladaOut> getPlanillaLiquidacionDetalladaList() {
		return planillaLiquidacionDetalladaList;
	}
	public void setPlanillaLiquidacionDetalladaList(
			List<AdresPlanillaLiquidacionDetalladaOut> planillaLiquidacionDetalladaList) {
		this.planillaLiquidacionDetalladaList = planillaLiquidacionDetalladaList;
	}
	public List<AdresPlanillaTotalesOut> getPlanillaTotalesList() {
		return planillaTotalesList;
	}
	public void setPlanillaTotalesList(List<AdresPlanillaTotalesOut> planillaTotalesList) {
		this.planillaTotalesList = planillaTotalesList;
	}
}
