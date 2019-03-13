package net.jaimetorres.pila.approval.core;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jtccia.tch.utiles.Utilidades;

import net.jaimetorres.pila.approval.core.exceptions.AdmUnidentifiedException;
import net.jaimetorres.pila.approval.core.exceptions.UnidentifiedFileNameException;
import net.jaimetorres.pila.approval.core.misc.ErrorsWriter;
import net.jaimetorres.pila.approval.core.misc.TipoArchivoSalida;
import net.jaimetorres.pila.approval.core.processors.EntireApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.ApprovalValidationError;

@Component
public class ApprovalFileValidationBean {

	@Autowired
	private DataSource interssiDs;
	private Connection interssiConnnection;
	
	@Autowired
	private ApprovalOutputFileDetectorBean approvalOutputFileDetectorBean;

	@PostConstruct
	public void init() throws SQLException{
		interssiConnnection = interssiDs.getConnection();
	}
	
	@PreDestroy
	public void destroy() throws SQLException{
	}
	
	public List<ApprovalValidationError> validateOutputFile(File file,
			Logger logValidador, TreeSet<String> archConError){
		return this.validateOutputFile(file.toPath(),logValidador,archConError);
	}
	
	public List<ApprovalValidationError> validateOutputFile(Path filePath,
			Logger logValidador, TreeSet<String> archConError){
		
		File file = filePath.toFile();
		String fileName = filePath.getFileName().toString();
		
		TipoArchivoSalida tipoArchivoSalida = null;
		try{
			tipoArchivoSalida = approvalOutputFileDetectorBean.identifyTipoArchivoSalidaByFilename(fileName);
		}catch(UnidentifiedFileNameException e){
			
			ApprovalValidationError approvalValidationError = new ApprovalValidationError();
			approvalValidationError.setDescripcion("El nombre del archivo no cumple con ningún patrón especificado en la norma para archivos de: ADMINISTRADORAS, ADRES o MINPS");
			approvalValidationError.setNombreArchivo(e.getFileName());
			List<ApprovalValidationError> errorsList = Arrays.asList(approvalValidationError);
			ErrorsWriter.write(logValidador, archConError, errorsList);
			return errorsList; 
		}catch(AdmUnidentifiedException e){
			
			ApprovalValidationError approvalValidationError = new ApprovalValidationError();
			approvalValidationError.setDescripcion("No se encuentra administradora");
			approvalValidationError.setNombreArchivo(e.getFileName());
			List<ApprovalValidationError> errorsList = Arrays.asList(approvalValidationError);
			ErrorsWriter.write(logValidador, archConError, errorsList);
			return errorsList; 
		}
		
		//By-pass
		if(tipoArchivoSalida != null){
			return this.validateSpecificFile(tipoArchivoSalida, file, logValidador, archConError);
		}else{
			return Collections.emptyList();
		}
		
	}
	
	private List<ApprovalValidationError> validateSpecificFile(TipoArchivoSalida tipoArchivoSalida, File file, 
			Logger logValidador, TreeSet<String> archConError){
		
		String pathFile = file.getAbsolutePath();
		
		//Parametros para el Processor
		ApprovalFileProcessorParameters approvalFileProcessorParameters = 
				new ApprovalFileProcessorParameters();
		approvalFileProcessorParameters.setPathFile(pathFile);
		approvalFileProcessorParameters.setLogValidador(logValidador);
		approvalFileProcessorParameters.setArchConError(archConError);
		
		EntireApprovalFileProcessor<?> approvalFileProcessor = 
				approvalOutputFileDetectorBean.retrieveApprovalFileProcessor(
						tipoArchivoSalida, approvalFileProcessorParameters);
		approvalFileProcessor.setInterssiConnection(interssiConnnection);
		
		if(approvalFileProcessor!=null){
			approvalFileProcessor.processFile(file);
		}
		
		return approvalFileProcessor.getErrorsList();
	}
	
	/**
	 * Administradoras File
	 * El nombre del archivo es similar para activos y pensionados
	 * 
	 *	Descripción de los campos 
	 *	[0]	= Fecha de pago
	 *	[1] = Modalidad de Planilla Integrada de Liquidación de Aportes
	 *	[2] = Número de formulario único o planilla pagada
	 *	[3] = Tipo de documento
	 *	[4] = Número de identificación del aportante sin digito de verificación
	 *	[5] = Código de la Entidad Administradora
	 *	[6] = Código del operador de información a través del cual pagó el aportante
	 *	[7] = Tipo de archivo ACTIVOS: {A,I||AR,IR} - PENSIONADOS: {AP,IP||APR,IPR}
	 *	[8] = Periodo de pago al cual pertenece la planilla.
	 *
	 * 	Termina en .TXT
	 */
	private String[] doSplit(String fileName){
		return fileName.split(".TXT")[0].split("_");
	} 
}
