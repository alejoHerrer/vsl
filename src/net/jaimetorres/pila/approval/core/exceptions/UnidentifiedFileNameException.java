package net.jaimetorres.pila.approval.core.exceptions;

public class UnidentifiedFileNameException 
extends RuntimeException{

	private static final long serialVersionUID = -6625145504324828992L;
	private String fileName;
	
	public UnidentifiedFileNameException(String fileName){
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
}
