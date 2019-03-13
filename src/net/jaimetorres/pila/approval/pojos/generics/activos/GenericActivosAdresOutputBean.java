package net.jaimetorres.pila.approval.pojos.generics.activos;

import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo0Adres;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo4Adres;
import net.jaimetorres.validaciones.to.PlanillaAdresTO;

public class GenericActivosAdresOutputBean 
extends GenericOutputBean{

	//XXX OJO TENER ENCUENTA QUE LOS TIPOS DE DATOS PARA PENSIONADOS PUEDEN SER DISTINTOS
	//(POR AHORA SOLO SE ASUMEN ACTIVOS)
	private LineaRegistroTipo0Adres lineaRegistroTipo0Adres;
	private List<PlanillaAdresTO> planillaAdresTOList = new ArrayList<>();
	private LineaRegistroTipo4Adres lineaRegistroTipo4Adres;
	
	public LineaRegistroTipo0Adres getLineaRegistroTipo0Adres() {
		return lineaRegistroTipo0Adres;
	}
	public void setLineaRegistroTipo0Adres(LineaRegistroTipo0Adres lineaRegistroTipo0Adres) {
		this.lineaRegistroTipo0Adres = lineaRegistroTipo0Adres;
	}
	public List<PlanillaAdresTO> getPlanillaAdresTOList() {
		return planillaAdresTOList;
	}
	public void setPlanillaAdresTOList(List<PlanillaAdresTO> planillaAdresTOList) {
		this.planillaAdresTOList = planillaAdresTOList;
	}
	public LineaRegistroTipo4Adres getLineaRegistroTipo4Adres() {
		return lineaRegistroTipo4Adres;
	}
	public void setLineaRegistroTipo4Adres(LineaRegistroTipo4Adres lineaRegistroTipo4Adres) {
		this.lineaRegistroTipo4Adres = lineaRegistroTipo4Adres;
	}
	
	
	
}
