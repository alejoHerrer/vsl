package net.jaimetorres.pila.approval.pojos;

import com.dwr.validadorHTRANS.MensajesValidacion;
import com.jtccia.tch.utiles.TiposMensajeValidador;

import net.jaimetorres.validaciones.general.CodigoError;

public class ApprovalValidationError 
extends MensajesValidacion{
	
	private CodigoError codigoError;
	
	public ApprovalValidationError(){
		super.setTipoMensaje(TiposMensajeValidador.ERROR);
	}
	
	public ApprovalValidationError(String descripcion, int posicionInicial, int posicionFinal, String valorEsperado,
			String valorEncontrado, int numeroLinea, String nombreArchivo){
		
		super(numeroLinea, posicionInicial, posicionFinal, 
				valorEncontrado, valorEsperado,
				descripcion, nombreArchivo);
	}

	public CodigoError getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(CodigoError codigoError) {
		this.codigoError = codigoError;
	}


}
