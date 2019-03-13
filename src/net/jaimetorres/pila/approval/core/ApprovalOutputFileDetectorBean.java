package net.jaimetorres.pila.approval.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.jaimetorres.pila.approval.core.exceptions.AdmUnidentifiedException;
import net.jaimetorres.pila.approval.core.exceptions.UnidentifiedFileNameException;
import net.jaimetorres.pila.approval.core.misc.SubsistemaPila;
import net.jaimetorres.pila.approval.core.misc.TipoArchivoSalida;
import net.jaimetorres.pila.approval.core.processors.EntireApprovalFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosCajasFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosEsapFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosIcbfFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosMineduFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosPensionFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosRegistroAFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosRiesgosFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosSaludFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosSenaFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.pensionados.ApprovalPensionadosCajasFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.pensionados.ApprovalPensionadosFspFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.pensionados.ApprovalPensionadosPensionFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.pensionados.ApprovalPensionadosRegistroAFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.pensionados.ApprovalPensionadosSaludFileProcessor;
import net.jaimetorres.pila.approval.core.processors.adres.ApprovalAdresFileProcessor;
import net.jaimetorres.pila.approval.core.processors.minps.ApprovalMinpsFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.OutputFilenameFields;
import net.jaimetorres.pila.approval.utils.PilaUtils;

@Component
public class ApprovalOutputFileDetectorBean {
	
	private PilaUtils pilaUtils = new PilaUtils();
	
	@Autowired
	private AllAdministradorasBean allAdministradorasBean;
	
	public EntireApprovalFileProcessor<?> retrieveApprovalFileProcessor(
			TipoArchivoSalida tipoArchivoSalida,
			ApprovalFileProcessorParameters approvalFileProcessorParameters){
		
		switch (tipoArchivoSalida) {
		
			//XXX TODO XXX SE DEBE CREAR UNA ASOCIACION DIRECTA ENTRE:
			// TipoArchivoSalida vs ApprovalFileProcessor (EntireApprovalFileProcessor)
		
			//ACTIVOS
			case ACTIVOS_ARCHIVO_A: return new ApprovalActivosRegistroAFileProcessor(approvalFileProcessorParameters); 
			case ACTIVOS_PENSION: return new ApprovalActivosPensionFileProcessor(approvalFileProcessorParameters);
			case ACTIVOS_SALUD: return new ApprovalActivosSaludFileProcessor(approvalFileProcessorParameters);
			case ACTIVOS_RIESGOS: return new ApprovalActivosRiesgosFileProcessor(approvalFileProcessorParameters);
			case ACTIVOS_CAJAS: return new ApprovalActivosCajasFileProcessor(approvalFileProcessorParameters);
			case ACTIVOS_SENA: return new ApprovalActivosSenaFileProcessor(approvalFileProcessorParameters);
			case ACTIVOS_ICBF: return new ApprovalActivosIcbfFileProcessor(approvalFileProcessorParameters);
			case ACTIVOS_ESAP: return new ApprovalActivosEsapFileProcessor(approvalFileProcessorParameters);
			case ACTIVOS_MINEDU: return new ApprovalActivosMineduFileProcessor(approvalFileProcessorParameters);

			//PENSIONADOS
			case PENSIONADOS_ARCHIVO_A: return new ApprovalPensionadosRegistroAFileProcessor(approvalFileProcessorParameters); 
			case PENSIONADOS_PENSION: return new ApprovalPensionadosPensionFileProcessor(approvalFileProcessorParameters);
			case PENSIONADOS_FONDO_SOLIDARIDAD_PENSIONAL: return new ApprovalPensionadosFspFileProcessor(approvalFileProcessorParameters);
			case PENSIONADOS_SALUD: return new ApprovalPensionadosSaludFileProcessor(approvalFileProcessorParameters);
			case PENSIONADOS_CAJAS: return new ApprovalPensionadosCajasFileProcessor(approvalFileProcessorParameters);

			//MINPS
			case MINPS: return new ApprovalMinpsFileProcessor(approvalFileProcessorParameters);
			
			//ADRES
			case ADRES: return new ApprovalAdresFileProcessor(approvalFileProcessorParameters);
			
			default: 
				return null;
		}
	}

	public SubsistemaPila identifySubsistemaByCodigoAdministradora(boolean isArchivoPensionado, String codigoAdministradora) {
		SubsistemaPila subsistemaPilaIdentificado = allAdministradorasBean.getAllAdministradorasMap().get(codigoAdministradora);
		if(SubsistemaPila.FONDO_SOLIDARIDAD_PENSIONAL.equals(subsistemaPilaIdentificado) && !isArchivoPensionado) {
			return 	SubsistemaPila.PENSION;
		}
		return subsistemaPilaIdentificado;
	}
	
	public TipoArchivoSalida identifyTipoArchivoSalidaByFilename(String fileName){

		//ADMINS
		//--ACTIVOS(SUBSISTEMAS)
		//--PENSIONADOS(SUBSISTEMAS)
		if(pilaUtils.adminFileNamePatternMatch(fileName) ){
			
			OutputFilenameFields retrieveOutputFilenameFields = pilaUtils.retrieveOutputFilenameFields(fileName);
			
			String tipoArchivo = retrieveOutputFilenameFields.getTipoArchivo();
			boolean isArchivoPensionado = tipoArchivo.contains("P");
			
			boolean isTipoArchivoA = tipoArchivo.contains("A"); 
			String codigoAdministradora = retrieveOutputFilenameFields.getCodigoAdministradora();
			SubsistemaPila subsistema = this.identifySubsistemaByCodigoAdministradora(isArchivoPensionado,codigoAdministradora);
			
			//Archivos de Pensionados
			if (isArchivoPensionado ){
				
				if(isTipoArchivoA){
					return TipoArchivoSalida.PENSIONADOS_ARCHIVO_A;
				}else{
					switch(subsistema){
						case PENSION: return TipoArchivoSalida.PENSIONADOS_PENSION;
						case FONDO_SOLIDARIDAD_PENSIONAL: return TipoArchivoSalida.PENSIONADOS_FONDO_SOLIDARIDAD_PENSIONAL;
						case SALUD: return TipoArchivoSalida.PENSIONADOS_SALUD;
						case PARAFISCALES_CAJAS: return TipoArchivoSalida.PENSIONADOS_CAJAS;
						default: throw new RuntimeException("Subsistema no identificado: " + subsistema);
					}
				}
			
			//Archivos de Activos
			}else{
				
				if(isTipoArchivoA){
					return TipoArchivoSalida.ACTIVOS_ARCHIVO_A;
				}else{
					subsistema = subsistema != null ? subsistema: SubsistemaPila.NO_EXISTE;
					switch(subsistema){
						case PENSION: return TipoArchivoSalida.ACTIVOS_PENSION;
						case SALUD: return TipoArchivoSalida.ACTIVOS_SALUD;
						case RIESGOS: return TipoArchivoSalida.ACTIVOS_RIESGOS;
						case PARAFISCALES_CAJAS: return TipoArchivoSalida.ACTIVOS_CAJAS;
						case PARAFISCALES_SENA: return TipoArchivoSalida.ACTIVOS_SENA;
						case PARAFISCALES_ICBF: return TipoArchivoSalida.ACTIVOS_ICBF;
						case PARAFISCALES_ESAP: return TipoArchivoSalida.ACTIVOS_ESAP;
						case PARAFISCALES_MINEDU: return TipoArchivoSalida.ACTIVOS_MINEDU;
						default: throw new AdmUnidentifiedException(fileName);
					}
				}
			}
		
		//MINPS
		}else if(pilaUtils.minpsFileNamePatternMatch(fileName) ){
			return TipoArchivoSalida.MINPS;
		
		//ADRES
		}else if(pilaUtils.adresFileNamePatternMatch(fileName) ){
			return TipoArchivoSalida.ADRES;
		
		}else{
			throw new UnidentifiedFileNameException(fileName);
		}
		
	}
}