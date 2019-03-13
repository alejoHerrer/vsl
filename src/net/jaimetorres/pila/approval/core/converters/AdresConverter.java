package net.jaimetorres.pila.approval.core.converters;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.jaimetorres.pila.approval.pojos.output.adres.AdresEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresPlanillaEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresPlanillaLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresPlanillaOut;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresPlanillaTotalesOut;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresTotalesOut;
import net.jaimetorres.pila.approval.utils.DataTypeConverter;
import net.jaimetorres.pila.approval.utils.DateUtils;
import net.jaimetorres.validaciones.entidad.RegistroAportante;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo0Adres;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo4Adres;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo5;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;
import net.jaimetorres.validaciones.general.FinalidadPlanilla;
import net.jaimetorres.validaciones.general.TipoArchivo;
import net.jaimetorres.validaciones.to.PlanillaAdresTO;

public class AdresConverter {

	//ADRES - ENCABEZADO
	public LineaRegistroTipo0Adres convertAdresEncabezado2LineaRegistroTipo0Adres(AdresEncabezadoOut encabezado) {
		
		LineaRegistroTipo0Adres lineaRegistroTipo0Adres = new LineaRegistroTipo0Adres();
		lineaRegistroTipo0Adres.setTipoArchivo(TipoArchivo.ADRES_ACTIVOS);
		lineaRegistroTipo0Adres.setIndiceLinea(encabezado.getLineNumber());
		
		//XXX Número del Registro
		//XXX Tipo de Registro
		//XXX Codigo del Operador
		lineaRegistroTipo0Adres.setFechaPago(DateUtils.convertString2LocalDate(encabezado.getFechaPago()));
		lineaRegistroTipo0Adres.setTotalPlanillas(DataTypeConverter.convert(encabezado.getNroTotalPlanillasReportadas(),Integer.class));
		
		
		return lineaRegistroTipo0Adres;
	}
	
	public List<PlanillaAdresTO> convertAdresPlanillaOutList2planillaAdresTO(List<AdresPlanillaOut> planillas) {
		
		Function<AdresPlanillaOut,PlanillaAdresTO> mapper = p ->{
			
			PlanillaAdresTO planillaAdresTO = new PlanillaAdresTO();
			
			//IDENTIFICACION DEL TIPO DE PLANILLA (ACTIVOS o PENSIONADOS)
			//ACTIVOS
			//XXX TAL VEZ SE DEBAN TENER EN CUENTAS MAS VARIABLES PARA IDENTIFICAR QUE PLENAMENTE SEA DE ACTIVOS O PENSIONADOS
			if(!p.getPlanillaEncabezado().getClaseAportante().equals("P") ){

				//ENCABEZADO
				LineaRegistroTipo1 encabezado = this.convertAdresPlanillaEncabezadoActivosOut2LineaRegistroTipo1(
						p.getPlanillaEncabezado());
				planillaAdresTO.setEncabezadoActivos(encabezado);
				
				//LIQUIDACION
				List<LineaRegistroTipo2> lstDetalle = this.convertAdresPlanillaLiquidacionDetalladaActivosOut2LineaRegistroTipo2List(
						p.getPlanillaLiquidacionDetalladaList());
				planillaAdresTO.setLstDetalleActivos(lstDetalle);
				
			}else{
				
				//ENCABEZADO
				LineaRegistroTipo1Pensionado encabezado = this.convertAdresPlanillaEncabezadoPensionadosOut2LineaRegistroTipo1(
						p.getPlanillaEncabezado());
				planillaAdresTO.setEncabezadoPensionados(encabezado);
				
				//LIQUIDACION
				List<LineaRegistroTipo2Pensionado> lstDetalle = this.convertAdresPlanillaLiquidacionDetalladaPensionadosOut2LineaRegistroTipo2List(
						p.getPlanillaLiquidacionDetalladaList());
				planillaAdresTO.setLstDetallePensionados(lstDetalle);
				
			}
			
			//TOTALES
			List<LineaRegistroTipo5> totales = this.convertAdresPlanillaTotales2LineaRegistroTipo5List(
					p.getPlanillaTotalesList());
			planillaAdresTO.setTotales(totales);

			return planillaAdresTO;
		};
		return planillas.stream().map(mapper)
				.collect(Collectors.<PlanillaAdresTO>toList());
		
	}

	//=======
	//ACTIVOS
	//=======
	//PLANILLA - ENCABEZADO
	private LineaRegistroTipo1 convertAdresPlanillaEncabezadoActivosOut2LineaRegistroTipo1(
			AdresPlanillaEncabezadoOut encabezado) {
		
		LineaRegistroTipo1 lineaRegistroTipo1 = new LineaRegistroTipo1();
		RegistroAportante registroAportante = new RegistroAportante();
		lineaRegistroTipo1.setIndiceLinea(encabezado.getLineNumber());
		lineaRegistroTipo1.setTipoArchivo(TipoArchivo.ADRES_ACTIVOS);
		//Requerido por la planilla N (no afecta para las planillas diferentes a N)
		lineaRegistroTipo1.setFinalidadPlanilla(FinalidadPlanilla.CORRECCION_NORMAL);

		//XXX Número del Registro
		//XXX Tipo de Registro
		//XXX Número secuencial de planilla
		//XXX Codigo del Operador
		lineaRegistroTipo1.setRazonSocial(encabezado.getRazonSocialAportante());
		registroAportante.setRazonSocial(encabezado.getRazonSocialAportante());
		registroAportante.setTipoIdentificacion(encabezado.getTipoIdentificacionAportante());
		registroAportante.setNumeroIdentificacion(encabezado.getNroIdentificacionAportante());
		Integer digitoVerificacion = DataTypeConverter.convert(encabezado.getDvAportante(),Integer.class);
		lineaRegistroTipo1.setNumeroDocumento(encabezado.getNroIdentificacionAportante());
		lineaRegistroTipo1.setDigitoVerificacion(digitoVerificacion);
		registroAportante.setDigitoVerificacion(digitoVerificacion);
		registroAportante.setTipoEntidad(DataTypeConverter.convert(encabezado.getNaturalezaJuridica(),Integer.class));
		lineaRegistroTipo1.setTipoAportante(DataTypeConverter.convert(encabezado.getTipoAportante(),Integer.class));
		registroAportante.setTipoAportante(DataTypeConverter.convert(encabezado.getTipoAportante(),Integer.class));
		registroAportante.setTipoPersona(DataTypeConverter.convert(encabezado.getTipoPersona(),Character.class));
		lineaRegistroTipo1.setCodigoSucursalAportante(encabezado.getCodigoSucursal());
		registroAportante.setSucursal(encabezado.getCodigoSucursal());
		registroAportante.setClase(DataTypeConverter.convert(encabezado.getClaseAportante(),Character.class));
		lineaRegistroTipo1.setFormaPresentacion(encabezado.getFormaPresentacion());
		registroAportante.setFormaPresentacion(DataTypeConverter.convert(encabezado.getFormaPresentacion(),Character.class));
		registroAportante.setFechaInicioConcordato(encabezado.getFechaConcordato());
		registroAportante.setTipoAccion(DataTypeConverter.convert(encabezado.getTipoAccion(),Integer.class));
		registroAportante.setFechaFinActividades(encabezado.getFechaFinActividades());
		registroAportante.setFechaMatriculaMercantil(encabezado.getFechaMatriculaMercantil());
		registroAportante.setDepartamentoMM(DataTypeConverter.convert(encabezado.getDepartamentoMatriculaMercantil(),Integer.class));
		lineaRegistroTipo1.setPeriodoSalud(encabezado.getPeriodoPagoSalud());
		lineaRegistroTipo1.setCodigoARP(encabezado.getCodigoAdmRiesgos());
		registroAportante.setArp(encabezado.getCodigoAdmRiesgos());
		lineaRegistroTipo1.setTipoPlanilla(encabezado.getTipoPlanilla());
		lineaRegistroTipo1.setFechaPagoPlanillaAsociada(encabezado.getFechaPagoPlanillaAsociada());
		lineaRegistroTipo1.setFechaPagoPlanilla(encabezado.getFechaPago());
		lineaRegistroTipo1.setNumeroPlanillaAsociada(encabezado.getNroPlanillaAsociada());
		lineaRegistroTipo1.setNumeroPlanilla(encabezado.getNroPlanilla());
		lineaRegistroTipo1.setModalidadPlanilla(DataTypeConverter.convert(encabezado.getModalidadPlanilla(),Integer.class));
		//XXX Días de mora
		//XXX Total cotización obligatoría
		//XXX Total intereses de mora cotizacion obligatoria
		//XXX Total UPC Adicional
		//XXX Intereses de mora Cotización UPC adicional
		//XXX Número de registros de salida tipo 2
		lineaRegistroTipo1.setNumeroEmpleados(DataTypeConverter.convert(encabezado.getNroRegistrosTipo2Reportados(),Integer.class));
		registroAportante.setExoParafSalud(encabezado.getExoneradoPagoParafiscalesSalud());
		
		lineaRegistroTipo1.setRegistroAportante(registroAportante);
		return lineaRegistroTipo1;
	}
	
	//PLANILLA - LIQUIDACION DETALLADA
	private List<LineaRegistroTipo2> convertAdresPlanillaLiquidacionDetalladaActivosOut2LineaRegistroTipo2List(
			List<AdresPlanillaLiquidacionDetalladaOut> planillaLiquidacionDetalladaList) {
		
		Function<AdresPlanillaLiquidacionDetalladaOut,LineaRegistroTipo2> mapper = ld -> {
			
			LineaRegistroTipo2 lineaRegistroTipo2 = new LineaRegistroTipo2();
			lineaRegistroTipo2.setTipoArchivo(TipoArchivo.ADRES_ACTIVOS);
			lineaRegistroTipo2.setIndiceLinea(ld.getLineNumber());
			
			//XXX Número del Registro
			//XXX Tipo de registro
			//XXX Número secuencial de planilla
			//XXX Código del operador
			//XXX Número de radicación o de la planilla Integrada de Liquidacion de Aportes
			//XXX Periodo de pago del Aportante para el sistema de salud
			//XXX Fecha de pago
			//Codigo de la EPS o EOC
			lineaRegistroTipo2.setEps(ld.getCodigoAdmSalud());
			//XXX Número de identificación (NIT) de la EPS o EOC
			//XXX Digito de Verificacion del NIT
			//XXX Serial
			lineaRegistroTipo2.setTipoDocumento(ld.getTipoIdentificacion());
			lineaRegistroTipo2.setNumeroDocumento(ld.getNroIdentificacion());
			//XXX Serial beneficiario UPC adicional
			lineaRegistroTipo2.setTipoDocumentoBeneficiarioUPC(ld.getTipoIdentificacionUPC());
			lineaRegistroTipo2.setNumeroDocumentoBeneficiarioUPC(ld.getNroIdentificacionUPC());
			lineaRegistroTipo2.setTipoCotizante(DataTypeConverter.convert(ld.getTipoCotizante(),Integer.class));
			lineaRegistroTipo2.setSubTipoCotizante(DataTypeConverter.convert(ld.getSubtipoCotizante(),Integer.class));
			//XXX Tipo de pensionado
			//XXX Tipo de pension
			//XXX Pensión compartida
			lineaRegistroTipo2.setExtranjeroNoObligadoCotizar(ld.getExtranjeroNoObligadoCotPension());
			lineaRegistroTipo2.setColombianoResideExterior(ld.getColombianoResidenteExterior());
			lineaRegistroTipo2.setDepartamento(ld.getCodigoDepartamento());
			lineaRegistroTipo2.setMunicipio(DataTypeConverter.convert(ld.getCodigoMunicipio(),Integer.class));
			lineaRegistroTipo2.setPrimerApellido(ld.getPrimerApellido());
			lineaRegistroTipo2.setSegundoApellido(ld.getSegundoApellido());
			lineaRegistroTipo2.setPrimerNombre(ld.getPrimerNombre());
			lineaRegistroTipo2.setSegundoNombre(ld.getSegundoNombre());
			lineaRegistroTipo2.setIng(DataTypeConverter.convert(ld.getNovedadIngreso(),Character.class));
			lineaRegistroTipo2.setRet(DataTypeConverter.convert(ld.getNovedadRetiro(),Character.class));
			lineaRegistroTipo2.setTde(DataTypeConverter.convert(ld.getNovedadTrasladoEpsDesde(),Character.class));
			lineaRegistroTipo2.setTae(DataTypeConverter.convert(ld.getNovedadTrasladoEpsA(),Character.class));
			lineaRegistroTipo2.setVsp(DataTypeConverter.convert(ld.getNovedadVariacionSalarioPermanente(),Character.class));
			lineaRegistroTipo2.setVst(DataTypeConverter.convert(ld.getNovedadVariacionSalarioTransitoria(),Character.class));
			lineaRegistroTipo2.setSln(DataTypeConverter.convert(ld.getNovedadSuspensionTemporalContrato(),Character.class));
			lineaRegistroTipo2.setVac(DataTypeConverter.convert(ld.getNovedadVacaciones(),Character.class));
			lineaRegistroTipo2.setDiasEps(DataTypeConverter.convert(ld.getDiasCotizadosSalud(),Integer.class));
			lineaRegistroTipo2.setSalarioBasico(DataTypeConverter.convert(ld.getSalarioBasico(),Long.class));
			lineaRegistroTipo2.setIbcEps(DataTypeConverter.convert(ld.getIbcSalud(),Long.class));
			lineaRegistroTipo2.setTarifaEps(DataTypeConverter.convert(ld.getTarifaSalud(),Double.class));
			lineaRegistroTipo2.setCotizacionObligatoriaEps(DataTypeConverter.convert(ld.getCotizacionSalud(),Long.class));
			lineaRegistroTipo2.setValorUPCAdicional(DataTypeConverter.convert(ld.getValorUpcAdicional(),Long.class));
			lineaRegistroTipo2.setCorrecciones(DataTypeConverter.convert(ld.getNovedadCorreccion(),Character.class));
			lineaRegistroTipo2.setSalarioIntegral(ld.getSalarioIntegral());
			lineaRegistroTipo2.setExoParafSalud(DataTypeConverter.convert(ld.getCotizanteExoneradoLey1607(),Character.class));
			lineaRegistroTipo2.setFechaIngreso(DateUtils.convertStringToDate(ld.getNovedadIngresoFecha()));
			lineaRegistroTipo2.setFechaRetiro(DateUtils.convertStringToDate(ld.getNovedadRetiroFecha()));
			lineaRegistroTipo2.setFechaInicioVSP(DateUtils.convertStringToDate(ld.getNovedadVariacionSalarioPermanenteFecha() ));
			lineaRegistroTipo2.setFechaInicioSLN(DateUtils.convertStringToDate(ld.getNovedadSuspensionFechaInicio() ));
			lineaRegistroTipo2.setFechaFinSLN(DateUtils.convertStringToDate(ld.getNovedadSuspensionFechaFin()));
			lineaRegistroTipo2.setFechaInicioVAC(DateUtils.convertStringToDate(ld.getNovedadVacacionesFechaInicio() ));
			lineaRegistroTipo2.setFechaFinVAC(DateUtils.convertStringToDate(ld.getNovedadVacacionesFechaFin()));
			lineaRegistroTipo2.setFechaRadicacionExterior(DateUtils.convertStringToDate(ld.getFechaRadicacionExterior()));
			lineaRegistroTipo2.llenarNovedades();
			//XXX Fecha inicio SUS
			//XXX Fecha fin SUS
			
			return lineaRegistroTipo2;
		};
		
		return planillaLiquidacionDetalladaList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo2>toList());
	}
	
	//===========
	//PENSIONADOS
	//===========
	//PLANILLA - ENCABEZADO
	private LineaRegistroTipo1Pensionado convertAdresPlanillaEncabezadoPensionadosOut2LineaRegistroTipo1(
			AdresPlanillaEncabezadoOut encabezado) {
		
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = new LineaRegistroTipo1Pensionado();
		RegistroAportante registroAportante = new RegistroAportante();
		lineaRegistroTipo1.setIndiceLinea(encabezado.getLineNumber());
		lineaRegistroTipo1.setTipoArchivo(TipoArchivo.ADRES_PENSIONADOS);

		//XXX Número del Registro
		//XXX Tipo de Registro
		//XXX Número secuencial de planilla
		//XXX Codigo del Operador
		lineaRegistroTipo1.setRazonSocial(encabezado.getRazonSocialAportante());
		registroAportante.setRazonSocial(encabezado.getRazonSocialAportante());
		registroAportante.setTipoIdentificacion(encabezado.getTipoIdentificacionAportante());
		registroAportante.setNumeroIdentificacion(encabezado.getNroIdentificacionAportante());
		Integer digitoVerificacion = DataTypeConverter.convert(encabezado.getDvAportante(),Integer.class);
		lineaRegistroTipo1.setNumeroDocumento(encabezado.getNroIdentificacionAportante());
		lineaRegistroTipo1.setDigitoVerificacion(digitoVerificacion);
		registroAportante.setDigitoVerificacion(digitoVerificacion);
		registroAportante.setTipoEntidad(DataTypeConverter.convert(encabezado.getNaturalezaJuridica(),Integer.class));
		lineaRegistroTipo1.setTipoPagador(DataTypeConverter.convert(encabezado.getTipoAportante(),Integer.class));
		registroAportante.setTipoAportante(DataTypeConverter.convert(encabezado.getTipoAportante(),Integer.class));
		registroAportante.setTipoPersona(DataTypeConverter.convert(encabezado.getTipoPersona(),Character.class));
		lineaRegistroTipo1.setCodigoSucursal(encabezado.getCodigoSucursal());
		registroAportante.setSucursal(encabezado.getCodigoSucursal());
		registroAportante.setClase(DataTypeConverter.convert(encabezado.getClaseAportante(),Character.class));
		lineaRegistroTipo1.setFormaPresentacion(encabezado.getFormaPresentacion());
		registroAportante.setFormaPresentacion(DataTypeConverter.convert(encabezado.getFormaPresentacion(),Character.class));
		registroAportante.setFechaInicioConcordato(encabezado.getFechaConcordato());
		registroAportante.setTipoAccion(DataTypeConverter.convert(encabezado.getTipoAccion(),Integer.class));
		registroAportante.setFechaFinActividades(encabezado.getFechaFinActividades());
		registroAportante.setFechaMatriculaMercantil(encabezado.getFechaMatriculaMercantil());
		registroAportante.setDepartamentoMM(DataTypeConverter.convert(encabezado.getDepartamentoMatriculaMercantil(),Integer.class));
		lineaRegistroTipo1.setPeriodoSalud(DateUtils.convertString2YearMonth(encabezado.getPeriodoPagoSalud(),"yyyy-MM"));
//NOT-USED		lineaRegistroTipo1.setCodigoARP(encabezado.getCodigoAdmRiesgos());
		registroAportante.setArp(encabezado.getCodigoAdmRiesgos());
		lineaRegistroTipo1.setTipoPlanilla(encabezado.getTipoPlanilla());
//NOT-USED		lineaRegistroTipo1.setFechaPagoPlanillaAsociada(encabezado.getFechaPagoPlanillaAsociada());
		lineaRegistroTipo1.setFechaPagoPlanilla(encabezado.getFechaPago());
//NOT-USED				lineaRegistroTipo1.setNumeroPlanillaAsociada(encabezado.getNroPlanillaAsociada());
		lineaRegistroTipo1.setNumeroPlanilla(encabezado.getNroPlanilla());
//NOT-USED				lineaRegistroTipo1.setModalidadPlanilla(DataTypeConverter.convert(encabezado.getModalidadPlanilla(),Integer.class));
		//XXX Días de mora
		//XXX Total cotización obligatoría
		//XXX Total intereses de mora cotizacion obligatoria
		//XXX Total UPC Adicional
		//XXX Intereses de mora Cotización UPC adicional
		//XXX Número de registros de salida tipo 2
		lineaRegistroTipo1.setNumeroPensionados(DataTypeConverter.convert(encabezado.getNroRegistrosTipo2Reportados(),Integer.class));
		registroAportante.setExoParafSalud(encabezado.getExoneradoPagoParafiscalesSalud());
		
		lineaRegistroTipo1.setRegistroAportante(registroAportante);
		return lineaRegistroTipo1;
	}
	
	//PLANILLA - LIQUIDACION DETALLADA
	private List<LineaRegistroTipo2Pensionado> convertAdresPlanillaLiquidacionDetalladaPensionadosOut2LineaRegistroTipo2List(
			List<AdresPlanillaLiquidacionDetalladaOut> planillaLiquidacionDetalladaList) {
		
		Function<AdresPlanillaLiquidacionDetalladaOut,LineaRegistroTipo2Pensionado> mapper = ld -> {
			
			LineaRegistroTipo2Pensionado lineaRegistroTipo2 = new LineaRegistroTipo2Pensionado();
			lineaRegistroTipo2.setTipoArchivo(TipoArchivo.ADRES_PENSIONADOS);
			lineaRegistroTipo2.setIndiceLinea(ld.getLineNumber());
			
			//XXX Número del Registro
			//XXX Tipo de registro
			//XXX Número secuencial de planilla
			//XXX Código del operador
			//XXX Número de radicación o de la planilla Integrada de Liquidacion de Aportes
			//XXX Periodo de pago del Aportante para el sistema de salud
			//XXX Fecha de pago
			//Codigo de la EPS o EOC
			lineaRegistroTipo2.setEps(ld.getCodigoAdmSalud());
			//XXX Número de identificación (NIT) de la EPS o EOC
			//XXX Digito de Verificacion del NIT
			//XXX Serial
			lineaRegistroTipo2.setTipoDocumento(ld.getTipoIdentificacion());
			lineaRegistroTipo2.setNumeroDocumento(ld.getNroIdentificacion());
			//XXX Serial beneficiario UPC adicional
			lineaRegistroTipo2.setTipoDocumentoBeneficiarioUPC(ld.getTipoIdentificacionUPC());
			lineaRegistroTipo2.setNumeroDocumentoBeneficiarioUPC(ld.getNroIdentificacionUPC());
//NOT-USED			lineaRegistroTipo2.setTipoCotizante(DataTypeConverter.convert(ld.getTipoCotizante(),Integer.class));
//NOT-USED			lineaRegistroTipo2.setSubTipoCotizante(DataTypeConverter.convert(ld.getSubtipoCotizante(),Integer.class));
			lineaRegistroTipo2.setTipoPensionado(DataTypeConverter.convert(ld.getTipoPensionado(),Integer.class));
			lineaRegistroTipo2.setTipoPension(DataTypeConverter.convert(ld.getTipoPension(),Integer.class));
			lineaRegistroTipo2.setPensionCompartida(ld.getPensionCompartida());
//NOT-USED			lineaRegistroTipo2.setExtranjeroNoObligadoCotizar(ld.getExtranjeroNoObligadoCotPension());
			lineaRegistroTipo2.setPensionadoExterior(ld.getColombianoResidenteExterior());
			lineaRegistroTipo2.setDepartamento(ld.getCodigoDepartamento());
			lineaRegistroTipo2.setMunicipio(ld.getCodigoMunicipio());
			lineaRegistroTipo2.setPrimerApellido(ld.getPrimerApellido());
			lineaRegistroTipo2.setSegundoApellido(ld.getSegundoApellido());
			lineaRegistroTipo2.setPrimerNombre(ld.getPrimerNombre());
			lineaRegistroTipo2.setSegundoNombre(ld.getSegundoNombre());
			lineaRegistroTipo2.setIng(DataTypeConverter.convert(ld.getNovedadIngreso(),Character.class));
			lineaRegistroTipo2.setRet(DataTypeConverter.convert(ld.getNovedadRetiro(),Character.class));
			lineaRegistroTipo2.setTde(DataTypeConverter.convert(ld.getNovedadTrasladoEpsDesde(),Character.class));
			lineaRegistroTipo2.setTae(DataTypeConverter.convert(ld.getNovedadTrasladoEpsA(),Character.class));
			lineaRegistroTipo2.setVsp(DataTypeConverter.convert(ld.getNovedadVariacionSalarioPermanente(),Character.class));
//NOT-USED			lineaRegistroTipo2.setVst(DataTypeConverter.convert(ld.getNovedadVariacionSalarioTransitoria(),Character.class));
			lineaRegistroTipo2.setSus(DataTypeConverter.convert(ld.getNovedadSuspensionTemporalContrato(),Character.class));
//NOT-USED			lineaRegistroTipo2.setVac(DataTypeConverter.convert(ld.getNovedadVacaciones(),Character.class));
			lineaRegistroTipo2.setDiasEps(DataTypeConverter.convert(ld.getDiasCotizadosSalud(),Integer.class));
			lineaRegistroTipo2.setMesadaPensional(DataTypeConverter.convert(ld.getSalarioBasico(),Long.class));
			lineaRegistroTipo2.setIbc(DataTypeConverter.convert(ld.getIbcSalud(),Long.class));
			lineaRegistroTipo2.setTarifaEps(DataTypeConverter.convert(ld.getTarifaSalud(),Double.class));
			lineaRegistroTipo2.setCotizacionObligatoriaEps(DataTypeConverter.convert(ld.getCotizacionSalud(),Long.class));
			lineaRegistroTipo2.setValorUPCAdicional(DataTypeConverter.convert(ld.getValorUpcAdicional(),Long.class));
//NOT-USED			lineaRegistroTipo2.setCorrecciones(DataTypeConverter.convert(ld.getNovedadCorreccion(),Character.class));
//NOT-USED			lineaRegistroTipo2.setSalarioIntegral(ld.getSalarioIntegral());
//NOT-USED			lineaRegistroTipo2.setExoParafSalud(DataTypeConverter.convert(ld.getCotizanteExoneradoLey1607(),Character.class));
			lineaRegistroTipo2.setFechaIngreso(DateUtils.convertStringToDate(ld.getNovedadIngresoFecha()));
			lineaRegistroTipo2.setFechaRetiro(DateUtils.convertStringToDate(ld.getNovedadRetiroFecha()));
			lineaRegistroTipo2.setFechaInicioVSP(DateUtils.convertStringToDate(ld.getNovedadVariacionSalarioPermanenteFecha() ));
//NOT-USED			lineaRegistroTipo2.setFechaInicioSLN(DateUtils.convertStringToDate(ld.getNovedadSuspensionFechaInicio() ));
//NOT-USED			lineaRegistroTipo2.setFechaFinSLN(DateUtils.convertStringToDate(ld.getNovedadSuspensionFechaFin()));
//NOT-USED			lineaRegistroTipo2.setFechaInicioVAC(DateUtils.convertStringToDate(ld.getNovedadVacacionesFechaInicio() ));
//NOT-USED			lineaRegistroTipo2.setFechaFinVAC(DateUtils.convertStringToDate(ld.getNovedadVacacionesFechaFin()));
			lineaRegistroTipo2.setFechaRadicacionExterior(DateUtils.convertStringToDate(ld.getFechaRadicacionExterior()));
			lineaRegistroTipo2.setFechaInicioSUS(DateUtils.convertStringToDate(ld.getFechaInicioSUS()));
			lineaRegistroTipo2.setFechaFinSUS(DateUtils.convertStringToDate(ld.getFechaFinSUS()));
			
			return lineaRegistroTipo2;
		};
		
		return planillaLiquidacionDetalladaList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo2Pensionado>toList());
	}
	
	
	//PLANILLA - TOTALES
	//BOTH (ACTIVOS - PENSIONADOS)
	private List<LineaRegistroTipo5> convertAdresPlanillaTotales2LineaRegistroTipo5List(
			List<AdresPlanillaTotalesOut> planillaTotalesList) {

		Function<AdresPlanillaTotalesOut,LineaRegistroTipo5> mapper = totales -> {
			
			LineaRegistroTipo5 lineaRegistroTipo5 = new LineaRegistroTipo5();
			lineaRegistroTipo5.setIndiceLinea(totales.getLineNumber());
			
			//XXX Número del Registro
			//XXX Tipo de registro
			//XXX Código del operador
			//XXX Número de radicación o de la Planilla Integrada de Liquidación de Aportes
			lineaRegistroTipo5.setAdministradora(totales.getCodigoAdmSalud());
			//XXX Periodo de pago del Aportante para el sistema de salud
			//XXX Fecha de pago
			lineaRegistroTipo5.setTotalCotizacion(DataTypeConverter.convert(totales.getTotalCotizacionSalud(),Long.class));
			lineaRegistroTipo5.setDiasMora(DataTypeConverter.convert(totales.getDiasMora(),Long.class));
			lineaRegistroTipo5.setInteresesCotizacion(DataTypeConverter.convert(totales.getTotalMoraCotizacion(),Long.class));
			lineaRegistroTipo5.setTotalUPC(DataTypeConverter.convert(totales.getTotalUpcAdicional(),Long.class));
			lineaRegistroTipo5.setInteresesUPC(DataTypeConverter.convert(totales.getToalMoraUpcAdicional(),Long.class));
			lineaRegistroTipo5.setTipoArchivo(TipoArchivo.ADRES_ACTIVOS);
			
			return lineaRegistroTipo5;
		};
		
		return planillaTotalesList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo5>toList());
	}

	//ADRES - TOTALES
	public LineaRegistroTipo4Adres convertAdresTotales2LineaRegistroTipo4Adres(AdresTotalesOut totales) {
		
		LineaRegistroTipo4Adres lineaRegistroTipo4Adres = new LineaRegistroTipo4Adres();
		lineaRegistroTipo4Adres.setTipoArchivo(TipoArchivo.ADRES_ACTIVOS);
		lineaRegistroTipo4Adres.setIndiceLinea(totales.getLineNumber());
		//XXX Número del Registro
		//XXX Tipo de registro
		//XXX Código del operador
		//XXX Fecha de pago formato AAAA-MM-DD
		lineaRegistroTipo4Adres.setNumeroPlanillasPagas(DataTypeConverter.convert(totales.getNroPlanillasPagadas(),Integer.class));
		lineaRegistroTipo4Adres.setTotalCotizacion(DataTypeConverter.convert(totales.getTotalCotizacion(),Long.class));
		lineaRegistroTipo4Adres.setTotalInteresesCotizacion(DataTypeConverter.convert(totales.getTotalMoraCotizacion(),Long.class));
		lineaRegistroTipo4Adres.setTotalUPC(DataTypeConverter.convert(totales.getTotalUpcAdicional(),Long.class));
		lineaRegistroTipo4Adres.setTotalInteresUPC(DataTypeConverter.convert(totales.getTotalMoraUpcAdicional(),Long.class));
		
		return lineaRegistroTipo4Adres;
	}
}
