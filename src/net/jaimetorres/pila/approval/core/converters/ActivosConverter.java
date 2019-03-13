package net.jaimetorres.pila.approval.core.converters;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.jaimetorres.pila.approval.core.misc.SubsistemaPila;
import net.jaimetorres.pila.approval.pojos.output.activos.cajas.ActivosCajasEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.cajas.ActivosCajasLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosTotalesAporte;
import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosTotalesMora;
import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosTotalesRenglon39;
import net.jaimetorres.pila.approval.pojos.output.activos.esap.ActivosEsapEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.icbf.ActivosIcbfEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.minedu.ActivosMineduEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.activos.pension.ActivosPensionTotalesRenglon39Out;
import net.jaimetorres.pila.approval.pojos.output.activos.registroA.ActivosRegistroAEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.activos.riesgos.ActivosRiesgosTotalesRenglon39Out;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.activos.salud.ActivosSaludTotalesRenglon39Out;
import net.jaimetorres.pila.approval.pojos.output.activos.sena.ActivosSenaEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.sena.ActivosSenaLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.utils.DataTypeConverter;
import net.jaimetorres.pila.approval.utils.DateUtils;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;
import net.jaimetorres.validaciones.general.TipoArchivo;

public class ActivosConverter {
	
	private ActivosCommonsConverter activosCommonsConverter = new ActivosCommonsConverter();

	//#################
	//###ENCABEZADOS###
	//#################
	public LineaRegistroTipo1 convertRegistroA2LineaRegistroTipo1(ActivosRegistroAEncabezadoOut encabezado) {
		
		LineaRegistroTipo1 lineaRegistroTipo1 = activosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.TIPO_A, encabezado);
		
		//Actividad Economica
		//XXX
		lineaRegistroTipo1.getRegistroAportante().setActividad(encabezado.getActividadEconomica());
//		Nro Identificacion RepLegal
		lineaRegistroTipo1.getRegistroAportante().setNumeroIDRep(encabezado.getNroIdentificacionRepLegal());
		//XXX
//		Digito Verificacion RepLegal
		lineaRegistroTipo1.getRegistroAportante().setDigitoVerificacionRep(DataTypeConverter.convert(encabezado.getDvRepLegal(),Integer.class));
		//XXX
//		Tipo Identificacion RepLegal
		lineaRegistroTipo1.getRegistroAportante().setTipoIDRep(encabezado.getTipoIdentificacionRepLegal());
//		Primer Apellido RepLegal
		lineaRegistroTipo1.getRegistroAportante().setPApellidoRep(encabezado.getPrimerApellidoRepLegal());
		//XXX
//		Segundo Apellido RepLegal
		lineaRegistroTipo1.getRegistroAportante().setSApellidoRep(encabezado.getSegundoApellidoRepLegal());
		//XXX
//		Primer Nombre RepLegal
		lineaRegistroTipo1.getRegistroAportante().setPNombreRep(encabezado.getPrimerNombreRepLegal());
		//XXX
//		Segundo Nombre RepLegal
		lineaRegistroTipo1.getRegistroAportante().setSNombreRep(encabezado.getSegundoNombreRepLegal());
		//XXX
//		Fecha Inicio Concordato
		//XXX
		lineaRegistroTipo1.getRegistroAportante().setFechaInicioConcordato(encabezado.getFechaConcordato());
//		Tipo Accion
		//XXX
		lineaRegistroTipo1.getRegistroAportante().setTipoAccion(Integer.parseInt(encabezado.getTipoAccion()));
//		Fecha Termino de Actividades
		//XXX
		lineaRegistroTipo1.getRegistroAportante().setFechaFinActividades(encabezado.getFechaFinActividades());
//		Periodo de Pago
		//XXX
//		Fecha Matricula Mercantil
		lineaRegistroTipo1.getRegistroAportante().setFechaMatriculaMercantil(encabezado.getFechaMatriculaMercantil());
//		Código Dpto Matricula Mercantil
		lineaRegistroTipo1.getRegistroAportante().setDepartamentoMM(DataTypeConverter.convert(encabezado.getDepartamentoMatriculaMercantil(),Integer.class));
		//XXX
//		Aportante Exonerado
		lineaRegistroTipo1.getRegistroAportante().setExoParafSalud(encabezado.getExoneradoPagoParafiscalesSalud());
//		Beneficiario Ley 1429
		lineaRegistroTipo1.getRegistroAportante().setBen1429CCF(DataTypeConverter.convert(encabezado.getBeneficiarioLey1429(),Boolean.class));
		
		lineaRegistroTipo1.getRegistroAportante().setBen_1429_ccf(encabezado.getBeneficiarioLey1429());
		
		lineaRegistroTipo1.getRegistroAportante().setNombreSucursal(encabezado.getNombreSucursal());
		
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo1 convertPensionEncabezado2LineaRegistroTipo1(ActivosPensionEncabezadoOut encabezado){
		
		LineaRegistroTipo1 lineaRegistroTipo1 = activosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.PENSION, encabezado);
		
		String codigoArp = encabezado.getCodigoArp();
		lineaRegistroTipo1.setCodigoARP(codigoArp);
		lineaRegistroTipo1.getRegistroAportante().setArp(codigoArp);
		
		lineaRegistroTipo1.setModalidadPlanilla(DataTypeConverter.convert(encabezado.getModalidadPlanilla(),Integer.class) );
		
		//XXX CAMPOS NO TENIDOS EN CUENTA
		//encabezado.getNroRegistrosTipo2();
		
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo1 convertSaludEncabezado2LineaRegistroTipo1(ActivosSaludEncabezadoOut encabezado) {

		LineaRegistroTipo1 lineaRegistroTipo1 = activosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.SALUD, encabezado);
		
		String codigoArp = encabezado.getCodigoArp();
		lineaRegistroTipo1.setCodigoARP(codigoArp);
		lineaRegistroTipo1.getRegistroAportante().setArp(codigoArp);
		
		lineaRegistroTipo1.getRegistroAportante().setFechaMatriculaMercantil(encabezado.getFechaMatriculaMercantil());
		lineaRegistroTipo1.getRegistroAportante().setDepartamentoMM(DataTypeConverter.convert(encabezado.getCodigoDepartamentoMatriculaMercantil(),Integer.class));
		lineaRegistroTipo1.getRegistroAportante().setExoParafSalud(encabezado.getAportanteExoneradoLey1607());
		
		lineaRegistroTipo1.setModalidadPlanilla(DataTypeConverter.convert(encabezado.getModalidadPlanilla(),Integer.class) );
		
		//XXX CAMPOS NO TENIDOS EN CUENTA
		//encabezado.getNroRegistrosTipo2();
		
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo1 convertRiesgosEncabezado2LineaRegistroTipo1(ActivosRiesgosEncabezadoOut encabezado) {
		
		LineaRegistroTipo1 lineaRegistroTipo1 = activosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.RIESGOS, encabezado);
		
		lineaRegistroTipo1.setModalidadPlanilla(DataTypeConverter.convert(encabezado.getModalidadPlanilla(),Integer.class) );
		
		//XXX CAMPOS NO TENIDOS EN CUENTA
		//encabezado.getNroRegistrosTipo2();
		
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo1 convertCajasEncabezado2LineaRegistroTipo1(ActivosCajasEncabezadoOut encabezado) {
		
		LineaRegistroTipo1 lineaRegistroTipo1 = activosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.CAJAS, encabezado);
		
		lineaRegistroTipo1.getRegistroAportante().setFechaMatriculaMercantil(encabezado.getFechaMatriculaMercantil());
		lineaRegistroTipo1.getRegistroAportante().setDepartamentoMM(DataTypeConverter.convert(encabezado.getCodigoDepartamentoMatriculaMercantil(),Integer.class));
		lineaRegistroTipo1.getRegistroAportante().setBen1429CCF(DataTypeConverter.convert(encabezado.getAportanteAcogeBeneficiosLey1429(),Boolean.class));
		//lineaRegistroTipo1.getRegistroAportante().setBen_1429_ccf(String);
		
		lineaRegistroTipo1.setModalidadPlanilla(DataTypeConverter.convert(encabezado.getModalidadPlanilla(),Integer.class) );
		
		//XXX CAMPOS NO TENIDOS EN CUENTA
		//encabezado.getNroRegistrosTipo2();
		
		//XXX OJO: VALORES DE CAMPOS REQUERIDOS POR INTERSSI-COMMONS
		lineaRegistroTipo1.getRegistroAportante().setExoParafSalud("");
		
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo1 convertSenaEncabezado2LineaRegistroTipo1(ActivosSenaEncabezadoOut encabezado) {
		
		LineaRegistroTipo1 lineaRegistroTipo1 = activosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.SENA, encabezado);
		return convertCommonsFields4SenaIcbf(encabezado, lineaRegistroTipo1);
	}
	
	public LineaRegistroTipo1 convertIcbfEncabezado2LineaRegistroTipo1(ActivosIcbfEncabezadoOut encabezado) {
		LineaRegistroTipo1 lineaRegistroTipo1 = activosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.ICBF, encabezado);
		return convertCommonsFields4SenaIcbf(encabezado, lineaRegistroTipo1);
	}
	
	private <T extends ActivosSenaEncabezadoOut> LineaRegistroTipo1 convertCommonsFields4SenaIcbf(T encabezado, LineaRegistroTipo1 lineaRegistroTipo1) {
		lineaRegistroTipo1.getRegistroAportante().setFechaMatriculaMercantil(encabezado.getFechaMatriculaMercantil());
		lineaRegistroTipo1.getRegistroAportante().setDepartamentoMM(DataTypeConverter.convert(encabezado.getCodigoDepartamentoMatriculaMercantil(),Integer.class));
		lineaRegistroTipo1.getRegistroAportante().setExoParafSalud(encabezado.getAportanteExoneradoLey1607());
		lineaRegistroTipo1.setModalidadPlanilla(DataTypeConverter.convert(encabezado.getModalidadPlanilla(),Integer.class) );
		//XXX CAMPOS NO TENIDOS EN CUENTA
		//encabezado.getNroRegistrosTipo2();
		
		return lineaRegistroTipo1;
	}

	public LineaRegistroTipo1 convertEsapEncabezado2LineaRegistroTipo1(ActivosEsapEncabezadoOut encabezado) {
		LineaRegistroTipo1 lineaRegistroTipo1 = activosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.ESAP, encabezado);
		lineaRegistroTipo1.setModalidadPlanilla(DataTypeConverter.convert(encabezado.getModalidadPlanilla(),Integer.class) );
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo1 convertMineduEncabezado2LineaRegistroTipo1(ActivosMineduEncabezadoOut encabezado) {
		//LA CONVERSION DE MINEDU ES LA MISMA DEL ESAP
		return this.convertEsapEncabezado2LineaRegistroTipo1(encabezado);
	}
	
	//##############################################
	//###LIQUIDACION_DETALLADA (REGISTROS TIPO 2)###
	//##############################################
	public List<LineaRegistroTipo2> convertPensionLiquidacionDetalladaList2LineaRegistroTipo2List(
			List<ActivosPensionLiquidacionDetalladaOut> liquidacionDetalladaList) {
		
		Function<ActivosPensionLiquidacionDetalladaOut,LineaRegistroTipo2> mapper = ld -> {
			
			LineaRegistroTipo2 lineaRegistroTipo2 = activosCommonsConverter.convertLiquidacionDetallada2LineaRegistroTipo2(ld);
			
			//Specific to PENSION
			lineaRegistroTipo2.setDiasAfp(DataTypeConverter.convert(ld.getDiasCotizadosPension(),Integer.class));
			lineaRegistroTipo2.setIbcAfp(DataTypeConverter.convert(ld.getIbcPension(),Long.class));
			lineaRegistroTipo2.setTarifaAfp(Double.valueOf(ld.getTarifaPension()));
			lineaRegistroTipo2.setCotizacionObligatoriaAfp(DataTypeConverter.convert(ld.getCotizacionPension(),Long.class));
			lineaRegistroTipo2.setAporteVoluntarioAfiliadoAfp( DataTypeConverter.convert(ld.getCotizacionVoluntariaPensionAfiliado(),Long.class));
			lineaRegistroTipo2.setAporteVoluntarioAportanteAfp( DataTypeConverter.convert(ld.getCotizacionVoluntariaPensionAportante(),Long.class));
			lineaRegistroTipo2.setTotalCotizacionAFP(DataTypeConverter.convert(ld.getTotalCotizacion(),Long.class));
			lineaRegistroTipo2.setAportesFSPsolidaridad(DataTypeConverter.convert(ld.getAporteFondoSolidaridadPensionalSubcuentaSolidaridad(),Long.class));
			lineaRegistroTipo2.setAportesFSPsubsistencia(DataTypeConverter.convert(ld.getAporteFondoSolidaridadPensionalSubcuentaSubsistencia(),Long.class));
			lineaRegistroTipo2.setValorNoRetenidoApteVoluntario(DataTypeConverter.convert(ld.getValorNoRetenidoAportesVoluntarios(),Long.class));
			lineaRegistroTipo2.setIndicadorTarifaPensiones(ld.getIndicadorTarifaEspecialPensiones());
			
			//Novedades
			lineaRegistroTipo2.setTdp(DataTypeConverter.convert(ld.getNovedadTrasladoAfpDesde(),Character.class));
			lineaRegistroTipo2.setTap(DataTypeConverter.convert(ld.getNovedadTrasladoAfpA(),Character.class));
			lineaRegistroTipo2.setAvp(DataTypeConverter.convert(ld.getNovedadAporteVoluntarioPensiones(),Character.class));
			lineaRegistroTipo2.setFechaInicioIRL(DateUtils.convertStringToDate(ld.getFechaInicioIRL()) );
			lineaRegistroTipo2.setFechaFinIRL(DateUtils.convertStringToDate(ld.getFechaFinIRL()) );
			lineaRegistroTipo2.setIrp(DataTypeConverter.convert(ld.getDiasNovedadIncapacidadRiesgosProfesionales(),Integer.class));
			
			return lineaRegistroTipo2;
		};
		
		return liquidacionDetalladaList.stream().map(mapper).collect(Collectors.toList());
	}

	public List<LineaRegistroTipo2> convertSaludLiquidacionDetalladaList2LineaRegistroTipo2List(
			List<ActivosSaludLiquidacionDetalladaOut> liquidacionDetalladaList) {
		
		Function<ActivosSaludLiquidacionDetalladaOut,LineaRegistroTipo2> mapper = ld -> {
			
			LineaRegistroTipo2 lineaRegistroTipo2 = activosCommonsConverter.convertLiquidacionDetallada2LineaRegistroTipo2(ld);
			
			//Specific to SALUD
			lineaRegistroTipo2.setDiasEps(DataTypeConverter.convert(ld.getDiasCotizadosSalud(),Integer.class));
			lineaRegistroTipo2.setIbcEps(DataTypeConverter.convert(ld.getIbcSalud(),Long.class));
			lineaRegistroTipo2.setTarifaEps(Double.valueOf(ld.getTarifaSalud()));
			lineaRegistroTipo2.setCotizacionObligatoriaEps(DataTypeConverter.convert(ld.getCotizacionSalud(),Long.class));
			lineaRegistroTipo2.setValorUPCAdicional(DataTypeConverter.convert(ld.getValorUpcAdicional(),Long.class));
			lineaRegistroTipo2.setExoParafSalud(DataTypeConverter.convert(ld.getCotizanteExoneradoLey1607(),Character.class));
			lineaRegistroTipo2.setFechaRadicacionExterior(DateUtils.convertStringToDate(ld.getFechaRadicacionExterior()));
			
			//Novedades
			lineaRegistroTipo2.setFechaInicioVCT(DateUtils.convertStringToDate(ld.getFechaInicioVCT()));
			lineaRegistroTipo2.setFechaFinVCT(DateUtils.convertStringToDate(ld.getFechaFinVCT()));
			lineaRegistroTipo2.setFechaInicioIRL(DateUtils.convertStringToDate(ld.getFechaInicioIRL()));
			lineaRegistroTipo2.setFechaFinIRL(DateUtils.convertStringToDate(ld.getFechaFinIRL()));
			lineaRegistroTipo2.setTde(DataTypeConverter.convert(ld.getNovedadTrasladoEpsDesde(), Character.class));
			lineaRegistroTipo2.setTae(DataTypeConverter.convert(ld.getNovedadTrasladoEpsA(), Character.class));
			
			return lineaRegistroTipo2;
		};
		
		return liquidacionDetalladaList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo2>toList());
	}
	
	public List<LineaRegistroTipo2> convertRiesgosLiquidacionDetalladaList2LineaRegistroTipo2List(
			List<ActivosRiesgosLiquidacionDetalladaOut> liquidacionDetalladaList) {
		
		Function<ActivosRiesgosLiquidacionDetalladaOut,LineaRegistroTipo2> mapper = ld -> {
			
			LineaRegistroTipo2 lineaRegistroTipo2 = activosCommonsConverter.convertLiquidacionDetallada2LineaRegistroTipo2(ld);
			
			//Specific to RIESGOS
			lineaRegistroTipo2.setDiasArp(DataTypeConverter.convert(ld.getDiasCotizadosRiesgos(),Integer.class));
			lineaRegistroTipo2.setIbcArp(DataTypeConverter.convert(ld.getIbcRiesgos(),Long.class));
			lineaRegistroTipo2.setTarifaArp(Double.valueOf(ld.getTarifaRiesgos()));
			lineaRegistroTipo2.setCodigoCT(DataTypeConverter.convert(ld.getCodigoCentroTrabajo(),Integer.class));
			lineaRegistroTipo2.setCotizacionObligatoriaArp(DataTypeConverter.convert(ld.getCotizacionRiesgos(),Long.class));
			lineaRegistroTipo2.setAfp(ld.getCodigoAfpAfiliado());
			lineaRegistroTipo2.setEps(ld.getCodigoEpsAfiliado());
			lineaRegistroTipo2.setCodigoARLResolucion2087(ld.getCodigoArlAfiliado());
			lineaRegistroTipo2.setClaseRiesgoResolucion2087(ld.getClaseRiesgoAfiliado());
			
			//Novedades
			lineaRegistroTipo2.setVct(DataTypeConverter.convert(ld.getNovedadVariacionCentrosTrabajo(),Character.class));
			lineaRegistroTipo2.setIrp(DataTypeConverter.convert(ld.getDiasNovedadIncapacidadRiesgosProfesionales(),Integer.class));
			
			lineaRegistroTipo2.setFechaInicioVCT(DateUtils.convertStringToDate(ld.getFechaInicioVCT()));
			lineaRegistroTipo2.setFechaFinVCT(DateUtils.convertStringToDate(ld.getFechaFinVCT()));
			lineaRegistroTipo2.setFechaInicioIRL(DateUtils.convertStringToDate(ld.getFechaInicioIRL()));
			lineaRegistroTipo2.setFechaFinIRL(DateUtils.convertStringToDate(ld.getFechaFinIRL()));
			
			return lineaRegistroTipo2;
		};
		
		return liquidacionDetalladaList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo2>toList());
	}
	
	public List<LineaRegistroTipo2> convertCajasLiquidacionDetalladaList2LineaRegistroTipo2List(
			List<ActivosCajasLiquidacionDetalladaOut> liquidacionDetalladaList) {
		
		Function<ActivosCajasLiquidacionDetalladaOut,LineaRegistroTipo2> mapper = ld -> {
			
			LineaRegistroTipo2 lineaRegistroTipo2 = activosCommonsConverter.convertLiquidacionDetallada2LineaRegistroTipo2(ld);
			
			//Specific to CAJAS
			lineaRegistroTipo2.setDiasccf(DataTypeConverter.convert(ld.getDiasCotizadosCajas(),Integer.class));
			lineaRegistroTipo2.setIbcCcf(DataTypeConverter.convert(ld.getIbcCajas(),Long.class));
			lineaRegistroTipo2.setTarifaCCF(Double.valueOf(ld.getTarifaCajas()));
			lineaRegistroTipo2.setAporteCCF(DataTypeConverter.convert(ld.getCotizacionCajas(),Long.class));
			lineaRegistroTipo2.setNumeroHorasLaboradas(DataTypeConverter.convert(ld.getNroHorasLaboradas(),Integer.class));
			
			//Novedades
			//lineaRegistroTipo2.setFechaInicioVCT(DateUtils.convertStringToDate(ld.getFechaInicioVCT()));
			//lineaRegistroTipo2.setFechaFinVCT(DateUtils.convertStringToDate(ld.getFechaFinVCT()));
			lineaRegistroTipo2.setIrp(DataTypeConverter.convert(ld.getDiasNovedadIncapacidadRiesgosProfesionales(), Integer.class));
			lineaRegistroTipo2.setFechaInicioIRL(DateUtils.convertStringToDate(ld.getFechaInicioIRL()));
			lineaRegistroTipo2.setFechaFinIRL(DateUtils.convertStringToDate(ld.getFechaFinIRL()));
			
			return lineaRegistroTipo2;
		};
		
		return liquidacionDetalladaList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo2>toList());
	}
	
	public List<LineaRegistroTipo2> convertSenaIcbfLiquidacionDetalladaList2LineaRegistroTipo2List(
			SubsistemaPila subsistema,
			List<? extends ActivosSenaLiquidacionDetalladaOut> liquidacionDetalladaList) {
		
		//Las estructuras del SENA e ICBF son similares
		Function<ActivosSenaLiquidacionDetalladaOut,LineaRegistroTipo2> mapper = ld -> {
			
			LineaRegistroTipo2 lineaRegistroTipo2 = activosCommonsConverter.convertLiquidacionDetallada2LineaRegistroTipo2(ld);
			lineaRegistroTipo2.setDiasccf(DataTypeConverter.convert(ld.getDiasCotizados(),Integer.class));
			lineaRegistroTipo2.setIbcCcf(DataTypeConverter.convert(ld.getIbc(),Long.class));
			lineaRegistroTipo2.setIbcOtrosParafiscales(DataTypeConverter.convert(ld.getIbc(),Long.class));
			lineaRegistroTipo2.setExoParafSalud(DataTypeConverter.convert(ld.getCotizanteExoneradoLey1607(),Character.class));
			
			//Novedades
			lineaRegistroTipo2.setIrp(DataTypeConverter.convert(ld.getDiasNovedadIncapacidadRiesgosProfesionales(),Integer.class));
			lineaRegistroTipo2.setFechaInicioVCT(DateUtils.convertStringToDate(ld.getFechaInicioVCT()));
			lineaRegistroTipo2.setFechaFinVCT(DateUtils.convertStringToDate(ld.getFechaFinVCT()));
			lineaRegistroTipo2.setFechaInicioIRL(DateUtils.convertStringToDate(ld.getFechaInicioIRL()));
			lineaRegistroTipo2.setFechaFinIRL(DateUtils.convertStringToDate(ld.getFechaFinIRL()));

			
			//Specific to SENA
			if(SubsistemaPila.PARAFISCALES_SENA.equals(subsistema)){
				lineaRegistroTipo2.setTarifaSENA(Double.valueOf(ld.getTarifa()));
				lineaRegistroTipo2.setAporteSENA(DataTypeConverter.convert(ld.getCotizacion(),Long.class));
			
			}else 
			//Specific to ICBF
				if(SubsistemaPila.PARAFISCALES_ICBF.equals(subsistema)){
					lineaRegistroTipo2.setTarifaICBF(Double.valueOf(ld.getTarifa()));
					lineaRegistroTipo2.setAporteICBF(DataTypeConverter.convert(ld.getCotizacion(),Long.class));
			}
			
			return lineaRegistroTipo2;
		};
		
		return liquidacionDetalladaList.stream().map(mapper)
				.collect(Collectors.<LineaRegistroTipo2>toList());
	}
	
	//####################
	//##TOTALES RENGLON 31
	//####################
	public LineaRegistroTipo31 convertPensionRenglon31ToLineaRegistroTipo31(
			ActivosPensionTotalesRenglon31Out renglon31) {
		
		LineaRegistroTipo31 lineaRegistroTipo31 = activosCommonsConverter.convertTotalesAportes2LineaRegistroTipo31(
				TipoArchivo.PENSION,renglon31);
		
		lineaRegistroTipo31.setCotizacionVoluntariaAfiliado(Long.valueOf(renglon31.getTotalCotizacionVoluntariaPensionAfiliado() ));
		lineaRegistroTipo31.setCotizacionVoluntariaAportante(Long.valueOf(renglon31.getTotalCotizacionVoluntariaPensionAportante()));
		lineaRegistroTipo31.setTotalCotizacion(DataTypeConverter.convert(renglon31.getTotalCotizacionPensionMasVoluntarias(),Long.class));
		lineaRegistroTipo31.setFondoSolidaridad(Long.valueOf(renglon31.getTotalAporteFondoSolidaridadPensionalSubcuentaSolidaridad()));
		lineaRegistroTipo31.setFondoSubsistenci(Long.valueOf(renglon31.getTotalAporteFondoSolidaridadPensionalSubcuentaSubsistencia()));
		
		return lineaRegistroTipo31;
	}
	
	public LineaRegistroTipo31 convertSaludRenglon31ToLineaRegistroTipo31(
			ActivosSaludTotalesRenglon31Out renglon31) {
		
		LineaRegistroTipo31 lineaRegistroTipo31 = activosCommonsConverter.convertTotalesAportes2LineaRegistroTipo31(
				TipoArchivo.SALUD,renglon31);
		lineaRegistroTipo31.setTotalUPC(Long.valueOf(renglon31.getTotalUpcAdicional()));
		
		return lineaRegistroTipo31;
	}
	
	public LineaRegistroTipo31 convertSimpleRenglon31ToLineaRegistroTipo31(
			SubsistemaPila subsistema,
			CommonActivosTotalesAporte renglon31) {
		
		TipoArchivo tipoArchivo;
		switch(subsistema){
		case RIESGOS:
			tipoArchivo = TipoArchivo.RIESGOS; break;
		case PARAFISCALES_CAJAS:
			tipoArchivo = TipoArchivo.CAJAS; break;
		case PARAFISCALES_SENA:
			tipoArchivo = TipoArchivo.SENA; break;
		case PARAFISCALES_ICBF:
			tipoArchivo = TipoArchivo.ICBF; break;
		case PARAFISCALES_ESAP:
			tipoArchivo = TipoArchivo.ESAP; break;
		case PARAFISCALES_MINEDU:
			tipoArchivo = TipoArchivo.MEN; break;
		default:
			throw new RuntimeException("Subsistema no contemplado: " + subsistema);
		}
		
		LineaRegistroTipo31 lineaRegistroTipo31 = activosCommonsConverter.convertTotalesAportes2LineaRegistroTipo31(tipoArchivo, renglon31);
		return lineaRegistroTipo31; 
	}

	//####################
	//##TOTALES RENGLON 36
	//####################
	public LineaRegistroTipo36 convertPensionRenglon36ToLineaRegistroTipo36(
			ActivosPensionTotalesRenglon36Out renglon36){
		
		LineaRegistroTipo36 lineaRegistroTipo36 = activosCommonsConverter.convertTotalesMora2LineaRegistroTipo36(
				TipoArchivo.PENSION,renglon36);
		
		lineaRegistroTipo36.setMoraFondoSolidaridad(Long.valueOf(renglon36.getMoraAporteFondoSolidaridadPensionalSubcuentaSolidaridad()));
		lineaRegistroTipo36.setMoraFondoSubsistencia(Long.valueOf(renglon36.getMoraAporteFondoSolidaridadPensionalSubcuentaSubsistencia()));
		return lineaRegistroTipo36;
	}
	
	public LineaRegistroTipo36 convertSaludRenglon36ToLineaRegistroTipo36(
			ActivosSaludTotalesRenglon36Out renglon36){
		
		LineaRegistroTipo36 lineaRegistroTipo36 = activosCommonsConverter.convertTotalesMora2LineaRegistroTipo36(
				TipoArchivo.SALUD,renglon36);
		
		lineaRegistroTipo36.setMoraUPC(Long.valueOf(renglon36.getMoraUpcAdicionales()));
		return lineaRegistroTipo36;
	}
	
	public LineaRegistroTipo36 convertSimpleRenglon36ToLineaRegistroTipo36(
			SubsistemaPila subsistema,
			CommonActivosTotalesMora renglon36) {
		
		TipoArchivo tipoArchivo;
		switch(subsistema){
		case RIESGOS:
			tipoArchivo = TipoArchivo.RIESGOS; break;
		case PARAFISCALES_CAJAS:
			tipoArchivo = TipoArchivo.CAJAS; break;
		case PARAFISCALES_SENA:
			tipoArchivo = TipoArchivo.SENA; break;
		case PARAFISCALES_ICBF:
			tipoArchivo = TipoArchivo.ICBF; break;
		case PARAFISCALES_ESAP:
			tipoArchivo = TipoArchivo.ESAP; break;
		case PARAFISCALES_MINEDU:
			tipoArchivo = TipoArchivo.MEN; break;
		default:
			throw new RuntimeException("Subsistema no contemplado: " + subsistema);
		}
		
		LineaRegistroTipo36 lineaRegistroTipo36 = activosCommonsConverter.convertTotalesMora2LineaRegistroTipo36(tipoArchivo, renglon36);
		return lineaRegistroTipo36; 
	}
	
	//####################
	//##TOTALES RENGLON 39
	//####################
	public LineaRegistroTipo39 convertPensionRenglon39ToLineaRegistroTipo39(
			ActivosPensionTotalesRenglon39Out renglon39){
	
		LineaRegistroTipo39 lineaRegistroTipo39 = activosCommonsConverter.convertRenglon39Totales2LineaRegistroTipo39(
				TipoArchivo.PENSION,renglon39);
		lineaRegistroTipo39.setTotalFondoSolidaridad(Long.valueOf(renglon39.getTotalAporteFondoSolidaridadPensionalSubcuentaSolidaridad()));
		lineaRegistroTipo39.setTotalFondoSubsustencia(Long.valueOf(renglon39.getTotalAporteFondoSolidaridadPensionalSubcuentaSubsistencia()));
		return lineaRegistroTipo39;
	}
	
	public LineaRegistroTipo39 convertSaludRenglon39ToLineaRegistroTipo39(
			ActivosSaludTotalesRenglon39Out renglon39){
	
		LineaRegistroTipo39 lineaRegistroTipo39 = activosCommonsConverter.convertRenglon39Totales2LineaRegistroTipo39(
				TipoArchivo.SALUD,renglon39);
		lineaRegistroTipo39.setNetoAportes(Long.valueOf(renglon39.getTotalNetoAportes()));
		lineaRegistroTipo39.setTotalUPC(Long.valueOf(renglon39.getTotalUpcAdicional()));
		return lineaRegistroTipo39;
	}
	
	public LineaRegistroTipo39 convertRiesgosRenglon39ToLineaRegistroTipo39(
			ActivosRiesgosTotalesRenglon39Out renglon39){
	
		LineaRegistroTipo39 lineaRegistroTipo39 = activosCommonsConverter.convertRenglon39Totales2LineaRegistroTipo39(
				TipoArchivo.RIESGOS,renglon39);
		lineaRegistroTipo39.setNetoAportes(
				DataTypeConverter.convert(renglon39.getTotalNetoAportes(), Long.class) );
		
		return lineaRegistroTipo39;
	}
	
	public LineaRegistroTipo39 convertSimpleRenglon39ToLineaRegistroTipo39(
			SubsistemaPila subsistema,
			CommonActivosTotalesRenglon39 renglon39) {
		
		TipoArchivo tipoArchivo;
		switch(subsistema){
		case PARAFISCALES_CAJAS:
			tipoArchivo = TipoArchivo.CAJAS; break;
		case PARAFISCALES_SENA:
			tipoArchivo = TipoArchivo.SENA; break;
		case PARAFISCALES_ICBF:
			tipoArchivo = TipoArchivo.ICBF; break;
		case PARAFISCALES_ESAP:
			tipoArchivo = TipoArchivo.ESAP; break;
		case PARAFISCALES_MINEDU:
			tipoArchivo = TipoArchivo.MEN; break;
		default:
			throw new RuntimeException("Subsistema no contemplado: " + subsistema);
		}
		
		LineaRegistroTipo39 lineaRegistroTipo39 = activosCommonsConverter.convertRenglon39Totales2LineaRegistroTipo39(tipoArchivo, renglon39);
		return lineaRegistroTipo39; 
	}
}
