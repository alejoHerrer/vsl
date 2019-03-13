package net.jaimetorres.pila.approval.pojos.output.activos.commons;

import java.io.File;

public abstract class CommonOutputFile {

	private File file;
	
	public CommonOutputFile(File file){
		this.file = file;
	}
	public File getFile() {
		return file;
	}
}
