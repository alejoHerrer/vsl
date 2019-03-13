package net.jaimetorres.pila.approval.core.misc;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.jtccia.tch.utiles.TiposMensajeValidador;

import net.jaimetorres.pila.approval.pojos.ApprovalValidationError;

public class ErrorsWriter {

	public static void write(Logger logger, Set<String> archivosConError, List<ApprovalValidationError> errorsList){
	
		//by-pass
		if(errorsList.isEmpty()){return;}
		
		//Ordenamiento por nro de linea
		Collections.sort(errorsList, 
				(o1,o2)-> Integer.compare(o1.getNumeroLinea(), o2.getNumeroLinea()));
		
		errorsList.forEach(error->{
			
			if (error.getTipoMensaje().equals(TiposMensajeValidador.ERROR)) {
				logger.error(error.toString());
				archivosConError.add(error.getNombreArchivo());
			} else if (error.getTipoMensaje().equals(TiposMensajeValidador.WARNING)) {
				logger.warn(error.toString());
			}
		});
	}
}
