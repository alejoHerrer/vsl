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
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosLey1819Out;
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosOutputFile;
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosTotalesRenglon32Out;
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosTotalesRenglon39Out;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.pila.approval.utils.NumberUtils;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;

public class ApprovalActivosRiesgosFileProcessor 
extends AdminApprovalFileProcessor<ActivosRiesgosOutputFile>{

	public ApprovalActivosRiesgosFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}

	@Override
	protected String getStreamName() {
		return "ActivosRiesgosOutputFile";
	}

	@Override
	protected ActivosRiesgosOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		//by-pass
		if(reader == null){return null;}
		
		ActivosRiesgosOutputFile activosRiesgosOutputFile = new ActivosRiesgosOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				activosRiesgosOutputFile.setEncabezado((ActivosRiesgosEncabezadoOut)record);
        	}else if("liquidacionDetallada".equals(recordName) ){
        		activosRiesgosOutputFile.getLiquidacionDetalladaList().add((ActivosRiesgosLiquidacionDetalladaOut)record);
        	}else if("totalesRenglon31".equals(recordName) ){
        		activosRiesgosOutputFile.setRenglon31((ActivosRiesgosTotalesRenglon31Out)record);
        	}else if("totalesRenglon32".equals(recordName) ){
        		activosRiesgosOutputFile.setRenglon32((ActivosRiesgosTotalesRenglon32Out)record);
        	}else if("totalesRenglon36".equals(recordName) ){
        		activosRiesgosOutputFile.setRenglon36((ActivosRiesgosTotalesRenglon36Out)record);
        	}else if("totalesRenglon39".equals(recordName) ){
        		activosRiesgosOutputFile.setRenglon39((ActivosRiesgosTotalesRenglon39Out)record);
        	}else if("ley1819".equals(recordName) ){
        		activosRiesgosOutputFile.setLey1819((ActivosRiesgosLey1819Out)record);
        	}
		}
		
		return activosRiesgosOutputFile;
	}

	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(ActivosRiesgosOutputFile fileParsed) {
		
		ActivosConverter activosConverter = new ActivosConverter();
		
		GenericActivosAdminOutputBean genericAdminOutputBean = new GenericActivosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1 lineaRegistroTipo1 = activosConverter
				.convertRiesgosEncabezado2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		//REGISTRO TIPO 2 (LIQUIDACION DETALLADA)
		List<LineaRegistroTipo2> lineaRegistroTipo2List = activosConverter
				.convertRiesgosLiquidacionDetalladaList2LineaRegistroTipo2List(
						fileParsed.getLiquidacionDetalladaList());
		genericAdminOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		//REGISTROS TOTALES
		LineaRegistroTipo31 lineaRegistroTipo31 = activosConverter.
				convertSimpleRenglon31ToLineaRegistroTipo31(SubsistemaPila.RIESGOS,fileParsed.getRenglon31());
		genericAdminOutputBean.setLineaRegistroTipo31(lineaRegistroTipo31);
		
		//XXX FALTA RENGLON 32 (DESCUENTOS)
		
		LineaRegistroTipo36 lineaRegistroTipo36 = activosConverter.
				convertSimpleRenglon36ToLineaRegistroTipo36(SubsistemaPila.RIESGOS,fileParsed.getRenglon36());
		genericAdminOutputBean.setLineaRegistroTipo36(lineaRegistroTipo36);
		
		LineaRegistroTipo39 lineaRegistroTipo39 = activosConverter.
				convertRiesgosRenglon39ToLineaRegistroTipo39(fileParsed.getRenglon39());
		genericAdminOutputBean.setLineaRegistroTipo39(lineaRegistroTipo39);
		
		//XXX FALTA RENGLON: BENEFICIARIO DE LEY 1819 DE 2016
		
		return genericAdminOutputBean;
	}

	@Override
	protected void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean,
			String codigoAdministradora) {
		((GenericActivosAdminOutputBean)genericAdminOutputBean).getLineaRegistroTipo1().setCodigoARP(codigoAdministradora);
		
//XXX VERIFICAR		
//		genericAdminOutputBean.getLineaRegistroTipo2List().forEach(rt2->{
//			rt2.setArp(codigoAdministradora); });
		
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
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(ActivosRiesgosOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericActivosAdminOutputBean) genericOutputBean);
	}
}
