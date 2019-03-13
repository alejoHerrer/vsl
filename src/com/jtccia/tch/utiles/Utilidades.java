package com.jtccia.tch.utiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import net.jaimetorres.pila.approval.core.CotizanteGrupo;
import net.jaimetorres.pila.approval.core.misc.ErrorsWriter;
import net.jaimetorres.pila.approval.core.processors.EntireApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalValidationError;
import net.jaimetorres.validaciones.entidad.HomologacionEstructuraArchivosSalida;
import net.jaimetorres.validaciones.entidad.RegistroAportante;
import net.jaimetorres.validaciones.estructura.Linea;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.general.CodigoError;
import net.jaimetorres.validaciones.general.TipoArchivo;
import net.jaimetorres.validaciones.to.ErrorTO;
import net.jaimetorres.validaciones.validador.Cotizante;
import net.jaimetorres.validaciones.validador.pensionado.Pensionado;

public class Utilidades {

	private static Map<String, CotizanteGrupo> mapaCotizantes = new HashMap<>();
	private static List<ApprovalValidationError> errorsList = new ArrayList<>(); 

	public static void cargarMapaCotizantes(List<File> archivos) {
		for (File file : archivos) {
			if (!file.getName().substring(0, 3).equals("PIL")) {
				String llave = file.getName().split("_")[2] + file.getName().split("_")[3]
						+ file.getName().split("_")[4];
				if (!mapaCotizantes.containsKey(llave)) {
					CotizanteGrupo cotizanteGrupo = new CotizanteGrupo();
					List<File> arFiles = new ArrayList<>();
					arFiles.add(file);
					cotizanteGrupo.setArchivos(arFiles);
					mapaCotizantes.put(llave, cotizanteGrupo);
				} else {
					mapaCotizantes.get(llave).getArchivos().add(file);
				}
			}
		}
	}

	public static void validationDual() {
		try {
			Set<String> mapKeys = mapaCotizantes.keySet();
			for (String key : mapKeys) {
				if (!isArchivoPensionado(mapaCotizantes.get(key).getArchivos().get(0).getName().split("_")[7])) {

					LineaRegistroTipo1 lineaValidar = new LineaRegistroTipo1();
					if (mapaCotizantes.get(key).getArchivoIdentificado().size() > 1) {
						for (Linea lineaObject : mapaCotizantes.get(key).getArchivoIdentificado()) {
							LineaRegistroTipo1 linea = (LineaRegistroTipo1) lineaObject;
							if (linea.getTipoArchivo().getCodigo().equals("TA")) {
								// Registro tipo A
								RegistroAportante aportante = linea.getRegistroAportante();
								lineaValidar.setRegistroAportante(aportante);
							} else {
								// Registro tipo I
								RegistroAportante temAportante = lineaValidar.getRegistroAportante();
								lineaValidar = linea;
								lineaValidar.setTipoDocumento(linea.getTipoDocumento());
								lineaValidar.setNumeroDocumento(linea.getNumeroDocumento());
								lineaValidar.setCorreo(linea.getRegistroAportante().getCorreo());
								lineaValidar.setFax(linea.getRegistroAportante().getFax());
								lineaValidar.setNombreSucursalAportante(linea.getNombreSucursalAportante());
								lineaValidar.setTelefono(linea.getRegistroAportante().getTelefono());
								lineaValidar.setTipoPersona(linea.getRegistroAportante().getTipoPersona());
								lineaValidar.setClaseAportante(linea.getRegistroAportante().getClase());
								lineaValidar.setNaturalezaJuridica(linea.getRegistroAportante().getNaturalezaJuridica());
								lineaValidar.setRazonSocial(linea.getRazonSocial());
								lineaValidar.setTipoAportante(linea.getTipoAportante());
								lineaValidar.setRegistroAportante(temAportante);
							}
						}
						lineaValidar.setTipoArchivo(TipoArchivo.TIPO_DUAL);
						Cotizante cotizante = new Cotizante();
						cotizante.validarEncabezado(lineaValidar);
						addValidationsErrorsMessages(cotizante.getListaErroresEncabezado(), mapaCotizantes.get(key).getArchivos().get(0).getName());
					} else {
						ApprovalValidationError approvalValidationError = new ApprovalValidationError();
						approvalValidationError.setDescripcion("(IC:10001) El archivo no contiene el archivo I o A");
						approvalValidationError.setNombreArchivo(mapaCotizantes.get(key).getArchivos().get(0).getName());
						errorsList.add(approvalValidationError);	
					}					
				} else {

					LineaRegistroTipo1Pensionado lineaValidarP = new LineaRegistroTipo1Pensionado();
					if (mapaCotizantes.get(key).getArchivoIdentificado().size() > 1) {
						for (Linea lineaObject : mapaCotizantes.get(key).getArchivoIdentificado()) {
							LineaRegistroTipo1Pensionado linea = (LineaRegistroTipo1Pensionado) lineaObject;
							if (linea.getTipoArchivo().getCodigo().equals("PEN_TA")) {
								// Registro tipo A
								RegistroAportante aportante = linea.getRegistroAportante();
								lineaValidarP.setRegistroAportante(aportante);
							} else {
								// Registro tipo I
								RegistroAportante temAportante = lineaValidarP.getRegistroAportante();
								lineaValidarP = linea;
								lineaValidarP.setTipoDocumento(linea.getTipoDocumento());
								lineaValidarP.setNumeroDocumento(linea.getNumeroDocumento());
								lineaValidarP.setCorreo(linea.getRegistroAportante().getCorreo());
								lineaValidarP.setFax(linea.getRegistroAportante().getFax());
								lineaValidarP.setTelefono(linea.getRegistroAportante().getTelefono());
								lineaValidarP.setClasePagadorPension(linea.getRegistroAportante().getClase());
								lineaValidarP.setNaturalezaJuridica(linea.getRegistroAportante().getNaturalezaJuridica());
								lineaValidarP.setRazonSocial(linea.getRazonSocial());
								lineaValidarP.setRegistroAportante(temAportante);
							}
						}
						Pensionado pensionado = new Pensionado();
						lineaValidarP.setTipoArchivo(TipoArchivo.PEN_TIPO_DUAL);
						pensionado.validarEncabezado(lineaValidarP);
						addValidationsErrorsMessages(pensionado.getListaErroresEncabezado(), mapaCotizantes.get(key).getArchivos().get(0).getName());
					} else {
						ApprovalValidationError approvalValidationError = new ApprovalValidationError();
						approvalValidationError.setDescripcion("(IC:10001) El arvhivo no contiene el archivo I o A");
						approvalValidationError.setNombreArchivo(mapaCotizantes.get(key).getArchivos().get(0).getName());
						errorsList.add(approvalValidationError);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	private static void addValidationsErrorsMessages(List<ErrorTO> errorListLocal, String linea) {

		if (errorListLocal == null || errorListLocal.isEmpty()) {
			return;
		}

		errorListLocal.forEach(e -> {

			CodigoError codigoError = e.getError();
			String errorDescripcion = codigoError.getDescripcion();
			if (errorDescripcion.contains(":@{valor}")) {
				errorDescripcion = errorDescripcion.replace(":@{valor}", e.getDetalleError());

			} else if (errorDescripcion.contains("@{valor}")) {
				errorDescripcion = errorDescripcion.replace("@{valor}", e.getDetalleError());
			}

			int posicionInicial = 0, posicionFinal = 0;
			HomologacionEstructuraArchivosSalida mologacionEstructuraSalida = e.getMologacionEstructuraSalida();
			if (mologacionEstructuraSalida != null) {
				posicionInicial = mologacionEstructuraSalida.getPosInicial();
				posicionFinal = mologacionEstructuraSalida.getPosFinal();
			}

			ApprovalValidationError approvalValidationError = new ApprovalValidationError(
					// XXX TODO QUITAR (IC)
					"(IC:" + codigoError.getCodigo() + ") " + errorDescripcion, posicionInicial, posicionFinal,
					StringUtils.join(e.getLstValoresEsperados(), ","), e.getValorEncontrado(),
					Long.valueOf(e.getConsecutivoLinea()).intValue(), linea);

			approvalValidationError.setCodigoError(codigoError);
			 errorsList.add(approvalValidationError);
			
		});
	}

	public static boolean isArchivoPensionado(String name) {
		return name.contains("IP") || name.contains("AP") || name.contains("IPR") || name.contains("APR");
	}

	public static void agregarEntire(String llave, Linea approvalFileProcessor) {
		try {
			mapaCotizantes.get(llave).getArchivoIdentificado().add(approvalFileProcessor);
		} catch (Exception e) {
			// TODO: handle exception
			ApprovalValidationError approvalValidationError = new ApprovalValidationError();
			approvalValidationError.setDescripcion("(IC:10000) El archivo contiene diferencia en el nombre del archivo y los datos encontrados en el encabezado. El documento encontrado en el archivo es: " + ((LineaRegistroTipo1) approvalFileProcessor).getNumeroDocumento());
			approvalValidationError.setNombreArchivo(mapaCotizantes.get(llave)!= null?mapaCotizantes.get(llave).getArchivos().get(0).getName():"No se puede determinar el archivo");
			errorsList.add(approvalValidationError);
		}
	}

	public static Map<String, CotizanteGrupo> getMapaCotizantes() {
		return mapaCotizantes;
	}

	public static void setMapaCotizantes(Map<String, CotizanteGrupo> mapaCotizantes) {
		Utilidades.mapaCotizantes = mapaCotizantes;
	}

	public static void resetMap() {
		mapaCotizantes = new HashMap<>();
		errorsList.clear();
	}

	public static List<ApprovalValidationError> getErrorsList() {
		return errorsList;
	}

	public static void setErrorsList(List<ApprovalValidationError> errorsList) {
		Utilidades.errorsList = errorsList;
	}

}
