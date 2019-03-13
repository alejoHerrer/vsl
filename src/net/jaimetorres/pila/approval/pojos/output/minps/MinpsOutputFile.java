package net.jaimetorres.pila.approval.pojos.output.minps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;

public class MinpsOutputFile 
extends CommonOutputFile{
	
	private MinpsEncabezadoOut encabezado;
	private List<MinpsLiquidacionDetalladaOut> liquidacionDetalladaList = new ArrayList<>();
	private List<MinpsTotalesRegistroTipo3PensionOut> renglon3 = new ArrayList<>();
	private MinpsTotalesRegistroTipo4FspOut renglon4;
	private List<MinpsTotalesRegistroTipo5SaludOut> renglon5 = new ArrayList<>();
	private List<MinpsTotalesRegistroTipo6RiesgosOut> renglon6 = new ArrayList<>();
	private List<MinpsTotalesRegistroTipo7CajasOut> renglon7 = new ArrayList<>();
	private MinpsTotalesRegistroTipo8SenaOut renglon8;
	private MinpsTotalesRegistroTipo9IcbfOut renglon9;
	private MinpsTotalesRegistroTipo10EsapOut renglon10;
	private MinpsTotalesRegistroTipo11MineduOut renglon11;
	private MinpsTotalesRegistroTipo12PlanillaOut renglon12;
	private MinpsLey1819Out renglon13;
	
	public MinpsOutputFile(File file) {
		super(file);
	}

	public MinpsEncabezadoOut getEncabezado() {
		return encabezado;
	}


	public void setEncabezado(MinpsEncabezadoOut encabezado) {
		this.encabezado = encabezado;
	}


	public List<MinpsLiquidacionDetalladaOut> getLiquidacionDetalladaList() {
		return liquidacionDetalladaList;
	}


	public void setLiquidacionDetalladaList(List<MinpsLiquidacionDetalladaOut> liquidacionDetalladaList) {
		this.liquidacionDetalladaList = liquidacionDetalladaList;
	}


	public List<MinpsTotalesRegistroTipo3PensionOut> getRenglon3() {
		return renglon3;
	}


	public void setRenglon3(List<MinpsTotalesRegistroTipo3PensionOut> renglon3) {
		this.renglon3 = renglon3;
	}


	public MinpsTotalesRegistroTipo4FspOut getRenglon4() {
		return renglon4;
	}


	public void setRenglon4(MinpsTotalesRegistroTipo4FspOut renglon4) {
		this.renglon4 = renglon4;
	}


	public List<MinpsTotalesRegistroTipo5SaludOut> getRenglon5() {
		return renglon5;
	}


	public void setRenglon5(List<MinpsTotalesRegistroTipo5SaludOut> renglon5) {
		this.renglon5 = renglon5;
	}


	public List<MinpsTotalesRegistroTipo6RiesgosOut> getRenglon6() {
		return renglon6;
	}


	public void setRenglon6(List<MinpsTotalesRegistroTipo6RiesgosOut> renglon6) {
		this.renglon6 = renglon6;
	}


	public List<MinpsTotalesRegistroTipo7CajasOut> getRenglon7() {
		return renglon7;
	}


	public void setRenglon7(List<MinpsTotalesRegistroTipo7CajasOut> renglon7) {
		this.renglon7 = renglon7;
	}


	public MinpsTotalesRegistroTipo8SenaOut getRenglon8() {
		return renglon8;
	}


	public void setRenglon8(MinpsTotalesRegistroTipo8SenaOut renglon8) {
		this.renglon8 = renglon8;
	}


	public MinpsTotalesRegistroTipo9IcbfOut getRenglon9() {
		return renglon9;
	}


	public void setRenglon9(MinpsTotalesRegistroTipo9IcbfOut renglon9) {
		this.renglon9 = renglon9;
	}


	public MinpsTotalesRegistroTipo10EsapOut getRenglon10() {
		return renglon10;
	}


	public void setRenglon10(MinpsTotalesRegistroTipo10EsapOut renglon10) {
		this.renglon10 = renglon10;
	}


	public MinpsTotalesRegistroTipo11MineduOut getRenglon11() {
		return renglon11;
	}


	public void setRenglon11(MinpsTotalesRegistroTipo11MineduOut renglon11) {
		this.renglon11 = renglon11;
	}


	public MinpsTotalesRegistroTipo12PlanillaOut getRenglon12() {
		return renglon12;
	}


	public void setRenglon12(MinpsTotalesRegistroTipo12PlanillaOut renglon12) {
		this.renglon12 = renglon12;
	}


	public MinpsLey1819Out getRenglon13() {
		return renglon13;
	}


	public void setRenglon13(MinpsLey1819Out renglon13) {
		this.renglon13 = renglon13;
	}

}
