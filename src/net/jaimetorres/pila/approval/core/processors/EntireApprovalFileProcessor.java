package net.jaimetorres.pila.approval.core.processors;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import com.jtccia.tch.utiles.Utilidades;

import net.jaimetorres.pila.approval.core.handlers.VASCustomBeanReaderErrorHandlerSupport;
import net.jaimetorres.pila.approval.core.misc.ErrorsWriter;
import net.jaimetorres.pila.approval.core.singletons.ApprovalFileParser;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.ApprovalValidationError;
import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.activos.GenericActivosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.pensionados.GenericPensionadosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsOutputFile;
import net.jaimetorres.validaciones.entidad.HomologacionEstructuraArchivosSalida;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.general.CodigoError;
import net.jaimetorres.validaciones.to.ErrorTO;

public abstract class EntireApprovalFileProcessor<T extends CommonOutputFile> 
extends EnhancedApprovalFileProcessor{
	
//XXX HACER USO CORRECTO DE LOS LOGGER	
//	private static final Logger logger = LoggerFactory.getLogger(EntireApprovalFileProcessor.class);
	
	private ApprovalFileParser approvalFileParser = ApprovalFileParser.getInstance();
	
	private T fileParsed;
	protected List<ApprovalValidationError> errorsList = new ArrayList<>();
	
	private ApprovalFileProcessorParameters approvalFileProcessorParameters;
	
	public EntireApprovalFileProcessor(
			ApprovalFileProcessorParameters approvalFileProcessorParameters){
		this.approvalFileProcessorParameters=approvalFileProcessorParameters;
	}
	
	public EntireApprovalFileProcessor() {}
	
	public void processFile(File file) {
		this.errorsList.clear();
		
		this.parse(file);
		this.postParse(fileParsed);
		this.validate();
		//this.writeErrors();
	}
	
	protected void postParse(T fileParsed) {
		//method to be overloaded when necessary
	}

	public void parse(File file) {
		@SuppressWarnings("unchecked")
		StreamFactory streamFactory = approvalFileParser.getStreamFactory(
				(Class<? extends EntireApprovalFileProcessor<?>>) super.getClass());
		
		String streamName = this.getStreamName();
		BeanReader reader = streamFactory.createReader(streamName, file);
		VASCustomBeanReaderErrorHandlerSupport errorsHandler = new VASCustomBeanReaderErrorHandlerSupport(file, errorsList);
		reader.setErrorHandler(errorsHandler);
		
		//clear
		fileParsed = null;
		try{
			fileParsed = this.parseSpecificOutputFile(reader, file);
			
			//FILE PARSING ERRORs
			if(errorsHandler.isFileParsingError()){
				fileParsed = null;
				ApprovalValidationError approvalValidationError = new ApprovalValidationError();
				approvalValidationError.setDescripcion("El archivo tiene error de estructura");
				approvalValidationError.setNombreArchivo(file.getName());
				List<ApprovalValidationError> errorsList = Arrays.asList(approvalValidationError);
				ErrorsWriter.write(approvalFileProcessorParameters.getLogValidador(), approvalFileProcessorParameters.getArchConError(), errorsList);
			}
			
		}catch(Exception e){															
			e.printStackTrace();
			fileParsed = null;
		}
		reader.close();
	}
	protected abstract String getStreamName();
	protected abstract T parseSpecificOutputFile(BeanReader reader, File file);

	public void validate() {
		
		//by-pass
		if(fileParsed == null){return;}
		
		GenericOutputBean genericOutputBean = this.retrieveGenericOutputBean(fileParsed);
		this.validateGenericOutputBean(genericOutputBean);
	}
	
	private GenericOutputBean retrieveGenericOutputBean(T fileParsed){
		
		GenericOutputBean genericOutputBean = this.convertSpecificOutputStructureToGenericOutputBean(fileParsed);
		this.postProcessConvertSpecificOutputStructureToGenericOutputBean(fileParsed, genericOutputBean);
		return genericOutputBean;
	}

	//XXX IDENTIFICAR LA ESTRATEGIA PARA EL SETEO DE ESTOS VALORES
	protected abstract GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(T fileParsed);
	protected abstract void validateSpecificOutputBean(GenericOutputBean genericOutputBean);
	protected abstract void postProcessConvertSpecificOutputStructureToGenericOutputBean(T fileParsed, GenericOutputBean genericOutputBean);
	
	
	public void writeErrors() {
		
		ErrorsWriter.write(
				approvalFileProcessorParameters.getLogValidador(),
				approvalFileProcessorParameters.getArchConError(),
				errorsList);
	}
	
	protected void addValidationsErrorsMessages(List<ErrorTO> errorListLocal) {
		
		if(errorListLocal == null || errorListLocal.isEmpty()){return;}
		
		errorListLocal.forEach(e->{
			
			CodigoError codigoError = e.getError();
			String errorDescripcion = codigoError.getDescripcion();
			if(errorDescripcion.contains(":@{valor}") ){
				errorDescripcion = errorDescripcion.replace(":@{valor}", e.getDetalleError());
			
			}else if( errorDescripcion.contains("@{valor}") ){
				errorDescripcion = errorDescripcion.replace("@{valor}", e.getDetalleError());
			}
			
			int posicionInicial = 0, posicionFinal = 0;
			HomologacionEstructuraArchivosSalida mologacionEstructuraSalida = e.getMologacionEstructuraSalida();
			if(mologacionEstructuraSalida != null){
				posicionInicial = mologacionEstructuraSalida.getPosInicial(); 
				posicionFinal = mologacionEstructuraSalida.getPosFinal();
			}
			
			ApprovalValidationError approvalValidationError = new ApprovalValidationError(
					//XXX TODO QUITAR (IC)
					"(IC:"+codigoError.getCodigo()+") " + errorDescripcion, 
					posicionInicial, posicionFinal,
					StringUtils.join(e.getLstValoresEsperados(), ","),
					e.getValorEncontrado(), 
					Long.valueOf(e.getConsecutivoLinea()).intValue(), 
					approvalFileProcessorParameters.getPathFile());
			
			approvalValidationError.setCodigoError(codigoError);
			
			this.errorsList.add(approvalValidationError);
		});
	}
	
	protected void validateGenericOutputBean(GenericOutputBean genericOutputBean){
		//by-pass
		if(genericOutputBean == null){return;}
		this.validateSpecificOutputBean(genericOutputBean);
	}

	public ApprovalFileProcessorParameters getApprovalFileProcessorParameters() {
		return approvalFileProcessorParameters;
	}

	public List<ApprovalValidationError> getErrorsList() {
		return errorsList;
	}

	public void setErrorsList(List<ApprovalValidationError> errorsList) {
		this.errorsList = errorsList;
	}
	
	
}