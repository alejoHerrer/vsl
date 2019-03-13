package net.jaimetorres.pila.approval.pojos.output.activos.commons;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public abstract class CommonActivosLiquidacionDetalladaOutputFile 
extends CommonOutputRecord{

	private String secuencia;
	private String tipoRegistro;
	private String tipoIdentificacion;
	private String nroIdentificacion;
	private String tipoCotizante;
	private String subtipoCotizante;
	private String extranjeroNoObligadoCotPension;
	private String colombianoResidenteExterior;
	private String codigoDepartamento;
	private String codigoMunicipio;
	private String primerApellido;
	private String segundoApellido;
	private String primerNombre;
	private String segundoNombre;
	
	private String salarioBasico;
	private String salarioIntegral;
	
	//Novedades
	private String novedadIngreso;
	private String novedadRetiro;
	private String novedadVariacionSalarioPermanente;
	private String novedadVariacionSalarioTransitoria;
	private String novedadSuspension;
	private String novedadIncapacidadEnfermedadGeneral;
	private String novedadLicenciaMaternidad;
	private String novedadVacaciones;
	private String novedadCorreccion;
	
	private String fechaIngreso;
	private String fechaRetiro;
	private String fechaInicioVSP;
	private String fechaInicioSLN;
	private String fechaFinSLN;
	private String fechaInicioIGE;
	private String fechaFinIGE;
	private String fechaInicioLMA;
	private String fechaFinLMA;
	private String fechaInicioVAC;
	private String fechaFinVAC;
	
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getNroIdentificacion() {
		return nroIdentificacion;
	}
	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}
	public String getTipoCotizante() {
		return tipoCotizante;
	}
	public void setTipoCotizante(String tipoCotizante) {
		this.tipoCotizante = tipoCotizante;
	}
	public String getSubtipoCotizante() {
		return subtipoCotizante;
	}
	public void setSubtipoCotizante(String subtipoCotizante) {
		this.subtipoCotizante = subtipoCotizante;
	}
	public String getExtranjeroNoObligadoCotPension() {
		return extranjeroNoObligadoCotPension;
	}
	public void setExtranjeroNoObligadoCotPension(String extranjeroNoObligadoCotPension) {
		this.extranjeroNoObligadoCotPension = extranjeroNoObligadoCotPension;
	}
	public String getColombianoResidenteExterior() {
		return colombianoResidenteExterior;
	}
	public void setColombianoResidenteExterior(String colombianoResidenteExterior) {
		this.colombianoResidenteExterior = colombianoResidenteExterior;
	}
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}
	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getSalarioBasico() {
		return salarioBasico;
	}
	public void setSalarioBasico(String salarioBasico) {
		this.salarioBasico = salarioBasico;
	}
	public String getSalarioIntegral() {
		return salarioIntegral;
	}
	public void setSalarioIntegral(String salarioIntegral) {
		this.salarioIntegral = salarioIntegral;
	}
	public String getNovedadIngreso() {
		return novedadIngreso;
	}
	public void setNovedadIngreso(String novedadIngreso) {
		this.novedadIngreso = novedadIngreso;
	}
	public String getNovedadRetiro() {
		return novedadRetiro;
	}
	public void setNovedadRetiro(String novedadRetiro) {
		this.novedadRetiro = novedadRetiro;
	}
	public String getNovedadVariacionSalarioPermanente() {
		return novedadVariacionSalarioPermanente;
	}
	public void setNovedadVariacionSalarioPermanente(String novedadVariacionSalarioPermanente) {
		this.novedadVariacionSalarioPermanente = novedadVariacionSalarioPermanente;
	}
	public String getNovedadVariacionSalarioTransitoria() {
		return novedadVariacionSalarioTransitoria;
	}
	public void setNovedadVariacionSalarioTransitoria(String novedadVariacionSalarioTransitoria) {
		this.novedadVariacionSalarioTransitoria = novedadVariacionSalarioTransitoria;
	}
	public String getNovedadSuspension() {
		return novedadSuspension;
	}
	public void setNovedadSuspension(String novedadSuspension) {
		this.novedadSuspension = novedadSuspension;
	}
	public String getNovedadIncapacidadEnfermedadGeneral() {
		return novedadIncapacidadEnfermedadGeneral;
	}
	public void setNovedadIncapacidadEnfermedadGeneral(String novedadIncapacidadEnfermedadGeneral) {
		this.novedadIncapacidadEnfermedadGeneral = novedadIncapacidadEnfermedadGeneral;
	}
	public String getNovedadLicenciaMaternidad() {
		return novedadLicenciaMaternidad;
	}
	public void setNovedadLicenciaMaternidad(String novedadLicenciaMaternidad) {
		this.novedadLicenciaMaternidad = novedadLicenciaMaternidad;
	}
	public String getNovedadVacaciones() {
		return novedadVacaciones;
	}
	public void setNovedadVacaciones(String novedadVacaciones) {
		this.novedadVacaciones = novedadVacaciones;
	}
	public String getNovedadCorreccion() {
		return novedadCorreccion;
	}
	public void setNovedadCorreccion(String novedadCorreccion) {
		this.novedadCorreccion = novedadCorreccion;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getFechaRetiro() {
		return fechaRetiro;
	}
	public void setFechaRetiro(String fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	public String getFechaInicioVSP() {
		return fechaInicioVSP;
	}
	public void setFechaInicioVSP(String fechaInicioVSP) {
		this.fechaInicioVSP = fechaInicioVSP;
	}
	public String getFechaInicioSLN() {
		return fechaInicioSLN;
	}
	public void setFechaInicioSLN(String fechaInicioSLN) {
		this.fechaInicioSLN = fechaInicioSLN;
	}
	public String getFechaFinSLN() {
		return fechaFinSLN;
	}
	public void setFechaFinSLN(String fechaFinSLN) {
		this.fechaFinSLN = fechaFinSLN;
	}
	public String getFechaInicioIGE() {
		return fechaInicioIGE;
	}
	public void setFechaInicioIGE(String fechaInicioIGE) {
		this.fechaInicioIGE = fechaInicioIGE;
	}
	public String getFechaFinIGE() {
		return fechaFinIGE;
	}
	public void setFechaFinIGE(String fechaFinIGE) {
		this.fechaFinIGE = fechaFinIGE;
	}
	public String getFechaInicioLMA() {
		return fechaInicioLMA;
	}
	public void setFechaInicioLMA(String fechaInicioLMA) {
		this.fechaInicioLMA = fechaInicioLMA;
	}
	public String getFechaFinLMA() {
		return fechaFinLMA;
	}
	public void setFechaFinLMA(String fechaFinLMA) {
		this.fechaFinLMA = fechaFinLMA;
	}
	public String getFechaInicioVAC() {
		return fechaInicioVAC;
	}
	public void setFechaInicioVAC(String fechaInicioVAC) {
		this.fechaInicioVAC = fechaInicioVAC;
	}
	public String getFechaFinVAC() {
		return fechaFinVAC;
	}
	public void setFechaFinVAC(String fechaFinVAC) {
		this.fechaFinVAC = fechaFinVAC;
	}
	
}
