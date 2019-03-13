package net.jaimetorres.pila.approval.pojos.output.minps;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public class MinpsEncabezadoOut extends CommonOutputRecord{
	
	private String tipoRegistro;
	private String fechaPago;
	private String nroRegistrosArchivo;
	private String nroRegistrosTipo2Reportados;
	//private String nroPlanilla; super
	//private String modalidadPlanilla; super
	private String tipoPlanilla;
	private String razonSocialAportante; 
	//private String tipoIdentificacionAportante; super
	//private String nroIdentificacionAportante; super
	//private String digitoVerificacionAportante; super
	private String codigoSucursal;
	private String nombreSucursal;
	private String claseAportante;
	private String naturalezaJuridica;
	private String tipoPersona;
	private String formaPresentacion;
	private String direccionCorrespondencia;
	private String ubicacionAportante;
	private String actividadEconomica;
	private String telefono;
	private String fax;
	private String correoElectronico;

	private String fechaConcordato;
	private String tipoAccion;
	private String fechaFinActividades;
	private String tipoAportante;
	private String fechaMatriculaMercantil;
	private String departamentoMatriculaMercantil;
	private String  exoneradoPagoParafiscalesSalud;
	private String  beneficiarioLey1429;
	private String nroPlanillaAsociada;
	private String fechaPagoPlanillaAsociada;
	private String codigoAdmRiesgos;
	private String  periodoPagoPension;
	private String  periodoPagoSalud;
	private String diasMora;
	private String nroTotalEmpleados;
	private String valorNomina;
	private String identificador;
	private String fechaUltimaActualizacion;
	
	private String codigoOperador;
	private String nroPlanilla;
	private String modalidadPlanilla;
	private String tipoIdentificacionAportante;
	private String nroIdentificacionAportante;
	private String dvAportante;
	
	
	private String tipoIdentificacionRepLegal;
	private String nroIdentificacionRepLegal;
	private String dvRepLegal;
	private String primerApellidoRepLegal;
	private String segundoApellidoRepLegal;
	private String primerNombreRepLegal;
	private String segundoNombreRepLegal;
	
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getNroRegistrosArchivo() {
		return nroRegistrosArchivo;
	}
	public void setNroRegistrosArchivo(String nroRegistrosArchivo) {
		this.nroRegistrosArchivo = nroRegistrosArchivo;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getNroRegistrosTipo2Reportados() {
		return nroRegistrosTipo2Reportados;
	}
	public void setNroRegistrosTipo2Reportados(String nroRegistrosTipo2Reportados) {
		this.nroRegistrosTipo2Reportados = nroRegistrosTipo2Reportados;
	}
	public String getTipoPlanilla() {
		return tipoPlanilla;
	}
	public void setTipoPlanilla(String tipoPlanilla) {
		this.tipoPlanilla = tipoPlanilla;
	}
	public String getRazonSocialAportante() {
		return razonSocialAportante;
	}
	public void setRazonSocialAportante(String razonSocialAportante) {
		this.razonSocialAportante = razonSocialAportante;
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
	public String getClaseAportante() {
		return claseAportante;
	}
	public void setClaseAportante(String claseAportante) {
		this.claseAportante = claseAportante;
	}
	public String getNaturalezaJuridica() {
		return naturalezaJuridica;
	}
	public void setNaturalezaJuridica(String naturalezaJuridica) {
		this.naturalezaJuridica = naturalezaJuridica;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public String getFormaPresentacion() {
		return formaPresentacion;
	}
	public void setFormaPresentacion(String formaPresentacion) {
		this.formaPresentacion = formaPresentacion;
	}
	public String getDireccionCorrespondencia() {
		return direccionCorrespondencia;
	}
	public void setDireccionCorrespondencia(String direccionCorrespondencia) {
		this.direccionCorrespondencia = direccionCorrespondencia;
	}
	public String getUbicacionAportante() {
		return ubicacionAportante;
	}
	public void setUbicacionAportante(String ubicacionAportante) {
		this.ubicacionAportante = ubicacionAportante;
	}
	public String getActividadEconomica() {
		return actividadEconomica;
	}
	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
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
	public String getFechaConcordato() {
		return fechaConcordato;
	}
	public void setFechaConcordato(String fechaConcordato) {
		this.fechaConcordato = fechaConcordato;
	}
	public String getTipoAccion() {
		return tipoAccion;
	}
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
	public String getFechaFinActividades() {
		return fechaFinActividades;
	}
	public void setFechaFinActividades(String fechaFinActividades) {
		this.fechaFinActividades = fechaFinActividades;
	}
	public String getTipoAportante() {
		return tipoAportante;
	}
	public void setTipoAportante(String tipoAportante) {
		this.tipoAportante = tipoAportante;
	}
	public String getFechaMatriculaMercantil() {
		return fechaMatriculaMercantil;
	}
	public void setFechaMatriculaMercantil(String fechaMatriculaMercantil) {
		this.fechaMatriculaMercantil = fechaMatriculaMercantil;
	}
	public String getDepartamentoMatriculaMercantil() {
		return departamentoMatriculaMercantil;
	}
	public void setDepartamentoMatriculaMercantil(String departamentoMatriculaMercantil) {
		this.departamentoMatriculaMercantil = departamentoMatriculaMercantil;
	}
	public String getExoneradoPagoParafiscalesSalud() {
		return exoneradoPagoParafiscalesSalud;
	}
	public void setExoneradoPagoParafiscalesSalud(String exoneradoPagoParafiscalesSalud) {
		this.exoneradoPagoParafiscalesSalud = exoneradoPagoParafiscalesSalud;
	}
	public String getBeneficiarioLey1429() {
		return beneficiarioLey1429;
	}
	public void setBeneficiarioLey1429(String beneficiarioLey1429) {
		this.beneficiarioLey1429 = beneficiarioLey1429;
	}
	public String getNroPlanillaAsociada() {
		return nroPlanillaAsociada;
	}
	public void setNroPlanillaAsociada(String nroPlanillaAsociada) {
		this.nroPlanillaAsociada = nroPlanillaAsociada;
	}
	public String getFechaPagoPlanillaAsociada() {
		return fechaPagoPlanillaAsociada;
	}
	public void setFechaPagoPlanillaAsociada(String fechaPagoPlanillaAsociada) {
		this.fechaPagoPlanillaAsociada = fechaPagoPlanillaAsociada;
	}
	public String getCodigoAdmRiesgos() {
		return codigoAdmRiesgos;
	}
	public void setCodigoAdmRiesgos(String codigoAdmRiesgos) {
		this.codigoAdmRiesgos = codigoAdmRiesgos;
	}
	public String getPeriodoPagoPension() {
		return periodoPagoPension;
	}
	public void setPeriodoPagoPension(String periodoPagoPension) {
		this.periodoPagoPension = periodoPagoPension;
	}
	public String getPeriodoPagoSalud() {
		return periodoPagoSalud;
	}
	public void setPeriodoPagoSalud(String periodoPagoSalud) {
		this.periodoPagoSalud = periodoPagoSalud;
	}
	public String getDiasMora() {
		return diasMora;
	}
	public void setDiasMora(String diasMora) {
		this.diasMora = diasMora;
	}
	public String getNroTotalEmpleados() {
		return nroTotalEmpleados;
	}
	public void setNroTotalEmpleados(String nroTotalEmpleados) {
		this.nroTotalEmpleados = nroTotalEmpleados;
	}
	public String getValorNomina() {
		return valorNomina;
	}
	public void setValorNomina(String valorNomina) {
		this.valorNomina = valorNomina;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	public String getTipoIdentificacionRepLegal() {
		return tipoIdentificacionRepLegal;
	}
	public void setTipoIdentificacionRepLegal(String tipoIdentificacionRepLegal) {
		this.tipoIdentificacionRepLegal = tipoIdentificacionRepLegal;
	}
	public String getNroIdentificacionRepLegal() {
		return nroIdentificacionRepLegal;
	}
	public void setNroIdentificacionRepLegal(String nroIdentificacionRepLegal) {
		this.nroIdentificacionRepLegal = nroIdentificacionRepLegal;
	}
	public String getDvRepLegal() {
		return dvRepLegal;
	}
	public void setDvRepLegal(String dvRepLegal) {
		this.dvRepLegal = dvRepLegal;
	}
	public String getPrimerApellidoRepLegal() {
		return primerApellidoRepLegal;
	}
	public void setPrimerApellidoRepLegal(String primerApellidoRepLegal) {
		this.primerApellidoRepLegal = primerApellidoRepLegal;
	}
	public String getSegundoApellidoRepLegal() {
		return segundoApellidoRepLegal;
	}
	public void setSegundoApellidoRepLegal(String segundoApellidoRepLegal) {
		this.segundoApellidoRepLegal = segundoApellidoRepLegal;
	}
	public String getPrimerNombreRepLegal() {
		return primerNombreRepLegal;
	}
	public void setPrimerNombreRepLegal(String primerNombreRepLegal) {
		this.primerNombreRepLegal = primerNombreRepLegal;
	}
	public String getSegundoNombreRepLegal() {
		return segundoNombreRepLegal;
	}
	public void setSegundoNombreRepLegal(String segundoNombreRepLegal) {
		this.segundoNombreRepLegal = segundoNombreRepLegal;
	}
	public String getCodigoOperador() {
		return codigoOperador;
	}
	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}
	public String getNroPlanilla() {
		return nroPlanilla;
	}
	public void setNroPlanilla(String nroPlanilla) {
		this.nroPlanilla = nroPlanilla;
	}
	public String getModalidadPlanilla() {
		return modalidadPlanilla;
	}
	public void setModalidadPlanilla(String modalidadPlanilla) {
		this.modalidadPlanilla = modalidadPlanilla;
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
}
