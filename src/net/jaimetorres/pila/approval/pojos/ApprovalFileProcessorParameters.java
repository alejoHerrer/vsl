package net.jaimetorres.pila.approval.pojos;

import java.util.Set;

import org.apache.log4j.Logger;

public class ApprovalFileProcessorParameters {

	private String pathFile;
	private Logger logValidador; 
	private Set<String> archConError;
	
	public String getPathFile() {
		return pathFile;
	}
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	public Logger getLogValidador() {
		return logValidador;
	}
	public void setLogValidador(Logger logValidador) {
		this.logValidador = logValidador;
	}
	public Set<String> getArchConError() {
		return archConError;
	}
	public void setArchConError(Set<String> archConError) {
		this.archConError = archConError;
	}
	
}
