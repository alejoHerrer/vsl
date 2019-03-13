package net.jaimetorres.pila.approval.pojos.output.pensionados.commons;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public abstract class CommonPensionadosEncabezadoOutputFile 
extends CommonOutputRecord{

	private String tipoRegistro;
	private String nroIdentificacionAdm;
	private String dvAdm;
	private String razonSocialAportante;
	private String tipoIdentificacionAportante;
	private String nroIdentificacionAportante;
	private String dvAportante;
	private String periodoPago;
	private String fechaPago;
	private String formaPresentacion;
	private String codigoSucursal;
	private String nombreSucursal;
	private String nroTotalPensionados;
	private String diasMora;
	private String codigoOperador;
	
	//XXX LOS SIGUIENTES CAMPOS NO SE ENCUENTRAN DEFINIDOS EN TODOS LOS TIPOS DE ARCHIVO DE SALIDA DE PENSIONADOS
	//XXX SE MAPEAN DE FORMA COMUN PARA MANTENER UNA ORGANIZACION DE CAMPOS
	private String nroRegistro;
	private String codigoFormato;
	private String nroPlanilla;
	private String clasePagadorPensiones;
	private String direccionCorrespondencia;
	private String codigoMunicipio;
	private String codigoDepartamento;
	private String telefono;
	private String fax;
	private String correoElectronico;
	private String nroAfiliadosAdm;
	private String tipoPlanilla;
	private String nroRegistrosTipo2;
	private String fechaUltimaActualizacion;

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getNroIdentificacionAdm() {
		return nroIdentificacionAdm;
	}

	public void setNroIdentificacionAdm(String nroIdentificacionAdm) {
		this.nroIdentificacionAdm = nroIdentificacionAdm;
	}

	public String getDvAdm() {
		return dvAdm;
	}

	public void setDvAdm(String dvAdm) {
		this.dvAdm = dvAdm;
	}

	public String getRazonSocialAportante() {
		return razonSocialAportante;
	}

	public void setRazonSocialAportante(String razonSocialAportante) {
		this.razonSocialAportante = razonSocialAportante;
	}

	public String getTipoIdentificacionAportante() {
		return tipoIdentificacionAportante;
	}

	public void setTipoIdentificacionAportante(String tipoIdentificacionAportante) {
		this.tipoIdentificacionAportante = tipoIdentificacionAportante;
	}

	public String getNroIdentificacionAportante() {
		return nroIdentificacionAportante;
	}

	public void setNroIdentificacionAportante(String nroIdentificacionAportante) {
		this.nroIdentificacionAportante = nroIdentificacionAportante;
	}

	public String getDvAportante() {
		return dvAportante;
	}

	public void setDvAportante(String dvAportante) {
		this.dvAportante = dvAportante;
	}

	public String getPeriodoPago() {
		return periodoPago;
	}

	public void setPeriodoPago(String periodoPago) {
		this.periodoPago = periodoPago;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getNroPlanilla() {
		return nroPlanilla;
	}

	public void setNroPlanilla(String nroPlanilla) {
		this.nroPlanilla = nroPlanilla;
	}

	public String getFormaPresentacion() {
		return formaPresentacion;
	}

	public void setFormaPresentacion(String formaPresentacion) {
		this.formaPresentacion = formaPresentacion;
	}

	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getNroTotalPensionados() {
		return nroTotalPensionados;
	}

	public void setNroTotalPensionados(String nroTotalPensionados) {
		this.nroTotalPensionados = nroTotalPensionados;
	}

	public String getDiasMora() {
		return diasMora;
	}

	public void setDiasMora(String diasMora) {
		this.diasMora = diasMora;
	}

	public String getCodigoOperador() {
		return codigoOperador;
	}

	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}

	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public String getClasePagadorPensiones() {
		return clasePagadorPensiones;
	}

	public void setClasePagadorPensiones(String clasePagadorPensiones) {
		this.clasePagadorPensiones = clasePagadorPensiones;
	}

	public String getDireccionCorrespondencia() {
		return direccionCorrespondencia;
	}

	public void setDireccionCorrespondencia(String direccionCorrespondencia) {
		this.direccionCorrespondencia = direccionCorrespondencia;
	}

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getNroRegistrosTipo2() {
		return nroRegistrosTipo2;
	}

	public void setNroRegistrosTipo2(String nroRegistrosTipo2) {
		this.nroRegistrosTipo2 = nroRegistrosTipo2;
	}

	public String getNroAfiliadosAdm() {
		return nroAfiliadosAdm;
	}

	public void setNroAfiliadosAdm(String nroAfiliadosAdm) {
		this.nroAfiliadosAdm = nroAfiliadosAdm;
	}

	public String getTipoPlanilla() {
		return tipoPlanilla;
	}

	public void setTipoPlanilla(String tipoPlanilla) {
		this.tipoPlanilla = tipoPlanilla;
	}

	public String getNroRegistro() {
		return nroRegistro;
	}

	public void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	public String getCodigoFormato() {
		return codigoFormato;
	}

	public void setCodigoFormato(String codigoFormato) {
		this.codigoFormato = codigoFormato;
	}
}
