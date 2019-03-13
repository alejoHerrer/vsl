package net.jaimetorres.pila.approval.pojos.generics.pensionados;

import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.generics.GenericMinpsOutputBean;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;

public class GenericPensionadosMinpsOutputBean 
extends GenericMinpsOutputBean{

	private LineaRegistroTipo1Pensionado lineaRegistroTipo1;
	private List<LineaRegistroTipo2Pensionado> lineaRegistroTipo2List = new ArrayList<>();
	
	public LineaRegistroTipo1Pensionado getLineaRegistroTipo1() {
		return lineaRegistroTipo1;
	}
	public void setLineaRegistroTipo1(LineaRegistroTipo1Pensionado lineaRegistroTipo1) {
		this.lineaRegistroTipo1 = lineaRegistroTipo1;
	}
	public List<LineaRegistroTipo2Pensionado> getLineaRegistroTipo2List() {
		return lineaRegistroTipo2List;
	}
	public void setLineaRegistroTipo2List(List<LineaRegistroTipo2Pensionado> lineaRegistroTipo2List) {
		this.lineaRegistroTipo2List = lineaRegistroTipo2List;
	}
	
}
