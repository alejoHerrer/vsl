package net.jaimetorres.pila.approval.core.processors.minps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.jaimetorres.pila.approval.core.Core;
import net.jaimetorres.pila.approval.core.converters.MinpsConverter;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.activos.GenericActivosMinpsOutputBean;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsOutputFile;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.to.ErrorTO;
import net.jaimetorres.validaciones.to.TotalesTO;
import net.jaimetorres.validaciones.validador.Cotizante;

public class ActivosContextMinpsFileProcessor 
extends SpecificContextMinpsFileProcessor{

	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(
			MinpsOutputFile fileParsed) {
		
		GenericActivosMinpsOutputBean genericMinpsOutputBean = new GenericActivosMinpsOutputBean();

		//ENCABEZADO
		LineaRegistroTipo1 lineaRegistroTipo1 = 
				minpsConverter.convertMinpsActivosEncabezado2LineaRegistroTipo1(
						fileParsed.getEncabezado());
		genericMinpsOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);

		//LIQUDACION_DETALLADA
		List<LineaRegistroTipo2> lineaRegistroTipo2List = 
				minpsConverter.convertMinpsActivosLiquidacionDetallada2LineaRegistroTipo2List(
						fileParsed.getLiquidacionDetalladaList());
		lineaRegistroTipo2List.forEach(l-> {//T137525
			if (l.getAfp().equals(Core.SINAFP)&&l.getAportesFSPsolidaridad()>0 && l.getTipoCotizante()==62) {
			l.setAfp(Core.FSP001);	
			}
		});
		genericMinpsOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		return super.finishConvertionOutputStructureTotalesToGenericOutputBean(fileParsed,genericMinpsOutputBean);
	}

	@Override
	protected List<ErrorTO> validateSpecificOutputBean(GenericOutputBean genericOutputBean) {
		
		List<ErrorTO> allErrorsDetected = new ArrayList<>();
		
		GenericActivosMinpsOutputBean genericMinpsOutputBean = (GenericActivosMinpsOutputBean) genericOutputBean;
		
		//ENCABEZADO
		LineaRegistroTipo1 lineaRegistroTipo1 = genericMinpsOutputBean.getLineaRegistroTipo1();
		
		//Se requiere una instancia de "Cotizante" por cada validacion de un record (linea)
		Cotizante interssiCommonsValidatorRegistroTipo1 = new Cotizante(getInterssiConnection());
		interssiCommonsValidatorRegistroTipo1.validarEncabezado(lineaRegistroTipo1);
		allErrorsDetected.addAll(interssiCommonsValidatorRegistroTipo1.getListaErroresEncabezado());
 		
		//LIQUIDACION DETALLADA
		Map<String, List<LineaRegistroTipo2>> lineasRegistroTipo2GroupByCotizante = genericMinpsOutputBean.getLineaRegistroTipo2List().stream().collect(
				Collectors.groupingBy(lrt2-> lrt2.getTipoDocumento()+lrt2.getNumeroDocumento() ) );
		
		genericMinpsOutputBean.getLineaRegistroTipo2List().forEach(lrt2->{
			List<LineaRegistroTipo2> lineasDelMismoCotizante = lineasRegistroTipo2GroupByCotizante.get(lrt2.getTipoDocumento()+lrt2.getNumeroDocumento());
			Cotizante interssiCommonsValidatorRegistroTipo2 = new Cotizante();
			interssiCommonsValidatorRegistroTipo2.validar(lineasDelMismoCotizante.size() == 1?null:lineasDelMismoCotizante, lineaRegistroTipo1, lrt2);
			allErrorsDetected.addAll(interssiCommonsValidatorRegistroTipo2.getListaErrores());
		});
		
		//TOTALES
		TotalesTO totalesTO = new TotalesTO();
		totalesTO.setLineaRegistro3(genericMinpsOutputBean.getLineaRegistroTipo3List());
		//XXX APLICA A PENSIONADOS
		//totalesTO.setLineaRegistro4(genericMinpsOutputBean.get);
		totalesTO.setLineaRegistro5(genericMinpsOutputBean.getLineaRegistroTipo5List());
		totalesTO.setLineaRegistro6(genericMinpsOutputBean.getLineaRegistroTipo6List());
		totalesTO.setLineaRegistro7(genericMinpsOutputBean.getLineaRegistroTipo7List());
		totalesTO.setLineaRegistro8(genericMinpsOutputBean.getLineaRegistroTipo8());
		totalesTO.setLineaRegistro9(genericMinpsOutputBean.getLineaRegistroTipo9());
		totalesTO.setLineaRegistro10(genericMinpsOutputBean.getLineaRegistroTipo10());
		totalesTO.setLineaRegistro11(genericMinpsOutputBean.getLineaRegistroTipo11());
		totalesTO.setLineaRegistro12(genericMinpsOutputBean.getLineaRegistroTipo12());
		totalesTO.setLineaRegistro13(genericMinpsOutputBean.getLineaRegistroTipo13());
		interssiCommonsValidatorRegistroTipo1.validarTotalesMinps(lineaRegistroTipo1, totalesTO, genericMinpsOutputBean.getLineaRegistroTipo2List());
		
		List<ErrorTO> totalesErrores = interssiCommonsValidatorRegistroTipo1.getListaErrores();
		allErrorsDetected.addAll(totalesErrores);
		
		return allErrorsDetected;
	}
}
