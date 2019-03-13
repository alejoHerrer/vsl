package net.jaimetorres.pila.approval.pojos.generics.pensionados;

import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;
import net.jaimetorres.validaciones.estructura.LineaTotales;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;

public class GenericPensionadosAdminOutputBean 
extends GenericAdminOutputBean{

	private LineaRegistroTipo1Pensionado lineaRegistroTipo1;
	private List<LineaRegistroTipo2Pensionado> lineaRegistroTipo2List = new ArrayList<>();
	
	//Aplica para los subsistemas: PENSION y SALUD
	private LineaRegistroTipo31 lineaRegistroTipo31;
	private LineaRegistroTipo36 lineaRegistroTipo36;
	private LineaRegistroTipo39 lineaRegistroTipo39;
	
	//Aplica para los subsistemas: FPS y CAJAS
	private LineaTotales lineaTotales;
	
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
	
	@Override
	public void establishFechaPago(String fechaPago) {
		this.lineaRegistroTipo1.setFechaPagoPlanilla(fechaPago);
	}
	public LineaRegistroTipo31 getLineaRegistroTipo31() {
		return lineaRegistroTipo31;
	}
	public void setLineaRegistroTipo31(LineaRegistroTipo31 lineaRegistroTipo31) {
		this.lineaRegistroTipo31 = lineaRegistroTipo31;
	}
	public LineaRegistroTipo36 getLineaRegistroTipo36() {
		return lineaRegistroTipo36;
	}
	public void setLineaRegistroTipo36(LineaRegistroTipo36 lineaRegistroTipo36) {
		this.lineaRegistroTipo36 = lineaRegistroTipo36;
	}
	public LineaRegistroTipo39 getLineaRegistroTipo39() {
		return lineaRegistroTipo39;
	}
	public void setLineaRegistroTipo39(LineaRegistroTipo39 lineaRegistroTipo39) {
		this.lineaRegistroTipo39 = lineaRegistroTipo39;
	}
	public LineaTotales getLineaTotales() {
		return lineaTotales;
	}
	public void setLineaTotales(LineaTotales lineaTotales) {
		this.lineaTotales = lineaTotales;
	}
}
