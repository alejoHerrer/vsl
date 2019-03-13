package net.jaimetorres.pila.approval.pojos.generics.activos;

import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.core.FamiliaTipoCotizante;
import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;

public class GenericActivosAdminOutputBean 
extends GenericAdminOutputBean{

	private FamiliaTipoCotizante familiaTipoCotizante; 
	private LineaRegistroTipo1 lineaRegistroTipo1;
	private List<LineaRegistroTipo2> lineaRegistroTipo2List = new ArrayList<>();
	private LineaRegistroTipo31 lineaRegistroTipo31;
	private LineaRegistroTipo36 lineaRegistroTipo36;
	private LineaRegistroTipo39 lineaRegistroTipo39;
	
	@Override
	public void establishFechaPago(String fechaPago) {
		this.lineaRegistroTipo1.setFechaPagoPlanilla(fechaPago);
	}
	
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
	public FamiliaTipoCotizante getFamiliaTipoCotizante() {
		return familiaTipoCotizante;
	}
	public void setFamiliaTipoCotizante(FamiliaTipoCotizante familiaTipoCotizante) {
		this.familiaTipoCotizante = familiaTipoCotizante;
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
}
