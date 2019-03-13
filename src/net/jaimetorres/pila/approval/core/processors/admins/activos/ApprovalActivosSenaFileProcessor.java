package net.jaimetorres.pila.approval.core.processors.admins.activos;

import java.io.File;
import java.time.YearMonth;
import java.util.List;

import org.beanio.BeanReader;

import net.jaimetorres.pila.approval.core.FamiliaTipoCotizante;
import net.jaimetorres.pila.approval.core.converters.ActivosConverter;
import net.jaimetorres.pila.approval.core.misc.SubsistemaPila;
import net.jaimetorres.pila.approval.core.processors.admins.AdminApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.activos.GenericActivosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.output.activos.sena.ActivosSenaEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.sena.ActivosSenaLey1819Out;
import net.jaimetorres.pila.approval.pojos.output.activos.sena.ActivosSenaLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.activos.sena.ActivosSenaOutputFile;
import net.jaimetorres.pila.approval.pojos.output.activos.sena.ActivosSenaTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.activos.sena.ActivosSenaTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.activos.sena.ActivosSenaTotalesRenglon39Out;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.pila.approval.utils.NumberUtils;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;

public class ApprovalActivosSenaFileProcessor
extends AdminApprovalFileProcessor<ActivosSenaOutputFile>{

	public ApprovalActivosSenaFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}

	@Override
	protected String getStreamName() {
		return "ActivosSenaOutputFile";
	}

	@Override
	protected ActivosSenaOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		//by-pass
		if(reader == null){return null;}
		
		ActivosSenaOutputFile activosSenaOutputFile = new ActivosSenaOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				activosSenaOutputFile.setEncabezado((ActivosSenaEncabezadoOut)record);
        	}else if("liquidacionDetallada".equals(recordName) ){
        		activosSenaOutputFile.getLiquidacionDetalladaList().add((ActivosSenaLiquidacionDetalladaOut)record);
        	}else if("totalesRenglon31".equals(recordName) ){
        		activosSenaOutputFile.setRenglon31((ActivosSenaTotalesRenglon31Out)record);
        	}else if("totalesRenglon36".equals(recordName) ){
        		activosSenaOutputFile.setRenglon36((ActivosSenaTotalesRenglon36Out)record);
        	}else if("totalesRenglon39".equals(recordName) ){
        		activosSenaOutputFile.setRenglon39((ActivosSenaTotalesRenglon39Out)record);
        	}else if("ley1819".equals(recordName) ){
        		activosSenaOutputFile.setLey1819((ActivosSenaLey1819Out)record);
        	}
		}
		
		return activosSenaOutputFile;
	}

	@Override
	protected void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean,
			String codigoAdministradora) {
		// N/A
	}

	@Override
	protected void establishPeriodo(GenericAdminOutputBean _genericAdminOutputBean, YearMonth periodoYearMonth) {
		
		GenericActivosAdminOutputBean genericAdminOutputBean = (GenericActivosAdminOutputBean)_genericAdminOutputBean;
		
		//PENSION
		String periodoPension = periodoYearMonth.getYear()+"-"+
				NumberUtils.padNumberWithZeros(periodoYearMonth.getMonthValue(), 2);
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoNoSalud(periodoPension);
		
		//SALUD
		String periodoSalud = periodoPension;
		FamiliaTipoCotizante familiaTipoCotizante = genericAdminOutputBean.getFamiliaTipoCotizante();
		if(FamiliaTipoCotizante.DEPENDIENTES.equals(familiaTipoCotizante)){
			YearMonth periodoSaludYearMonth = periodoYearMonth.plusMonths(1);
			periodoSalud = periodoSaludYearMonth.getYear()+"-"+NumberUtils.padNumberWithZeros(periodoSaludYearMonth.getMonthValue(), 2);
		}
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoSalud(periodoSalud);
	}

	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(
			ActivosSenaOutputFile fileParsed) {
		
		ActivosConverter activosConverter = new ActivosConverter();
		
		GenericActivosAdminOutputBean genericAdminOutputBean = new GenericActivosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1 lineaRegistroTipo1 = activosConverter
				.convertSenaEncabezado2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		//REGISTRO TIPO 2 (LIQUIDACION DETALLADA)
		List<LineaRegistroTipo2> lineaRegistroTipo2List = activosConverter
				.convertSenaIcbfLiquidacionDetalladaList2LineaRegistroTipo2List(
						SubsistemaPila.PARAFISCALES_SENA,
						fileParsed.getLiquidacionDetalladaList());
		genericAdminOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		//REGISTROS TOTALES
		LineaRegistroTipo31 lineaRegistroTipo31 = activosConverter.
				convertSimpleRenglon31ToLineaRegistroTipo31(SubsistemaPila.PARAFISCALES_SENA,fileParsed.getRenglon31());
		genericAdminOutputBean.setLineaRegistroTipo31(lineaRegistroTipo31);
		
		LineaRegistroTipo36 lineaRegistroTipo36 = activosConverter.
				convertSimpleRenglon36ToLineaRegistroTipo36(SubsistemaPila.PARAFISCALES_SENA,fileParsed.getRenglon36());
		genericAdminOutputBean.setLineaRegistroTipo36(lineaRegistroTipo36);
		
		LineaRegistroTipo39 lineaRegistroTipo39 = activosConverter.
				convertSimpleRenglon39ToLineaRegistroTipo39(SubsistemaPila.PARAFISCALES_SENA,fileParsed.getRenglon39());
		genericAdminOutputBean.setLineaRegistroTipo39(lineaRegistroTipo39);
		
		//XXX FALTA RENGLON: BENEFICIARIO DE LEY 1819 DE 2016
		
		return genericAdminOutputBean;
	}
	
	@Override
	protected void validateSpecificOutputBean(GenericOutputBean genericOutputBean) {
		//T268777
		try{
			super.validateAdminOutputBean((GenericAdminOutputBean) genericOutputBean);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Override
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(ActivosSenaOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericActivosAdminOutputBean) genericOutputBean);
	}

}
