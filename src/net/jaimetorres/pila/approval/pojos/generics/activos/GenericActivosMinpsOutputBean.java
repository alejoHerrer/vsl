package net.jaimetorres.pila.approval.pojos.generics.activos;

import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.generics.GenericMinpsOutputBean;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;

public class GenericActivosMinpsOutputBean 
extends GenericMinpsOutputBean{

	private LineaRegistroTipo1 lineaRegistroTipo1;
	private List<LineaRegistroTipo2> lineaRegistroTipo2List = new ArrayList<>();
	
	public LineaRegistroTipo1 getLineaRegistroTipo1() {
		return lineaRegistroTipo1;
	}
	public void setLineaRegistroTipo1(LineaRegistroTipo1 lineaRegistroTipo1) {
		this.lineaRegistroTipo1 = lineaRegistroTipo1;
	}
	public List<LineaRegistroTipo2> getLineaRegistroTipo2List() {
		return lineaRegistroTipo2List;
	}
	public void setLineaRegistroTipo2List(List<LineaRegistroTipo2> lineaRegistroTipo2List) {
		this.lineaRegistroTipo2List = lineaRegistroTipo2List;
	}
}
