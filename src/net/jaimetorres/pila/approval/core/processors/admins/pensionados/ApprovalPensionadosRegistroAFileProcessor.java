package net.jaimetorres.pila.approval.core.processors.admins.pensionados;

import java.io.File;
import java.time.YearMonth;

import org.beanio.BeanReader;

import com.jtccia.tch.utiles.Utilidades;

import net.jaimetorres.pila.approval.core.converters.PensionadosConverter;
import net.jaimetorres.pila.approval.core.processors.admins.AdminApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.pensionados.GenericPensionadosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.pila.approval.pojos.output.pensionados.registroA.PensionadosRegistroAEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.pensionados.registroA.PensionadosRegistroAOutputFile;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.validador.pensionado.Pensionado;

public class ApprovalPensionadosRegistroAFileProcessor 
extends AdminApprovalFileProcessor<PensionadosRegistroAOutputFile>{

	public ApprovalPensionadosRegistroAFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}
	
	@Override
	protected String getStreamName() {
		return "PensionadosRegistroAOutputFile";
	}
	
	@Override
	protected PensionadosRegistroAOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		
		//by-pass
		if(reader == null){return null;}
		
		PensionadosRegistroAOutputFile pensionadosRegistroAOutputFile = new PensionadosRegistroAOutputFile(file);
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				pensionadosRegistroAOutputFile.setEncabezado((PensionadosRegistroAEncabezadoOut)record);
        	}
		}
		pensionadosRegistroAOutputFile.getEncabezado().setNroPlanilla(file.getName().split("_")[2]);
		return pensionadosRegistroAOutputFile;
	}
	
	@Override
	protected void establishPeriodo(GenericAdminOutputBean _genericAdminOutputBean, YearMonth periodoYearMonth) {
		
		GenericPensionadosAdminOutputBean genericAdminOutputBean = (GenericPensionadosAdminOutputBean)_genericAdminOutputBean;
		
		YearMonth periodoSaludYearMonth_ = periodoYearMonth.minusMonths(1);
		//PENSION
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoPension(periodoSaludYearMonth_);
		
		//SALUD
		YearMonth periodoSaludYearMonth = periodoYearMonth;
//TODO XXX 
//LA LOGICA CAMBIA, SE DEBE IDENTIFICAR EL SUBSISTEMA Y ASI ESTABLECER EL PERIODO PENSION Y SALUD ADECUADAMENTE
// TENIENDO EN CUENTA QUE PARA LOS PENSIONADOS, EL PERIODO DE PENSION Y SALUD NUNCA SON IGUALES
		
		
//		FamiliaTipoCotizante familiaTipoCotizante = genericAdminOutputBean.getFamiliaTipoCotizante();
//		if(FamiliaTipoCotizante.DEPENDIENTES.equals(familiaTipoCotizante)){
//			YearMonth periodoSaludYearMonth = periodoYearMonth.plusMonths(1);
//			periodoSalud = periodoSaludYearMonth.getYear()+"-"+NumberUtils.padNumberWithZeros(periodoSaludYearMonth.getMonthValue(), 2);
//		}
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoSalud(periodoSaludYearMonth);
	}
	
	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(
			PensionadosRegistroAOutputFile fileParsed) {
		
		PensionadosConverter pensionadosConverter = new PensionadosConverter();
		
		GenericPensionadosAdminOutputBean genericAdminOutputBean = new GenericPensionadosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = pensionadosConverter
				.convertRegistroA2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		return genericAdminOutputBean;
	}
	
	@Override
	protected void validateSpecificOutputBean(GenericOutputBean genericOutputBean) {
		
		//ENCABEZADO
		LineaRegistroTipo1Pensionado lineaRegistroTipo1 = ((GenericPensionadosAdminOutputBean) genericOutputBean).getLineaRegistroTipo1();
		String llave = lineaRegistroTipo1.getNumeroPlanilla() + lineaRegistroTipo1.getTipoDocumento() + lineaRegistroTipo1.getNumeroDocumento();
		Utilidades.agregarEntire(llave, lineaRegistroTipo1);
		//XXX Se requiere una instancia de "Pensionado" por cada validacion de un record (linea)
		Pensionado interssiCommonsValidatorRegistroTipo1 = new Pensionado();
		interssiCommonsValidatorRegistroTipo1.validarEncabezado(lineaRegistroTipo1);
		this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo1.getListaErroresEncabezado());
	}
	@Override
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(PensionadosRegistroAOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericPensionadosAdminOutputBean) genericOutputBean);
	}

	@Override
	protected void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean,
			String codigoAdministradora) {
		// N/A
	}
}
