package net.jaimetorres.pila.approval.core.processors.admins.activos;

import java.io.File;
import java.time.YearMonth;

import org.beanio.BeanReader;

import net.jaimetorres.pila.approval.core.FamiliaTipoCotizante;
import net.jaimetorres.pila.approval.core.converters.ActivosConverter;
import net.jaimetorres.pila.approval.core.misc.SubsistemaPila;
import net.jaimetorres.pila.approval.core.processors.admins.AdminApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.activos.GenericActivosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.output.activos.minedu.ActivosMineduEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.minedu.ActivosMineduOutputFile;
import net.jaimetorres.pila.approval.pojos.output.activos.minedu.ActivosMineduTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.activos.minedu.ActivosMineduTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.activos.minedu.ActivosMineduTotalesRenglon39Out;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.pila.approval.utils.NumberUtils;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;

public class ApprovalActivosMineduFileProcessor
extends AdminApprovalFileProcessor<ActivosMineduOutputFile>{

	public ApprovalActivosMineduFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}

	@Override
	protected String getStreamName() {
		return "ActivosMineduOutputFile";
	}

	@Override
	protected ActivosMineduOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		//by-pass
		if(reader == null){return null;}
		
		ActivosMineduOutputFile activosMineduOutputFile = new ActivosMineduOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				activosMineduOutputFile.setEncabezado((ActivosMineduEncabezadoOut)record);
        	}else if("totalesRenglon31".equals(recordName) ){
        		activosMineduOutputFile.setRenglon31((ActivosMineduTotalesRenglon31Out)record);
        	}else if("totalesRenglon36".equals(recordName) ){
        		activosMineduOutputFile.setRenglon36((ActivosMineduTotalesRenglon36Out)record);
        	}else if("totalesRenglon39".equals(recordName) ){
        		activosMineduOutputFile.setRenglon39((ActivosMineduTotalesRenglon39Out)record);
        	}
		}
		
		return activosMineduOutputFile;
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
			ActivosMineduOutputFile fileParsed) {
		
		ActivosConverter activosConverter = new ActivosConverter();
		
		GenericActivosAdminOutputBean genericAdminOutputBean = new GenericActivosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1 lineaRegistroTipo1 = activosConverter
				.convertMineduEncabezado2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		//REGISTROS TOTALES
		LineaRegistroTipo31 lineaRegistroTipo31 = activosConverter.
				convertSimpleRenglon31ToLineaRegistroTipo31(SubsistemaPila.PARAFISCALES_MINEDU,fileParsed.getRenglon31());
		genericAdminOutputBean.setLineaRegistroTipo31(lineaRegistroTipo31);
		
		LineaRegistroTipo36 lineaRegistroTipo36 = activosConverter.
				convertSimpleRenglon36ToLineaRegistroTipo36(SubsistemaPila.PARAFISCALES_MINEDU,fileParsed.getRenglon36());
		genericAdminOutputBean.setLineaRegistroTipo36(lineaRegistroTipo36);
		
		LineaRegistroTipo39 lineaRegistroTipo39 = activosConverter.
				convertSimpleRenglon39ToLineaRegistroTipo39(SubsistemaPila.PARAFISCALES_MINEDU,fileParsed.getRenglon39());
		genericAdminOutputBean.setLineaRegistroTipo39(lineaRegistroTipo39);
		
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
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(ActivosMineduOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericActivosAdminOutputBean) genericOutputBean);
	}
}
