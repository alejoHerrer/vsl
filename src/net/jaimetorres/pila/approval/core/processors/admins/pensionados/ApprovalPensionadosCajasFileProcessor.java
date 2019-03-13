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
import net.jaimetorres.pila.approval.pojos.output.pensionados.cajas.PensionadosCajasEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.cajas.PensionadosCajasLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.cajas.PensionadosCajasOutputFile;
import net.jaimetorres.pila.approval.pojos.output.pensionados.cajas.PensionadosCajasTotalesOut;
import net.jaimetorres.validaciones.estructura.LineaTotales;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;

public class ApprovalPensionadosCajasFileProcessor 
extends AdminApprovalFileProcessor<PensionadosCajasOutputFile>{

	public ApprovalPensionadosCajasFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}
	
	@Override
	protected String getStreamName() {
		return "PensionadosCajasOutputFile";
	}
	
	@Override
	protected PensionadosCajasOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		
		//by-pass
		if(reader == null){return null;}
		
		PensionadosCajasOutputFile pensionadosCajasOutputFile = new PensionadosCajasOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				pensionadosCajasOutputFile.setEncabezado((PensionadosCajasEncabezadoOut)record);
        	}else if("liquidacionDetallada".equals(recordName) ){
        		pensionadosCajasOutputFile.getLiquidacionDetalladaList().add((PensionadosCajasLiquidacionDetalladaOut)record);
        	}else if("totales".equals(recordName) ){
        		pensionadosCajasOutputFile.setTotales((PensionadosCajasTotalesOut)record);
        	}
		}
		pensionadosCajasOutputFile.getEncabezado().setNroPlanilla(file.getName().split("_")[2]);
		return pensionadosCajasOutputFile;
	}
	
	@Override
	protected void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean,
			String codigoAdministradora) {
		((GenericPensionadosAdminOutputBean)genericAdminOutputBean).getLineaRegistroTipo2List().forEach(rt2->{
			rt2.setCcf(codigoAdministradora); });
	}
	
	@Override
	protected void establishPeriodo(GenericAdminOutputBean _genericAdminOutputBean, YearMonth periodoYearMonth) {
		
		GenericPensionadosAdminOutputBean genericAdminOutputBean = (GenericPensionadosAdminOutputBean)_genericAdminOutputBean;
		
		//PENSION
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoPension(periodoYearMonth);
		
		//SALUD
		YearMonth periodoSaludYearMonth = periodoYearMonth.plusMonths(1);;
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoSalud(periodoSaludYearMonth);
	}
	
	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(
			PensionadosCajasOutputFile fileParsed) {
		
		PensionadosConverter pensionadosConverter = new PensionadosConverter();
		
		GenericPensionadosAdminOutputBean genericAdminOutputBean = new GenericPensionadosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = pensionadosConverter
				.convertCajasEncabezado2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		//REGISTRO TIPO 2 (LIQUIDACION DETALLADA)
		List<LineaRegistroTipo2Pensionado> lineaRegistroTipo2List = pensionadosConverter
				.convertCajasLiquidacionDetalladaList2LineaRegistroTipo2List(
						fileParsed.getLiquidacionDetalladaList());
		genericAdminOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		//REGISTROS TOTALES
		LineaTotales lineaTotales = pensionadosConverter.
				convertCajasRenglonTotalesToLineaTotales(fileParsed.getTotales());
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
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(PensionadosCajasOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericPensionadosAdminOutputBean) genericOutputBean);
		//N/A
	}
}
