package net.jaimetorres.pila.approval.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.jaimetorres.validaciones.estructura.Linea;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;

public class CotizanteGrupo {
	
	private List<File> archivos;
	private List<Linea> archivoIdentificado;
	
	public CotizanteGrupo() {
		// TODO Auto-generated constructor stub
		archivos = new ArrayList<>();
		archivoIdentificado = new ArrayList<>();
	}

	public List<File> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<File> archivos) {
		this.archivos = archivos;
	}

	public List<Linea> getArchivoIdentificado() {
		return archivoIdentificado;
	}

	public void setArchivoIdentificado(List<Linea> archivoIdentificado) {
		this.archivoIdentificado = archivoIdentificado;
	}

}
