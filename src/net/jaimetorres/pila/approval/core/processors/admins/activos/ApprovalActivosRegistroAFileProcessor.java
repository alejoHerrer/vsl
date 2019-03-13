package net.jaimetorres.pila.approval.core.processors.admins.activos;

import java.io.File;
import java.time.YearMonth;

import org.beanio.BeanReader;

import com.jtccia.tch.utiles.Utilidades;

import net.jaimetorres.pila.approval.core.FamiliaTipoCotizante;
import net.jaimetorres.pila.approval.core.converters.ActivosConverter;
import net.jaimetorres.pila.approval.core.processors.admins.AdminApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.generics.GenericAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.activos.GenericActivosAdminOutputBean;
import net.jaimetorres.pila.approval.pojos.output.activos.registroA.ActivosRegistroAEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.activos.registroA.ActivosRegistroAOutputFile;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.pila.approval.utils.NumberUtils;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.validador.Cotizante;
import net.jaimetorres.validaciones.validador.ValidadorEncabezado;

public class ApprovalActivosRegistroAFileProcessor
extends AdminApprovalFileProcessor<ActivosRegistroAOutputFile>{

	public ApprovalActivosRegistroAFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}

	@Override
	protected String getStreamName() {
		return "ActivosRegistroAOutputFile";
	}

	@Override
	protected ActivosRegistroAOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		
		//by-pass
		if(reader == null){return null;}
		
		ActivosRegistroAOutputFile activosRegistroAOutputFile = new ActivosRegistroAOutputFile(file);
		Object record;
		while ( (record =  reader.read()) != null) {
			
			//Line Number
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			
			String recordName = reader.getRecordName();
			if("encabezado".equals(recordName)){
				activosRegistroAOutputFile.setEncabezado((ActivosRegistroAEncabezadoOut)record);
				activosRegistroAOutputFile.getEncabezado().setNroPlanilla(file.getName().split("_")[2]);
        	}
		}
		
		return activosRegistroAOutputFile;
	}

	@Override
	protected void establishPeriodo(GenericAdminOutputBean _genericAdminOutputBean, YearMonth periodoYearMonth) {

		GenericActivosAdminOutputBean genericAdminOutputBean = (GenericActivosAdminOutputBean)_genericAdminOutputBean;
		
		//PENSION
		String periodoPension = periodoYearMonth.getYear()+"-"+
				NumberUtils.padNumberWithZeros(periodoYearMonth.getMonthValue(), 2);
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoNoSalud(periodoPension);
		
		//SALUD
		String periodoSalud = periodoPension;
		FamiliaTipoCotizante familiaTipoCotizante = genericAdminOutputBean.getFamiliaTipoCotizante();
		if(FamiliaTipoCotizante.DEPENDIENTES.equals(familiaTipoCotizante)){
			YearMonth periodoSaludYearMonth = periodoYearMonth.plusMonths(1);
			periodoSalud = periodoSaludYearMonth.getYear()+"-"+NumberUtils.padNumberWithZeros(periodoSaludYearMonth.getMonthValue(), 2);
		}
		genericAdminOutputBean.getLineaRegistroTipo1().setPeriodoSalud(periodoSalud);
	}

	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(
			ActivosRegistroAOutputFile fileParsed) {
		
		ActivosConverter activosConverter = new ActivosConverter();
		
		GenericActivosAdminOutputBean genericAdminOutputBean = new GenericActivosAdminOutputBean();
		
		//REGISTRO TIPO 1 (ENCABEZADO)
		LineaRegistroTipo1 lineaRegistroTipo1 = activosConverter
				.convertRegistroA2LineaRegistroTipo1(fileParsed.getEncabezado());
		genericAdminOutputBean.setLineaRegistroTipo1(lineaRegistroTipo1);
		
		return genericAdminOutputBean;
	}

	@Override
	protected void validateSpecificOutputBean(GenericOutputBean genericOutputBean) {

		//ENCABEZADO
		LineaRegistroTipo1 lineaRegistroTipo1 = ((GenericActivosAdminOutputBean) genericOutputBean).getLineaRegistroTipo1();
		String llave = lineaRegistroTipo1.getNumeroPlanilla() + lineaRegistroTipo1.getTipoDocumento() + lineaRegistroTipo1.getNumeroDocumento();
		Utilidades.agregarEntire(llave, lineaRegistroTipo1);		
		//XXX Se requiere una instancia de "Cotizante" por cada validacion de un record (linea)
		Cotizante interssiCommonsValidatorRegistroTipo1 = new Cotizante();
		interssiCommonsValidatorRegistroTipo1.validarEncabezado(lineaRegistroTipo1);
		this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo1.getListaErroresEncabezado());
		
	}
	
	@Override
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(ActivosRegistroAOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		
		super.postProcessConvertSpecificContextOutputStructureToGenericOutputBean(fileParsed, (GenericActivosAdminOutputBean) genericOutputBean);
	}

	@Override
	protected void establishCodigoAdministradora(GenericAdminOutputBean genericAdminOutputBean,
			String codigoAdministradora) {
		// N/A
	}
}
