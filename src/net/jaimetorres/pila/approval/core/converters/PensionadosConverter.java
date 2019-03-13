package net.jaimetorres.pila.approval.core.converters;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.jaimetorres.pila.approval.pojos.output.pensionados.cajas.PensionadosCajasEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.cajas.PensionadosCajasLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.cajas.PensionadosCajasTotalesOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.fsp.PensionadosFspEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.fsp.PensionadosFspLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.fsp.PensionadosFspTotalesOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.pensionados.pension.PensionadosPensionTotalesRenglon39Out;
import net.jaimetorres.pila.approval.pojos.output.pensionados.registroA.PensionadosRegistroAEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludTotalesRenglon31Out;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludTotalesRenglon36Out;
import net.jaimetorres.pila.approval.pojos.output.pensionados.salud.PensionadosSaludTotalesRenglon39Out;
import net.jaimetorres.pila.approval.utils.DataTypeConverter;
import net.jaimetorres.pila.approval.utils.DateUtils;
import net.jaimetorres.validaciones.entidad.RegistroAportante;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;
import net.jaimetorres.validaciones.estructura.LineaTotales;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;
import net.jaimetorres.validaciones.general.TipoArchivo;

public class PensionadosConverter {

	private PensionadosCommonsConverter pensionadosCommonsConverter = new PensionadosCommonsConverter();
	
	//#################
	//###ENCABEZADOS###
	//#################
	public LineaRegistroTipo1Pensionado convertRegistroA2LineaRegistroTipo1(
			PensionadosRegistroAEncabezadoOut encabezado) {
		
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = pensionadosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.PEN_TIPO_A, encabezado);
		lineaRegistroTipo1.setTipoPagador(Integer.valueOf(encabezado.getTipoPagadorPensiones()));
		
		RegistroAportante registroAportante = lineaRegistroTipo1.getRegistroAportante();
		registroAportante.setTipoEntidad(Integer.valueOf(encabezado.getNaturalezaJuridica()));
		registroAportante.setTipoPersona(DataTypeConverter.convert(encabezado.getTipoPersona(),Character.class));
		registroAportante.setTipoAportante(Integer.valueOf(encabezado.getTipoPagadorPensiones()));
		registroAportante.setCorreo(encabezado.getEmail());
		
		registroAportante.setTipoIDRep(encabezado.getTipoIdentificacionRepLegal());
		registroAportante.setNumeroIDRep(encabezado.getNroIdentificacionRepLegal());
		registroAportante.setDigitoVerificacionRep(DataTypeConverter.convert(encabezado.getDvRepLegal(),Character.class));
		
		registroAportante.setPNombreRep(encabezado.getPrimerNombreRepLegal());
		registroAportante.setSNombreRep(encabezado.getSegundoNombreRepLegal());
		registroAportante.setPApellidoRep(encabezado.getPrimerApellidoRepLegal());
		registroAportante.setSApellidoRep(encabezado.getSegundoApellidoRepLegal());

		//XXX ASK DAISY
		//encabezado.getPeriodoPago()
		
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo1Pensionado convertPensionEncabezado2LineaRegistroTipo1(
			PensionadosPensionEncabezadoOut encabezado) {
		
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = pensionadosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.PEN_PENSION, encabezado);
		lineaRegistroTipo1.setNumeroRegistrosTipo2(DataTypeConverter.convert(encabezado.getNroRegistrosTipo2(),Integer.class));
		// TODO 
		//encabezado.getCodigoFormato()
		//encabezado.getNroIdentificacionAdm()
		//encabezado.getDvAdm()
		//encabezado.getClasePagadorPensiones()
		//encabezado.getNroPlanilla()
		//encabezado.getNroTotalPensionados()
		//encabezado.getNroAfiliadosAdm()
		//encabezado.getTipoPlanilla()
		//encabezado.getFechaUltimaActualizacion()
		
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo1Pensionado convertFspEncabezado2LineaRegistroTipo1(
			PensionadosFspEncabezadoOut encabezado) {
		
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = pensionadosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.PEN_FSP, encabezado);
		
		// TODO 
		//encabezado.getCodigoFormato()
		//encabezado.getClasePagadorPensiones()
		//encabezado.getNroPlanilla()
		
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo1Pensionado convertSaludEncabezado2LineaRegistroTipo1(
			PensionadosSaludEncabezadoOut encabezado) {
		
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = pensionadosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.PEN_SALUD, encabezado);
		lineaRegistroTipo1.setNumeroRegistrosTipo2(DataTypeConverter.convert(encabezado.getNroRegistrosTipo2(),Integer.class));
		// TODO 
		//encabezado.getCodigoFormato()
		//encabezado.getClasePagadorPensiones()
		//encabezado.getNroTotalPensionados()
		//encabezado.getNroAfiliadosAdm()
		//encabezado.getTipoPlanilla()
		
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo1Pensionado convertCajasEncabezado2LineaRegistroTipo1(
			PensionadosCajasEncabezadoOut encabezado) {
		
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = pensionadosCommonsConverter.convertEncabezado2LineaRegistroTipo1(TipoArchivo.PEN_CAJAS, encabezado);
		
		lineaRegistroTipo1.setTotalMesadas(Long.valueOf(encabezado.getTotalMesadas()));
		lineaRegistroTipo1.setNumeroRegistrosTipo2(DataTypeConverter.convert(encabezado.getNroRegistrosTipo2(), Integer.class));
		// TODO 
		//encabezado.getSecuencia()
		//encabezado.getNroIdentificacionAdm()
		//encabezado.getDvAdm()
		//encabezado.getCodigoAdm()
		//encabezado.getNroPlanilla()
		//encabezado.getFechaUltimaActualizacion()

		return lineaRegistroTipo1;
	}

	//##############################################
	//###LIQUIDACION_DETALLADA (REGISTROS TIPO 2)###
	//##############################################
	public List<LineaRegistroTipo2Pensionado> convertPensionLiquidacionDetalladaList2LineaRegistroTipo2List(
			List<PensionadosPensionLiquidacionDetalladaOut> liquidacionDetalladaList) {

		Function<PensionadosPensionLiquidacionDetalladaOut,LineaRegistroTipo2Pensionado> mapper = ld -> {
			
			LineaRegistroTipo2Pensionado lineaRegistroTipo2 = pensionadosCommonsConverter.convertLiquidacionDetallada2LineaRegistroTipo2(TipoArchivo.PEN_PENSION,ld);
			
			//Specific to PENSION
			lineaRegistroTipo2.setPensionadoExterior(ld.getColombianoResidenteExterior());
			lineaRegistroTipo2.setTdp( DataTypeConverter.convert(ld.getNovedadTrasladoAfpDesde(),Character.class));
			lineaRegistroTipo2.setTap( DataTypeConverter.convert(ld.getNovedadTrasladoAfpA(),Character.class));
			lineaRegistroTipo2.setDiasAfp( DataTypeConverter.convert(ld.getDiasCotizadosPension(),Integer.class));
			lineaRegistroTipo2.setTarifaAfp( DataTypeConverter.convert(ld.getTarifaPension(),Double.class));
			lineaRegistroTipo2.setCotizacionObligatoriaAfp( DataTypeConverter.convert(ld.getCotizacionPension(),Long.class));
			lineaRegistroTipo2.setAporteVoluntarioAfiliadoAfp( DataTypeConverter.convert(ld.getCotizacionVoluntariaPensionAfiliado(),Long.class));
			lineaRegistroTipo2.setTotalCotizacionAFP( DataTypeConverter.convert(ld.getTotalCotizacion(),Long.class));
			lineaRegistroTipo2.setAportesFSPsolidaridad( DataTypeConverter.convert(ld.getAporteFondoSolidaridadPensionalSubcuentaSolidaridad(),Long.class));
			lineaRegistroTipo2.setAportesFSPsubsistencia( DataTypeConverter.convert(ld.getAporteFondoSolidaridadPensionalSubcuentaSubsistencia(),Long.class));
			lineaRegistroTipo2.setValorNoRetenidoApteVoluntario( DataTypeConverter.convert(ld.getValorNoRetenidoAportesVoluntarios(),Long.class));
			
			return lineaRegistroTipo2;
		};

		return liquidacionDetalladaList.stream().map(mapper).collect(Collectors.toList());
	}
	
	public List<LineaRegistroTipo2Pensionado> convertFspLiquidacionDetalladaList2LineaRegistroTipo2List(
			List<PensionadosFspLiquidacionDetalladaOut> liquidacionDetalladaList) {
		
		Function<PensionadosFspLiquidacionDetalladaOut,LineaRegistroTipo2Pensionado> mapper = ld -> {
			
			LineaRegistroTipo2Pensionado lineaRegistroTipo2 = pensionadosCommonsConverter.convertLiquidacionDetallada2LineaRegistroTipo2(TipoArchivo.PEN_FSP,ld);
			
			//Specific to FSP
			lineaRegistroTipo2.setDiasFsp( DataTypeConverter.convert(ld.getDiasCotizadosFsp(),Integer.class));
			lineaRegistroTipo2.setTarifaFSP( DataTypeConverter.convert(ld.getTarifaFsp(),Double.class));
			lineaRegistroTipo2.setAportesFSPsubsistenciaSuperior10SMLV(DataTypeConverter.convert(ld.getCotizacionFsp(),Long.class));
			
			return lineaRegistroTipo2;
		};
		
		return liquidacionDetalladaList.stream().map(mapper).collect(Collectors.toList());
	}
	
	public List<LineaRegistroTipo2Pensionado> convertSaludLiquidacionDetalladaList2LineaRegistroTipo2List(
			List<PensionadosSaludLiquidacionDetalladaOut> liquidacionDetalladaList) {
		
		Function<PensionadosSaludLiquidacionDetalladaOut,LineaRegistroTipo2Pensionado> mapper = ld -> {
			
			LineaRegistroTipo2Pensionado lineaRegistroTipo2 = pensionadosCommonsConverter.convertLiquidacionDetallada2LineaRegistroTipo2(TipoArchivo.PEN_SALUD,ld);
			
			//Specific to SALUD
			lineaRegistroTipo2.setPensionadoExterior( ld.getColombianoResidenteExterior() );
			lineaRegistroTipo2.setTde( DataTypeConverter.convert(ld.getNovedadTrasladoEpsDesde(),Character.class));
			lineaRegistroTipo2.setTae( DataTypeConverter.convert(ld.getNovedadTrasladoEpsA(),Character.class));
			lineaRegistroTipo2.setDiasEps( DataTypeConverter.convert(ld.getDiasCotizadosSalud(),Integer.class));
			lineaRegistroTipo2.setTarifaEps( DataTypeConverter.convert(ld.getTarifaSalud(),Double.class));
			lineaRegistroTipo2.setCotizacionObligatoriaEps( DataTypeConverter.convert(ld.getCotizacionSalud(),Long.class));
			lineaRegistroTipo2.setValorUPCAdicional( DataTypeConverter.convert(ld.getValorUpcAdicional(),Long.class));
			lineaRegistroTipo2.setFechaRadicacionExterior( DateUtils.convertStringToDate(ld.getFechaRadicacionExterior() ));
			
			return lineaRegistroTipo2;
		};
		
		return liquidacionDetalladaList.stream().map(mapper).collect(Collectors.toList());
	}
	
	public List<LineaRegistroTipo2Pensionado> convertCajasLiquidacionDetalladaList2LineaRegistroTipo2List(
			List<PensionadosCajasLiquidacionDetalladaOut> liquidacionDetalladaList) {
		
		Function<PensionadosCajasLiquidacionDetalladaOut,LineaRegistroTipo2Pensionado> mapper = ld -> {
			
			LineaRegistroTipo2Pensionado lineaRegistroTipo2 = pensionadosCommonsConverter.convertLiquidacionDetallada2LineaRegistroTipo2(TipoArchivo.PEN_CAJAS,ld);
			
			//Specific to CAJAS
			//SE REALIZA DICHA ASIGNACION (TipoPension=1), POR LOS EFECTOS DE LA PARAMETRIZACION DE LAS VALIDACIONES 
			lineaRegistroTipo2.setTipoPension(1);
			
			lineaRegistroTipo2.setTarifaCCF( DataTypeConverter.convert(ld.getTarifaCajas(),Double.class));
			lineaRegistroTipo2.setAporteCCF(( DataTypeConverter.convert(ld.getCotizacionCajas(),Long.class)));
			lineaRegistroTipo2.setDiasCcf( DataTypeConverter.convert(ld.getDiasCotizadosCajas(),Integer.class));
			
			return lineaRegistroTipo2;
		};
		
		return liquidacionDetalladaList.stream().map(mapper).collect(Collectors.toList());
	}
	
	//####################
	//##TOTALES RENGLON 31
	//####################
	public LineaRegistroTipo31 convertPensionRenglon31ToLineaRegistroTipo31(
			PensionadosPensionTotalesRenglon31Out renglon31) {
		
		LineaRegistroTipo31 lineaRegistroTipo31 = pensionadosCommonsConverter.convertTotalesAportes2LineaRegistroTipo31(
				TipoArchivo.PEN_PENSION,renglon31);
		
		lineaRegistroTipo31.setCotizacion(Long.valueOf(renglon31.getTotalCotizacionObligatoria()));
		lineaRegistroTipo31.setCotizacionVoluntariaAfiliado(Long.valueOf(renglon31.getTotalCotizacionVoluntariaAfiliado() ));
		lineaRegistroTipo31.setTotalCotizacion(Long.valueOf(renglon31.getTotalCotizacion()));
		lineaRegistroTipo31.setFondoSolidaridad(Long.valueOf(renglon31.getTotalAporteFondoSolidaridadPensionalSubcuentaSolidaridad()));
		lineaRegistroTipo31.setFondoSubsistenci(Long.valueOf(renglon31.getTotalAporteFondoSolidaridadPensionalSubcuentaSubsistencia()));
		
		return lineaRegistroTipo31;
	}
	
	public LineaRegistroTipo31 convertSaludRenglon31ToLineaRegistroTipo31(
			PensionadosSaludTotalesRenglon31Out renglon31) {
		
		LineaRegistroTipo31 lineaRegistroTipo31 = pensionadosCommonsConverter.convertTotalesAportes2LineaRegistroTipo31(
				TipoArchivo.PEN_SALUD,renglon31);
		
		lineaRegistroTipo31.setCotizacion(Long.valueOf(renglon31.getTotalCotizacion()));
		lineaRegistroTipo31.setTotalUPC(Long.valueOf(renglon31.getTotalUpcAdicional()));
		
		return lineaRegistroTipo31;
	}
	
	//####################
	//##TOTALES RENGLON 36
	//####################
	public LineaRegistroTipo36 convertPensionRenglon36ToLineaRegistroTipo36(
			PensionadosPensionTotalesRenglon36Out renglon36) {
		
		LineaRegistroTipo36 lineaRegistroTipo36 = pensionadosCommonsConverter.convertTotalesMora2LineaRegistroTipo36(
				TipoArchivo.PEN_PENSION,renglon36);
		
		//XXX 1 TODO ASK DAISY lineaRegistroTipo36.SETMORACOTIZACIONVOLUNTARIA
		//renglon36.getMoraTotalCotizacionVoluntariaAfiliado();
		
		//XXX 2 TODO ASK DAISY renglon36.getMoraTotalCotizacion();
		
		lineaRegistroTipo36.setMoraFondoSolidaridad(Long.valueOf(renglon36.getMoraAporteFondoSolidaridadPensionalSubcuentaSolidaridad()));
		lineaRegistroTipo36.setMoraFondoSubsistencia(Long.valueOf(renglon36.getMoraAporteFondoSolidaridadPensionalSubcuentaSubsistencia()));
		
		return lineaRegistroTipo36;
	}
	
	public LineaRegistroTipo36 convertSaludRenglon36ToLineaRegistroTipo36(
			PensionadosSaludTotalesRenglon36Out renglon36) {
		
		LineaRegistroTipo36 lineaRegistroTipo36 = pensionadosCommonsConverter.convertTotalesMora2LineaRegistroTipo36(
				TipoArchivo.PEN_SALUD,renglon36);
		
		lineaRegistroTipo36.setMoraUPC(Long.valueOf(renglon36.getMoraUpcAdicional()));
		return lineaRegistroTipo36;
	}
	
	//####################
	//##TOTALES RENGLON 39
	//####################
	public LineaRegistroTipo39 convertPensionRenglon39ToLineaRegistroTipo39(
			PensionadosPensionTotalesRenglon39Out renglon39) {
		
		LineaRegistroTipo39 lineaRegistroTipo39 = pensionadosCommonsConverter.convertRenglon39Totales2LineaRegistroTipo39(
				TipoArchivo.PEN_PENSION,renglon39);
		lineaRegistroTipo39.setIbc(Long.valueOf(renglon39.getValorIngresoBaseCotizacion()));
		lineaRegistroTipo39.setTotalCotizacionObligatoria(Long.valueOf(renglon39.getTotalCotizacionObligatoria()));
		lineaRegistroTipo39.setTotalCotizacionVoluntaria(Long.valueOf(renglon39.getTotalCotizacionVoluntariaAfiliado()));
		
		lineaRegistroTipo39.setTotalFondoSolidaridad(Long.valueOf(renglon39.getTotalAporteFondoSolidaridadPensionalSubcuentaSolidaridad()));
		lineaRegistroTipo39.setTotalFondoSubsustencia(Long.valueOf(renglon39.getTotalAporteFondoSolidaridadPensionalSubcuentaSubsistencia()));
		return lineaRegistroTipo39;
	}
	
	public LineaRegistroTipo39 convertSaludRenglon39ToLineaRegistroTipo39(
			PensionadosSaludTotalesRenglon39Out renglon39) {
		
		LineaRegistroTipo39 lineaRegistroTipo39 = pensionadosCommonsConverter.convertRenglon39Totales2LineaRegistroTipo39(
				TipoArchivo.PEN_SALUD,renglon39);
		lineaRegistroTipo39.setIbc(Long.valueOf(renglon39.getValorIngresoBaseCotizacion()));
		lineaRegistroTipo39.setTotalAportesFosyga(Long.valueOf(renglon39.getAporteFondoSolidaridadGarantiaFosyga()));
		lineaRegistroTipo39.setTotalUPC(Long.valueOf(renglon39.getTotalUpcAdicional()));
		return lineaRegistroTipo39;
	}
	
	//################
	//##TOTALES (ONLY)
	//################
	public LineaTotales convertFspRenglonTotalesToLineaTotales(
			PensionadosFspTotalesOut renglonTotales) {
		
		LineaTotales lineaTotales = pensionadosCommonsConverter.convertRenglonTotalesOnly2LineaTotales(
				TipoArchivo.PEN_FSP,renglonTotales);
		
		return lineaTotales;
	}
	
	public LineaTotales convertCajasRenglonTotalesToLineaTotales(
			PensionadosCajasTotalesOut renglonTotales) {
		
		LineaTotales lineaTotales = pensionadosCommonsConverter.convertRenglonTotalesOnly2LineaTotales(
				TipoArchivo.PEN_CAJAS,renglonTotales);
		
		return lineaTotales;
	}
}
