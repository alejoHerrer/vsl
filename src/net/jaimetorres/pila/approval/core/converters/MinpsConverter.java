package net.jaimetorres.pila.approval.core.converters;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.jaimetorres.pila.approval.pojos.output.minps.MinpsEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsLey1819Out;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo10EsapOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo11MineduOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo12PlanillaOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo3PensionOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo4FspOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo5SaludOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo6RiesgosOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo7CajasOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo8SenaOut;
import net.jaimetorres.pila.approval.pojos.output.minps.MinpsTotalesRegistroTipo9IcbfOut;
import net.jaimetorres.pila.approval.utils.DataTypeConverter;
import net.jaimetorres.pila.approval.utils.DateUtils;
import net.jaimetorres.validaciones.entidad.RegistroAportante;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo10;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo11;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo12;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo13;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo3;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo4;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo5;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo6;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo7;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo8;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo9;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;
import net.jaimetorres.validaciones.general.FinalidadPlanilla;
import net.jaimetorres.validaciones.general.TipoArchivo;

public class MinpsConverter {

	//#######
	//ACTIVOS
	//#######
	//ENCABEZADO(1)
	public LineaRegistroTipo1 convertMinpsActivosEncabezado2LineaRegistroTipo1(MinpsEncabezadoOut encabezado){
		
		LineaRegistroTipo1 lineaRegistroTipo1 = new LineaRegistroTipo1();
		lineaRegistroTipo1.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
		//Requerido por la planilla N (no afecta para las planillas diferentes a N)
		lineaRegistroTipo1.setFinalidadPlanilla(FinalidadPlanilla.CORRECCION_NORMAL);
		lineaRegistroTipo1.setIndiceLinea(encabezado.getLineNumber());
		
		//FIELDS
		//XXX Tipo de Registro
		lineaRegistroTipo1.setFechaPagoPlanilla(encabezado.getFechaPago());		
		//XXX Número de registros en el archivo
		lineaRegistroTipo1.setCodigoOperadorInfo(DataTypeConverter.convert(encabezado.getCodigoOperador(),Integer.class));
		lineaRegistroTipo1.setNumeroEmpleados(DataTypeConverter.convert(encabezado.getNroTotalEmpleados(),Integer.class));
		lineaRegistroTipo1.setNumeroPlanilla(encabezado.getNroPlanilla());
		lineaRegistroTipo1.setModalidadPlanilla(DataTypeConverter.convert(encabezado.getModalidadPlanilla(),Integer.class));
		lineaRegistroTipo1.setTipoPlanilla(encabezado.getTipoPlanilla());
		lineaRegistroTipo1.setRazonSocial(encabezado.getRazonSocialAportante());
		lineaRegistroTipo1.setTipoDocumento(encabezado.getTipoIdentificacionAportante());
		lineaRegistroTipo1.setNumeroDocumento(encabezado.getNroIdentificacionAportante());
		Integer digitoVerificacion = DataTypeConverter.convert(encabezado.getDvAportante(),Integer.class);
		lineaRegistroTipo1.setDigitoVerificacion(digitoVerificacion);
		lineaRegistroTipo1.setCodigoSucursalAportante(encabezado.getCodigoSucursal());
		lineaRegistroTipo1.setNombreSucursalAportante(encabezado.getNombreSucursal());
		//Clase de Aportante (ONLY RegistroAportante)
		//Naturaleza Juridica(ONLY RegistroAportante y el atributo es TipoEntidad)
		//Tipo de Persona (ONLY RegistroAportante)
		lineaRegistroTipo1.setFormaPresentacion(encabezado.getFormaPresentacion());
		//Direccion de Correspondencia (ONLY RegistroAportante)
		//Ubicación del aportante (ONLY RegistroAportante)
		//XXX Codigo DANE o Actividad Economica
		//Teléfono (ONLY RegistroAportante)
		//Fax (ONLY RegistroAportante)
		//Correo Electronico (ONLY RegistroAportante)
		//Nro Identificacion del representante legal (ONLY RegistroAportante)
		//Digito Verificacion del representante legal (ONLY RegistroAportante)
		//Tipo Identificacion Representante legal (ONLY RegistroAportante)
		//primer apellido del representante legal (ONLY RegistroAportante)
		//segundo apellido del representante legal (ONLY RegistroAportante)
		//primer nombre del representante legal (ONLY RegistroAportante)
		//segundo nombre del representante legal (ONLY RegistroAportante)
		//Fecha inicio concordato (ONLY RegistroAportante)
		//tipo de accion (ONLY RegistroAportante)
		//fecha en que termino actividades comerciales (ONLY RegistroAportante)
		lineaRegistroTipo1.setTipoAportante(DataTypeConverter.convert(encabezado.getTipoAportante(),Integer.class));
		//Fecha matricula mercantil (ONLY RegistroAportante)
		//Codigo DPTO, matricula mercantil (ONLY RegistroAportante)
		//Aportante exonerado pago salud y parafiscales (ley 1607) (ONLY RegistroAportante)
		//Aportante se acoge a beneficios ley 1429 (cajas) (ONLY RegistroAportante)
		lineaRegistroTipo1.setNumeroPlanillaAsociada(encabezado.getNroPlanillaAsociada().equals("0") ? "" : encabezado.getNroPlanillaAsociada());
		lineaRegistroTipo1.setFechaPagoPlanillaAsociada(encabezado.getFechaPagoPlanillaAsociada());
		lineaRegistroTipo1.setCodigoARP(encabezado.getCodigoAdmRiesgos());
		lineaRegistroTipo1.setPeriodoNoSalud(encabezado.getPeriodoPagoPension());
		lineaRegistroTipo1.setPeriodoSalud(encabezado.getPeriodoPagoSalud());
		//XXX Días de mora
		//XXX Nro total de empleados/pensionados
		lineaRegistroTipo1.setValorNomina(DataTypeConverter.convert(encabezado.getValorNomina(),Long.class));
		//XXX Identificador, encabezado.getIdentificador();
		
		//POJO RegistroAportante
		RegistroAportante registroAportante = new RegistroAportante();
		registroAportante.setRazonSocial(encabezado.getRazonSocialAportante());
		registroAportante.setTipoIdentificacion(encabezado.getTipoIdentificacionAportante());
		registroAportante.setNumeroIdentificacion(encabezado.getNroIdentificacionAportante());
		registroAportante.setDigitoVerificacion(digitoVerificacion);
		registroAportante.setSucursal(encabezado.getCodigoSucursal());
		registroAportante.setNombreSucursal(encabezado.getNombreSucursal());
		registroAportante.setFormaPresentacion(DataTypeConverter.convert(encabezado.getFormaPresentacion(),Character.class));
		registroAportante.setClase(DataTypeConverter.convert(encabezado.getClaseAportante(),Character.class));
		registroAportante.setTipoPersona(DataTypeConverter.convert(encabezado.getTipoPersona(),Character.class));
		registroAportante.setDireccion(encabezado.getDireccionCorrespondencia());
		/*XXX OJO SE DEBE HACER UN SPLIT
		*/
		String ubicacionAportante = encabezado.getUbicacionAportante();
		registroAportante.setDepartamento(DataTypeConverter.convert(ubicacionAportante.substring(0,2),Integer.class));
		registroAportante.setMunicipio(DataTypeConverter.convert(ubicacionAportante.substring(2,ubicacionAportante.length()),Integer.class));
		
		registroAportante.setTelefono(encabezado.getTelefono());
		registroAportante.setFax(encabezado.getFax());
		registroAportante.setCorreo(encabezado.getCorreoElectronico());
		registroAportante.setNumeroIDRep(encabezado.getNroIdentificacionRepLegal());
		registroAportante.setDigitoVerificacionRep(DataTypeConverter.convert(encabezado.getDvRepLegal(),Integer.class));
		registroAportante.setTipoIDRep(encabezado.getTipoIdentificacionRepLegal());
		registroAportante.setPApellidoRep(encabezado.getPrimerApellidoRepLegal());
		registroAportante.setSApellidoRep(encabezado.getSegundoApellidoRepLegal());
		registroAportante.setPNombreRep(encabezado.getPrimerNombreRepLegal());
		registroAportante.setSNombreRep(encabezado.getSegundoNombreRepLegal());
		registroAportante.setFechaInicioConcordato(encabezado.getFechaConcordato());
		registroAportante.setTipoAccion(DataTypeConverter.convert(encabezado.getTipoAccion(),Integer.class));
		registroAportante.setFechaFinActividades(encabezado.getFechaFinActividades());
		registroAportante.setTipoAportante(DataTypeConverter.convert(encabezado.getTipoAportante(),Integer.class));
		registroAportante.setFechaMatriculaMercantil(encabezado.getFechaMatriculaMercantil());
		registroAportante.setDepartamentoMM(DataTypeConverter.convert(encabezado.getDepartamentoMatriculaMercantil(),Integer.class));
		registroAportante.setExoParafSalud(encabezado.getExoneradoPagoParafiscalesSalud());
		registroAportante.setBen1429CCF(DataTypeConverter.convert(encabezado.getBeneficiarioLey1429(),Boolean.class));
		registroAportante.setTipoEntidad(DataTypeConverter.convert(encabezado.getNaturalezaJuridica(),Integer.class));
		registroAportante.setFechaUltimaActualizacion(DateUtils.convertStringToDate(encabezado.getFechaUltimaActualizacion()));
		
		lineaRegistroTipo1.setRegistroAportante(registroAportante);
		
		return lineaRegistroTipo1;
	}
	
	//LIQUIDACION_DETALLADA(2)
	public List<LineaRegistroTipo2> convertMinpsActivosLiquidacionDetallada2LineaRegistroTipo2List(
			List<MinpsLiquidacionDetalladaOut> liquidacionDetalladaList){
		
		Function<MinpsLiquidacionDetalladaOut,LineaRegistroTipo2> mapper = ld -> {
			
			LineaRegistroTipo2 lineaRegistroTipo2 = new LineaRegistroTipo2();
			
			lineaRegistroTipo2.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
			lineaRegistroTipo2.setIndiceLinea(ld.getLineNumber());
			
			//XXX Tipo de Registro
			//XXX Nro del Registro
			//XXX Codigo Operador
			//XXX Nro de la planilla
			lineaRegistroTipo2.setTipoDocumento(ld.getTipoIdentificacion());
			lineaRegistroTipo2.setNumeroDocumento(ld.getNroIdentificacion());
			lineaRegistroTipo2.setTipoCotizante(DataTypeConverter.convert(ld.getTipoCotizante(),Integer.class));
			lineaRegistroTipo2.setSubTipoCotizante(DataTypeConverter.convert(ld.getSubtipoCotizante(),Integer.class));
			//XXX Tipo de Pensionado
			//XXX Tipo de Pension
			//XXX Pension Compartida
			lineaRegistroTipo2.setExtranjeroNoObligadoCotizar(ld.getExtranjeroNoObligadoCotPension());
			lineaRegistroTipo2.setColombianoResideExterior(ld.getColombianoResidenteExterior());
			//XXX Tipo Identificacion Causante
			//XXX Nro Identificacion Causante
			//XXX Ubicacion del Cotizante (ojo este dato viene concatenado) ld.getUbicacionCotizante();
			String ubicacionCotizante = ld.getUbicacionCotizante();
			if(!ubicacionCotizante.trim().isEmpty() && ubicacionCotizante.trim().length() == 5){
				String dpto = ubicacionCotizante.substring(0, 2);
				String municipio = ubicacionCotizante.substring(2, 5);
				lineaRegistroTipo2.setDepartamento(dpto);
				lineaRegistroTipo2.setMunicipio(DataTypeConverter.convert(municipio,Integer.class));
			}else{
				lineaRegistroTipo2.setDepartamento("0");
				lineaRegistroTipo2.setMunicipio(0);
			}
			
			lineaRegistroTipo2.setPrimerApellido(ld.getPrimerApellido());
			lineaRegistroTipo2.setSegundoApellido(ld.getSegundoApellido());
			lineaRegistroTipo2.setPrimerNombre(ld.getPrimerNombre());
			lineaRegistroTipo2.setSegundoNombre(ld.getSegundoNombre());
			//XXX Primer Apellido Causante
			//XXX Segundo Apellido Causante
			//XXX Primer Nombre Causante
			//XXX Segundo Nombre Causante
			//XXX Pensionado Cuyo pago es exterior
			lineaRegistroTipo2.setIng(DataTypeConverter.convert(ld.getNovedadIngreso(),Character.class));
			lineaRegistroTipo2.setRet(DataTypeConverter.convert(ld.getNovedadRetiro(),Character.class));
			lineaRegistroTipo2.setTde(DataTypeConverter.convert(ld.getNovedadTrasladoEpsDesde(),Character.class));
			lineaRegistroTipo2.setTae(DataTypeConverter.convert(ld.getNovedadTrasladoEpsA(),Character.class));
			lineaRegistroTipo2.setTdp(DataTypeConverter.convert(ld.getNovedadTrasladoAfpDesde(),Character.class));
			lineaRegistroTipo2.setTap(DataTypeConverter.convert(ld.getNovedadTrasladoAfpA(),Character.class));
			lineaRegistroTipo2.setVsp(DataTypeConverter.convert(ld.getNovedadVariacionSalarioPermanente(),Character.class));
			lineaRegistroTipo2.setCorrecciones(DataTypeConverter.convert(ld.getNovedadCorreccion(),Character.class));
			lineaRegistroTipo2.setVst(DataTypeConverter.convert(ld.getNovedadVariacionSalarioTransitoria(),Character.class));
			lineaRegistroTipo2.setSln(DataTypeConverter.convert(ld.getNovedadSuspensionTemporalContrato(),Character.class));
			lineaRegistroTipo2.setIge(DataTypeConverter.convert(ld.getNovedadIncapacidadEnfermedadGeneral(),Character.class));
			lineaRegistroTipo2.setLma(DataTypeConverter.convert(ld.getNovedadLicenciaMaternidad(),Character.class));
			lineaRegistroTipo2.setVac(DataTypeConverter.convert(ld.getNovedadVacaciones(),Character.class));
			lineaRegistroTipo2.setAvp(DataTypeConverter.convert(ld.getNovedadAporteVoluntarioPensiones(),Character.class));
			lineaRegistroTipo2.setVct(DataTypeConverter.convert(ld.getNovedadVariacionCentrosTrabajo(),Character.class));
			lineaRegistroTipo2.setIrp(DataTypeConverter.convert(ld.getDiasNovedadIncapacidadRiesgosProfesionales(),Integer.class));
			//XXX SUS (exclusiva de pensionados)
			lineaRegistroTipo2.setAfp(ld.getCodigoAdmPension());
			//XXX Codigo Adm Pension Traslado
			lineaRegistroTipo2.setEps(ld.getCodigoAdmSalud());
			//XXX Codigo Adm Salud Traslado
			lineaRegistroTipo2.setCcf(ld.getCodigoAdmCajas());
			lineaRegistroTipo2.setDiasAfp(DataTypeConverter.convert(ld.getDiasCotizadosPension(),Integer.class));
			lineaRegistroTipo2.setDiasEps(DataTypeConverter.convert(ld.getDiasCotizadosSalud(),Integer.class));
			lineaRegistroTipo2.setDiasArp(DataTypeConverter.convert(ld.getDiasCotizadosRiesgos(),Integer.class));
			lineaRegistroTipo2.setDiasccf(DataTypeConverter.convert(ld.getDiasCotizadosCajas(),Integer.class));
			//XXX Nro Dias FSP
			//Salario Básico/XXX OJO Mesada Pensional
			lineaRegistroTipo2.setSalarioBasico(DataTypeConverter.convert(ld.getSalarioBasico(),Long.class));
			lineaRegistroTipo2.setSalarioIntegral(ld.getSalarioIntegral());
			lineaRegistroTipo2.setIbcAfp(DataTypeConverter.convert(ld.getIbcPension(),Long.class));
			lineaRegistroTipo2.setIbcEps(DataTypeConverter.convert(ld.getIbcSalud(),Long.class));
			lineaRegistroTipo2.setIbcArp(DataTypeConverter.convert(ld.getIbcRiesgos(),Long.class));
			lineaRegistroTipo2.setIbcCcf(DataTypeConverter.convert(ld.getIbcCajas(),Long.class));
			lineaRegistroTipo2.setTarifaAfp(Double.valueOf(ld.getTarifaPension()));
			lineaRegistroTipo2.setCotizacionObligatoriaAfp(DataTypeConverter.convert(ld.getCotizacionPension(),Long.class));
			lineaRegistroTipo2.setAporteVoluntarioAfiliadoAfp( DataTypeConverter.convert(ld.getCotizacionVoluntariaPensionAfiliado(),Long.class));
			lineaRegistroTipo2.setAporteVoluntarioAportanteAfp( DataTypeConverter.convert(ld.getCotizacionVoluntariaPensionAportante(),Long.class));
			lineaRegistroTipo2.setTotalCotizacionAFP(DataTypeConverter.convert(ld.getTotalCotizacionPension(),Long.class));
			lineaRegistroTipo2.setAportesFSPsolidaridad(DataTypeConverter.convert(ld.getAporteFondoSolidaridadPensionalSubcuentaSolidaridad(),Long.class));
			lineaRegistroTipo2.setAportesFSPsubsistencia(DataTypeConverter.convert(ld.getAporteFondoSolidaridadPensionalSubcuentaSubsistencia(),Long.class));
			lineaRegistroTipo2.setValorNoRetenidoApteVoluntario(DataTypeConverter.convert(ld.getValorNoRetenidoAportesVoluntarios(),Long.class));
			lineaRegistroTipo2.setTarifaEps(DataTypeConverter.convert(ld.getTarifaSalud(),Double.class));
			lineaRegistroTipo2.setCotizacionObligatoriaEps(DataTypeConverter.convert(ld.getCotizacionSalud(),Long.class));
			lineaRegistroTipo2.setValorUPCAdicional(DataTypeConverter.convert(ld.getValorUpcAdicional(),Long.class));
			lineaRegistroTipo2.setTarifaArp(Double.valueOf(ld.getTarifaRiesgos()));
			lineaRegistroTipo2.setCodigoCT(DataTypeConverter.convert(ld.getCodigoCentroTrabajo(),Integer.class));
			lineaRegistroTipo2.setCotizacionObligatoriaArp(DataTypeConverter.convert(ld.getCotizacionRiesgos(),Long.class));
			lineaRegistroTipo2.setTarifaCCF(Double.valueOf(ld.getTarifaCajas()));
			lineaRegistroTipo2.setAporteCCF(DataTypeConverter.convert(ld.getCotizacionCajas(),Long.class));
			lineaRegistroTipo2.setTarifaSENA(Double.valueOf(ld.getTarifaSena() ));
			lineaRegistroTipo2.setAporteSENA(DataTypeConverter.convert(ld.getCotizacionSena(),Long.class));
			lineaRegistroTipo2.setTarifaICBF(Double.valueOf(ld.getTarifaIcbf() ));
			lineaRegistroTipo2.setAporteICBF(DataTypeConverter.convert(ld.getCotizacionIcbf(),Long.class));
			lineaRegistroTipo2.setTarifaESAP(Double.valueOf(ld.getTarifaEsap() ));
			lineaRegistroTipo2.setAporteESAP(DataTypeConverter.convert(ld.getCotizacionEsap(),Long.class));
			lineaRegistroTipo2.setTarifaMEN(Double.valueOf(ld.getTarifaMinedu() ));
			lineaRegistroTipo2.setAporteMEN(DataTypeConverter.convert(ld.getCotizacionMinedu(),Long.class));
			lineaRegistroTipo2.setTipoDocumentoBeneficiarioUPC(ld.getTipoIdentificacionUPC());
			lineaRegistroTipo2.setNumeroDocumentoBeneficiarioUPC(ld.getNroIdentificacionUPC());
			lineaRegistroTipo2.setExoParafSalud(DataTypeConverter.convert(ld.getCotizanteExoneradoLey1607(),Character.class));
			lineaRegistroTipo2.setCodigoARLResolucion2087(ld.getCodigoAdmRiesgos());
			lineaRegistroTipo2.setClaseRiesgoResolucion2087(ld.getClaseRiesgo() );
			lineaRegistroTipo2.setIndicadorTarifaPensiones(ld.getIndicadorTarifaEspecialPensiones());
			lineaRegistroTipo2.setFechaIngreso(DateUtils.convertStringToDate(ld.getNovedadIngresoFecha()));
			lineaRegistroTipo2.setFechaRetiro(DateUtils.convertStringToDate(ld.getNovedadRetiroFecha()));
			lineaRegistroTipo2.setFechaInicioVSP(DateUtils.convertStringToDate(ld.getNovedadVariacionSalarioPermanenteFecha() ));
			lineaRegistroTipo2.setFechaInicioSLN(DateUtils.convertStringToDate(ld.getNovedadSuspensionFechaInicio() ));
			lineaRegistroTipo2.setFechaFinSLN(DateUtils.convertStringToDate(ld.getNovedadSuspensionFechaFin()));
			lineaRegistroTipo2.setFechaInicioIGE(DateUtils.convertStringToDate(ld.getNovedadIncapacidadEnfermedadGeneralFechaInicio() ));
			lineaRegistroTipo2.setFechaFinIGE(DateUtils.convertStringToDate(ld.getNovedadIncapacidadEnfermedadGeneralFechaFin()));
			lineaRegistroTipo2.setFechaInicioLMA(DateUtils.convertStringToDate(ld.getNovedadLicenciaMaternidadFechaInicio() ));
			lineaRegistroTipo2.setFechaFinLMA(DateUtils.convertStringToDate(ld.getNovedadLicenciaMaternidadFechaFin()));
			lineaRegistroTipo2.setFechaInicioVAC(DateUtils.convertStringToDate(ld.getNovedadVacacionesFechaInicio() ));
			lineaRegistroTipo2.setFechaFinVAC(DateUtils.convertStringToDate(ld.getNovedadVacacionesFechaFin()));
			lineaRegistroTipo2.setFechaInicioVCT(DateUtils.convertStringToDate(ld.getNovedadVariacionCentrosTrabajoFechaInicio() ));
			lineaRegistroTipo2.setFechaFinVCT(DateUtils.convertStringToDate(ld.getNovedadVariacionCentrosTrabajoFechaFin() ));
			lineaRegistroTipo2.setFechaInicioIRL(DateUtils.convertStringToDate(ld.getNovedadIncapacidadRiesgosLaboralesFechaInicio() ));
			lineaRegistroTipo2.setFechaFinIRL(DateUtils.convertStringToDate(ld.getNovedadIncapacidadRiesgosLaboralesFechaFin() ));
			lineaRegistroTipo2.setIbcOtrosParafiscales(DataTypeConverter.convert(ld.getIbcOtrosParafiscales(),Long.class));
			lineaRegistroTipo2.setFechaRadicacionExterior(DateUtils.convertStringToDate(ld.getFechaRadicacionExterior()));
			lineaRegistroTipo2.llenarNovedades();
			
			return lineaRegistroTipo2;
		};
		
		return liquidacionDetalladaList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo2>toList());
	}
	
	//###########
	//PENSIONADOS
	//###########
	//ENCABEZADO(1)
	public LineaRegistroTipo1Pensionado convertMinpsPensionadosEncabezado2LineaRegistroTipo1(MinpsEncabezadoOut encabezado){
		
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = new LineaRegistroTipo1Pensionado();
		lineaRegistroTipo1.setTipoArchivo(TipoArchivo.MINPS_PENSIONADOS);
		lineaRegistroTipo1.setIndiceLinea(encabezado.getLineNumber());
		
		//FIELDS
		//XXX Tipo de Registro
		lineaRegistroTipo1.setFechaPagoPlanilla(encabezado.getFechaPago());		
		//XXX Número de registros en el archivo
		lineaRegistroTipo1.setCodigoOperador(DataTypeConverter.convert(encabezado.getCodigoOperador(),Integer.class));
		lineaRegistroTipo1.setNumeroPensionados(DataTypeConverter.convert(encabezado.getNroTotalEmpleados(),Integer.class));
		lineaRegistroTipo1.setNumeroPlanilla(encabezado.getNroPlanilla());
//NOT USED		lineaRegistroTipo1.setModalidadPlanilla(DataTypeConverter.convert(encabezado.getModalidadPlanilla(),Integer.class));
		lineaRegistroTipo1.setTipoPlanilla(encabezado.getTipoPlanilla());
		lineaRegistroTipo1.setRazonSocial(encabezado.getRazonSocialAportante());
		lineaRegistroTipo1.setTipoDocumento(encabezado.getTipoIdentificacionAportante());
		lineaRegistroTipo1.setNumeroDocumento(encabezado.getNroIdentificacionAportante());
		Integer digitoVerificacion = DataTypeConverter.convert(encabezado.getDvAportante(),Integer.class);
		lineaRegistroTipo1.setDigitoVerificacion(digitoVerificacion);
		lineaRegistroTipo1.setCodigoSucursal(encabezado.getCodigoSucursal());
		lineaRegistroTipo1.setNombreSucursal(encabezado.getNombreSucursal());
		//Clase de Aportante (ONLY RegistroAportante)
		//Naturaleza Juridica(ONLY RegistroAportante y el atributo es TipoEntidad)
		//Tipo de Persona (ONLY RegistroAportante)
		lineaRegistroTipo1.setFormaPresentacion(encabezado.getFormaPresentacion());
		//Direccion de Correspondencia (ONLY RegistroAportante)
		//Ubicación del aportante (ONLY RegistroAportante)
		//XXX Codigo DANE o Actividad Economica
		//Teléfono (ONLY RegistroAportante)
		//Fax (ONLY RegistroAportante)
		//Correo Electronico (ONLY RegistroAportante)
		//Nro Identificacion del representante legal (ONLY RegistroAportante)
		//Digito Verificacion del representante legal (ONLY RegistroAportante)
		//Tipo Identificacion Representante legal (ONLY RegistroAportante)
		//primer apellido del representante legal (ONLY RegistroAportante)
		//segundo apellido del representante legal (ONLY RegistroAportante)
		//primer nombre del representante legal (ONLY RegistroAportante)
		//segundo nombre del representante legal (ONLY RegistroAportante)
		//Fecha inicio concordato (ONLY RegistroAportante)
		//tipo de accion (ONLY RegistroAportante)
		//fecha en que termino actividades comerciales (ONLY RegistroAportante)
		lineaRegistroTipo1.setTipoPagador(DataTypeConverter.convert(encabezado.getTipoAportante(),Integer.class));
		//Fecha matricula mercantil (ONLY RegistroAportante)
		//Codigo DPTO, matricula mercantil (ONLY RegistroAportante)
		//Aportante exonerado pago salud y parafiscales (ley 1607) (ONLY RegistroAportante)
		//Aportante se acoge a beneficios ley 1429 (cajas) (ONLY RegistroAportante)
//NOT USED		lineaRegistroTipo1.setNumeroPlanillaAsociada(encabezado.getNroPlanillaAsociada());
//NOT USED		lineaRegistroTipo1.setFechaPagoPlanillaAsociada(encabezado.getFechaPagoPlanillaAsociada());
//NOT USED		lineaRegistroTipo1.setCodigoARP(encabezado.getCodigoAdmRiesgos());
		lineaRegistroTipo1.setPeriodoPension(DateUtils.convertString2YearMonth(encabezado.getPeriodoPagoPension(),"yyyy-MM") );
		lineaRegistroTipo1.setPeriodoSalud(DateUtils.convertString2YearMonth(encabezado.getPeriodoPagoSalud(),"yyyy-MM") );
		//XXX Días de mora
		//XXX Nro total de empleados/pensionados
		lineaRegistroTipo1.setValorNomina(DataTypeConverter.convert(encabezado.getValorNomina(),Long.class));
		//XXX Identificador, encabezado.getIdentificador();
		
		
		//POJO RegistroAportante
		RegistroAportante registroAportante = new RegistroAportante();
		registroAportante.setRazonSocial(encabezado.getRazonSocialAportante());
		registroAportante.setTipoIdentificacion(encabezado.getTipoIdentificacionAportante());
		registroAportante.setNumeroIdentificacion(encabezado.getNroIdentificacionAportante());
		registroAportante.setDigitoVerificacion(digitoVerificacion);
		registroAportante.setSucursal(encabezado.getCodigoSucursal());
		registroAportante.setNombreSucursal(encabezado.getNombreSucursal());
		registroAportante.setFormaPresentacion(DataTypeConverter.convert(encabezado.getFormaPresentacion(),Character.class));
		registroAportante.setClase(DataTypeConverter.convert(encabezado.getClaseAportante(),Character.class));
		registroAportante.setTipoPersona(DataTypeConverter.convert(encabezado.getTipoPersona(),Character.class));
		registroAportante.setDireccion(encabezado.getDireccionCorrespondencia());
		
		String ubicacionAportante = encabezado.getUbicacionAportante();
		registroAportante.setDepartamento(DataTypeConverter.convert(ubicacionAportante.substring(0,2),Integer.class));
		registroAportante.setMunicipio(DataTypeConverter.convert(ubicacionAportante.substring(2),Integer.class));
		
		registroAportante.setTelefono(encabezado.getTelefono());
		registroAportante.setFax(encabezado.getFax());
		registroAportante.setCorreo(encabezado.getCorreoElectronico());
		registroAportante.setNumeroIDRep(encabezado.getNroIdentificacionRepLegal());
		registroAportante.setDigitoVerificacionRep(DataTypeConverter.convert(encabezado.getDvRepLegal(),Character.class));
		registroAportante.setTipoIDRep(encabezado.getTipoIdentificacionRepLegal());
		registroAportante.setPApellidoRep(encabezado.getPrimerApellidoRepLegal());
		registroAportante.setSApellidoRep(encabezado.getSegundoApellidoRepLegal());
		registroAportante.setPNombreRep(encabezado.getPrimerNombreRepLegal());
		registroAportante.setSNombreRep(encabezado.getSegundoNombreRepLegal());
		registroAportante.setFechaInicioConcordato(encabezado.getFechaConcordato());
		registroAportante.setTipoAccion(DataTypeConverter.convert(encabezado.getTipoAccion(),Integer.class));
		registroAportante.setFechaFinActividades(encabezado.getFechaFinActividades());
		registroAportante.setTipoAportante(DataTypeConverter.convert(encabezado.getTipoAportante(),Integer.class));
		registroAportante.setFechaMatriculaMercantil(encabezado.getFechaMatriculaMercantil());
		registroAportante.setDepartamentoMM(DataTypeConverter.convert(encabezado.getDepartamentoMatriculaMercantil(),Integer.class));
		registroAportante.setExoParafSalud(encabezado.getExoneradoPagoParafiscalesSalud());
		registroAportante.setBen1429CCF(DataTypeConverter.convert(encabezado.getBeneficiarioLey1429(),Boolean.class));
		registroAportante.setTipoEntidad(DataTypeConverter.convert(encabezado.getNaturalezaJuridica(),Integer.class));
		registroAportante.setFechaUltimaActualizacion(DateUtils.convertStringToDate(encabezado.getFechaUltimaActualizacion()));
		
		lineaRegistroTipo1.setRegistroAportante(registroAportante);
		
		return lineaRegistroTipo1;
	}
	
	//LIQUIDACION_DETALLADA(2)
	public List<LineaRegistroTipo2Pensionado> convertMinpsPensionadosLiquidacionDetallada2LineaRegistroTipo2List(
			List<MinpsLiquidacionDetalladaOut> liquidacionDetalladaList){
		
		Function<MinpsLiquidacionDetalladaOut,LineaRegistroTipo2Pensionado> mapper = ld -> {
			
			LineaRegistroTipo2Pensionado lineaRegistroTipo2 = new LineaRegistroTipo2Pensionado();
			lineaRegistroTipo2.setTipoArchivo(TipoArchivo.MINPS_PENSIONADOS);
			lineaRegistroTipo2.setIndiceLinea(ld.getLineNumber());
			
			//XXX Tipo de Registro
			//XXX Nro del Registro
			//XXX Codigo Operador
			//XXX Nro de la planilla
			lineaRegistroTipo2.setTipoDocumento(ld.getTipoIdentificacion());
			lineaRegistroTipo2.setNumeroDocumento(ld.getNroIdentificacion());
			lineaRegistroTipo2.setTipoPensionado(DataTypeConverter.convert(ld.getTipoCotizante(),Integer.class));
//NOT USED			lineaRegistroTipo2.setSubTipoCotizante(DataTypeConverter.convert(ld.getSubtipoCotizante(),Integer.class));
			
			lineaRegistroTipo2.setTipoPensionado(DataTypeConverter.convert(ld.getTipoPensionado(),Integer.class));
			lineaRegistroTipo2.setTipoPension(DataTypeConverter.convert(ld.getTipoPension(),Integer.class));
			lineaRegistroTipo2.setPensionCompartida(ld.getPensionCompartida());
//NOT USED			lineaRegistroTipo2.setExtranjeroNoObligadoCotizar(ld.getExtranjeroNoObligadoCotPension());
			lineaRegistroTipo2.setPensionadoExterior(ld.getColombianoResidenteExterior());
			
			lineaRegistroTipo2.setTipoDocumentoCausante(ld.getTipoIdentificacionCausante());
			lineaRegistroTipo2.setNumeroDocumentoCausante(ld.getNroIdentificacionCausante());
			
			//XXX Ubicacion del Cotizante (ojo este dato viene concatenado) ld.getUbicacionCotizante();			
			String ubicacionCotizante = ld.getUbicacionCotizante();
			if(!ubicacionCotizante.trim().isEmpty() && ubicacionCotizante.trim().length() == 5){
				String dpto = ubicacionCotizante.substring(0, 2);
				String municipio = ubicacionCotizante.substring(2, 5);
				lineaRegistroTipo2.setDepartamento(dpto);
				lineaRegistroTipo2.setMunicipio(municipio);
			}else{
				lineaRegistroTipo2.setDepartamento("0");
				lineaRegistroTipo2.setMunicipio("0");
			}
			
			lineaRegistroTipo2.setPrimerApellido(ld.getPrimerApellido());
			lineaRegistroTipo2.setSegundoApellido(ld.getSegundoApellido());
			lineaRegistroTipo2.setPrimerNombre(ld.getPrimerNombre());
			lineaRegistroTipo2.setSegundoNombre(ld.getSegundoNombre());
			
			lineaRegistroTipo2.setPrimerApellidoCausante(ld.getPrimerApellidoCausante());
			lineaRegistroTipo2.setSegundoApellidoCausante(ld.getSegundoApellidoCausante());
			lineaRegistroTipo2.setPrimerNombreCausante(ld.getPrimerNombreCausante());
			lineaRegistroTipo2.setSegundoNombreCausante(ld.getSegundoNombreCausante());
			
			lineaRegistroTipo2.setPensionadoPagoExterior(ld.getPensionadoPagoExterior());
			lineaRegistroTipo2.setIng(DataTypeConverter.convert(ld.getNovedadIngreso(),Character.class));
			lineaRegistroTipo2.setRet(DataTypeConverter.convert(ld.getNovedadRetiro(),Character.class));
			lineaRegistroTipo2.setTde(DataTypeConverter.convert(ld.getNovedadTrasladoEpsDesde(),Character.class));
			lineaRegistroTipo2.setTae(DataTypeConverter.convert(ld.getNovedadTrasladoEpsA(),Character.class));
			lineaRegistroTipo2.setTdp(DataTypeConverter.convert(ld.getNovedadTrasladoAfpDesde(),Character.class));
			lineaRegistroTipo2.setTap(DataTypeConverter.convert(ld.getNovedadTrasladoAfpA(),Character.class));
			lineaRegistroTipo2.setVsp(DataTypeConverter.convert(ld.getNovedadVariacionSalarioPermanente(),Character.class));
//NOT USED			lineaRegistroTipo2.setCorrecciones(DataTypeConverter.convert(ld.getNovedadCorreccion(),Character.class));
//NOT USED			lineaRegistroTipo2.setVst(DataTypeConverter.convert(ld.getNovedadVariacionSalarioTransitoria(),Character.class));
//NOT USED			lineaRegistroTipo2.setSln(DataTypeConverter.convert(ld.getNovedadSuspensionTemporalContrato(),Character.class));
//NOT USED			lineaRegistroTipo2.setIge(DataTypeConverter.convert(ld.getNovedadIncapacidadEnfermedadGeneral(),Character.class));
//NOT USED			lineaRegistroTipo2.setLma(DataTypeConverter.convert(ld.getNovedadLicenciaMaternidad(),Character.class));
//NOT USED			lineaRegistroTipo2.setVac(DataTypeConverter.convert(ld.getNovedadVacaciones(),Character.class));
//NOT USED			lineaRegistroTipo2.setAvp(DataTypeConverter.convert(ld.getNovedadAporteVoluntarioPensiones(),Character.class));
//NOT USED			lineaRegistroTipo2.setVct(DataTypeConverter.convert(ld.getNovedadVariacionCentrosTrabajo(),Character.class));
//NOT USED			lineaRegistroTipo2.setIrp(DataTypeConverter.convert(ld.getDiasNovedadIncapacidadRiesgosProfesionales(),Integer.class));
			lineaRegistroTipo2.setSus(DataTypeConverter.convert(ld.getNovedadSuspension(),Character.class));
			lineaRegistroTipo2.setAfp(ld.getCodigoAdmPension());
			//XXX Codigo Adm Pension Traslado
			lineaRegistroTipo2.setEps(ld.getCodigoAdmSalud());
			//XXX Codigo Adm Salud Traslado
			lineaRegistroTipo2.setCcf(ld.getCodigoAdmCajas());
			lineaRegistroTipo2.setDiasAfp(DataTypeConverter.convert(ld.getDiasCotizadosPension(),Integer.class));
			lineaRegistroTipo2.setDiasEps(DataTypeConverter.convert(ld.getDiasCotizadosSalud(),Integer.class));
//NOT USED			lineaRegistroTipo2.setDiasArp(DataTypeConverter.convert(ld.getDiasCotizadosRiesgos(),Integer.class));
			lineaRegistroTipo2.setDiasCcf(DataTypeConverter.convert(ld.getDiasCotizadosCajas(),Integer.class));
			lineaRegistroTipo2.setDiasFsp(DataTypeConverter.convert(ld.getDiasCotizadosFsp(),Integer.class));
			//Salario Básico/XXX OJO Mesada Pensional
			lineaRegistroTipo2.setMesadaPensional(DataTypeConverter.convert(ld.getSalarioBasico(),Long.class));
//NOT USED			lineaRegistroTipo2.setSalarioIntegral(ld.getSalarioIntegral());
//XXX OJO NO SE ESTA DISCRIMIANDO IBC de los diferentes subsistemas
//			lineaRegistroTipo2.setIbcAf(DataTypeConverter.convert(ld.getIbcPension(),Long.class));
			lineaRegistroTipo2.setIbc(DataTypeConverter.convert(ld.getIbcSalud(),Long.class));
//			lineaRegistroTipo2.setIbcArp(DataTypeConverter.convert(ld.getIbcRiesgos(),Long.class));
//			lineaRegistroTipo2.setIbcCcf(DataTypeConverter.convert(ld.getIbcCajas(),Long.class));
			lineaRegistroTipo2.setTarifaAfp(Double.valueOf(ld.getTarifaPension()));
			lineaRegistroTipo2.setCotizacionObligatoriaAfp(DataTypeConverter.convert(ld.getCotizacionPension(),Long.class));
			lineaRegistroTipo2.setAporteVoluntarioAfiliadoAfp( DataTypeConverter.convert(ld.getCotizacionVoluntariaPensionAfiliado(),Long.class));
//NOT USED			lineaRegistroTipo2.setAporteVoluntarioAportanteAfp( DataTypeConverter.convert(ld.getCotizacionVoluntariaPensionAportante(),Long.class));
			lineaRegistroTipo2.setTotalCotizacionAFP(DataTypeConverter.convert(ld.getTotalCotizacionPension(),Long.class));
			lineaRegistroTipo2.setAportesFSPsolidaridad(DataTypeConverter.convert(ld.getAporteFondoSolidaridadPensionalSubcuentaSolidaridad(),Long.class));
			lineaRegistroTipo2.setAportesFSPsubsistencia(DataTypeConverter.convert(ld.getAporteFondoSolidaridadPensionalSubcuentaSubsistencia(),Long.class));
			lineaRegistroTipo2.setValorNoRetenidoApteVoluntario(DataTypeConverter.convert(ld.getValorNoRetenidoAportesVoluntarios(),Long.class));
			lineaRegistroTipo2.setTarifaEps(DataTypeConverter.convert(ld.getTarifaSalud(),Double.class));
			lineaRegistroTipo2.setCotizacionObligatoriaEps(DataTypeConverter.convert(ld.getCotizacionSalud(),Long.class));
			lineaRegistroTipo2.setValorUPCAdicional(DataTypeConverter.convert(ld.getValorUpcAdicional(),Long.class));
//NOT USED			lineaRegistroTipo2.setTarifaArp(Double.valueOf(ld.getTarifaRiesgos()));
//NOT USED			lineaRegistroTipo2.setCodigoCT(DataTypeConverter.convert(ld.getCodigoCentroTrabajo(),Integer.class));
//NOT USED			lineaRegistroTipo2.setCotizacionObligatoriaArp(DataTypeConverter.convert(ld.getCotizacionRiesgos(),Long.class));
			lineaRegistroTipo2.setTarifaCCF(Double.valueOf(ld.getTarifaCajas()));
			lineaRegistroTipo2.setAporteCCF(DataTypeConverter.convert(ld.getCotizacionCajas(),Long.class));
//NOT USED			lineaRegistroTipo2.setTarifaSENA(Double.valueOf(ld.getTarifaSena() ));
//NOT USED			lineaRegistroTipo2.setAporteSENA(DataTypeConverter.convert(ld.getCotizacionSena(),Long.class));
//NOT USED			lineaRegistroTipo2.setTarifaICBF(Double.valueOf(ld.getTarifaIcbf() ));
//NOT USED			lineaRegistroTipo2.setAporteICBF(DataTypeConverter.convert(ld.getCotizacionIcbf(),Long.class));
//NOT USED			lineaRegistroTipo2.setTarifaESAP(Double.valueOf(ld.getTarifaEsap() ));
//NOT USED			lineaRegistroTipo2.setAporteESAP(DataTypeConverter.convert(ld.getCotizacionEsap(),Long.class));
//NOT USED			lineaRegistroTipo2.setTarifaMEN(Double.valueOf(ld.getTarifaMinedu() ));
//NOT USED			lineaRegistroTipo2.setAporteMEN(DataTypeConverter.convert(ld.getCotizacionMinedu(),Long.class));
			//XXX Tipo Identificacion Cotizante Principal
			//XXX Nro Identificacion Cotizante Principal
//NOT USED			lineaRegistroTipo2.setExoParafSalud(DataTypeConverter.convert(ld.getCotizanteExoneradoLey1607(),Character.class));
//NOT USED			lineaRegistroTipo2.setCodigoARLResolucion2087(ld.getCodigoAdmRiesgos());
//NOT USED			lineaRegistroTipo2.setClaseRiesgoResolucion2087(ld.getClaseRiesgo() );
//NOT USED			lineaRegistroTipo2.setIndicadorTarifaPensiones(ld.getIndicadorTarifaEspecialPensiones());
			lineaRegistroTipo2.setFechaIngreso(DateUtils.convertStringToDate(ld.getNovedadIngresoFecha()));
			lineaRegistroTipo2.setFechaRetiro(DateUtils.convertStringToDate(ld.getNovedadRetiroFecha()));
			lineaRegistroTipo2.setFechaInicioVSP(DateUtils.convertStringToDate(ld.getNovedadVariacionSalarioPermanenteFecha() ));
//NOT USED			lineaRegistroTipo2.setFechaInicioSLN(DateUtils.convertStringToDate(ld.getNovedadSuspensionFechaInicio() ));
//NOT USED			lineaRegistroTipo2.setFechaFinSLN(DateUtils.convertStringToDate(ld.getNovedadSuspensionFechaFin()));
//NOT USED			lineaRegistroTipo2.setFechaInicioIGE(DateUtils.convertStringToDate(ld.getNovedadIncapacidadEnfermedadGeneralFechaInicio() ));
//NOT USED			lineaRegistroTipo2.setFechaFinIGE(DateUtils.convertStringToDate(ld.getNovedadIncapacidadEnfermedadGeneralFechaFin()));
//NOT USED			lineaRegistroTipo2.setFechaInicioLMA(DateUtils.convertStringToDate(ld.getNovedadLicenciaMaternidadFechaInicio() ));
//NOT USED			lineaRegistroTipo2.setFechaFinLMA(DateUtils.convertStringToDate(ld.getNovedadLicenciaMaternidadFechaFin()));
//NOT USED			lineaRegistroTipo2.setFechaInicioVAC(DateUtils.convertStringToDate(ld.getNovedadVacacionesFechaInicio() ));
//NOT USED			lineaRegistroTipo2.setFechaFinVAC(DateUtils.convertStringToDate(ld.getNovedadVacacionesFechaFin()));
//NOT USED			lineaRegistroTipo2.setFechaInicioVCT(DateUtils.convertStringToDate(ld.getNovedadVariacionCentrosTrabajoFechaInicio() ));
//NOT USED			lineaRegistroTipo2.setFechaFinVCT(DateUtils.convertStringToDate(ld.getNovedadVariacionCentrosTrabajoFechaFin() ));
//NOT USED			lineaRegistroTipo2.setFechaInicioIRL(DateUtils.convertStringToDate(ld.getNovedadIncapacidadRiesgosLaboralesFechaInicio() ));
//NOT USED			lineaRegistroTipo2.setFechaFinIRL(DateUtils.convertStringToDate(ld.getNovedadIncapacidadRiesgosLaboralesFechaFin() ));
//NOT USED			lineaRegistroTipo2.setIbcOtrosParafiscales(DataTypeConverter.convert(ld.getIbcOtrosParafiscales(),Long.class));
			lineaRegistroTipo2.setFechaRadicacionExterior(DateUtils.convertStringToDate(ld.getFechaRadicacionExterior()));
			
			return lineaRegistroTipo2;
		};
		
		return liquidacionDetalladaList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo2Pensionado>toList());
	}
		
	
	//#########################################
	//TOTALES aplica para ACTIVOS y PENSIONADOS
	//#########################################
	
	//TOTALES - PENSION(3)
	public List<LineaRegistroTipo3> convertMinpsTotalesPension2LineaRegistroTipo3(
			List<MinpsTotalesRegistroTipo3PensionOut> totalesPensionList){

		Function<MinpsTotalesRegistroTipo3PensionOut,LineaRegistroTipo3> mapper = total ->{
			LineaRegistroTipo3 lineaTotal = new LineaRegistroTipo3();
			
			lineaTotal.setIndiceLinea(total.getLineNumber());
			//XXX Tipo de Registro
			//XXX Número del registro
			//XXX Código de Operador
			//XXX Nro de la planilla
			lineaTotal.setAdministradora(total.getCodigoAdministradora());
			//XXX NIT Adm Pension
			//XXX Digito Verificacion Adm Pension
			lineaTotal.setTotalCotizacion(DataTypeConverter.convert(total.getCotizacion(),Long.class)); 
			lineaTotal.setTotalCotizacionVolAfiliado(DataTypeConverter.convert(total.getCotizacionVoluntariaAfiliado(),Long.class));
			lineaTotal.setTotalCotizacionVolAportante(DataTypeConverter.convert(total.getCotizacionVoluntariaAportante(),Long.class));
			lineaTotal.setTotalAporteSolidaridad(DataTypeConverter.convert(total.getAporteFondoSolidaridadPensionalSubcuentaSolidaridad(),Long.class));
			lineaTotal.setTotalAporteSusbsistencia(DataTypeConverter.convert(total.getAporteFondoSolidaridadPensionalSubcuentaSubsistencia(),Long.class));
			lineaTotal.setDiasMora(DataTypeConverter.convert(total.getDiasMora(),Long.class));
			lineaTotal.setInteresesCotizacion(DataTypeConverter.convert(total.getMoraCotizacion(),Long.class));
			lineaTotal.setInteresesSolidaridad(DataTypeConverter.convert(total.getMoraAporteFondoSolidaridadPensionalSubcuentaSolidaridad() ,Long.class));
			lineaTotal.setInteresesSubsistencia(DataTypeConverter.convert(total.getMoraAporteFondoSolidaridadPensionalSubcuentaSubsistencia() ,Long.class));
			lineaTotal.setTotalPago(DataTypeConverter.convert(total.getTotalPago(),Long.class));
			lineaTotal.setNumeroAFiliados(DataTypeConverter.convert(total.getNroAfiliados(),Integer.class));
			lineaTotal.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
			
			return lineaTotal;
		};
		
		return totalesPensionList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo3>toList());
	}
	
	//TOTALES - FSP(4)
	public LineaRegistroTipo4 convertMinpsTotalesFsp2LineaRegistroTipo4(
			MinpsTotalesRegistroTipo4FspOut totalesFsp){
		
		//BY-PASS
		if(totalesFsp == null){return null;}
		
		LineaRegistroTipo4 lineaRegistroTipo4 = new LineaRegistroTipo4();
		lineaRegistroTipo4.setTipoArchivo(TipoArchivo.MINPS_PENSIONADOS);
		lineaRegistroTipo4.setIndiceLinea(totalesFsp.getLineNumber());
		//XXX Tipo de Registro
		//XXX Número del registro
		//XXX Código de Operador
		//XXX Nro de la planilla
		//XXX Codigo Adm FSP
		//XXX NIT Adm FSP
		//XXX Digito Verificacion Adm FSP
		lineaRegistroTipo4.setTotalCotizacion(DataTypeConverter.convert(totalesFsp.getCotizacion(),Long.class));
		lineaRegistroTipo4.setDiasMora(DataTypeConverter.convert(totalesFsp.getDiasMora(),Long.class));
		lineaRegistroTipo4.setInteresesCotizacion(DataTypeConverter.convert(totalesFsp.getMoraCotizacion(),Long.class));

		return lineaRegistroTipo4;
	}
	
	//TOTALES - SALUD(5)
	public List<LineaRegistroTipo5> convertMinpsTotalesSalud2LineaRegistroTipo5(
			List<MinpsTotalesRegistroTipo5SaludOut> totalesSaludList){
		
		Function<MinpsTotalesRegistroTipo5SaludOut,LineaRegistroTipo5> mapper = total ->{
			LineaRegistroTipo5 lineaTotal = new LineaRegistroTipo5();
			
			lineaTotal.setIndiceLinea(total.getLineNumber());
			//XXX Tipo de Registro
			//XXX Número del registro
			//XXX Código de Operador
			//XXX Nro de la planilla
			lineaTotal.setAdministradora(total.getCodigoAdministradora());
			//XXX NIT Adm Salud
			//XXX Digito Verificacion Adm Salud
			lineaTotal.setTotalCotizacion(DataTypeConverter.convert(total.getCotizacion(),Long.class));
			lineaTotal.setTotalUPC(DataTypeConverter.convert(total.getUpcAdicional(),Long.class));
			lineaTotal.setDiasMora(DataTypeConverter.convert(total.getDiasMora(),Long.class));
			lineaTotal.setInteresesCotizacion(DataTypeConverter.convert(total.getMoraCotizacion(),Long.class));
			lineaTotal.setInteresesUPC(DataTypeConverter.convert(total.getMoraUpcAdicional(),Long.class));
			lineaTotal.setSubtotalCotizacion(DataTypeConverter.convert(total.getSubtotal(),Long.class));
			lineaTotal.setSubtotalUPC(DataTypeConverter.convert(total.getSubtotalUpc(),Long.class));
			lineaTotal.setTotalPago(DataTypeConverter.convert(total.getTotalPago(),Long.class));
			//XXX Nro de registros tipo 2 de la Administradora
			lineaTotal.setNumeroAfiliadosUPC(DataTypeConverter.convert(total.getNroAfiliadosUpcAdicionalAdministradora(),Long.class));
			lineaTotal.setNumeroAFiliados(DataTypeConverter.convert(total.getNroAfiliados(),Integer.class));
			lineaTotal.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
			
			return lineaTotal;
		};
		
		return totalesSaludList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo5>toList());
	}
	//TOTALES - RIESGOS(6)
	public List<LineaRegistroTipo6> convertMinpsTotalesRiesgos2LineaRegistroTipo6(
			List<MinpsTotalesRegistroTipo6RiesgosOut> totalesRiesgosList){
		
		Function<MinpsTotalesRegistroTipo6RiesgosOut,LineaRegistroTipo6> mapper = total ->{
			LineaRegistroTipo6 lineaTotal = new LineaRegistroTipo6();
			
			lineaTotal.setIndiceLinea(total.getLineNumber());
			//XXX Tipo de Registro
			//XXX Número del registro
			//XXX Código de Operador
			//XXX Nro de la planilla
			lineaTotal.setAdministradora(total.getCodigoAdministradora());
			//XXX NIT Adm Riesgos
			//XXX Digito Verificacion Adm Riesgos
			lineaTotal.setTotalCotizacion(DataTypeConverter.convert(total.getCotizacion(),Long.class));
			//XXX Nro Autorizacion pago de las IGE
			//XXX Valor Total IGE
			//XXX Valor Aportes pagados a otros sistemas
			//XXX Valor Neto
			lineaTotal.setDiasMora(DataTypeConverter.convert(total.getDiasMora(),Long.class));
			lineaTotal.setInteresesCotizacion(DataTypeConverter.convert(total.getMoraCotizacion(),Long.class));
			lineaTotal.setSubtotalCotizacion(DataTypeConverter.convert(total.getSubtotal(),Long.class));
			//XXX Nro Planilla incial Objeto Saldo a Favor
			//XXX Valor Saldo a Favor
			lineaTotal.setTotalPago(DataTypeConverter.convert(total.getTotalPago(),Long.class));
			//XXX Fondo de Riesgos Laborales
			lineaTotal.setNumeroAFiliados(DataTypeConverter.convert(total.getNroAfiliados(),Integer.class));
			lineaTotal.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
			
			return lineaTotal;
		};
		
		return totalesRiesgosList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo6>toList());
	}
	
	//TOTALES - CAJAS(7)
	public List<LineaRegistroTipo7> convertMinpsTotalesCajas2LineaRegistroTipo7(
			List<MinpsTotalesRegistroTipo7CajasOut> totalesCajasList){
		
		Function<MinpsTotalesRegistroTipo7CajasOut,LineaRegistroTipo7> mapper = total ->{
			LineaRegistroTipo7 lineaTotal = new LineaRegistroTipo7();
			
			lineaTotal.setIndiceLinea(total.getLineNumber());
			//XXX Tipo de Registro
			//XXX Número del registro
			//XXX Código de Operador
			//XXX Nro de la planilla
			lineaTotal.setAdministradora(total.getCodigoAdministradora());
			//XXX Nro Identificacion Adm Cajas
			//XXX Digito Verificacion Adm Cajas
			lineaTotal.setTotalCotizacion(DataTypeConverter.convert(total.getCotizacion(),Long.class));
			lineaTotal.setDiasMora(DataTypeConverter.convert(total.getDiasMora(),Long.class));
			lineaTotal.setInteresesCotizacion(DataTypeConverter.convert(total.getMoraCotizacion(),Long.class));
			lineaTotal.setTotalPago(DataTypeConverter.convert(total.getTotalPago(),Long.class));
			lineaTotal.setNumeroAFiliados(DataTypeConverter.convert(total.getNroAfiliados(),Integer.class));
			lineaTotal.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
			
			return lineaTotal;
		};
		
		return totalesCajasList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo7>toList());
	}
	
	//TOTALES - SENA(8)
	public LineaRegistroTipo8 convertMinpsTotalesSena2LineaRegistroTipo8(
			MinpsTotalesRegistroTipo8SenaOut totalesSena){
		
		//BY-PASS
		if(totalesSena == null){return null;}
		
		LineaRegistroTipo8 lineaRegistroTipo8 = new LineaRegistroTipo8();
		
		lineaRegistroTipo8.setIndiceLinea(totalesSena.getLineNumber());
		//XXX Tipo de Registro
		//XXX Número del registro
		//XXX Código de Operador
		//XXX Nro de la planilla
		//XXX Nro Identificacion SENA
		//XXX Digito Verificacion SENA
		
		lineaRegistroTipo8.setTotalCotizacion(DataTypeConverter.convert(totalesSena.getCotizacion(),Long.class));
		lineaRegistroTipo8.setDiasMora(DataTypeConverter.convert(totalesSena.getDiasMora(),Long.class));
		lineaRegistroTipo8.setInteresesCotizacion(DataTypeConverter.convert(totalesSena.getMoraCotizacion(),Long.class));
		lineaRegistroTipo8.setTotalPago(DataTypeConverter.convert(totalesSena.getTotalPago(),Long.class));
		lineaRegistroTipo8.setNumeroAFiliados(DataTypeConverter.convert(totalesSena.getNroAfiliados(),Integer.class));
		lineaRegistroTipo8.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
		
		return lineaRegistroTipo8;
		
	}
	
	//TOTALES - ICBF(9)
	public LineaRegistroTipo9 convertMinpsTotalesIcbf2LineaRegistroTipo9(
			MinpsTotalesRegistroTipo9IcbfOut totalesIcbf){
		
		//BY-PASS
		if(totalesIcbf == null){return null;}
		
		LineaRegistroTipo9 lineaRegistroTipo9 = new LineaRegistroTipo9();

		lineaRegistroTipo9.setIndiceLinea(totalesIcbf.getLineNumber());
		//XXX Tipo de Registro
		//XXX Número del registro
		//XXX Código de Operador
		//XXX Nro de la planilla
		//XXX Nro Identificacion ICBF
		//XXX Digito Verificacion ICBF
		
		lineaRegistroTipo9.setTotalCotizacion(DataTypeConverter.convert(totalesIcbf.getCotizacion(),Long.class));
		lineaRegistroTipo9.setDiasMora(DataTypeConverter.convert(totalesIcbf.getDiasMora(),Long.class));
		lineaRegistroTipo9.setInteresesCotizacion(DataTypeConverter.convert(totalesIcbf.getMoraCotizacion(),Long.class));
		lineaRegistroTipo9.setTotalPago(DataTypeConverter.convert(totalesIcbf.getTotalPago(),Long.class));
		lineaRegistroTipo9.setNumeroAFiliados(DataTypeConverter.convert(totalesIcbf.getNroAfiliados(),Integer.class));
		lineaRegistroTipo9.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
		
		return lineaRegistroTipo9;
	}
	
	//TOTALES - ESAP(10)
	public LineaRegistroTipo10 convertMinpsTotalesEsap2LineaRegistroTipo10(
			MinpsTotalesRegistroTipo10EsapOut totalesEsap){
		
		//BY-PASS
		if(totalesEsap == null){return null;}
		
		LineaRegistroTipo10 lineaRegistroTipo10 = new LineaRegistroTipo10();

		lineaRegistroTipo10.setIndiceLinea(totalesEsap.getLineNumber());
		//XXX Tipo de Registro
		//XXX Número del registro
		//XXX Código de Operador
		//XXX Nro de la planilla
		//XXX Nro Identificacion ESAP
		//XXX Digito Verificacion ESAP
		
		lineaRegistroTipo10.setTotalCotizacion(DataTypeConverter.convert(totalesEsap.getCotizacion(),Long.class));
		lineaRegistroTipo10.setDiasMora(DataTypeConverter.convert(totalesEsap.getDiasMora(),Long.class));
		lineaRegistroTipo10.setInteresesCotizacion(DataTypeConverter.convert(totalesEsap.getMoraCotizacion(),Long.class));
		lineaRegistroTipo10.setTotalPago(DataTypeConverter.convert(totalesEsap.getTotalPago(),Long.class));
		lineaRegistroTipo10.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
		
		return lineaRegistroTipo10;
	}
	
	//TOTALES - MINEDU(11)
	public LineaRegistroTipo11 convertMinpsTotalesMinedu2LineaRegistroTipo11(
			MinpsTotalesRegistroTipo11MineduOut totalesMinedu){
		
		//BY-PASS
		if(totalesMinedu == null){return null;}
		
		LineaRegistroTipo11 lineaRegistroTipo11 = new LineaRegistroTipo11();
		
		lineaRegistroTipo11.setIndiceLinea(totalesMinedu.getLineNumber());
		//XXX Tipo de Registro
		//XXX Número del registro
		//XXX Código de Operador
		//XXX Nro de la planilla
		//XXX Nro Identificacion ESAP
		//XXX Digito Verificacion ESAP
		lineaRegistroTipo11.setTotalCotizacion(DataTypeConverter.convert(totalesMinedu.getCotizacion(),Long.class));
		lineaRegistroTipo11.setDiasMora(DataTypeConverter.convert(totalesMinedu.getDiasMora(),Long.class));
		lineaRegistroTipo11.setInteresesCotizacion(DataTypeConverter.convert(totalesMinedu.getMoraCotizacion(),Long.class));
		lineaRegistroTipo11.setTotalPago(DataTypeConverter.convert(totalesMinedu.getTotalPago(),Long.class));
		lineaRegistroTipo11.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);

		return lineaRegistroTipo11;
	}
	
	//TOTALES - GRANTOTAL(12)
	public LineaRegistroTipo12 convertMinpsGranTotal2LineaRegistroTipo12(
			MinpsTotalesRegistroTipo12PlanillaOut totalesPlanilla){
		
		//BY-PASS
		if(totalesPlanilla == null){return null;}
		
		LineaRegistroTipo12 lineaRegistroTipo12 = new LineaRegistroTipo12();
		
		lineaRegistroTipo12.setIndiceLinea(totalesPlanilla.getLineNumber());
		//XXX Tipo de Registro
		//XXX Número del registro
		//XXX Código de Operador
		//XXX Nro de la planilla
		lineaRegistroTipo12.setTotalPagoAFP(DataTypeConverter.convert(totalesPlanilla.getTotalPension(),Long.class));
		lineaRegistroTipo12.setTotalPagoFSP(DataTypeConverter.convert(totalesPlanilla.getTotalFsp(),Long.class));
		lineaRegistroTipo12.setTotalPagoEPS(DataTypeConverter.convert(totalesPlanilla.getTotalSalud(),Long.class));
		lineaRegistroTipo12.setTotalPagoARL(DataTypeConverter.convert(totalesPlanilla.getTotalRiesgos(),Long.class));
		lineaRegistroTipo12.setTotalPagoCCF(DataTypeConverter.convert(totalesPlanilla.getTotalCajas(),Long.class));
		lineaRegistroTipo12.setTotalPagoSENA(DataTypeConverter.convert(totalesPlanilla.getTotalSena(),Long.class));
		lineaRegistroTipo12.setTotalPagoICBF(DataTypeConverter.convert(totalesPlanilla.getTotalIcbf(),Long.class));
		lineaRegistroTipo12.setTotalPagoESAP(DataTypeConverter.convert(totalesPlanilla.getTotalEsap(),Long.class));
		lineaRegistroTipo12.setTotalPagoMIN(DataTypeConverter.convert(totalesPlanilla.getTotalMinedu(),Long.class));
		lineaRegistroTipo12.setGranTotal(DataTypeConverter.convert(totalesPlanilla.getTotalPago(),Long.class));
		lineaRegistroTipo12.setNumeroAfiliadosAFP(DataTypeConverter.convert(totalesPlanilla.getNroAfiliadosPension(),Integer.class));
		lineaRegistroTipo12.setNumeroAfiliadosEPS(DataTypeConverter.convert(totalesPlanilla.getNroAfiliadosSalud(),Integer.class));
		lineaRegistroTipo12.setNumeroAfiliadosUPC(DataTypeConverter.convert(totalesPlanilla.getNroAfiliadosSaludUpcAdicional(),Integer.class));
		lineaRegistroTipo12.setNumeroAfiliadosARL(DataTypeConverter.convert(totalesPlanilla.getNroAfiliadosRiesgos(),Integer.class));
		lineaRegistroTipo12.setNumeroAfiliadosCCF(DataTypeConverter.convert(totalesPlanilla.getNroAfiliadosCajas(),Integer.class));
		lineaRegistroTipo12.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
		
		return lineaRegistroTipo12;
	}
	
	//LEY 1819(13)
	public LineaRegistroTipo13 convertMinpsLey18192LineaRegistroTipo13(
			MinpsLey1819Out minpsLey1819){
		
		//BY-PASS
		if(minpsLey1819 == null){return null;}
		
		LineaRegistroTipo13 lineaRegistroTipo13 = new LineaRegistroTipo13();
		
		lineaRegistroTipo13.setIndiceLinea(minpsLey1819.getLineNumber());
		//XXX Tipo de Registro
		//XXX Número del registro
		//XXX Código de Operador
		//XXX Nro de la planilla
		lineaRegistroTipo13.setIndicadorUGPP(DataTypeConverter.convert(minpsLey1819.getIndicadorUgpp(),Integer.class));
		lineaRegistroTipo13.setNumeroActoAdm(minpsLey1819.getNroActoAdministrativo());
		lineaRegistroTipo13.setFechaApertura(DateUtils.convertString2LocalDate(minpsLey1819.getFechaAperturaLiquidacion()));
		lineaRegistroTipo13.setNombreEntidad(minpsLey1819.getNombreEntidad());
		lineaRegistroTipo13.setValorPagadoSancion(DataTypeConverter.convert(minpsLey1819.getValorPagadoSancion(),Long.class));
		lineaRegistroTipo13.setTipoArchivo(TipoArchivo.MINPS_ACTIVOS);
		
		return lineaRegistroTipo13;
	}
}
