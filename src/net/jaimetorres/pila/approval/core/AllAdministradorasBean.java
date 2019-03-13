package net.jaimetorres.pila.approval.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import net.jaimetorres.pila.approval.core.misc.SubsistemaPila;

@Component
public class AllAdministradorasBean {

	private Map<String,SubsistemaPila> allAdministradorasMap;
	
	{this.allAdministradorasMap = new HashMap<>();
	
	//PENSION
	Set<String> pensionAdms = new HashSet<>(Arrays.asList(
		"ISSFSP", "25-8", "25-7", "25-4", "25-3", "25-2", "25-14", "25-11",
		"231001", "230904", "230901", "230801", "230501", "230301", "230201"));
	pensionAdms.forEach(adm -> allAdministradorasMap.put(adm, SubsistemaPila.PENSION));
	
	//FONDO_SOLIDARIDAD_PENSIONAL
	allAdministradorasMap.put("FSP001", SubsistemaPila.FONDO_SOLIDARIDAD_PENSIONAL);
	
	//SALUD
	Set<String> saludAdms = new HashSet<>(Arrays.asList(
		"RES014","RES013","RES012","RES011","RES010","RES009","RES008","RES007",
		"RES006","RES005","MIN003","MIN002","MIN001","ESSC91","ESSC76","ESSC62",
		"ESSC33","ESSC24","ESSC18","ESSC07","ESSC02","EPSIC6","EPSIC5","EPSIC4",
		"EPSIC3","EPSIC2","EPSIC1","EPSC34","EPSC33","EPSC25","EPSC22","EPSC20",
		"EPSC03","EPS045","EPS044","EPS042","EPS041","EPS040","EPS039","EPS038",
		"EPS037","EPS035","EPS034","EPS033","EPS031","EPS030","EPS028","EPS026",
		"EPS025","EPS024","EPS023","EPS020","EPS018","EPS017","EPS016","EPS015",
		"EPS014","EPS013","EPS012","EPS010","EPS009","EPS008","EPS006","EPS005",
		"EPS003","EPS002","EPS001","EAS027","EAS021","EAS016","CCFC55","CCFC53",
		"CCFC50","CCFC33","CCFC27","CCFC24","CCFC23","CCFC20","CCFC18","CCFC15",
		"CCFC10","CCFC09","CCFC02", "EPS046"));
	saludAdms.forEach(adm -> allAdministradorasMap.put(adm, SubsistemaPila.SALUD));
	
	//RIESGOS
	Set<String> riesgosAdms = new HashSet<>(Arrays.asList(
		"25-10","14-8",	"14-7",	"14-5",	"14-4",	"14-30","14-29","14-28","14-27",
		"14-26","14-25","14-23","14-19","14-18","14-17","14-1","14-11"));
	riesgosAdms.forEach(adm -> allAdministradorasMap.put(adm, SubsistemaPila.RIESGOS));
	
	//CAJAS
	Set<String> cajasAdms = new HashSet<>(Arrays.asList(
		"CCF69","CCF68","CCF67","CCF65","CCF64","CCF63","CCF62","CCF61","CCF59","CCF57",
			"CCF56","CCF51","CCF50","CCF48","CCF47","CCF46","CCF44","CCF43","CCF42","CCF41",
			"CCF40","CCF39","CCF38","CCF37","CCF36","CCF35","CCF34","CCF33","CCF32","CCF30",
			"CCF29","CCF28","CCF26","CCF24","CCF23","CCF22","CCF21","CCF20","CCF18","CCF16",
			"CCF15","CCF14","CCF13","CCF12","CCF11","CCF10","CCF09","CCF08","CCF07","CCF06",
			"CCF05","CCF04","CCF03","CCF02"));
	cajasAdms.forEach(adm -> allAdministradorasMap.put(adm, SubsistemaPila.PARAFISCALES_CAJAS));
	
	//SENA
	allAdministradorasMap.put("PASENA", SubsistemaPila.PARAFISCALES_SENA);
	
	//ICBF
	allAdministradorasMap.put("PAICBF", SubsistemaPila.PARAFISCALES_ICBF);
	
	//ESAP
	allAdministradorasMap.put("PAESAP", SubsistemaPila.PARAFISCALES_ESAP);
	
	//MINEDU
	allAdministradorasMap.put("PAMIED", SubsistemaPila.PARAFISCALES_MINEDU);
	}

	public Map<String, SubsistemaPila> getAllAdministradorasMap() {
		return allAdministradorasMap;
	}
	
}
