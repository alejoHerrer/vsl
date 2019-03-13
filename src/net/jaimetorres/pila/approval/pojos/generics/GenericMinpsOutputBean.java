package net.jaimetorres.pila.approval.pojos.generics;

import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.validaciones.estructura.LineaRegistroTipo10;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo11;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo12;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo13;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo3;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo4;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo5;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo6;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo7;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo8;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo9;

public class GenericMinpsOutputBean 
extends GenericOutputBean{

	private List<LineaRegistroTipo3> lineaRegistroTipo3List = new ArrayList<>();
	private LineaRegistroTipo4 lineaRegistroTipo4;
	private List<LineaRegistroTipo5> lineaRegistroTipo5List = new ArrayList<>();
	private List<LineaRegistroTipo6> lineaRegistroTipo6List = new ArrayList<>();
	private List<LineaRegistroTipo7> lineaRegistroTipo7List = new ArrayList<>();

	private LineaRegistroTipo8 lineaRegistroTipo8;
	private LineaRegistroTipo9 lineaRegistroTipo9;
	private LineaRegistroTipo10 lineaRegistroTipo10;
	private LineaRegistroTipo11 lineaRegistroTipo11;
	private LineaRegistroTipo12 lineaRegistroTipo12;
	private LineaRegistroTipo13 lineaRegistroTipo13;
	
	public List<LineaRegistroTipo3> getLineaRegistroTipo3List() {
		return lineaRegistroTipo3List;
	}
	public void setLineaRegistroTipo3List(List<LineaRegistroTipo3> lineaRegistroTipo3List) {
		this.lineaRegistroTipo3List = lineaRegistroTipo3List;
	}
	public LineaRegistroTipo4 getLineaRegistroTipo4() {
		return lineaRegistroTipo4;
	}
	public void setLineaRegistroTipo4(LineaRegistroTipo4 lineaRegistroTipo4) {
		this.lineaRegistroTipo4 = lineaRegistroTipo4;
	}
	public List<LineaRegistroTipo5> getLineaRegistroTipo5List() {
		return lineaRegistroTipo5List;
	}
	public void setLineaRegistroTipo5List(List<LineaRegistroTipo5> lineaRegistroTipo5List) {
		this.lineaRegistroTipo5List = lineaRegistroTipo5List;
	}
	public List<LineaRegistroTipo6> getLineaRegistroTipo6List() {
		return lineaRegistroTipo6List;
	}
	public void setLineaRegistroTipo6List(List<LineaRegistroTipo6> lineaRegistroTipo6List) {
		this.lineaRegistroTipo6List = lineaRegistroTipo6List;
	}
	public List<LineaRegistroTipo7> getLineaRegistroTipo7List() {
		return lineaRegistroTipo7List;
	}
	public void setLineaRegistroTipo7List(List<LineaRegistroTipo7> lineaRegistroTipo7List) {
		this.lineaRegistroTipo7List = lineaRegistroTipo7List;
	}
	public LineaRegistroTipo8 getLineaRegistroTipo8() {
		return lineaRegistroTipo8;
	}
	public void setLineaRegistroTipo8(LineaRegistroTipo8 lineaRegistroTipo8) {
		this.lineaRegistroTipo8 = lineaRegistroTipo8;
	}
	public LineaRegistroTipo9 getLineaRegistroTipo9() {
		return lineaRegistroTipo9;
	}
	public void setLineaRegistroTipo9(LineaRegistroTipo9 lineaRegistroTipo9) {
		this.lineaRegistroTipo9 = lineaRegistroTipo9;
	}
	public LineaRegistroTipo10 getLineaRegistroTipo10() {
		return lineaRegistroTipo10;
	}
	public void setLineaRegistroTipo10(LineaRegistroTipo10 lineaRegistroTipo10) {
		this.lineaRegistroTipo10 = lineaRegistroTipo10;
	}
	public LineaRegistroTipo11 getLineaRegistroTipo11() {
		return lineaRegistroTipo11;
	}
	public void setLineaRegistroTipo11(LineaRegistroTipo11 lineaRegistroTipo11) {
		this.lineaRegistroTipo11 = lineaRegistroTipo11;
	}
	public LineaRegistroTipo12 getLineaRegistroTipo12() {
		return lineaRegistroTipo12;
	}
	public void setLineaRegistroTipo12(LineaRegistroTipo12 lineaRegistroTipo12) {
		this.lineaRegistroTipo12 = lineaRegistroTipo12;
	}
	public LineaRegistroTipo13 getLineaRegistroTipo13() {
		return lineaRegistroTipo13;
	}
	public void setLineaRegistroTipo13(LineaRegistroTipo13 lineaRegistroTipo13) {
		this.lineaRegistroTipo13 = lineaRegistroTipo13;
	}
}
