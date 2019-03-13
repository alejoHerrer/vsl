package net.jaimetorres.pila.approval.core.handlers;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.beanio.BeanReaderErrorHandlerSupport;
import org.beanio.InvalidRecordException;
import org.beanio.RecordContext;
import org.beanio.UnexpectedRecordException;
import org.beanio.UnidentifiedRecordException;

import net.jaimetorres.pila.approval.pojos.ApprovalValidationError;

//VAS stand for ValidadorArchivosSalida
public class VASCustomBeanReaderErrorHandlerSupport 
extends BeanReaderErrorHandlerSupport{
	
	private boolean fileParsingError;
	private File file;
	private List<ApprovalValidationError> errorsList;
	
	public VASCustomBeanReaderErrorHandlerSupport(File file, List<ApprovalValidationError> errorsList) {
		
		this.file = file;
		this.errorsList = errorsList;
	}

	@Override
	public void unidentifiedRecord(UnidentifiedRecordException ex) throws Exception {
		
		RecordContext recordContext = ex.getRecordContext();
		int lineNumberWithError = recordContext.getLineNumber();
		
		ApprovalValidationError approvalValidationError = new ApprovalValidationError();
		approvalValidationError.setNumeroLinea(lineNumberWithError);
		approvalValidationError.setDescripcion("Tipo de Registro no identificado (No es posible validar la estructura interna del archivo)");
		approvalValidationError.setNombreArchivo(file.getAbsolutePath());
		errorsList.add(approvalValidationError);
		this.fileParsingError = true;
	}
	
	@Override
	public void unexpectedRecord(UnexpectedRecordException ex) throws Exception {
        
		RecordContext recordContext = ex.getRecordContext();
		int lineNumberWithError = recordContext.getLineNumber();
		
		ApprovalValidationError approvalValidationError = new ApprovalValidationError();
		approvalValidationError.setNumeroLinea(lineNumberWithError);
		approvalValidationError.setDescripcion("El Tipo de Registro no se encuentra en la posicion esperada");
		approvalValidationError.setNombreArchivo(file.getAbsolutePath());
		errorsList.add(approvalValidationError);
		
		this.fileParsingError = true;
    }
	
	@Override
	 public void invalidRecord(InvalidRecordException ex) throws Exception {
		
		RecordContext recordContext = ex.getRecordContext();
		int lineNumberWithError = recordContext.getLineNumber();
		
		if (recordContext.hasRecordErrors()) {
			for (String error : recordContext.getRecordErrors()) {
				ApprovalValidationError approvalValidationError = new ApprovalValidationError();
				approvalValidationError.setNumeroLinea(lineNumberWithError);
				approvalValidationError.setDescripcion(error);
				approvalValidationError.setNombreArchivo(file.getAbsolutePath());
				errorsList.add(approvalValidationError);
				this.fileParsingError = true;
	        }
		}
		
		
		if (recordContext.hasFieldErrors()) {
			
			Map<String, Collection<String>> fieldsWithErrors = recordContext.getFieldErrors();
			
			for (String field : fieldsWithErrors.keySet()) {
				Collection<String> fieldErrorsDesc = fieldsWithErrors.get(field);
				for(String errorDesc :fieldErrorsDesc){
					ApprovalValidationError approvalValidationError = new ApprovalValidationError();
					approvalValidationError.setNumeroLinea(lineNumberWithError);
					approvalValidationError.setDescripcion(errorDesc);
					approvalValidationError.setNombreArchivo(file.getAbsolutePath());
					errorsList.add(approvalValidationError);
					this.fileParsingError = true;
				}
			}
			
		}
	 }

	public boolean isFileParsingError() {
		return fileParsingError;
	}

}
