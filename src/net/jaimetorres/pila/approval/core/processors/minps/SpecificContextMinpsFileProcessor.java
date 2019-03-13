package net.jaimetorres.pila.approval.core.processors.minps;

import java.util.List;

import net.jaimetorres.pila.approval.core.converters.MinpsConverter;
import net.jaimetorres.pila.approval.core.processors.EnhancedApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.generics.GenericMinpsOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.activos.GenericActivosMinpsOutputBean;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsOutputFile;
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
import net.jaimetorres.validaciones.to.ErrorTO;

public abstract class SpecificContextMinpsFileProcessor 
extends EnhancedApprovalFileProcessor {

	protected MinpsConverter minpsConverter = new MinpsConverter();
	
	protected abstract GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(MinpsOutputFile fileParsed);
	protected abstract List<ErrorTO> validateSpecificOutputBean(GenericOutputBean genericOutputBean);
	
	protected GenericMinpsOutputBean finishConvertionOutputStructureTotalesToGenericOutputBean(MinpsOutputFile fileParsed,
			GenericMinpsOutputBean genericMinpsOutputBean) {
		
		//TOTALES
		//-PENSION
		List<LineaRegistroTipo3> lineaRegistroTipo3List = 
				minpsConverter.convertMinpsTotalesPension2LineaRegistroTipo3(fileParsed.getRenglon3());
		genericMinpsOutputBean.setLineaRegistroTipo3List(lineaRegistroTipo3List);
		
		//-FSP
		LineaRegistroTipo4 lineaRegistroTipo4 = 
				minpsConverter.convertMinpsTotalesFsp2LineaRegistroTipo4(fileParsed.getRenglon4());
		genericMinpsOutputBean.setLineaRegistroTipo4(lineaRegistroTipo4);
		
		//-SALUD
		List<LineaRegistroTipo5> lineaRegistroTipo5List = 
				minpsConverter.convertMinpsTotalesSalud2LineaRegistroTipo5(fileParsed.getRenglon5());
		genericMinpsOutputBean.setLineaRegistroTipo5List(lineaRegistroTipo5List);
		
		//-RIESGOS
		List<LineaRegistroTipo6> lineaRegistroTipo6List = 
				minpsConverter.convertMinpsTotalesRiesgos2LineaRegistroTipo6(fileParsed.getRenglon6());
		genericMinpsOutputBean.setLineaRegistroTipo6List(lineaRegistroTipo6List);
		
		//-CAJAS
		List<LineaRegistroTipo7> lineaRegistroTipo7List = 
				minpsConverter.convertMinpsTotalesCajas2LineaRegistroTipo7(fileParsed.getRenglon7());
		genericMinpsOutputBean.setLineaRegistroTipo7List(lineaRegistroTipo7List);
		
		//-SENA
		LineaRegistroTipo8 lineaRegistroTipo8 = minpsConverter.convertMinpsTotalesSena2LineaRegistroTipo8(fileParsed.getRenglon8());
		genericMinpsOutputBean.setLineaRegistroTipo8(lineaRegistroTipo8);
		
		//-ICBF
		LineaRegistroTipo9 lineaRegistroTipo9 = minpsConverter.convertMinpsTotalesIcbf2LineaRegistroTipo9(fileParsed.getRenglon9());
		genericMinpsOutputBean.setLineaRegistroTipo9(lineaRegistroTipo9);
		
		//-ESAP
		LineaRegistroTipo10 lineaRegistroTipo10 = minpsConverter.convertMinpsTotalesEsap2LineaRegistroTipo10(fileParsed.getRenglon10());
		genericMinpsOutputBean.setLineaRegistroTipo10(lineaRegistroTipo10);
		
		//-MINEDU
		LineaRegistroTipo11 lineaRegistroTipo11 = minpsConverter.convertMinpsTotalesMinedu2LineaRegistroTipo11(fileParsed.getRenglon11());
		genericMinpsOutputBean.setLineaRegistroTipo11(lineaRegistroTipo11);
		
		//-GRAN-TOTAL-PLANILLA
		LineaRegistroTipo12 lineaRegistroTipo12 = minpsConverter.convertMinpsGranTotal2LineaRegistroTipo12(fileParsed.getRenglon12());
		genericMinpsOutputBean.setLineaRegistroTipo12(lineaRegistroTipo12);
		
		//-LEY 1819
		LineaRegistroTipo13 lineaRegistroTipo13 = minpsConverter.convertMinpsLey18192LineaRegistroTipo13(fileParsed.getRenglon13());
		genericMinpsOutputBean.setLineaRegistroTipo13(lineaRegistroTipo13);
		
		return genericMinpsOutputBean;
	}
}
