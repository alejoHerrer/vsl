package net.jaimetorres.pila.approval.core.processors.minps;

import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.pensionados.GenericPensionadosMinpsOutputBean;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsOutputFile;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;
import net.jaimetorres.validaciones.to.ErrorTO;
import net.jaimetorres.validaciones.to.TotalesTO;
import net.jaimetorres.validaciones.validador.pensionado.Pensionado;

public class PensionadosContextMinpsFileProcessor 
extends SpecificContextMinpsFileProcessor{

	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(MinpsOutputFile fileParsed) {
		
		GenericPensionadosMinpsOutputBean genericMinpsOutputBean = new GenericPensionadosMinpsOutputBean();
		
		//ENCABEZADO
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = 
				minpsConverter.convertMinpsPensionadosEncabezado2LineaRegistroTipo1(
						fileParsed.getEncabezado());
		genericMinpsOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);

		//LIQUDACION_DETALLADA
		List<LineaRegistroTipo2Pensionado> lineaRegistroTipo2List = 
				minpsConverter.convertMinpsPensionadosLiquidacionDetallada2LineaRegistroTipo2List(
						fileParsed.getLiquidacionDetalladaList());
		genericMinpsOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		return super.finishConvertionOutputStructureTotalesToGenericOutputBean(fileParsed,genericMinpsOutputBean);
	}

	@Override
	protected List<ErrorTO> validateSpecificOutputBean(GenericOutputBean genericOutputBean) {
		
		List<ErrorTO> allErrorsDetected = new ArrayList<>();
		
		GenericPensionadosMinpsOutputBean genericMinpsOutputBean = (GenericPensionadosMinpsOutputBean) genericOutputBean;
		
		//ENCABEZADO
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = genericMinpsOutputBean.getLineaRegistroTipo1();
		
		//Se requiere una instancia de "Pensionado" por cada validacion de un record (linea)
		Pensionado interssiCommonsValidatorRegistroTipo1 = new Pensionado();
		interssiCommonsValidatorRegistroTipo1.validarEncabezado(lineaRegistroTipo1);
		allErrorsDetected.addAll(interssiCommonsValidatorRegistroTipo1.getListaErroresEncabezado());
 		
		//LIQUIDACION DETALLADA
//		Map<String, List<LineaRegistroTipo2Pensionado>> lineasRegistroTipo2GroupByCotizante = genericMinpsOutputBean.getLineaRegistroTipo2List().stream().collect(
//				Collectors.groupingBy(lrt2-> lrt2.getTipoDocumentoPensionado()+lrt2.getNumeroDocumentoPensionado() ) );
		
		genericMinpsOutputBean.getLineaRegistroTipo2List().forEach(lrt2->{
//			List<LineaRegistroTipo2> lineasDelMismoCotizante = lineasRegistroTipo2GroupByCotizante.get(lrt2.getTipoDocumento()+lrt2.getNumeroDocumento());
			Pensionado interssiCommonsValidatorRegistroTipo2 = new Pensionado();
			interssiCommonsValidatorRegistroTipo2.validar(lineaRegistroTipo1, lrt2);
			allErrorsDetected.addAll(interssiCommonsValidatorRegistroTipo2.getListaErrores());
		});
		
		//TOTALES
		TotalesTO totalesTO = new TotalesTO();
		totalesTO.setLineaRegistro3(genericMinpsOutputBean.getLineaRegistroTipo3List());
		totalesTO.setLineaRegistro4(genericMinpsOutputBean.getLineaRegistroTipo4());
		totalesTO.setLineaRegistro5(genericMinpsOutputBean.getLineaRegistroTipo5List());
		totalesTO.setLineaRegistro6(genericMinpsOutputBean.getLineaRegistroTipo6List());
		totalesTO.setLineaRegistro7(genericMinpsOutputBean.getLineaRegistroTipo7List());
		totalesTO.setLineaRegistro8(genericMinpsOutputBean.getLineaRegistroTipo8());
		totalesTO.setLineaRegistro9(genericMinpsOutputBean.getLineaRegistroTipo9());
		totalesTO.setLineaRegistro10(genericMinpsOutputBean.getLineaRegistroTipo10());
		totalesTO.setLineaRegistro11(genericMinpsOutputBean.getLineaRegistroTipo11());
		totalesTO.setLineaRegistro12(genericMinpsOutputBean.getLineaRegistroTipo12());
		totalesTO.setLineaRegistro13(genericMinpsOutputBean.getLineaRegistroTipo13());
		interssiCommonsValidatorRegistroTipo1.validarTotalesMinpsPensionados(lineaRegistroTipo1, totalesTO, genericMinpsOutputBean.getLineaRegistroTipo2List());
		List<ErrorTO> totalesErrores = interssiCommonsValidatorRegistroTipo1.getListaErrores();
		allErrorsDetected.addAll(totalesErrores);
		
		return allErrorsDetected;
	}

}
