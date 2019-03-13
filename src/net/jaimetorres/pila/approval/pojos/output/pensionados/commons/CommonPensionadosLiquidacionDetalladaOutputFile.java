package net.jaimetorres.pila.approval.pojos.output.pensionados.commons;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public abstract class CommonPensionadosLiquidacionDetalladaOutputFile 
extends CommonOutputRecord{

	private String secuencia;
	private String tipoRegistro;
	private String tipoIdentificacion;
	private String nroIdentificacion;
	private String primerApellido;
	private String segundoApellido;
	private String primerNombre;
	private String segundoNombre;
	private String codigoDepartamento;
	private String codigoMunicipio;
	
	private String novedadIngreso;
	private String novedadRetiro;
	private String novedadVariacionSalarioPermanente;
	private String novedadSuspension;
	
	private String fechaIngreso;
	private String fechaRetiro;
	private String fechaInicioVSP;
	private String mesadaPensional;
	
	//XXX LOS SIGUIENTES CAMPOS NO SE ENCUENTRAN DEFINIDOS EN TODOS LOS TIPOS DE ARCHIVO DE SALIDA DE PENSIONADOS
	//XXX SE MAPEAN DE FORMA COMUN PARA MANTENER UNA ORGANIZACION DE CAMPOS
	private String primerApellidoCausante;
	private String segundoApellidoCausante;
	private String primerNombreCausante;
	private String segundoNombreCausante;
	private String tipoIdentificacionCausante;
	private String nroIdentificacionCausante;
	private String tipoPension;
	private String pensionCompartida;
	private String tipoPensionado;
	private String fechaInicioSUS;
	private String fechaFinSUS;
	private String valorIngresoBaseCotizacion;
	
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
	public String getNovedadSuspension() {
		return novedadSuspension;
	}
	public void setNovedadSuspension(String novedadSuspension) {
		this.novedadSuspension = novedadSuspension;
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
	public String getMesadaPensional() {
		return mesadaPensional;
	}
	public void setMesadaPensional(String mesadaPensional) {
		this.mesadaPensional = mesadaPensional;
	}
	public String getPrimerApellidoCausante() {
		return primerApellidoCausante;
	}
	public void setPrimerApellidoCausante(String primerApellidoCausante) {
		this.primerApellidoCausante = primerApellidoCausante;
	}
	public String getSegundoApellidoCausante() {
		return segundoApellidoCausante;
	}
	public void setSegundoApellidoCausante(String segundoApellidoCausante) {
		this.segundoApellidoCausante = segundoApellidoCausante;
	}
	public String getPrimerNombreCausante() {
		return primerNombreCausante;
	}
	public void setPrimerNombreCausante(String primerNombreCausante) {
		this.primerNombreCausante = primerNombreCausante;
	}
	public String getSegundoNombreCausante() {
		return segundoNombreCausante;
	}
	public void setSegundoNombreCausante(String segundoNombreCausante) {
		this.segundoNombreCausante = segundoNombreCausante;
	}
	public String getTipoIdentificacionCausante() {
		return tipoIdentificacionCausante;
	}
	public void setTipoIdentificacionCausante(String tipoIdentificacionCausante) {
		this.tipoIdentificacionCausante = tipoIdentificacionCausante;
	}
	public String getNroIdentificacionCausante() {
		return nroIdentificacionCausante;
	}
	public void setNroIdentificacionCausante(String nroIdentificacionCausante) {
		this.nroIdentificacionCausante = nroIdentificacionCausante;
	}
	public String getTipoPension() {
		return tipoPension;
	}
	public void setTipoPension(String tipoPension) {
		this.tipoPension = tipoPension;
	}
	public String getPensionCompartida() {
		return pensionCompartida;
	}
	public void setPensionCompartida(String pensionCompartida) {
		this.pensionCompartida = pensionCompartida;
	}
	public String getTipoPensionado() {
		return tipoPensionado;
	}
	public void setTipoPensionado(String tipoPensionado) {
		this.tipoPensionado = tipoPensionado;
	}
	public String getFechaInicioSUS() {
		return fechaInicioSUS;
	}
	public void setFechaInicioSUS(String fechaInicioSUS) {
		this.fechaInicioSUS = fechaInicioSUS;
	}
	public String getFechaFinSUS() {
		return fechaFinSUS;
	}
	public void setFechaFinSUS(String fechaFinSUS) {
		this.fechaFinSUS = fechaFinSUS;
	}
	public String getValorIngresoBaseCotizacion() {
		return valorIngresoBaseCotizacion;
	}
	public void setValorIngresoBaseCotizacion(String valorIngresoBaseCotizacion) {
		this.valorIngresoBaseCotizacion = valorIngresoBaseCotizacion;
	}
}
