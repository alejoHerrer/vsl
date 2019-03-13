package net.jaimetorres.pila.approval.pojos.output.minps;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public class MinpsLiquidacionDetalladaOut extends CommonOutputRecord{
	
	private String secuencia;
	private String codigoOperador;
	private String nroPlanilla;
	private String tipoCotizante;
	private String subtipoCotizante;
	private String tipoPensionado;
	private String tipoPension;
	private String pensionCompartida;
	private String extranjeroNoObligadoCotPension;
	private String colombianoResidenteExterior;
	private String tipoIdentificacionCausante;
	private String nroIdentificacionCausante;
	private String ubicacionCotizante;
	private String primerApellido;
	private String segundoApellido;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellidoCausante;
	private String segundoApellidoCausante;
	private String primerNombreCausante;
	private String segundoNombreCausante;
	private String pensionadoPagoExterior;
	private String novedadIngreso;
	private String novedadRetiro;
	private String novedadTrasladoEpsDesde;
	private String novedadTrasladoEpsA;
	private String novedadTrasladoAfpDesde;
	private String novedadTrasladoAfpA;
	private String novedadVariacionSalarioPermanente;
	private String novedadCorreccion;
	private String novedadVariacionSalarioTransitoria;
	private String novedadSuspensionTemporalContrato;
	private String novedadIncapacidadEnfermedadGeneral;
	private String novedadLicenciaMaternidad;
	private String novedadVacaciones;
	private String novedadAporteVoluntarioPensiones;
	private String novedadVariacionCentrosTrabajo;
	private String diasNovedadIncapacidadRiesgosProfesionales;
	private String novedadSuspension;
	private String codigoAdmPension;
	private String codigoAdmPensionTraslado;
	private String codigoAdmSalud;
	private String codigoAdmSaludTraslado;
	private String codigoAdmCajas;
	private String diasCotizadosPension;
	private String diasCotizadosSalud;
	private String diasCotizadosRiesgos;
	private String diasCotizadosCajas;
	private String diasCotizadosFsp;
	private String salarioBasico;
	private String salarioIntegral;
	private String ibcPension;
	private String ibcSalud;
	private String ibcRiesgos;
	private String ibcCajas;
	private String tarifaPension;
	private String cotizacionPension;
	private String cotizacionVoluntariaPensionAfiliado;
	private String cotizacionVoluntariaPensionAportante;
	private String totalCotizacionPension;
	private String aporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	private String aporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	private String valorNoRetenidoAportesVoluntarios;
	private String tarifaSalud;
	private String cotizacionSalud;
	private String valorUpcAdicional;
	private String tarifaRiesgos;
	private String codigoCentroTrabajo;
	private String cotizacionRiesgos;
	private String tarifaCajas;
	private String cotizacionCajas;
	private String tarifaSena;
	private String cotizacionSena;
	private String tarifaIcbf;
	private String cotizacionIcbf;
	private String tarifaEsap;
	private String cotizacionEsap;
	private String tarifaMinedu;
	private String cotizacionMinedu;
	private String tipoIdentificacionUPC;
	private String nroIdentificacionUPC;
	private String cotizanteExoneradoLey1607;
	private String codigoAdmRiesgos;
	private String claseRiesgo;
	private String indicadorTarifaEspecialPensiones;
	private String novedadIngresoFecha;
	private String novedadRetiroFecha;
	private String novedadVariacionSalarioPermanenteFecha;
	private String novedadSuspensionFechaInicio;
	private String novedadSuspensionFechaFin;
	private String novedadIncapacidadEnfermedadGeneralFechaInicio;
	private String novedadIncapacidadEnfermedadGeneralFechaFin;
	private String novedadLicenciaMaternidadFechaInicio;
	private String novedadLicenciaMaternidadFechaFin;
	private String novedadVacacionesFechaInicio;
	private String novedadVacacionesFechaFin;
	private String novedadVariacionCentrosTrabajoFechaInicio;
	private String novedadVariacionCentrosTrabajoFechaFin;
	private String novedadIncapacidadRiesgosLaboralesFechaInicio;
	private String novedadIncapacidadRiesgosLaboralesFechaFin;
	private String ibcOtrosParafiscales;
	private String fechaRadicacionExterior;
	
	private String tipoRegistro;
	private String tipoIdentificacion;
	private String nroIdentificacion;
	
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
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
	public String getTipoPensionado() {
		return tipoPensionado;
	}
	public void setTipoPensionado(String tipoPensionado) {
		this.tipoPensionado = tipoPensionado;
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
	public String getUbicacionCotizante() {
		return ubicacionCotizante;
	}
	public void setUbicacionCotizante(String ubicacionCotizante) {
		this.ubicacionCotizante = ubicacionCotizante;
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
	public String getPensionadoPagoExterior() {
		return pensionadoPagoExterior;
	}
	public void setPensionadoPagoExterior(String pensionadoPagoExterior) {
		this.pensionadoPagoExterior = pensionadoPagoExterior;
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
	public String getNovedadTrasladoEpsDesde() {
		return novedadTrasladoEpsDesde;
	}
	public void setNovedadTrasladoEpsDesde(String novedadTrasladoEpsDesde) {
		this.novedadTrasladoEpsDesde = novedadTrasladoEpsDesde;
	}
	public String getNovedadTrasladoEpsA() {
		return novedadTrasladoEpsA;
	}
	public void setNovedadTrasladoEpsA(String novedadTrasladoEpsA) {
		this.novedadTrasladoEpsA = novedadTrasladoEpsA;
	}
	public String getNovedadTrasladoAfpDesde() {
		return novedadTrasladoAfpDesde;
	}
	public void setNovedadTrasladoAfpDesde(String novedadTrasladoAfpDesde) {
		this.novedadTrasladoAfpDesde = novedadTrasladoAfpDesde;
	}
	public String getNovedadTrasladoAfpA() {
		return novedadTrasladoAfpA;
	}
	public void setNovedadTrasladoAfpA(String novedadTrasladoAfpA) {
		this.novedadTrasladoAfpA = novedadTrasladoAfpA;
	}
	public String getNovedadVariacionSalarioPermanente() {
		return novedadVariacionSalarioPermanente;
	}
	public void setNovedadVariacionSalarioPermanente(String novedadVariacionSalarioPermanente) {
		this.novedadVariacionSalarioPermanente = novedadVariacionSalarioPermanente;
	}
	public String getNovedadCorreccion() {
		return novedadCorreccion;
	}
	public void setNovedadCorreccion(String novedadCorreccion) {
		this.novedadCorreccion = novedadCorreccion;
	}
	public String getNovedadVariacionSalarioTransitoria() {
		return novedadVariacionSalarioTransitoria;
	}
	public void setNovedadVariacionSalarioTransitoria(String novedadVariacionSalarioTransitoria) {
		this.novedadVariacionSalarioTransitoria = novedadVariacionSalarioTransitoria;
	}
	public String getNovedadSuspensionTemporalContrato() {
		return novedadSuspensionTemporalContrato;
	}
	public void setNovedadSuspensionTemporalContrato(String novedadSuspensionTemporalContrato) {
		this.novedadSuspensionTemporalContrato = novedadSuspensionTemporalContrato;
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
	public String getNovedadAporteVoluntarioPensiones() {
		return novedadAporteVoluntarioPensiones;
	}
	public void setNovedadAporteVoluntarioPensiones(String novedadAporteVoluntarioPensiones) {
		this.novedadAporteVoluntarioPensiones = novedadAporteVoluntarioPensiones;
	}
	public String getNovedadVariacionCentrosTrabajo() {
		return novedadVariacionCentrosTrabajo;
	}
	public void setNovedadVariacionCentrosTrabajo(String novedadVariacionCentrosTrabajo) {
		this.novedadVariacionCentrosTrabajo = novedadVariacionCentrosTrabajo;
	}
	public String getDiasNovedadIncapacidadRiesgosProfesionales() {
		return diasNovedadIncapacidadRiesgosProfesionales;
	}
	public void setDiasNovedadIncapacidadRiesgosProfesionales(String diasNovedadIncapacidadRiesgosProfesionales) {
		this.diasNovedadIncapacidadRiesgosProfesionales = diasNovedadIncapacidadRiesgosProfesionales;
	}
	public String getNovedadSuspension() {
		return novedadSuspension;
	}
	public void setNovedadSuspension(String novedadSuspension) {
		this.novedadSuspension = novedadSuspension;
	}
	public String getCodigoAdmPension() {
		return codigoAdmPension;
	}
	public void setCodigoAdmPension(String codigoAdmPension) {
		this.codigoAdmPension = codigoAdmPension;
	}
	public String getCodigoAdmPensionTraslado() {
		return codigoAdmPensionTraslado;
	}
	public void setCodigoAdmPensionTraslado(String codigoAdmPensionTraslado) {
		this.codigoAdmPensionTraslado = codigoAdmPensionTraslado;
	}
	public String getCodigoAdmSalud() {
		return codigoAdmSalud;
	}
	public void setCodigoAdmSalud(String codigoAdmSalud) {
		this.codigoAdmSalud = codigoAdmSalud;
	}
	public String getCodigoAdmSaludTraslado() {
		return codigoAdmSaludTraslado;
	}
	public void setCodigoAdmSaludTraslado(String codigoAdmSaludTraslado) {
		this.codigoAdmSaludTraslado = codigoAdmSaludTraslado;
	}
	public String getCodigoAdmCajas() {
		return codigoAdmCajas;
	}
	public void setCodigoAdmCajas(String codigoAdmCajas) {
		this.codigoAdmCajas = codigoAdmCajas;
	}
	public String getDiasCotizadosPension() {
		return diasCotizadosPension;
	}
	public void setDiasCotizadosPension(String diasCotizadosPension) {
		this.diasCotizadosPension = diasCotizadosPension;
	}
	public String getDiasCotizadosSalud() {
		return diasCotizadosSalud;
	}
	public void setDiasCotizadosSalud(String diasCotizadosSalud) {
		this.diasCotizadosSalud = diasCotizadosSalud;
	}
	public String getDiasCotizadosRiesgos() {
		return diasCotizadosRiesgos;
	}
	public void setDiasCotizadosRiesgos(String diasCotizadosRiesgos) {
		this.diasCotizadosRiesgos = diasCotizadosRiesgos;
	}
	public String getDiasCotizadosCajas() {
		return diasCotizadosCajas;
	}
	public void setDiasCotizadosCajas(String diasCotizadosCajas) {
		this.diasCotizadosCajas = diasCotizadosCajas;
	}
	public String getDiasCotizadosFsp() {
		return diasCotizadosFsp;
	}
	public void setDiasCotizadosFsp(String diasCotizadosFsp) {
		this.diasCotizadosFsp = diasCotizadosFsp;
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
	public String getIbcPension() {
		return ibcPension;
	}
	public void setIbcPension(String ibcPension) {
		this.ibcPension = ibcPension;
	}
	public String getIbcSalud() {
		return ibcSalud;
	}
	public void setIbcSalud(String ibcSalud) {
		this.ibcSalud = ibcSalud;
	}
	public String getIbcRiesgos() {
		return ibcRiesgos;
	}
	public void setIbcRiesgos(String ibcRiesgos) {
		this.ibcRiesgos = ibcRiesgos;
	}
	public String getIbcCajas() {
		return ibcCajas;
	}
	public void setIbcCajas(String ibcCajas) {
		this.ibcCajas = ibcCajas;
	}
	public String getTarifaPension() {
		return tarifaPension;
	}
	public void setTarifaPension(String tarifaPension) {
		this.tarifaPension = tarifaPension;
	}
	public String getCotizacionPension() {
		return cotizacionPension;
	}
	public void setCotizacionPension(String cotizacionPension) {
		this.cotizacionPension = cotizacionPension;
	}
	public String getCotizacionVoluntariaPensionAfiliado() {
		return cotizacionVoluntariaPensionAfiliado;
	}
	public void setCotizacionVoluntariaPensionAfiliado(String cotizacionVoluntariaPensionAfiliado) {
		this.cotizacionVoluntariaPensionAfiliado = cotizacionVoluntariaPensionAfiliado;
	}
	public String getCotizacionVoluntariaPensionAportante() {
		return cotizacionVoluntariaPensionAportante;
	}
	public void setCotizacionVoluntariaPensionAportante(String cotizacionVoluntariaPensionAportante) {
		this.cotizacionVoluntariaPensionAportante = cotizacionVoluntariaPensionAportante;
	}
	public String getTotalCotizacionPension() {
		return totalCotizacionPension;
	}
	public void setTotalCotizacionPension(String totalCotizacionPension) {
		this.totalCotizacionPension = totalCotizacionPension;
	}
	public String getAporteFondoSolidaridadPensionalSubcuentaSolidaridad() {
		return aporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	}
	public void setAporteFondoSolidaridadPensionalSubcuentaSolidaridad(
			String aporteFondoSolidaridadPensionalSubcuentaSolidaridad) {
		this.aporteFondoSolidaridadPensionalSubcuentaSolidaridad = aporteFondoSolidaridadPensionalSubcuentaSolidaridad;
	}
	public String getAporteFondoSolidaridadPensionalSubcuentaSubsistencia() {
		return aporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	}
	public void setAporteFondoSolidaridadPensionalSubcuentaSubsistencia(
			String aporteFondoSolidaridadPensionalSubcuentaSubsistencia) {
		this.aporteFondoSolidaridadPensionalSubcuentaSubsistencia = aporteFondoSolidaridadPensionalSubcuentaSubsistencia;
	}
	public String getValorNoRetenidoAportesVoluntarios() {
		return valorNoRetenidoAportesVoluntarios;
	}
	public void setValorNoRetenidoAportesVoluntarios(String valorNoRetenidoAportesVoluntarios) {
		this.valorNoRetenidoAportesVoluntarios = valorNoRetenidoAportesVoluntarios;
	}
	public String getTarifaSalud() {
		return tarifaSalud;
	}
	public void setTarifaSalud(String tarifaSalud) {
		this.tarifaSalud = tarifaSalud;
	}
	public String getCotizacionSalud() {
		return cotizacionSalud;
	}
	public void setCotizacionSalud(String cotizacionSalud) {
		this.cotizacionSalud = cotizacionSalud;
	}
	public String getValorUpcAdicional() {
		return valorUpcAdicional;
	}
	public void setValorUpcAdicional(String valorUpcAdicional) {
		this.valorUpcAdicional = valorUpcAdicional;
	}
	public String getTarifaRiesgos() {
		return tarifaRiesgos;
	}
	public void setTarifaRiesgos(String tarifaRiesgos) {
		this.tarifaRiesgos = tarifaRiesgos;
	}
	public String getCodigoCentroTrabajo() {
		return codigoCentroTrabajo;
	}
	public void setCodigoCentroTrabajo(String codigoCentroTrabajo) {
		this.codigoCentroTrabajo = codigoCentroTrabajo;
	}
	public String getCotizacionRiesgos() {
		return cotizacionRiesgos;
	}
	public void setCotizacionRiesgos(String cotizacionRiesgos) {
		this.cotizacionRiesgos = cotizacionRiesgos;
	}
	public String getTarifaCajas() {
		return tarifaCajas;
	}
	public void setTarifaCajas(String tarifaCajas) {
		this.tarifaCajas = tarifaCajas;
	}
	public String getCotizacionCajas() {
		return cotizacionCajas;
	}
	public void setCotizacionCajas(String cotizacionCajas) {
		this.cotizacionCajas = cotizacionCajas;
	}
	public String getTarifaSena() {
		return tarifaSena;
	}
	public void setTarifaSena(String tarifaSena) {
		this.tarifaSena = tarifaSena;
	}
	public String getCotizacionSena() {
		return cotizacionSena;
	}
	public void setCotizacionSena(String cotizacionSena) {
		this.cotizacionSena = cotizacionSena;
	}
	public String getTarifaIcbf() {
		return tarifaIcbf;
	}
	public void setTarifaIcbf(String tarifaIcbf) {
		this.tarifaIcbf = tarifaIcbf;
	}
	public String getCotizacionIcbf() {
		return cotizacionIcbf;
	}
	public void setCotizacionIcbf(String cotizacionIcbf) {
		this.cotizacionIcbf = cotizacionIcbf;
	}
	public String getTarifaEsap() {
		return tarifaEsap;
	}
	public void setTarifaEsap(String tarifaEsap) {
		this.tarifaEsap = tarifaEsap;
	}
	public String getCotizacionEsap() {
		return cotizacionEsap;
	}
	public void setCotizacionEsap(String cotizacionEsap) {
		this.cotizacionEsap = cotizacionEsap;
	}
	public String getTarifaMinedu() {
		return tarifaMinedu;
	}
	public void setTarifaMinedu(String tarifaMinedu) {
		this.tarifaMinedu = tarifaMinedu;
	}
	public String getCotizacionMinedu() {
		return cotizacionMinedu;
	}
	public void setCotizacionMinedu(String cotizacionMinedu) {
		this.cotizacionMinedu = cotizacionMinedu;
	}
	public String getTipoIdentificacionUPC() {
		return tipoIdentificacionUPC;
	}
	public void setTipoIdentificacionUPC(String tipoIdentificacionUPC) {
		this.tipoIdentificacionUPC = tipoIdentificacionUPC;
	}
	public String getNroIdentificacionUPC() {
		return nroIdentificacionUPC;
	}
	public void setNroIdentificacionUPC(String nroIdentificacionUPC) {
		this.nroIdentificacionUPC = nroIdentificacionUPC;
	}
	public String getCotizanteExoneradoLey1607() {
		return cotizanteExoneradoLey1607;
	}
	public void setCotizanteExoneradoLey1607(String cotizanteExoneradoLey1607) {
		this.cotizanteExoneradoLey1607 = cotizanteExoneradoLey1607;
	}
	public String getCodigoAdmRiesgos() {
		return codigoAdmRiesgos;
	}
	public void setCodigoAdmRiesgos(String codigoAdmRiesgos) {
		this.codigoAdmRiesgos = codigoAdmRiesgos;
	}
	public String getClaseRiesgo() {
		return claseRiesgo;
	}
	public void setClaseRiesgo(String claseRiesgo) {
		this.claseRiesgo = claseRiesgo;
	}
	public String getIndicadorTarifaEspecialPensiones() {
		return indicadorTarifaEspecialPensiones;
	}
	public void setIndicadorTarifaEspecialPensiones(String indicadorTarifaEspecialPensiones) {
		this.indicadorTarifaEspecialPensiones = indicadorTarifaEspecialPensiones;
	}
	public String getNovedadIngresoFecha() {
		return novedadIngresoFecha;
	}
	public void setNovedadIngresoFecha(String novedadIngresoFecha) {
		this.novedadIngresoFecha = novedadIngresoFecha;
	}
	public String getNovedadRetiroFecha() {
		return novedadRetiroFecha;
	}
	public void setNovedadRetiroFecha(String novedadRetiroFecha) {
		this.novedadRetiroFecha = novedadRetiroFecha;
	}
	public String getNovedadVariacionSalarioPermanenteFecha() {
		return novedadVariacionSalarioPermanenteFecha;
	}
	public void setNovedadVariacionSalarioPermanenteFecha(String novedadVariacionSalarioPermanenteFecha) {
		this.novedadVariacionSalarioPermanenteFecha = novedadVariacionSalarioPermanenteFecha;
	}
	public String getNovedadSuspensionFechaInicio() {
		return novedadSuspensionFechaInicio;
	}
	public void setNovedadSuspensionFechaInicio(String novedadSuspensionFechaInicio) {
		this.novedadSuspensionFechaInicio = novedadSuspensionFechaInicio;
	}
	public String getNovedadSuspensionFechaFin() {
		return novedadSuspensionFechaFin;
	}
	public void setNovedadSuspensionFechaFin(String novedadSuspensionFechaFin) {
		this.novedadSuspensionFechaFin = novedadSuspensionFechaFin;
	}
	public String getNovedadIncapacidadEnfermedadGeneralFechaInicio() {
		return novedadIncapacidadEnfermedadGeneralFechaInicio;
	}
	public void setNovedadIncapacidadEnfermedadGeneralFechaInicio(String novedadIncapacidadEnfermedadGeneralFechaInicio) {
		this.novedadIncapacidadEnfermedadGeneralFechaInicio = novedadIncapacidadEnfermedadGeneralFechaInicio;
	}
	public String getNovedadIncapacidadEnfermedadGeneralFechaFin() {
		return novedadIncapacidadEnfermedadGeneralFechaFin;
	}
	public void setNovedadIncapacidadEnfermedadGeneralFechaFin(String novedadIncapacidadEnfermedadGeneralFechaFin) {
		this.novedadIncapacidadEnfermedadGeneralFechaFin = novedadIncapacidadEnfermedadGeneralFechaFin;
	}
	public String getNovedadLicenciaMaternidadFechaInicio() {
		return novedadLicenciaMaternidadFechaInicio;
	}
	public void setNovedadLicenciaMaternidadFechaInicio(String novedadLicenciaMaternidadFechaInicio) {
		this.novedadLicenciaMaternidadFechaInicio = novedadLicenciaMaternidadFechaInicio;
	}
	public String getNovedadLicenciaMaternidadFechaFin() {
		return novedadLicenciaMaternidadFechaFin;
	}
	public void setNovedadLicenciaMaternidadFechaFin(String novedadLicenciaMaternidadFechaFin) {
		this.novedadLicenciaMaternidadFechaFin = novedadLicenciaMaternidadFechaFin;
	}
	public String getNovedadVacacionesFechaInicio() {
		return novedadVacacionesFechaInicio;
	}
	public void setNovedadVacacionesFechaInicio(String novedadVacacionesFechaInicio) {
		this.novedadVacacionesFechaInicio = novedadVacacionesFechaInicio;
	}
	public String getNovedadVacacionesFechaFin() {
		return novedadVacacionesFechaFin;
	}
	public void setNovedadVacacionesFechaFin(String novedadVacacionesFechaFin) {
		this.novedadVacacionesFechaFin = novedadVacacionesFechaFin;
	}
	public String getNovedadVariacionCentrosTrabajoFechaInicio() {
		return novedadVariacionCentrosTrabajoFechaInicio;
	}
	public void setNovedadVariacionCentrosTrabajoFechaInicio(String novedadVariacionCentrosTrabajoFechaInicio) {
		this.novedadVariacionCentrosTrabajoFechaInicio = novedadVariacionCentrosTrabajoFechaInicio;
	}
	public String getNovedadVariacionCentrosTrabajoFechaFin() {
		return novedadVariacionCentrosTrabajoFechaFin;
	}
	public void setNovedadVariacionCentrosTrabajoFechaFin(String novedadVariacionCentrosTrabajoFechaFin) {
		this.novedadVariacionCentrosTrabajoFechaFin = novedadVariacionCentrosTrabajoFechaFin;
	}
	public String getNovedadIncapacidadRiesgosLaboralesFechaInicio() {
		return novedadIncapacidadRiesgosLaboralesFechaInicio;
	}
	public void setNovedadIncapacidadRiesgosLaboralesFechaInicio(String novedadIncapacidadRiesgosLaboralesFechaInicio) {
		this.novedadIncapacidadRiesgosLaboralesFechaInicio = novedadIncapacidadRiesgosLaboralesFechaInicio;
	}
	public String getNovedadIncapacidadRiesgosLaboralesFechaFin() {
		return novedadIncapacidadRiesgosLaboralesFechaFin;
	}
	public void setNovedadIncapacidadRiesgosLaboralesFechaFin(String novedadIncapacidadRiesgosLaboralesFechaFin) {
		this.novedadIncapacidadRiesgosLaboralesFechaFin = novedadIncapacidadRiesgosLaboralesFechaFin;
	}
	public String getIbcOtrosParafiscales() {
		return ibcOtrosParafiscales;
	}
	public void setIbcOtrosParafiscales(String ibcOtrosParafiscales) {
		this.ibcOtrosParafiscales = ibcOtrosParafiscales;
	}
	public String getFechaRadicacionExterior() {
		return fechaRadicacionExterior;
	}
	public void setFechaRadicacionExterior(String fechaRadicacionExterior) {
		this.fechaRadicacionExterior = fechaRadicacionExterior;
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


}
