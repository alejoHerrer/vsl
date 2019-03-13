package net.jaimetorres.pila.approval.core.processors.admins.pensionados;

import java.io.File;
import java.time.YearMonth;
import java.util.List;

import org.beanio.BeanReader;

import net.jaimetorres.pila.approval.core.converters.PensionadosConverter;
import net.jaimetorres.pila.approval.core.processors.admins.AdminApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.pensionados.GenericPensionadosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.pila.approval.pojos.output.pensionados.fsp.PensionadosFspEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.fsp.PensionadosFspLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.fsp.PensionadosFspOutputFile;
import net.jaimetorres.pila.approval.pojos.output.pensionados.fsp.PensionadosFspTotalesOut;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaTotales;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;

public class ApprovalPensionadosFspFileProcessor 
extends AdminApprovalFileProcessor<PensionadosFspOutputFile>{

	public ApprovalPensionadosFspFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}
	
	@Override
	protected String getStreamName() {
		return "PensionadosFspOutputFile";
	}
	
	@Override
	protected PensionadosFspOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		
		//by-pass
		if(reader == null){return null;}
		
		PensionadosFspOutputFile pensionadosFspOutputFile = new PensionadosFspOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				pensionadosFspOutputFile.setEncabezado((PensionadosFspEncabezadoOut)record);
        	}else if("liquidacionDetallada".equals(recordName) ){
        		pensionadosFspOutputFile.getLiquidacionDetalladaList().add((PensionadosFspLiquidacionDetalladaOut)record);
        	}else if("totales".equals(recordName) ){
        		pensionadosFspOutputFile.setTotales((PensionadosFspTotalesOut)record);
        	}
		}
		pensionadosFspOutputFile.getEncabezado().setNroPlanilla(file.getName().split("_")[2]);
		return pensionadosFspOutputFile;
	}
	
	@Override
	protected void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean,
			String codigoAdministradora) {
		//N/A
	}
	
	@Override
	protected void establishPeriodo(GenericAdminOutputBean _genericAdminOutputBean, YearMonth periodoYearMonth) {
		
		GenericPensionadosAdminOutputBean genericAdminOutputBean = (GenericPensionadosAdminOutputBean)_genericAdminOutputBean;
		
		//PENSION
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoPension(periodoYearMonth);
		
		//SALUD
		YearMonth periodoSaludYearMonth = periodoYearMonth.plusMonths(1);
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoSalud(periodoSaludYearMonth);
	}
	
	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(
			PensionadosFspOutputFile fileParsed) {
		
		PensionadosConverter pensionadosConverter = new PensionadosConverter();
		
		GenericPensionadosAdminOutputBean genericAdminOutputBean = new GenericPensionadosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = pensionadosConverter
				.convertFspEncabezado2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		//REGISTRO TIPO 2 (LIQUIDACION DETALLADA)
		List<LineaRegistroTipo2Pensionado> lineaRegistroTipo2List = pensionadosConverter
				.convertFspLiquidacionDetalladaList2LineaRegistroTipo2List(
						fileParsed.getLiquidacionDetalladaList());
		genericAdminOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		//REGISTROS TOTALES
		LineaTotales lineaTotales = pensionadosConverter.
				convertFspRenglonTotalesToLineaTotales(fileParsed.getTotales());
		genericAdminOutputBean.setLineaTotales(lineaTotales);
		
		return genericAdminOutputBean;
	}
	
	@Override
	protected void validateSpecificOutputBean(GenericOutputBean genericOutputBean) {
		//T268777
		try{
			super.validateAdminOutputBean((GenericAdminOutputBean)genericOutputBean);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@Override
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(PensionadosFspOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericPensionadosAdminOutputBean) genericOutputBean);
		//N/A
	}
}
