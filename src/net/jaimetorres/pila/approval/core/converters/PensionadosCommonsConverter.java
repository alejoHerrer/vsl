package net.jaimetorres.pila.approval.core.converters;

import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosEncabezadoOutputFile;
import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosLiquidacionDetalladaOutputFile;
import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosTotalesAporte;
import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosTotalesMora;
import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosTotalesOnly;
import net.jaimetorres.pila.approval.pojos.output.pensionados.commons.CommonPensionadosTotalesRenglon39;
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

public class PensionadosCommonsConverter {

	public LineaRegistroTipo1Pensionado convertEncabezado2LineaRegistroTipo1(TipoArchivo tipoArchivo, CommonPensionadosEncabezadoOutputFile encabezado){
		
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = new LineaRegistroTipo1Pensionado();
		lineaRegistroTipo1.setTipoArchivo(tipoArchivo);
		
		lineaRegistroTipo1.setIndiceLinea(encabezado.getLineNumber());
		lineaRegistroTipo1.setNumeroPlanilla(encabezado.getNroPlanilla());
		lineaRegistroTipo1.setRazonSocial(encabezado.getRazonSocialAportante());
		lineaRegistroTipo1.setTipoDocumento(encabezado.getTipoIdentificacionAportante());
		lineaRegistroTipo1.setNumeroDocumento(encabezado.getNroIdentificacionAportante());
		lineaRegistroTipo1.setCodigoSucursal(encabezado.getCodigoSucursal());
		lineaRegistroTipo1.setNombreSucursal(encabezado.getNombreSucursal());
		lineaRegistroTipo1.setFormaPresentacion(encabezado.getFormaPresentacion());
		lineaRegistroTipo1.setTipoPlanilla(encabezado.getTipoPlanilla());
		lineaRegistroTipo1.setCorreo(encabezado.getCorreoElectronico());
		lineaRegistroTipo1.setNumeroPensionados(DataTypeConverter.convert(encabezado.getNroTotalPensionados(),Integer.class));
		lineaRegistroTipo1.setNumeroAfiliadosAdministradora(DataTypeConverter.convert(encabezado.getNroAfiliadosAdm(),Integer.class));
		lineaRegistroTipo1.setDiasMora(DataTypeConverter.convert(encabezado.getDiasMora(),Integer.class));
		
		//XXX CAMPOS DESCONOCIDOS
		//encabezado.getPeriodoPago()
		
		//POJO RegistroAportante
		RegistroAportante registroAportante = new RegistroAportante();
		registroAportante.setClase(DataTypeConverter.convert(encabezado.getClasePagadorPensiones(),Character.class));
		registroAportante.setTipoIdentificacion(encabezado.getTipoIdentificacionAportante());
		registroAportante.setNumeroIdentificacion(encabezado.getNroIdentificacionAportante());
		registroAportante.setDigitoVerificacion(DataTypeConverter.convert(encabezado.getDvAportante(),Integer.class));
		registroAportante.setRazonSocial(encabezado.getRazonSocialAportante());
		registroAportante.setSucursal(encabezado.getCodigoSucursal());
		registroAportante.setNombreSucursal(encabezado.getNombreSucursal());
		registroAportante.setFormaPresentacion(encabezado.getFormaPresentacion().charAt(0));
		registroAportante.setDireccion(encabezado.getDireccionCorrespondencia());
		registroAportante.setDepartamento(DataTypeConverter.convert(encabezado.getCodigoDepartamento(),Integer.class));
		registroAportante.setMunicipio(DataTypeConverter.convert(encabezado.getCodigoMunicipio(),Integer.class));
		registroAportante.setTelefono(encabezado.getTelefono());
		registroAportante.setFax(encabezado.getFax());
		registroAportante.setCorreo(encabezado.getCorreoElectronico());
		registroAportante.setFechaUltimaActualizacion(DateUtils.convertStringToDate(encabezado.getFechaUltimaActualizacion()));
		
		lineaRegistroTipo1.setRegistroAportante(registroAportante);
		
		return lineaRegistroTipo1;
	}
	
	public LineaRegistroTipo2Pensionado convertLiquidacionDetallada2LineaRegistroTipo2(TipoArchivo tipoArchivo, CommonPensionadosLiquidacionDetalladaOutputFile ld){
		LineaRegistroTipo2Pensionado lineaRegistroTipo2 = new LineaRegistroTipo2Pensionado();
		lineaRegistroTipo2.setTipoArchivo(tipoArchivo);
		lineaRegistroTipo2.setIndiceLinea(ld.getLineNumber());
		
		//pensionado
		lineaRegistroTipo2.setTipoDocumento(ld.getTipoIdentificacion());
		lineaRegistroTipo2.setNumeroDocumento(ld.getNroIdentificacion());
		lineaRegistroTipo2.setPrimerNombre(ld.getPrimerNombre());
		lineaRegistroTipo2.setSegundoNombre(ld.getSegundoNombre());
		lineaRegistroTipo2.setPrimerApellido(ld.getPrimerApellido());
		lineaRegistroTipo2.setSegundoApellido(ld.getSegundoApellido());
		
		//causante
		lineaRegistroTipo2.setTipoDocumentoCausante(ld.getTipoIdentificacionCausante());
		lineaRegistroTipo2.setNumeroDocumentoCausante(ld.getNroIdentificacionCausante());
		lineaRegistroTipo2.setPrimerNombreCausante(ld.getPrimerNombreCausante());
		lineaRegistroTipo2.setSegundoNombreCausante(ld.getSegundoNombreCausante());
		lineaRegistroTipo2.setPrimerApellidoCausante(ld.getPrimerApellidoCausante());
		lineaRegistroTipo2.setSegundoApellidoCausante(ld.getSegundoApellidoCausante());
		
		lineaRegistroTipo2.setTipoPension(DataTypeConverter.convert(ld.getTipoPension(),Integer.class));
		lineaRegistroTipo2.setPensionCompartida(ld.getPensionCompartida());
		lineaRegistroTipo2.setTipoPensionado(DataTypeConverter.convert(ld.getTipoPensionado(),Integer.class));
		
		lineaRegistroTipo2.setDepartamento(ld.getCodigoDepartamento());
		lineaRegistroTipo2.setMunicipio(ld.getCodigoMunicipio());
		
		lineaRegistroTipo2.setIng( DataTypeConverter.convert(ld.getNovedadIngreso(),Character.class) );
		lineaRegistroTipo2.setRet( DataTypeConverter.convert(ld.getNovedadRetiro(),Character.class) );
		lineaRegistroTipo2.setVsp( DataTypeConverter.convert(ld.getNovedadVariacionSalarioPermanente(),Character.class) );
		lineaRegistroTipo2.setSus( DataTypeConverter.convert(ld.getNovedadSuspension(),Character.class) );
		
		lineaRegistroTipo2.setFechaIngreso(DateUtils.convertStringToDate(ld.getFechaIngreso()));
		lineaRegistroTipo2.setFechaRetiro(DateUtils.convertStringToDate(ld.getFechaRetiro()));
		lineaRegistroTipo2.setFechaInicioVSP(DateUtils.convertStringToDate(ld.getFechaInicioVSP()));
		lineaRegistroTipo2.setFechaInicioSUS(DateUtils.convertStringToDate(ld.getFechaInicioSUS()));
		lineaRegistroTipo2.setFechaFinSUS(DateUtils.convertStringToDate(ld.getFechaFinSUS()));
		
		lineaRegistroTipo2.setMesadaPensional( DataTypeConverter.convert(ld.getMesadaPensional(),Long.class) );
		lineaRegistroTipo2.setIbc( DataTypeConverter.convert(ld.getValorIngresoBaseCotizacion(),Long.class) );
		
		return lineaRegistroTipo2;
	}
	
	public LineaRegistroTipo31 convertTotalesAportes2LineaRegistroTipo31(
			TipoArchivo tipoArchivo,
			CommonPensionadosTotalesAporte totalesAporte){
		LineaRegistroTipo31 lineaRegistroTipo31 = new LineaRegistroTipo31();
		
		lineaRegistroTipo31.setIndiceLinea(totalesAporte.getLineNumber());
		lineaRegistroTipo31.setTipoArchivo(tipoArchivo);
		lineaRegistroTipo31.setIbc(Long.valueOf(totalesAporte.getTotalIbc()));
		
		return lineaRegistroTipo31;
	}
	
	public LineaRegistroTipo36 convertTotalesMora2LineaRegistroTipo36(
			TipoArchivo tipoArchivo, CommonPensionadosTotalesMora totalesMora){
		LineaRegistroTipo36 lineaRegistroTipo36 = new LineaRegistroTipo36();
		lineaRegistroTipo36.setIndiceLinea(totalesMora.getLineNumber());
		lineaRegistroTipo36.setTipoArchivo(tipoArchivo);
		lineaRegistroTipo36.setDiasMora(Integer.valueOf(totalesMora.getDiasMora()));
		lineaRegistroTipo36.setMoraCotizacion(Long.valueOf(totalesMora.getMoraTotalCotizacionObligatoria()));
		return lineaRegistroTipo36;
	}
	
	public LineaRegistroTipo39 convertRenglon39Totales2LineaRegistroTipo39(
			TipoArchivo tipoArchivo, CommonPensionadosTotalesRenglon39 totalesRenglon39){
		LineaRegistroTipo39 lineaRegistroTipo39 = new LineaRegistroTipo39();
		lineaRegistroTipo39.setIndiceLinea(totalesRenglon39.getLineNumber());
		lineaRegistroTipo39.setTipoArchivo(tipoArchivo);
		lineaRegistroTipo39.setTotalCotizacion(Long.valueOf(totalesRenglon39.getTotalCotizacion()));
		return lineaRegistroTipo39;
	}

	public LineaTotales convertRenglonTotalesOnly2LineaTotales(TipoArchivo tipoArchivo,
			CommonPensionadosTotalesOnly totalesOnly) {
		
		LineaTotales lineaTotales = new LineaTotales();
		lineaTotales.setIndiceLinea(totalesOnly.getLineNumber());
		lineaTotales.setTipoArchivo(tipoArchivo);
		
		lineaTotales.setTotalCotizacion(Long.valueOf(totalesOnly.getTotalCotizacion()));
		lineaTotales.setDiasMora(Long.valueOf(totalesOnly.getDiasMora()));
		lineaTotales.setInteresesCotizacion(Long.valueOf(totalesOnly.getMoraTotalCotizacion()));
		lineaTotales.setTotalPago(Long.valueOf(totalesOnly.getTotalPagar()));
		
		
		
		return lineaTotales;
	}
}
