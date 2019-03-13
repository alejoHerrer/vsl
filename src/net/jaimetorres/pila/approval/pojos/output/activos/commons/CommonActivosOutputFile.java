package net.jaimetorres.pila.approval.pojos.output.activos.commons;

//XXX BORRAR ESTA CLASE
public abstract class CommonActivosOutputFile {

	protected String codigoAdministradora;
	protected String periodoAno;
	protected String periodoMes;
	
	protected CommonActivosOutputFile(String codigoAdministradora,String periodoAno, String periodoMes){
		this.codigoAdministradora = codigoAdministradora;
		this.periodoAno = periodoAno;
		this.periodoMes = periodoMes;
	}

	public String getCodigoAdministradora() {
		return codigoAdministradora;
	}
	public String getPeriodoAno() {
		return periodoAno;
	}
	public String getPeriodoMes() {
		return periodoMes;
	}
}
