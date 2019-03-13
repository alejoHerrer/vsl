package net.jaimetorres.pila.approval.core.processors.admins.activos;

import java.io.File;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.beanio.BeanReader;

import net.jaimetorres.pila.approval.core.FamiliaTipoCotizante;
import net.jaimetorres.pila.approval.core.converters.ActivosConverter;
import net.jaimetorres.pila.approval.core.processors.admins.AdminApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.activos.GenericActivosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludLey1819Out;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludOutputFile;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludTotalesRenglon39Out;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.pila.approval.utils.NumberUtils;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;

public class ApprovalActivosSaludFileProcessor
extends AdminApprovalFileProcessor<ActivosSaludOutputFile>{

	public ApprovalActivosSaludFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}

	@Override
	protected String getStreamName() {
		return "ActivosSaludOutputFile";
	}

	@Override
	protected ActivosSaludOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		
		//by-pass
		if(reader == null){return null;}
		
		ActivosSaludOutputFile activosSaludOutputFile = new ActivosSaludOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				activosSaludOutputFile.setEncabezado((ActivosSaludEncabezadoOut)record);
        	}else if("liquidacionDetallada".equals(recordName) ){
        		activosSaludOutputFile.getLiquidacionDetalladaList().add((ActivosSaludLiquidacionDetalladaOut)record);
        	}else if("totalesRenglon31".equals(recordName) ){
        		activosSaludOutputFile.setRenglon31((ActivosSaludTotalesRenglon31Out)record);
        	}else if("totalesRenglon36".equals(recordName) ){
        		activosSaludOutputFile.setRenglon36((ActivosSaludTotalesRenglon36Out)record);
        	}else if("totalesRenglon39".equals(recordName) ){
        		activosSaludOutputFile.setRenglon39((ActivosSaludTotalesRenglon39Out)record);
        	}else if("ley1819".equals(recordName) ){
        		activosSaludOutputFile.setLey1819((ActivosSaludLey1819Out)record);
        	}
		}
		
		return activosSaludOutputFile;
	}

	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(ActivosSaludOutputFile fileParsed) {
		
		ActivosConverter activosConverter = new ActivosConverter();
		
		GenericActivosAdminOutputBean genericAdminOutputBean = new GenericActivosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1 lineaRegistroTipo1 = activosConverter
				.convertSaludEncabezado2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		//REGISTRO TIPO 2 (LIQUIDACION DETALLADA)
		List<LineaRegistroTipo2> lineaRegistroTipo2List = activosConverter
				.convertSaludLiquidacionDetalladaList2LineaRegistroTipo2List(
						fileParsed.getLiquidacionDetalladaList());
		genericAdminOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		//REGISTROS TOTALES
		LineaRegistroTipo31 lineaRegistroTipo31 = activosConverter.
				convertSaludRenglon31ToLineaRegistroTipo31(fileParsed.getRenglon31());
		genericAdminOutputBean.setLineaRegistroTipo31(lineaRegistroTipo31);
		
		LineaRegistroTipo36 lineaRegistroTipo36 = activosConverter.
				convertSaludRenglon36ToLineaRegistroTipo36(fileParsed.getRenglon36());
		genericAdminOutputBean.setLineaRegistroTipo36(lineaRegistroTipo36);
		
		LineaRegistroTipo39 lineaRegistroTipo39 = activosConverter.
				convertSaludRenglon39ToLineaRegistroTipo39(fileParsed.getRenglon39());
		genericAdminOutputBean.setLineaRegistroTipo39(lineaRegistroTipo39);
		
		//XXX FALTA RENGLON: BENEFICIARIO DE LEY 1819 DE 2016
		
		return genericAdminOutputBean;
	}

	@Override
	protected void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean,
			String codigoAdministradora) {
		((GenericActivosAdminOutputBean)genericAdminOutputBean).getLineaRegistroTipo2List().forEach(rt2->{
			rt2.setEps(codigoAdministradora); });
		
	}

	private DateTimeFormatter periodoDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM", Locale.ENGLISH);
	@Override
	protected void establishPeriodo(GenericAdminOutputBean _genericAdminOutputBean, YearMonth periodo) {
		
		GenericActivosAdminOutputBean genericAdminOutputBean = (GenericActivosAdminOutputBean)_genericAdminOutputBean;
		
		//SALUD
		String periodoSalud = periodo.getYear()+"-"+
				NumberUtils.padNumberWithZeros(periodo.getMonthValue(), 2);
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoSalud(periodoSalud);
		
		//PENSION
		String periodoPension = periodoSalud;
		if (!FamiliaTipoCotizante.INDEPENDIENTES.equals(genericAdminOutputBean.getFamiliaTipoCotizante())){
			YearMonth periodoPensionYearMonth = periodo.minusMonths(1);
			periodoPension = periodoDateFormatter.format(periodoPensionYearMonth); 
		}
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoNoSalud(periodoPension);
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
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(ActivosSaludOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericActivosAdminOutputBean) genericOutputBean);
	}
}
