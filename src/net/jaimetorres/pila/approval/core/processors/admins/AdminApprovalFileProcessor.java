package net.jaimetorres.pila.approval.core.processors.admins;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jtccia.tch.utiles.Utilidades;

import net.jaimetorres.pila.approval.core.FamiliaTipoCotizante;
import net.jaimetorres.pila.approval.core.processors.EntireApprovalFileProcessor;
import net.jaimetorres.pila.approval.core.processors.adres.ApprovalAdresFileProcessor;
import net.jaimetorres.pila.approval.core.processors.minps.ApprovalMinpsFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.OutputFilenameFields;
import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.activos.GenericActivosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.pensionados.GenericPensionadosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.output.activos.commons.CommonOutputFile;
import net.jaimetorres.pila.approval.utils.DateUtils;
import net.jaimetorres.pila.approval.utils.PilaUtils;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo31;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo36;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo39;
import net.jaimetorres.validaciones.estructura.LineaTotales;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;
import net.jaimetorres.validaciones.exception.DAOException;
import net.jaimetorres.validaciones.general.TipoArchivo;
import net.jaimetorres.validaciones.helper.ConfiguracionesHelper;
import net.jaimetorres.validaciones.validador.Cotizante;
import net.jaimetorres.validaciones.validador.ValidadorCotizanteSubsistema;
import net.jaimetorres.validaciones.validador.pensionado.Pensionado;

public abstract class AdminApprovalFileProcessor<T extends CommonOutputFile> 
extends EntireApprovalFileProcessor<T>{

	private static final Logger logger = LoggerFactory.getLogger(AdminApprovalFileProcessor.class);
	
	private PilaUtils pilaUtils = new PilaUtils();
	
	public AdminApprovalFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}
	
	//Fields From FileName
	protected abstract void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean, String codigoAdministradora);
	protected abstract void establishPeriodo(GenericAdminOutputBean genericAdminOutputBean, YearMonth periodo);
	

	protected void validateAdminOutputBean(GenericAdminOutputBean genericAdminOutputBean) throws DAOException{
		if(genericAdminOutputBean instanceof GenericActivosAdminOutputBean){
			this.activosValidateAdminOutputBean((GenericActivosAdminOutputBean)genericAdminOutputBean);
		}else if(genericAdminOutputBean instanceof GenericPensionadosAdminOutputBean){
			this.pensionadosValidateAdminOutputBean((GenericPensionadosAdminOutputBean)genericAdminOutputBean);
		}
	}
	
	protected void postProcessConvertSpecificContextOutputStructureToGenericOutputBean(T fileParsed,
			GenericAdminOutputBean genericAdminOutputBean) {
		
		try{
			//LOGICA SEGUN CONTEXTO
			//Va de primero porque el proximo metodo (establishFilenameValues2GenericAdminOutputBean) depende de esta logica
			if(genericAdminOutputBean instanceof GenericActivosAdminOutputBean){
				//Se intenta deducir si la es de Independientes o Dependientes
				this.activosGuessFamiliaTipoCotizantes((GenericActivosAdminOutputBean)genericAdminOutputBean);
			}
			
			//Aplicar Valores del nombre del archivo
			this.establishFilenameValues2GenericAdminOutputBean(fileParsed, genericAdminOutputBean);
			
		}catch(Exception e){e.printStackTrace();
			logger.error("Error al convertir archivo parseado (string) a GenericAdminOutputBean");
			logger.error(fileParsed.getFile().getAbsolutePath());			
			logger.error(org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(e));
		}
	}
	
	private void establishFilenameValues2GenericAdminOutputBean(T fileParsed, GenericAdminOutputBean genericAdminOutputBean) {
		
		//XXX TODO esta logica sobra
		if( (new HashSet<>(Arrays.asList(ApprovalAdresFileProcessor.class,ApprovalMinpsFileProcessor.class))).contains(super.getClass()) ){
			return;
		}
		
		//LOGICA QUE SOLO APLICA PARA ARCHIVOS DE ADMINISTRADORAS
		String filename = fileParsed.getFile().getName();
		OutputFilenameFields outputFilenameFields = pilaUtils.retrieveOutputFilenameFields(filename);
		
		//FECHA_PAGO
		LocalDate fechaPago = outputFilenameFields.getFechaPago();
		//genericAdminOutputBean.getLineaRegistroTipo1().setFechaPagoPlanilla(DateUtils.convertLocalDate2String(fechaPago) );
		genericAdminOutputBean.establishFechaPago(DateUtils.convertLocalDate2String(fechaPago) );
		
		//CODIGO DE ADMINISTRADORA
		this.establishCodigoAdministradora(genericAdminOutputBean, outputFilenameFields.getCodigoAdministradora());
		
		//PERIODO_PAGO
		YearMonth periodoPago = outputFilenameFields.getPeriodoPago();
		this.establishPeriodo(genericAdminOutputBean, periodoPago );
		
	}
	
	//========================
	//SPECIFIC CONTEXT METHODS
	//========================
	//ACTIVOS
	private void activosGuessFamiliaTipoCotizantes(GenericActivosAdminOutputBean genericAdminOutputBean) {
		
		//by-pass
		if (genericAdminOutputBean == null){return;} 
		
		String tipoPlanilla = genericAdminOutputBean.getLineaRegistroTipo1().getTipoPlanilla();
		Set<Integer> tipoCotizantesIndepenientesSet = new HashSet<>(ValidadorCotizanteSubsistema.getCotizantesMismoPeriodo(tipoPlanilla));
		
		List<LineaRegistroTipo2> lineaRegistroTipo2List = genericAdminOutputBean.getLineaRegistroTipo2List();
		if(lineaRegistroTipo2List != null ){
			boolean isTiposCotizanteIndependientes = 
					lineaRegistroTipo2List.stream().anyMatch(rt2-> 
						tipoCotizantesIndepenientesSet.contains(rt2.getTipoCotizante() ) );
			if(isTiposCotizanteIndependientes){
				genericAdminOutputBean.setFamiliaTipoCotizante(FamiliaTipoCotizante.INDEPENDIENTES);
			}else{
				genericAdminOutputBean.setFamiliaTipoCotizante(FamiliaTipoCotizante.DEPENDIENTES);
			}
		}
	}
	
	private void activosValidateAdminOutputBean(GenericActivosAdminOutputBean genericAdminOutputBean) throws DAOException{
		
		//ENCABEZADO
		LineaRegistroTipo1 lineaRegistroTipo1 = genericAdminOutputBean.getLineaRegistroTipo1();
		//268777	
		this.setupPreValidate(lineaRegistroTipo1);
		//XXX Se requiere una instancia de "Cotizante" por cada validacion de un record (linea)
		Cotizante interssiCommonsValidatorRegistroTipo1 = new Cotizante(getInterssiConnection());
		String llave = lineaRegistroTipo1.getNumeroPlanilla() + lineaRegistroTipo1.getTipoDocumento() + lineaRegistroTipo1.getNumeroDocumento();
		Utilidades.agregarEntire(llave, lineaRegistroTipo1);		
		interssiCommonsValidatorRegistroTipo1.validarEncabezado(lineaRegistroTipo1);
		this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo1.getListaErroresEncabezado());
		
		//LIQUIDACION DETALLADA
		Map<String, List<LineaRegistroTipo2>> lineasRegistroTipo2GroupByCotizante = genericAdminOutputBean.getLineaRegistroTipo2List().stream().collect(
				Collectors.groupingBy(lrt2-> lrt2.getTipoDocumento()+lrt2.getNumeroDocumento() ) );
		
		genericAdminOutputBean.getLineaRegistroTipo2List().forEach(lrt2->{
			List<LineaRegistroTipo2> lineasDelMismoCotizante = lineasRegistroTipo2GroupByCotizante.get(lrt2.getTipoDocumento()+lrt2.getNumeroDocumento());
			Cotizante interssiCommonsValidatorRegistroTipo2 = new Cotizante();
			interssiCommonsValidatorRegistroTipo2.validar(lineasDelMismoCotizante.size() == 1?null:lineasDelMismoCotizante, lineaRegistroTipo1, lrt2);
			this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo2.getListaErrores());
		});

		//TOTALES
		LineaRegistroTipo31 lineaRegistroTipo31 = genericAdminOutputBean.getLineaRegistroTipo31();
		LineaRegistroTipo36 lineaRegistroTipo36 = genericAdminOutputBean.getLineaRegistroTipo36();
		LineaRegistroTipo39 lineaRegistroTipo39 = genericAdminOutputBean.getLineaRegistroTipo39();
		
		//XXX OJO SE REALIZO PORQUE EL REGISTRO A, NO TIENE ESTOS TIPOS DE REGISTROS
		if(!(lineaRegistroTipo31 == null || lineaRegistroTipo36 == null || lineaRegistroTipo39 == null)){
			List<LineaRegistroTipo2> lineaRegistroTipo2List = genericAdminOutputBean.getLineaRegistroTipo2List();
			interssiCommonsValidatorRegistroTipo1.validarTotales(
					lineaRegistroTipo2List, lineaRegistroTipo1, lineaRegistroTipo31,lineaRegistroTipo36,lineaRegistroTipo39);
			this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo1.getListaErrores());
		}
		
		//OTROS RECORDS
		//XXX VALIDAR DEMAS RECORDS
	}
	
	
	//T268777
	private void setupPreValidate(LineaRegistroTipo1 lineaRegistroTipo1) throws DAOException {
		if(lineaRegistroTipo1.getTipoArchivo().equals(TipoArchivo.ESAP)){
			ConfiguracionesHelper.obtenerAportanteEsap(lineaRegistroTipo1.getRegistroAportante().getTipoIdentificacion(), lineaRegistroTipo1.getRegistroAportante().getNumeroIdentificacion(), getInterssiConnection());
		}
	}
	
	//PENSIONADOS
	private void pensionadosValidateAdminOutputBean(GenericPensionadosAdminOutputBean genericAdminOutputBean){
		
		//ENCABEZADO
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = genericAdminOutputBean.getLineaRegistroTipo1();
		String llave = lineaRegistroTipo1.getNumeroPlanilla() + lineaRegistroTipo1.getTipoDocumento() + lineaRegistroTipo1.getNumeroDocumento();
		Utilidades.agregarEntire(llave, lineaRegistroTipo1);		
		//XXX Se requiere una instancia de "Pensionado" por cada validacion de un record (linea)
		Pensionado interssiCommonsValidatorRegistroTipo1 = new Pensionado();
		interssiCommonsValidatorRegistroTipo1.validarEncabezado(lineaRegistroTipo1);
		this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo1.getListaErroresEncabezado());
		
		//LIQUIDACION DETALLADA
		genericAdminOutputBean.getLineaRegistroTipo2List().forEach(lrt2->{
			Pensionado interssiCommonsValidatorRegistroTipo2 = new Pensionado();
			interssiCommonsValidatorRegistroTipo2.validar(lineaRegistroTipo1,lrt2);
			this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo2.getListaErrores());
		});

		//TOTALES
		LineaRegistroTipo31 lineaRegistroTipo31 = genericAdminOutputBean.getLineaRegistroTipo31();
		LineaRegistroTipo36 lineaRegistroTipo36 = genericAdminOutputBean.getLineaRegistroTipo36();
		LineaRegistroTipo39 lineaRegistroTipo39 = genericAdminOutputBean.getLineaRegistroTipo39();
		LineaTotales lineaTotales = genericAdminOutputBean.getLineaTotales();
		
		
		//XXX OJO SE REALIZO PORQUE EL REGISTRO A, NO TIENE ESTOS TIPOS DE REGISTROS
		//if(!(lineaRegistroTipo31 == null || lineaRegistroTipo36 == null || lineaRegistroTipo39 == null)){
			List<LineaRegistroTipo2Pensionado> lineaRegistroTipo2List = genericAdminOutputBean.getLineaRegistroTipo2List();
			interssiCommonsValidatorRegistroTipo1.validarTotalesPensionados(
					lineaRegistroTipo2List, lineaRegistroTipo1, 
					lineaRegistroTipo31,lineaRegistroTipo36,lineaRegistroTipo39, lineaTotales);
			this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo1.getListaErrores());
		//}
		
		//OTROS RECORDS
		//XXX VALIDAR DEMAS RECORDS
	}
}
