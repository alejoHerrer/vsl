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
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludOutputFile;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludTotalesRenglon37Out;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludTotalesRenglon39Out;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;

public class ApprovalPensionadosSaludFileProcessor 
extends AdminApprovalFileProcessor<PensionadosSaludOutputFile>{

	public ApprovalPensionadosSaludFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}

	@Override
	protected String getStreamName() {
		return "PensionadosSaludOutputFile";
	}
	
	@Override
	protected PensionadosSaludOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		
		//by-pass
		if(reader == null){return null;}
		
		PensionadosSaludOutputFile pensionadosSaludOutputFile = new PensionadosSaludOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				pensionadosSaludOutputFile.setEncabezado((PensionadosSaludEncabezadoOut)record);
        	}else if("liquidacionDetallada".equals(recordName) ){
        		pensionadosSaludOutputFile.getLiquidacionDetalladaList().add((PensionadosSaludLiquidacionDetalladaOut)record);
        	}else if("totalesRenglon31".equals(recordName) ){
        		pensionadosSaludOutputFile.setRenglon31((PensionadosSaludTotalesRenglon31Out)record);
        	}else if("totalesRenglon36".equals(recordName) ){
        		pensionadosSaludOutputFile.setRenglon36((PensionadosSaludTotalesRenglon36Out)record);
        	}else if("totalesRenglon37".equals(recordName) ){
            	pensionadosSaludOutputFile.setRenglon37((PensionadosSaludTotalesRenglon37Out)record);
        	}else if("totalesRenglon39".equals(recordName) ){
        		pensionadosSaludOutputFile.setRenglon39((PensionadosSaludTotalesRenglon39Out)record);
        	}
		}
		pensionadosSaludOutputFile.getEncabezado().setNroPlanilla(file.getName().split("_")[2]);
		return pensionadosSaludOutputFile;
	}
	
	@Override
	protected void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean,
			String codigoAdministradora) {
		
		((GenericPensionadosAdminOutputBean)genericAdminOutputBean).getLineaRegistroTipo2List().forEach(rt2->{
			rt2.setEps(codigoAdministradora); });
	}
	
	@Override
	protected void establishPeriodo(GenericAdminOutputBean _genericAdminOutputBean, YearMonth periodoYearMonth) {
		
		GenericPensionadosAdminOutputBean genericAdminOutputBean = (GenericPensionadosAdminOutputBean)_genericAdminOutputBean;
		
		//SALUD
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoSalud(periodoYearMonth);
		
		//PENSION
		YearMonth periodoPensionYearMonth = periodoYearMonth.minusMonths(1);
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoPension(periodoPensionYearMonth);
	}
	
	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(
			PensionadosSaludOutputFile fileParsed) {
		
		PensionadosConverter pensionadosConverter = new PensionadosConverter();
		
		GenericPensionadosAdminOutputBean genericAdminOutputBean = new GenericPensionadosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = pensionadosConverter
				.convertSaludEncabezado2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		//REGISTRO TIPO 2 (LIQUIDACION DETALLADA)
		List<LineaRegistroTipo2Pensionado> lineaRegistroTipo2List = pensionadosConverter
				.convertSaludLiquidacionDetalladaList2LineaRegistroTipo2List(
						fileParsed.getLiquidacionDetalladaList());
		genericAdminOutputBean.setLineaRegistroTipo2List(lineaRegistroTipo2List);
		
		//REGISTROS TOTALES
		LineaRegistroTipo31 lineaRegistroTipo31 = pensionadosConverter.
				convertSaludRenglon31ToLineaRegistroTipo31(fileParsed.getRenglon31());
		genericAdminOutputBean.setLineaRegistroTipo31(lineaRegistroTipo31);
		
		LineaRegistroTipo36 lineaRegistroTipo36 = pensionadosConverter.
				convertSaludRenglon36ToLineaRegistroTipo36(fileParsed.getRenglon36());
		genericAdminOutputBean.setLineaRegistroTipo36(lineaRegistroTipo36);
		
		//XXX TODO FALTA EL RENGLON 37
		
		LineaRegistroTipo39 lineaRegistroTipo39 = pensionadosConverter.
				convertSaludRenglon39ToLineaRegistroTipo39(fileParsed.getRenglon39());
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
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(PensionadosSaludOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericPensionadosAdminOutputBean) genericOutputBean);
		//N/A
	}
}
