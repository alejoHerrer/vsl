package net.jaimetorres.pila.approval.core.processors.admins.activos;

import java.io.File;
import java.time.YearMonth;
import java.util.List;

import org.beanio.BeanReader;

import net.jaimetorres.pila.approval.core.FamiliaTipoCotizante;
import net.jaimetorres.pila.approval.core.converters.ActivosConverter;
import net.jaimetorres.pila.approval.core.processors.admins.AdminApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.activos.GenericActivosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionOutputFile;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionTotalesRenglon39Out;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.pila.approval.utils.NumberUtils;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;

public class ApprovalActivosPensionFileProcessor 
extends AdminApprovalFileProcessor<ActivosPensionOutputFile>{

	public ApprovalActivosPensionFileProcessor(
			ApprovalFileProcessorParameters approvalFileProcessorParameters){
		super(approvalFileProcessorParameters);
	} 
	
	@Override
	protected String getStreamName() {
		return "ActivosPensionOutputFile";
	}

	@Override
	protected ActivosPensionOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		
		//by-pass
		if(reader == null){return null;}
		
		ActivosPensionOutputFile activosPensionOutputFile = new ActivosPensionOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				activosPensionOutputFile.setEncabezado((ActivosPensionEncabezadoOut)record);
        	}else if("liquidacionDetallada".equals(recordName) ){
        		activosPensionOutputFile.getLiquidacionDetalladaList().add((ActivosPensionLiquidacionDetalladaOut)record);
        	}else if("totalesRenglon31".equals(recordName) ){
        		activosPensionOutputFile.setRenglon31((ActivosPensionTotalesRenglon31Out)record);
        	}else if("totalesRenglon36".equals(recordName) ){
        		activosPensionOutputFile.setRenglon36((ActivosPensionTotalesRenglon36Out)record);
        	}else if("totalesRenglon39".equals(recordName) ){
        		activosPensionOutputFile.setRenglon39((ActivosPensionTotalesRenglon39Out)record);
        	}
		}
		
		return activosPensionOutputFile;
	}

	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(ActivosPensionOutputFile fileParsed) {
		
		ActivosConverter activosConverter = new ActivosConverter();
		
		GenericActivosAdminOutputBean genericAdminOutputBean = new GenericActivosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1 lineaRegistroTipo1 = activosConverter
				.convertPensionEncabezado2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		//REGISTRO TIPO 2 (LIQUIDACION DETALLADA)
		List<LineaRegistroTipo2> lineaRegistroTipo2List = activosConverter
				.convertPensionLiquidacionDetalladaList2LineaRegistroTipo2List(
						fileParsed.getLiquidacionDetalladaList());
		genericAdminOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		//REGISTROS TOTALES
		LineaRegistroTipo31 lineaRegistroTipo31 = activosConverter.
				convertPensionRenglon31ToLineaRegistroTipo31(fileParsed.getRenglon31());
		genericAdminOutputBean.setLineaRegistroTipo31(lineaRegistroTipo31);
		
		LineaRegistroTipo36 lineaRegistroTipo36 = activosConverter.
				convertPensionRenglon36ToLineaRegistroTipo36(fileParsed.getRenglon36());
		genericAdminOutputBean.setLineaRegistroTipo36(lineaRegistroTipo36);
		
		LineaRegistroTipo39 lineaRegistroTipo39 = activosConverter.
				convertPensionRenglon39ToLineaRegistroTipo39(fileParsed.getRenglon39());
		genericAdminOutputBean.setLineaRegistroTipo39(lineaRegistroTipo39);
		
		return genericAdminOutputBean;
	}

	@Override
	protected void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean,
			String codigoAdministradora) {
		((GenericActivosAdminOutputBean)genericAdminOutputBean).getLineaRegistroTipo2List().forEach(rt2->{
			rt2.setAfp(codigoAdministradora); });
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
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(ActivosPensionOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericActivosAdminOutputBean) genericOutputBean);
	}
}
