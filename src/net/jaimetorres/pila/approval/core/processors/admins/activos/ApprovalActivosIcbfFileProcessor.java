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
import net.jaimetorres.pila.approval.pojos.output.activos.icbf.ActivosIcbfEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.icbf.ActivosIcbfLey1819Out;
import net.jaimetorres.pila.approval.pojos.output.activos.icbf.ActivosIcbfLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.activos.icbf.ActivosIcbfOutputFile;
import net.jaimetorres.pila.approval.pojos.output.activos.icbf.ActivosIcbfTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.activos.icbf.ActivosIcbfTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.activos.icbf.ActivosIcbfTotalesRenglon39Out;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.pila.approval.utils.NumberUtils;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;
import net.jaimetorres.validaciones.exception.DAOException;

public class ApprovalActivosIcbfFileProcessor
extends AdminApprovalFileProcessor<ActivosIcbfOutputFile>{

	public ApprovalActivosIcbfFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}

	@Override
	protected String getStreamName() {
		return "ActivosIcbfOutputFile";
	}

	@Override
	protected ActivosIcbfOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		//by-pass
		if(reader == null){return null;}
		
		ActivosIcbfOutputFile activosIcbfOutputFile = new ActivosIcbfOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				activosIcbfOutputFile.setEncabezado((ActivosIcbfEncabezadoOut)record);
        	}else if("liquidacionDetallada".equals(recordName) ){
        		activosIcbfOutputFile.getLiquidacionDetalladaList().add((ActivosIcbfLiquidacionDetalladaOut)record);
        	}else if("totalesRenglon31".equals(recordName) ){
        		activosIcbfOutputFile.setRenglon31((ActivosIcbfTotalesRenglon31Out)record);
        	}else if("totalesRenglon36".equals(recordName) ){
        		activosIcbfOutputFile.setRenglon36((ActivosIcbfTotalesRenglon36Out)record);
        	}else if("totalesRenglon39".equals(recordName) ){
        		activosIcbfOutputFile.setRenglon39((ActivosIcbfTotalesRenglon39Out)record);
        	}else if("ley1819".equals(recordName) ){
        		activosIcbfOutputFile.setLey1819((ActivosIcbfLey1819Out)record);
        	}
		}
		
		return activosIcbfOutputFile;
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
			ActivosIcbfOutputFile fileParsed) {
		
		ActivosConverter activosConverter = new ActivosConverter();
		
		GenericActivosAdminOutputBean genericAdminOutputBean = new GenericActivosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1 lineaRegistroTipo1 = activosConverter
				.convertIcbfEncabezado2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		//REGISTRO TIPO 2 (LIQUIDACION DETALLADA)
		List<LineaRegistroTipo2> lineaRegistroTipo2List = activosConverter
				.convertSenaIcbfLiquidacionDetalladaList2LineaRegistroTipo2List(
						SubsistemaPila.PARAFISCALES_ICBF,
						fileParsed.getLiquidacionDetalladaList());
		genericAdminOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		//REGISTROS TOTALES
		LineaRegistroTipo31 lineaRegistroTipo31 = activosConverter.
				convertSimpleRenglon31ToLineaRegistroTipo31(SubsistemaPila.PARAFISCALES_ICBF,fileParsed.getRenglon31());
		genericAdminOutputBean.setLineaRegistroTipo31(lineaRegistroTipo31);
		
		LineaRegistroTipo36 lineaRegistroTipo36 = activosConverter.
				convertSimpleRenglon36ToLineaRegistroTipo36(SubsistemaPila.PARAFISCALES_ICBF,fileParsed.getRenglon36());
		genericAdminOutputBean.setLineaRegistroTipo36(lineaRegistroTipo36);
		
		LineaRegistroTipo39 lineaRegistroTipo39 = activosConverter.
				convertSimpleRenglon39ToLineaRegistroTipo39(SubsistemaPila.PARAFISCALES_ICBF,fileParsed.getRenglon39());
		genericAdminOutputBean.setLineaRegistroTipo39(lineaRegistroTipo39);
		
		//XXX FALTA RENGLON: BENEFICIARIO DE LEY 1819 DE 2016
		
		return genericAdminOutputBean;
	}

	@Override
	protected void validateSpecificOutputBean(GenericOutputBean genericOutputBean) {
		try{
			super.validateAdminOutputBean((GenericAdminOutputBean) genericOutputBean);
		}catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(ActivosIcbfOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericActivosAdminOutputBean) genericOutputBean);
	}
}
