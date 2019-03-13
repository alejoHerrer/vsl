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
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionOutputFile;
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionTotalesRenglon39Out;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;

public class ApprovalPensionadosPensionFileProcessor 
extends AdminApprovalFileProcessor<PensionadosPensionOutputFile>{

	public ApprovalPensionadosPensionFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}
	
	@Override
	protected String getStreamName() {
		return "PensionadosPensionOutputFile";
	}
	
	@Override
	protected PensionadosPensionOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		
		//by-pass
		if(reader == null){return null;}
		
		PensionadosPensionOutputFile pensionadosPensionOutputFile = new PensionadosPensionOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				pensionadosPensionOutputFile.setEncabezado((PensionadosPensionEncabezadoOut)record);
        	}else if("liquidacionDetallada".equals(recordName) ){
        		pensionadosPensionOutputFile.getLiquidacionDetalladaList().add((PensionadosPensionLiquidacionDetalladaOut)record);
        	}else if("totalesRenglon31".equals(recordName) ){
        		pensionadosPensionOutputFile.setRenglon31((PensionadosPensionTotalesRenglon31Out)record);
        	}else if("totalesRenglon36".equals(recordName) ){
        		pensionadosPensionOutputFile.setRenglon36((PensionadosPensionTotalesRenglon36Out)record);
        	}else if("totalesRenglon39".equals(recordName) ){
        		pensionadosPensionOutputFile.setRenglon39((PensionadosPensionTotalesRenglon39Out)record);
        	}
		}
		pensionadosPensionOutputFile.getEncabezado().setNroPlanilla(file.getName().split("_")[2]);
		return pensionadosPensionOutputFile;
	}
	
	@Override
	protected void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean,
			String codigoAdministradora) {
		((GenericPensionadosAdminOutputBean)genericAdminOutputBean).getLineaRegistroTipo2List().forEach(rt2->{
			rt2.setAfp(codigoAdministradora); });
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
			PensionadosPensionOutputFile fileParsed) {
		
		PensionadosConverter pensionadosConverter = new PensionadosConverter();
		
		GenericPensionadosAdminOutputBean genericAdminOutputBean = new GenericPensionadosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = pensionadosConverter
				.convertPensionEncabezado2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		//REGISTRO TIPO 2 (LIQUIDACION DETALLADA)
		List<LineaRegistroTipo2Pensionado> lineaRegistroTipo2List = pensionadosConverter
				.convertPensionLiquidacionDetalladaList2LineaRegistroTipo2List(
						fileParsed.getLiquidacionDetalladaList());
		genericAdminOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		
		//REGISTROS TOTALES
		LineaRegistroTipo31 lineaRegistroTipo31 = pensionadosConverter.
				convertPensionRenglon31ToLineaRegistroTipo31(fileParsed.getRenglon31());
		genericAdminOutputBean.setLineaRegistroTipo31(lineaRegistroTipo31);
		
		LineaRegistroTipo36 lineaRegistroTipo36 = pensionadosConverter.
				convertPensionRenglon36ToLineaRegistroTipo36(fileParsed.getRenglon36());
		genericAdminOutputBean.setLineaRegistroTipo36(lineaRegistroTipo36);
		
		LineaRegistroTipo39 lineaRegistroTipo39 = pensionadosConverter.
				convertPensionRenglon39ToLineaRegistroTipo39(fileParsed.getRenglon39());
		genericAdminOutputBean.setLineaRegistroTipo39(lineaRegistroTipo39);
		
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
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(PensionadosPensionOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {

		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericPensionadosAdminOutputBean) genericOutputBean);
		//N/A
	}
}
