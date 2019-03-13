package net.jaimetorres.pila.approval.core.processors.adres;

import java.io.File;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.beanio.BeanReader;

import net.jaimetorres.pila.approval.core.FamiliaTipoCotizante;
import net.jaimetorres.pila.approval.core.converters.AdresConverter;
import net.jaimetorres.pila.approval.core.processors.EntireApprovalFileProcessor;
import net.jaimetorres.pila.approval.pojos.ApprovalFileProcessorParameters;
import net.jaimetorres.pila.approval.pojos.generics.GenericOutputBean;
import net.jaimetorres.pila.approval.pojos.generics.activos.GenericActivosAdresOutputBean;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresOutputFile;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresPlanillaEncabezadoOut;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresPlanillaLiquidacionDetalladaOut;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresPlanillaOut;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresPlanillaTotalesOut;
import net.jaimetorres.pila.approval.pojos.output.adres.AdresTotalesOut;
import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo0Adres;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo1;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo2;
import net.jaimetorres.validaciones.estructura.LineaRegistroTipo4Adres;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo1Pensionado;
import net.jaimetorres.validaciones.estructura.pensionado.LineaRegistroTipo2Pensionado;
import net.jaimetorres.validaciones.to.PlanillaAdresTO;
import net.jaimetorres.validaciones.validador.Cotizante;
import net.jaimetorres.validaciones.validador.ValidadorCotizanteSubsistema;
import net.jaimetorres.validaciones.validador.pensionado.Pensionado;

public class ApprovalAdresFileProcessor 
extends EntireApprovalFileProcessor<AdresOutputFile>{
		
	public ApprovalAdresFileProcessor(ApprovalFileProcessorParameters approvalFileProcessorParameters) {
		super(approvalFileProcessorParameters);
	}
	
	@Override
	protected String getStreamName() {
		return "AdresOutputFile";
	}
	
	@Override
	protected AdresOutputFile parseSpecificOutputFile(BeanReader reader, File file) {
		
		//by-pass
		if(reader == null){return null;}
				
		AdresOutputFile adresOutputFile = new AdresOutputFile(file);
		
		Object record;
		
		List<CommonOutputRecord> registrosArchivoList = new ArrayList<>();
		
		while ( (record =  reader.read()) != null) {
			((CommonOutputRecord) record).setLineNumber(reader.getLineNumber());
			registrosArchivoList.add((CommonOutputRecord)record);
		}
		
		int i = 0;
		while( i < registrosArchivoList.size()) {
			CommonOutputRecord commonOutputRecord = registrosArchivoList.get(i);
			String simpleNameRecord = commonOutputRecord.getClass().getSimpleName();
			if("AdresEncabezadoOut".equals(simpleNameRecord)){
				adresOutputFile.setEncabezado((AdresEncabezadoOut)commonOutputRecord);
			}
			else if("AdresPlanillaEncabezadoOut".equals(simpleNameRecord)){
				AdresPlanillaOut adresPlanillaOut = new AdresPlanillaOut();
				adresPlanillaOut.setPlanillaEncabezado((AdresPlanillaEncabezadoOut)commonOutputRecord);
				i = this.addAdresEachPlanilla(i,registrosArchivoList, adresPlanillaOut, adresOutputFile);
			}
			else if("AdresTotalesOut".equals(simpleNameRecord)){
					adresOutputFile.setTotales(((AdresTotalesOut)commonOutputRecord));
			}
			i++;
		}

		return adresOutputFile;
	}
	
	private int addAdresEachPlanilla(int encabezadoIndex, List<CommonOutputRecord> registrosArchivoList, 
			AdresPlanillaOut adresPlanillaOut, AdresOutputFile adresOutputFile) {
		
		
		int nextPlanilla = ++encabezadoIndex;
		samePlanilla:while(true){
			CommonOutputRecord nextRecord = registrosArchivoList.get(nextPlanilla);
			String simpleNameNextRecord = nextRecord.getClass().getSimpleName();
			if("AdresPlanillaLiquidacionDetalladaOut".equals(simpleNameNextRecord)){
				adresPlanillaOut.getPlanillaLiquidacionDetalladaList().add((AdresPlanillaLiquidacionDetalladaOut)nextRecord);
			}
			else if("AdresPlanillaTotalesOut".equals(simpleNameNextRecord)){
				adresPlanillaOut.getPlanillaTotalesList().add((AdresPlanillaTotalesOut)nextRecord);
			}
			//if("AdresPlanillaEncabezadoOut".equals(simpleNameNextRecord)){
			else{
				break samePlanilla;
			}
			nextPlanilla++;
		}
		adresOutputFile.getPlanillas().add(adresPlanillaOut);
		return --nextPlanilla;
		
	}

	private DateTimeFormatter periodoDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM", Locale.ENGLISH);

	@Override
	protected GenericOutputBean convertSpecificOutputStructureToGenericOutputBean(
			AdresOutputFile fileParsed) {
		
		AdresConverter adresConverter = new AdresConverter(); 
		
		GenericActivosAdresOutputBean genericAdresOutputBean = new GenericActivosAdresOutputBean();
		
		//ENCABEZADO
		LineaRegistroTipo0Adres lineaRegistroTipo0Adres = 
				adresConverter.convertAdresEncabezado2LineaRegistroTipo0Adres(
						fileParsed.getEncabezado());
		genericAdresOutputBean.setLineaRegistroTipo0Adres(lineaRegistroTipo0Adres);
		
		//PLANILLAS
		List<PlanillaAdresTO> planillaAdresTOList = 
				adresConverter.convertAdresPlanillaOutList2planillaAdresTO(
						fileParsed.getPlanillas());
		genericAdresOutputBean.setPlanillaAdresTOList(planillaAdresTOList);
		
		//TOTALES
		LineaRegistroTipo4Adres lineaRegistroTipo4Adres = 
				adresConverter.convertAdresTotales2LineaRegistroTipo4Adres(
						fileParsed.getTotales());
		genericAdresOutputBean.setLineaRegistroTipo4Adres(lineaRegistroTipo4Adres);
				
		return genericAdresOutputBean;
	}
	
	@Override
	protected void validateSpecificOutputBean(GenericOutputBean genericOutputBean) {
		
		GenericActivosAdresOutputBean genericAdresOutputBean = (GenericActivosAdresOutputBean) genericOutputBean;
			
		//ENCABEZADO-TIPO 0
		LineaRegistroTipo0Adres lineaRegistroTipo0Adres = genericAdresOutputBean.getLineaRegistroTipo0Adres();
		
		//LISTA DE PLANILLAS
		List<PlanillaAdresTO> planillaAdresTOList = genericAdresOutputBean.getPlanillaAdresTOList();
		
		//ENCABEZADO-TIPO4
		LineaRegistroTipo4Adres lineaRegistroTipo4Adres = genericAdresOutputBean.getLineaRegistroTipo4Adres();
		
		//Se requiere una instancia de "Cotizante" por cada validacion de un record (linea)
		Cotizante interssiCommonsValidatorRegistroTipo0 = new Cotizante();
		interssiCommonsValidatorRegistroTipo0.validarRegistro0Y4Adres(
				lineaRegistroTipo0Adres, planillaAdresTOList, lineaRegistroTipo4Adres);
		this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo0.getListaErrores());
		
		//super.validateGenericOutputBean(genericAdresOutputBean);
		genericAdresOutputBean.getPlanillaAdresTOList().forEach(lPlanillas->{

			//=======
			//ACTIVOS
			//=======
			if(lPlanillas.getEncabezadoActivos() != null){
			
				//ENCABEZADO
				LineaRegistroTipo1 lineaRegistroTipo1 = lPlanillas.getEncabezadoActivos();
	//			YearMonth periodoPago = YearMonth.of(lineaRegistroTipo1.getAnnoPeriodoSalud(), lineaRegistroTipo1.getMesPeriodoSalud());
				this.establishPeriodoActivos(lineaRegistroTipo1, lPlanillas.getLstDetalleActivos());		
				//XXX Se requiere una instancia de "Cotizante" por cada validacion de un record (linea)
				Cotizante interssiCommonsValidatorRegistroTipo1 = new Cotizante(getInterssiConnection());
				interssiCommonsValidatorRegistroTipo1.validarEncabezado(lineaRegistroTipo1);
				this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo1.getListaErroresEncabezado());
				
				//LIQUIDACION DETALLADA
				Map<String, List<LineaRegistroTipo2>> lineasRegistroTipo2GroupByCotizante = lPlanillas.getLstDetalleActivos().stream().collect(
						Collectors.groupingBy(linea-> linea.getTipoDocumento()+linea.getNumeroDocumento() ) );
				
				lPlanillas.getLstDetalleActivos().forEach(lrt2->{
					List<LineaRegistroTipo2> lineasDelMismoCotizante = lineasRegistroTipo2GroupByCotizante.get(lrt2.getTipoDocumento()+lrt2.getNumeroDocumento());
					Cotizante interssiCommonsValidatorRegistroTipo2 = new Cotizante();
					interssiCommonsValidatorRegistroTipo2.validar(lineasDelMismoCotizante.size() == 1?null:lineasDelMismoCotizante, lineaRegistroTipo1, lrt2);
					this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo2.getListaErrores());
				});
			
				//TOTALES
				Cotizante interssiCommonsValidatorRegistroTipo3 = new Cotizante();
				interssiCommonsValidatorRegistroTipo3.validarTotalesAdress(lPlanillas.getLstDetalleActivos(), lineaRegistroTipo1, lPlanillas.getTotales());
				this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo3.getListaErrores());
			
			}else if (lPlanillas.getEncabezadoPensionados()!=null){
			//===========
			//PENSIONADOS
			//===========
					//ENCABEZADO
					LineaRegistroTipo1Pensionado lineaRegistroTipo1 = lPlanillas.getEncabezadoPensionados();
		//			YearMonth periodoPago = YearMonth.of(lineaRegistroTipo1.getAnnoPeriodoSalud(), lineaRegistroTipo1.getMesPeriodoSalud());
					this.establishPeriodoPensionados(lineaRegistroTipo1, lPlanillas.getLstDetallePensionados());		
					//XXX Se requiere una instancia de "Pensionado" por cada validacion de un record (linea)
					Pensionado interssiCommonsValidatorRegistroTipo1 = new Pensionado();
					interssiCommonsValidatorRegistroTipo1.validarEncabezado(lineaRegistroTipo1);
					this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo1.getListaErroresEncabezado());
					
					//LIQUIDACION DETALLADA
					lPlanillas.getLstDetallePensionados().forEach(lrt2->{
						Pensionado interssiCommonsValidatorRegistroTipo2 = new Pensionado();
						interssiCommonsValidatorRegistroTipo2.validar(lineaRegistroTipo1, lrt2);
						this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo2.getListaErrores());
					});
					
					//TOTALES
					Pensionado interssiCommonsValidatorRegistroTipo3 = new Pensionado();
					interssiCommonsValidatorRegistroTipo3.validarTotalesAdressPensionados(lPlanillas.getLstDetallePensionados(), lineaRegistroTipo1, lPlanillas.getTotales());
					this.addValidationsErrorsMessages(interssiCommonsValidatorRegistroTipo3.getListaErrores());
				
			}else{
				throw new RuntimeException("Contexto de planilla no identificado");
			}
			
		});
	}
	
	@Override
	protected void postProcessConvertSpecificOutputStructureToGenericOutputBean(AdresOutputFile fileParsed,
			GenericOutputBean genericOutputBean) {
		//N/A
	}
	
	private FamiliaTipoCotizante guessFamiliaTipoCotizantes(LineaRegistroTipo1 encabezado, List<LineaRegistroTipo2> lineaRegistroTipo2List) {
		FamiliaTipoCotizante familiaTipoCotizante=null; 
		if(lineaRegistroTipo2List != null ){
			
			String tipoPlanilla = encabezado.getTipoPlanilla();
			Set<Integer> tipoCotizantesIndepenientesSet = new HashSet<>(ValidadorCotizanteSubsistema.getCotizantesMismoPeriodo(tipoPlanilla));
			
			boolean isTiposCotizanteIndependientes = 
					lineaRegistroTipo2List.stream().anyMatch(rt2-> 
						tipoCotizantesIndepenientesSet.contains(rt2.getTipoCotizante()) );
			if(isTiposCotizanteIndependientes){
				familiaTipoCotizante = FamiliaTipoCotizante.INDEPENDIENTES;
			}else{
				familiaTipoCotizante = FamiliaTipoCotizante.DEPENDIENTES;
			}
		}
		
		return familiaTipoCotizante;
	}

	private void establishPeriodoActivos(LineaRegistroTipo1 encabezado, List<LineaRegistroTipo2> lineaRegistroTipo2List) {
		
		//PENSION
		String periodoPension = encabezado.getPeriodoSalud();
		YearMonth periodo = YearMonth.of(encabezado.getAnnoPeriodoSalud(), encabezado.getMesPeriodoSalud());
		FamiliaTipoCotizante familiaTipoCotizante = guessFamiliaTipoCotizantes(encabezado,lineaRegistroTipo2List);
		if (!FamiliaTipoCotizante.INDEPENDIENTES.equals(familiaTipoCotizante)){
			YearMonth periodoPensionYearMonth = periodo.minusMonths(1);
			periodoPension = periodoDateFormatter.format(periodoPensionYearMonth); 
		}
		encabezado.setPeriodoNoSalud(periodoPension);
	}
	
	private void establishPeriodoPensionados(LineaRegistroTipo1Pensionado encabezado, List<LineaRegistroTipo2Pensionado> lineaRegistroTipo2List) {
		
		//PENSION
		YearMonth periodoPensionYearMonth = encabezado.getPeriodoSalud().minusMonths(1);
		/*
		YearMonth periodo = YearMonth.of(encabezado.getAnnoPeriodoSalud(), encabezado.getMesPeriodoSalud());
		FamiliaTipoCotizante familiaTipoCotizante = guessFamiliaTipoCotizantes(encabezado,lineaRegistroTipo2List);
		if (!FamiliaTipoCotizante.INDEPENDIENTES.equals(familiaTipoCotizante)){
			YearMonth periodoPensionYearMonth = periodo.minusMonths(1);
			periodoPension = periodoDateFormatter.format(periodoPensionYearMonth); 
		}
		*/
		encabezado.setPeriodoPension(periodoPensionYearMonth);
	}
}
