package net.jaimetorres.pila.approval.core.converters;

import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosEncabezadoOutputFile;
import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosLiquidacionDetalladaOutputFile;
import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosTotalesAporte;
import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosTotalesMora;
import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonActivosTotalesRenglon39;
import net.jaimetorres.pila.approval.utils.DataTypeConverter;
import net.jaimetorres.pila.approval.utils.DateUtils;
import net.jaimetorres.validaciones.entidad.RegistroAportante;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;
import net.jaimetorres.validaciones.general.FinalidadPlanilla;
import net.jaimetorres.validaciones.general.TipoArchivo;

public class ActivosCommonsConverter {

	public LineaRegistroTipo1 convertEncabezado2LineaRegistroTipo1(TipoArchivo tipoArchivo, CommonActivosEncabezadoOutputFile encabezado){
		
		LineaRegistroTipo1 lineaRegistroTipo1 = new LineaRegistroTipo1();
		lineaRegistroTipo1.setTipoArchivo(tipoArchivo);
		
		//Requerido por la planilla N (no afecta para las planillas diferentes a N)
		lineaRegistroTipo1.setFinalidadPlanilla(FinalidadPlanilla.CORRECCION_NORMAL);
		
		//lineaRegistroTipo1.setSecuencia(DataTypeConverter.convert(encabezado.getNroRegistro(),Integer.class));
		//lineaRegistroTipo1.setIndiceLinea(DataTypeConverter.convert(encabezado.getNroRegistro(),Integer.class)+1);
		lineaRegistroTipo1.setIndiceLinea(encabezado.getLineNumber());
		
		//XXX lineaRegistroTipo1 (encabezado.getTipoRegistro());
		lineaRegistroTipo1.setDigitoVerificacionAdministradora(encabezado.getDvAdm());
		lineaRegistroTipo1.setNitAdministradora(encabezado.getNroIdentificacionAdm());
		lineaRegistroTipo1.setRazonSocial(encabezado.getRazonSocialAportante());
		lineaRegistroTipo1.setTipoDocumento(encabezado.getTipoIdentificacionAportante());
		lineaRegistroTipo1.setNumeroDocumento(encabezado.getNroIdentificacionAportante());
		lineaRegistroTipo1.setNumeroPlanilla(encabezado.getNroPlanilla());
		lineaRegistroTipo1.setTipoAportante(DataTypeConverter.convert(encabezado.getTipoAportante(),Integer.class));
		lineaRegistroTipo1.setTipoPlanilla(encabezado.getTipoPlanilla());
		lineaRegistroTipo1.setFechaPagoPlanillaAsociada(encabezado.getFechaPagoPlanillaAsociada());
		lineaRegistroTipo1.setFechaPagoPlanilla(encabezado.getFechaPago());
		lineaRegistroTipo1.setNumeroPlanillaAsociada(encabezado.getNroPlanillaAsociada());
		lineaRegistroTipo1.setFormaPresentacion(encabezado.getFormaPresentacion());
		lineaRegistroTipo1.setCodigoSucursalAportante(encabezado.getCodigoSucursal());
		lineaRegistroTipo1.setNombreSucursalAportante(encabezado.getNombreSucursal());
		lineaRegistroTipo1.setNumeroEmpleados(DataTypeConverter.convert(encabezado.getNroAfiliadosAdm(),Integer.class));
		lineaRegistroTipo1.setNroTotalEmpleadosPlanilla(DataTypeConverter.convert(encabezado.getNroEmpleados(),Integer.class));
		lineaRegistroTipo1.setCodigoOperadorInfo(DataTypeConverter.convert(encabezado.getCodigoOperador(),Integer.class));
		
		//POJO RegistroAportante
		RegistroAportante registroAportante = new RegistroAportante();
		registroAportante.setTipoIdentificacion(encabezado.getTipoIdentificacionAportante());
		registroAportante.setNumeroIdentificacion(encabezado.getNroIdentificacionAportante());
		registroAportante.setSucursal(encabezado.getCodigoSucursal());
		registroAportante.setDigitoVerificacion(DataTypeConverter.convert(encabezado.getDvAportante(),Integer.class));
		registroAportante.setTipoPersona(DataTypeConverter.convert(encabezado.getTipoPersona(),Character.class));
		registroAportante.setClase(DataTypeConverter.convert(encabezado.getClaseAportante(),Character.class));
		registroAportante.setFormaPresentacion(DataTypeConverter.convert(encabezado.getFormaPresentacion(),Character.class));
		registroAportante.setDireccion(encabezado.getDireccionCorrespondencia());
		registroAportante.setDepartamento(DataTypeConverter.convert(encabezado.getCodigoDepartamento(),Integer.class));
		registroAportante.setMunicipio(DataTypeConverter.convert(encabezado.getCodigoMunicipio(),Integer.class));
		registroAportante.setTelefono(encabezado.getTelefono());
		registroAportante.setFax(encabezado.getFax());
		registroAportante.setCorreo(encabezado.getCorreoElectronico());
		registroAportante.setRazonSocial(encabezado.getRazonSocialAportante());
		registroAportante.setCodigoOperador(DataTypeConverter.convert(encabezado.getCodigoOperador(),Integer.class));
		registroAportante.setTipoAportante(DataTypeConverter.convert(encabezado.getTipoAportante(),Integer.class));
		registroAportante.setFechaUltimaActualizacion(DateUtils.convertStringToSqlDate(encabezado.getFechaUltimaActualizacion()));
		registroAportante.setTipoEntidad(DataTypeConverter.convert(encabezado.getNaturalezaJuridica(),Integer.class));
		registroAportante.setTipoPersona(DataTypeConverter.convert(encabezado.getTipoPersona(),Character.class));
		registroAportante.setNaturalezaJuridica(encabezado.getNaturalezaJuridica());
		
		lineaRegistroTipo1.setRegistroAportante(registroAportante);
		
		return lineaRegistroTipo1;
	}

	public LineaRegistroTipo2 convertLiquidacionDetallada2LineaRegistroTipo2(CommonActivosLiquidacionDetalladaOutputFile ld) {
		LineaRegistroTipo2 lineaRegistroTipo2 = new LineaRegistroTipo2();
		
		//lineaRegistroTipo2.setSecuencia(DataTypeConverter.convert(ld.getSecuencia(),Integer.class));
		//lineaRegistroTipo2.setConsecutivo(DataTypeConverter.convert(ld.getSecuencia(),Integer.class));
		lineaRegistroTipo2.setIndiceLinea(ld.getLineNumber());
		
		//XXX lineaRegistroTipo2 (ld.getTipoRegistro());
		lineaRegistroTipo2.setTipoDocumento(ld.getTipoIdentificacion());
		lineaRegistroTipo2.setNumeroDocumento(ld.getNroIdentificacion());
		lineaRegistroTipo2.setTipoCotizante(DataTypeConverter.convert(ld.getTipoCotizante(),Integer.class));
		lineaRegistroTipo2.setSubTipoCotizante(DataTypeConverter.convert(ld.getSubtipoCotizante(),Integer.class));
		lineaRegistroTipo2.setExtranjeroNoObligadoCotizar(ld.getExtranjeroNoObligadoCotPension());
		lineaRegistroTipo2.setColombianoResideExterior(ld.getColombianoResidenteExterior());
		lineaRegistroTipo2.setDepartamento(ld.getCodigoDepartamento());
		lineaRegistroTipo2.setMunicipio(DataTypeConverter.convert(ld.getCodigoMunicipio(),Integer.class));
		lineaRegistroTipo2.setPrimerApellido(ld.getPrimerApellido());
		lineaRegistroTipo2.setSegundoApellido(ld.getSegundoApellido());
		lineaRegistroTipo2.setPrimerNombre(ld.getPrimerNombre());
		lineaRegistroTipo2.setSegundoNombre(ld.getSegundoNombre());
		lineaRegistroTipo2.setSalarioBasico(DataTypeConverter.convert(ld.getSalarioBasico(),Long.class));
		lineaRegistroTipo2.setSalarioIntegral(ld.getSalarioIntegral());
		
		lineaRegistroTipo2.setIng(DataTypeConverter.convert(ld.getNovedadIngreso(),Character.class));
		lineaRegistroTipo2.setRet(DataTypeConverter.convert(ld.getNovedadRetiro(),Character.class));
		lineaRegistroTipo2.setVsp(DataTypeConverter.convert(ld.getNovedadVariacionSalarioPermanente(),Character.class));
		lineaRegistroTipo2.setVst(DataTypeConverter.convert(ld.getNovedadVariacionSalarioTransitoria(),Character.class));
		lineaRegistroTipo2.setSln(DataTypeConverter.convert(ld.getNovedadSuspension(),Character.class));
		lineaRegistroTipo2.setIge(DataTypeConverter.convert(ld.getNovedadIncapacidadEnfermedadGeneral(),Character.class));
		lineaRegistroTipo2.setLma(DataTypeConverter.convert(ld.getNovedadLicenciaMaternidad(),Character.class));
		lineaRegistroTipo2.setVac(DataTypeConverter.convert(ld.getNovedadVacaciones(),Character.class));
		lineaRegistroTipo2.setCorrecciones(DataTypeConverter.convert(ld.getNovedadCorreccion(),Character.class));
		
		lineaRegistroTipo2.setFechaIngreso(DateUtils.convertStringToDate(ld.getFechaIngreso()));
		lineaRegistroTipo2.setFechaRetiro(DateUtils.convertStringToDate(ld.getFechaRetiro()));
		lineaRegistroTipo2.setFechaInicioVSP(DateUtils.convertStringToDate(ld.getFechaInicioVSP()));
		lineaRegistroTipo2.setFechaInicioSLN(DateUtils.convertStringToDate(ld.getFechaInicioSLN()));
		lineaRegistroTipo2.setFechaFinSLN(DateUtils.convertStringToDate(ld.getFechaFinSLN()));
		lineaRegistroTipo2.setFechaInicioIGE(DateUtils.convertStringToDate(ld.getFechaInicioIGE()));
		lineaRegistroTipo2.setFechaFinIGE(DateUtils.convertStringToDate(ld.getFechaFinIGE()));
		lineaRegistroTipo2.setFechaInicioLMA(DateUtils.convertStringToDate(ld.getFechaInicioLMA()));
		lineaRegistroTipo2.setFechaFinLMA(DateUtils.convertStringToDate(ld.getFechaFinLMA()));
		lineaRegistroTipo2.setFechaInicioVAC(DateUtils.convertStringToDate(ld.getFechaInicioVAC()));
		lineaRegistroTipo2.setFechaFinVAC(DateUtils.convertStringToDate(ld.getFechaFinVAC()));
		lineaRegistroTipo2.llenarNovedades();
		
		return lineaRegistroTipo2;
	}

	public LineaRegistroTipo31 convertTotalesAportes2LineaRegistroTipo31(
			TipoArchivo tipoArchivo,
			CommonActivosTotalesAporte totalesAporte){
		LineaRegistroTipo31 lineaRegistroTipo31 = new LineaRegistroTipo31();
		
		lineaRegistroTipo31.setIndiceLinea(totalesAporte.getLineNumber());
		lineaRegistroTipo31.setTipoArchivo(tipoArchivo);
		lineaRegistroTipo31.setIbc(DataTypeConverter.convert(totalesAporte.getTotalIbc(),Long.class));
		lineaRegistroTipo31.setCotizacion(DataTypeConverter.convert(totalesAporte.getTotalCotizacion(),Long.class));
		return lineaRegistroTipo31;
	}
	
	public LineaRegistroTipo36 convertTotalesMora2LineaRegistroTipo36(
			TipoArchivo tipoArchivo, CommonActivosTotalesMora totalesMora){
		LineaRegistroTipo36 lineaRegistroTipo36 = new LineaRegistroTipo36();
		lineaRegistroTipo36.setIndiceLinea(totalesMora.getLineNumber());
		lineaRegistroTipo36.setTipoArchivo(tipoArchivo);
		lineaRegistroTipo36.setDiasMora(Integer.valueOf(totalesMora.getDiasMora()));
		lineaRegistroTipo36.setMoraCotizacion(Long.valueOf(totalesMora.getMoraCotizacion()));
		return lineaRegistroTipo36;
	}
	
	public LineaRegistroTipo39 convertRenglon39Totales2LineaRegistroTipo39(
			TipoArchivo tipoArchivo, CommonActivosTotalesRenglon39 totalesRenglon39){
		LineaRegistroTipo39 lineaRegistroTipo39 = new LineaRegistroTipo39();
		lineaRegistroTipo39.setIndiceLinea(totalesRenglon39.getLineNumber());
		lineaRegistroTipo39.setTipoArchivo(tipoArchivo);
		lineaRegistroTipo39.setTotalCotizacion(Long.valueOf(totalesRenglon39.getTotalCotizacion()));
		return lineaRegistroTipo39;
	}

}
