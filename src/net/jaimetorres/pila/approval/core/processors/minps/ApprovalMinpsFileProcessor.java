package net.jaimetorres.pila.approval.core.processors.minps;

import java.io.File;
import java.util.List;

import org.beanio.BeanReader;

import net.jaimetorres.pila.approval.core.processors.EntireApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsLey1819Out;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsOutputFile;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo10EsapOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo11MineduOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo12PlanillaOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo3PensionOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo4FspOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo5SaludOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo6RiesgosOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo7CajasOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo8SenaOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo9IcbfOut;
import net.jaimetorres.validaciones.to.ErrorTO;

public class ApprovalMinpsFileProcessor 
extends EntireApprovalFileProcessor<MinpsOutputFile>{

	private SpecificContextMinpsFileProcessor specificContextMinpsFileProcessor; 

	public ApprovalMinpsFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}
	
	@Override
	protected String getStreamName() {
		return "MinpsOutputFile";
	}
	
	@Override
	protected MinpsOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		
		//by-pass
		if(reader == null){return null;}
		
		MinpsOutputFile minpsOutputFile = new MinpsOutputFile(file);
		
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				minpsOutputFile.setEncabezado((MinpsEncabezadoOut)record);
        	}else if("liquidacionDetallada".equals(recordName) ){
        		minpsOutputFile.getLiquidacionDetalladaList().add((MinpsLiquidacionDetalladaOut)record);
        	}else if("totalesPension".equals(recordName) ){
        		minpsOutputFile.getRenglon3().add((MinpsTotalesRegistroTipo3PensionOut)record);
        	}else if("totalesFsp".equals(recordName) ){
        		minpsOutputFile.setRenglon4((MinpsTotalesRegistroTipo4FspOut)record);
        	}else if("totalesSalud".equals(recordName) ){
        		minpsOutputFile.getRenglon5().add((MinpsTotalesRegistroTipo5SaludOut)record);
        	}else if("totalesRiesgos".equals(recordName) ){
        		minpsOutputFile.getRenglon6().add((MinpsTotalesRegistroTipo6RiesgosOut)record);
        	}else if("totalesCajas".equals(recordName) ){
        		minpsOutputFile.getRenglon7().add((MinpsTotalesRegistroTipo7CajasOut)record);
        	}else if("totalesSena".equals(recordName) ){
        		minpsOutputFile.setRenglon8((MinpsTotalesRegistroTipo8SenaOut)record);
        	}else if("totalesIcbf".equals(recordName) ){
        		minpsOutputFile.setRenglon9((MinpsTotalesRegistroTipo9IcbfOut)record);
        	}else if("totalesEsap".equals(recordName) ){
        		minpsOutputFile.setRenglon10((MinpsTotalesRegistroTipo10EsapOut)record);
        	}else if("totalesMinedu".equals(recordName) ){
        		minpsOutputFile.setRenglon11((MinpsTotalesRegistroTipo11MineduOut)record);
        	}else if("totalesPlanilla".equals(recordName) ){
        		minpsOutputFile.setRenglon12((MinpsTotalesRegistroTipo12PlanillaOut)record);
        	}else if("ley1819".equals(recordName) ){
        		minpsOutputFile.setRenglon13((MinpsLey1819Out)record);
        	}
		}
		
		return minpsOutputFile;
	}
	
	@Override
	protected void postParse(MinpsOutputFile fileParsed){
		//by-pass
		if(fileParsed == null){return;}
		
		String identificador = fileParsed.getEncabezado().getIdentificador();
		if(identificador.equals("1")){
			specificContextMinpsFileProcessor = new ActivosContextMinpsFileProcessor();
		}else if(identificador.equals("2")){
			specificContextMinpsFileProcessor = new PensionadosContextMinpsFileProcessor();
		}else{
			throw new RuntimeException("Identificador no reconocido: " + identificador);
		}
		
		//Needed by InterssiCommons
		specificContextMinpsFileProcessor.setInterssiConnection(super.getInterssiConnection());
	}
	
	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(
			MinpsOutputFile fileParsed) {
		return specificContextMinpsFileProcessor.convertSpecificOutputStructureToGenericOutputBean(fileParsed);
	}
	
	@Override
	protected void validateSpecificOutputBean(GenericOutputBean genericOutputBean) {
		
		List<ErrorTO> allErrorsDetected = specificContextMinpsFileProcessor.validateSpecificOutputBean(genericOutputBean);
		this.addValidationsErrorsMessages(allErrorsDetected);
	}
	
	@Override
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(MinpsOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		//N/A
	}

}
